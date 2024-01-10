import { createSlice } from "@reduxjs/toolkit";

const initialState = {
	popup: false,
	popupTitle: "",
	action: "",
	notifications: [],
};

const options = (variant) => {
	return { key: new Date().getTime() + Math.random(), variant: variant };
};

export const globalSlice = createSlice({
	name: "globalStore",
	initialState,
	reducers: {
		openPopup: (state, { payload }) => {
			state.popup = true;
			state.popupTitle = payload.text;
			state.action = payload.action;
		},
		closePopup: (state) => {
			state.popup = false;
			state.popupTitle = "";
			state.action = "";
		},
		successSnackBar: (state, { payload }) => {
			let newNotification = {
				message: payload,
				options: options("success"),
			};
			state.notifications = [...state.notifications, newNotification];
		},
		errorSnackBar: (state, { payload }) => {
			state.notifications = [
				...state.notifications,
				{
					message: payload,
					options: options("error"),
				},
			];
		},
		removeSnackbar: (state, { payload }) => {
			state.notifications = state.notifications.filter(
				(notification) => notification.options.key !== payload
			);
		},
	},
});

export const {
	openPopup,
	closePopup,
	successSnackBar,
	errorSnackBar,
	removeSnackbar,
} = globalSlice.actions;

export const selectPopup = (state) => state.globalStore.popup;
export const selectPopupTitle = (state) => state.globalStore.popupTitle;
export const selectAction = (state) => state.globalStore.action;
export const selectNotifications = (state) => state.globalStore.notifications;

export default globalSlice.reducer;
