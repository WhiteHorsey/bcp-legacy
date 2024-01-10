import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import StudentService from "../../services/StudentService.js";

const getErrorMessage = (error) => {
	return (
		(error.response && error.response.data && error.response.data.message) ||
		error.message ||
		error.toString()
	);
};

const initialState = {
	students: [],
	studentToEdit: null,
	searchedStudent: "",
};

export const getAllStudents = createAsyncThunk(
	"studentStore/getAllStudents",
	async (_, { rejectWithValue }) => {
		try {
			return await StudentService.getAllStudents();
		} catch (error) {
			return rejectWithValue(getErrorMessage(error));
		}
	}
);
export const getStudent = createAsyncThunk(
	"studentStore/getStudent",
	async (studentId, { rejectWithValue }) => {
		try {
			return await StudentService.getStudent(studentId);
		} catch (error) {
			return rejectWithValue(getErrorMessage(error));
		}
	}
);
export const createStudent = createAsyncThunk(
	"studentStore/createStudent",
	async (request, { rejectWithValue }) => {
		try {
			return await StudentService.createStudent(request);
		} catch (error) {
			return rejectWithValue(getErrorMessage(error));
		}
	}
);
export const updateStudent = createAsyncThunk(
	"studentStore/updateStudent",
	async (request, { rejectWithValue }) => {
		try {
			await StudentService.updateStudent(request);
			return request;
		} catch (error) {
			return rejectWithValue(getErrorMessage(error));
		}
	}
);

export const studentSlice = createSlice({
	name: "studentStore",
	initialState,
	reducers: {},
	extraReducers: (builder) => {
		builder
			.addCase(getAllStudents.fulfilled, (state, { payload }) => {
				state.students = payload;
			})
			.addCase(getAllStudents.rejected, (state, { payload }) => {
				state.students = payload;
			})
			.addCase(createStudent.fulfilled, (state, { payload }) => {
				// ADD STUDENT
				state.students.push(payload);
			})
			.addCase(getStudent.fulfilled, (state, { payload }) => {
				state.studentToEdit = payload;
			})
			.addCase(updateStudent.fulfilled, (state, { payload }) => {
				state.studentToEdit = null;
			});
	},
});

export const { increment, decrement, incrementByAmount } = studentSlice.actions;

export const selectStudents = (state) => state.studentStore.students;
export const selectStudentToEdit = (state) => state.studentStore.studentToEdit;

export default studentSlice.reducer;
