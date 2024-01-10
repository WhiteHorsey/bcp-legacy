import { createSlice } from "@reduxjs/toolkit";

const initialState = {
	notification: {
		isOpen: false,
		message: "",
		type: "",
	},
	error: "",
	success: "",
};

export const NotificationSlice = createSlice({
	name: "notificationsStore",
	initialState,
	reducers: {
		setSuccessNotification: (state, { payload }) => {
			state.notification = {
				isOpen: true,
				message: payload,
				type: "success",
			};
		},
		setErrorNotification: (state, { payload }) => {
			state.notification = {
				isOpen: true,
				message: payload,
				type: "error",
			};
		},
		closeNotification: (state, { payload }) => {
			state.notification = {
				isOpen: false,
				message: payload || "",
				type: "success",
			};
		},
		setError: (state, { payload }) => {
			state.error = payload;
		},
		setSuccess: (state, { payload }) => {
			state.success = payload;
		},
	},
});

export const {
	setSuccessNotification,
	setErrorNotification,
	closeNotification,
	setError,
	setSuccess,
} = NotificationSlice.actions;

export const selectNotification = (state) => state.notificationsStore.notification;
export const selectError = (state) => state.notificationsStore.error;
export const selectSuccess = (state) => state.notificationsStore.success;

export default NotificationSlice.reducer;
