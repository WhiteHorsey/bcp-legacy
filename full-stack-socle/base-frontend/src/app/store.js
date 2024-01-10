import { configureStore } from "@reduxjs/toolkit";
import passportReducer from "../features/passports/passportSlice.js";
import globalReducer from "../features/global/globalSlice.js";
import studentReducer from "../features/students/studentSlice.js";
import carReducer from "../features/cars/carSlice.js";
import tagReducer from "../features/tags/tagSlice";
import authReducer from "../features/auth/authSlice.js";

export const store = configureStore({
	reducer: {
		authStore: authReducer,
		passportStore: passportReducer,
		studentStore: studentReducer,
		carStore: carReducer,
		tagStore: tagReducer,
		globalStore: globalReducer,
	},
	middleware: (getDefaultMiddleware) => getDefaultMiddleware(),
});
