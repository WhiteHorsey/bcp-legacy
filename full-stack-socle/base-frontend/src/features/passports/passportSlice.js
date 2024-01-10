import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import PassportService from "../../services/PassportService.js";

const initialState = {
	passports: [],
	passportToEdit: null,
	searchedPassport: "",
};

export const getAllPassports = createAsyncThunk(
	"passportStore/getAllPassports",
	async (_, { dispatch, rejectWithValue, fulfillWithValue }) => {
		try {
			const { status, data } = await PassportService.getAllPassports();
			if (status !== 200) {
				return rejectWithValue(data.message);
			}
			return fulfillWithValue(data);
		} catch (error) {
			return rejectWithValue(error.message);
		}
	}
);

export const getPassport = createAsyncThunk(
	"passportStore/getPassport",
	async (studentId, { dispatch, rejectWithValue, fulfillWithValue }) => {
		try {
			const { status, data } = await PassportService.getPassport(studentId);
			if (status !== 200) {
				return rejectWithValue(data.message);
			}
			return fulfillWithValue(data);
		} catch (error) {
			return rejectWithValue(error.message);
		}
	}
);

export const passportSlice = createSlice({
	name: "passportStore",
	initialState,
	reducers: {},
	extraReducers: (builder) => {
		builder
			.addCase(getAllPassports.fulfilled, (state, { payload }) => {
				state.passports = payload;
			})
			.addCase(getAllPassports.rejected, (state, { payload }) => {
				state.passports = payload;
			})
			.addCase(getPassport.fulfilled, (state, { payload }) => {
				state.passportToEdit = payload;
			});
	},
});

export const { increment, decrement, incrementByAmount } =
	passportSlice.actions;

export const selectPassports = (state) => state.passportStore.passports;
export const selectPassportToEdit = (state) =>
	state.passportStore.passportToEdit;

export default passportSlice.reducer;
