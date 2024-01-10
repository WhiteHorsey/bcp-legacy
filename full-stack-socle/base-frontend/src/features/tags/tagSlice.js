import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import TagService from "../../services/TagService";

const initialState = {
	tags: [],
	carToEdit: null,
	carPassport: "",
};

export const getAllTags = createAsyncThunk(
	"tagStore/getAllTags",
	async (studentId, { dispatch, rejectWithValue, fulfillWithValue }) => {
		try {
			const { status, data } = await TagService.getAllTags(studentId);
			if (status !== 200) {
				return rejectWithValue(data.message);
			}
			return fulfillWithValue(data);
		} catch (error) {
			return rejectWithValue(error.message);
		}
	}
);


export const tagSlice = createSlice({
	name: "tagStore",
	initialState,
	reducers: {},
	extraReducers: (builder) => {
		builder
			.addCase(getAllTags.fulfilled, (state, { payload }) => {
				state.tags = payload;
			})
			.addCase(getAllTags.rejected, (state, { payload }) => {
				state.tags = payload;
			});
	},
});

export const { increment, decrement, incrementByAmount } =
	tagSlice.actions;

export const selectTags = (state) => state.tagStore.tags;

export default tagSlice.reducer;
