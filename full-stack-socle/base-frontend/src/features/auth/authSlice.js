import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { errorSnackBar, successSnackBar } from "../global/globalSlice";
import AuthService from "../../services/auth.service.js";
import TokenService from "../../services/TokenService";

const modulePrefix = "authStore";

const user = TokenService.getUser();

const initialState = {
	user: user ? user : null,
	isLoggedIn: user ? true : false,
	isLoading: false,
	isSuccess: false,
	isError: false,
	message: "",
};

const getErrorMessage = (error) => {
	return (
		(error.response && error.response.data && error.response.data.message) ||
		error.message ||
		error.toString()
	);
};

export const register = createAsyncThunk(
	`${modulePrefix}/register`,
	async (request, { rejectWithValue }) => {
		try {
			return await AuthService.register(request);
		} catch (error) {
			return rejectWithValue(getErrorMessage(error));
		}
	}
);

export const login = createAsyncThunk(
	`${modulePrefix}/login`,
	async (request, { rejectWithValue }) => {
		try {
			const { data } = await AuthService.login(request);
			return await data;
		} catch (error) {
			return rejectWithValue(getErrorMessage(error));
		}
	}
);

export const logout = createAsyncThunk(
	`${modulePrefix}/logout`,
	async (userId, { rejectWithValue }) => {
		try {
			return await AuthService.logout(userId);
		} catch (error) {
			return rejectWithValue(getErrorMessage(error));
		}
	}
);

export const refreshToken = createAsyncThunk(
	`${modulePrefix}/refreshToken`,
	async (_, { rejectWithValue }) => {
		try {
			return await AuthService.refresh({
				refreshToken: TokenService.getLocalRefreshToken(),
			});
		} catch (error) {
			return rejectWithValue(getErrorMessage(error));
		}
	}
);

export const authSlice = createSlice({
	name: "authStore",
	initialState,
	reducers: {},
	extraReducers: (builder) => {
		builder
			// REGISTER
			.addCase(register.pending, (state) => {
				state.isLoading = true;
			})
			.addCase(register.fulfilled, (state) => {
				state.isLoading = false;
				state.isSuccess = true;
			})
			.addCase(register.rejected, (state, { payload }) => {
				state.isLoading = false;
				state.isError = true;
				state.message = payload;
			})
			// LOGIN
			.addCase(login.pending, (state) => {
				state.isLoading = true;
			})
			.addCase(login.fulfilled, (state, { payload }) => {
				state.isLoading = false;
				state.isSuccess = true;
				state.isLoggedIn = true;
				state.user = payload;
				TokenService.setUser(payload);
			})
			.addCase(login.rejected, (state, { payload }) => {
				state.isLoading = false;
				state.isError = true;
				state.message = payload;
			})
			// LOGOUT
			.addCase(logout.fulfilled, (state) => {
				state.isLoggedIn = false;
				state.isLoading = false;
				state.isError = false;
				state.message = "";
				state.isSuccess = false;
				state.user = null;
				TokenService.removeUser();
			})
			// REFRESH
			.addCase(refreshToken.fulfilled, (state, { payload }) => {
				const { accessToken, refreshToken } = payload;
				TokenService.updateLocalAccessAndRefreshToken(
					accessToken,
					refreshToken
				);
				state.isSuccess = true;
				state.user.accessToken = accessToken;
				state.user.refreshToken = refreshToken;
			});
	},
});

export const {} = authSlice.actions;

export const selectIsLoggedIn = (state) => state.authStore.isLoggedIn;
export const selectUser = (state) => state.authStore.user;

export default authSlice.reducer;
