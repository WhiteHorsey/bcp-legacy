import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import CarService from "../../services/CarService.js";

const initialState = {
	cars: [],
	carToEdit: null,
	carPassport: "",
};

export const getAllCars = createAsyncThunk(
	"carStore/getAllCars",
	async (studentId, { dispatch, rejectWithValue, fulfillWithValue }) => {
		try {
			const { status, data } = await CarService.getAllStudentCars(studentId);
			if (status !== 200) {
				return rejectWithValue(data.message);
			}
			return fulfillWithValue(data);
		} catch (error) {
			return rejectWithValue(error.message);
		}
	}
);

export const carslice = createSlice({
	name: "carStore",
	initialState,
	reducers: {},
	extraReducers: (builder) => {
		builder
			.addCase(getAllCars.fulfilled, (state, { payload }) => {
				state.cars = payload;
			})
			.addCase(getAllCars.rejected, (state, { payload }) => {
				state.cars = payload;
			});
	},
});

export const { increment, decrement, incrementByAmount } = carslice.actions;

export const selectCars = (state) => state.carStore.cars;

export default carslice.reducer;
