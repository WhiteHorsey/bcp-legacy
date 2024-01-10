import axios from "../hooks/publicAxios";

const ROUTE_BASE = "/api/auth/";

const SIGNIN_ROUTE = ROUTE_BASE + "signIn";

const SIGNUP_ROUTE = ROUTE_BASE + "signUp";

const LOGOUT_ROUTE = ROUTE_BASE + "logout/";

const REFRESH_ROUTE = ROUTE_BASE + "refreshToken";

const register = (request) => {
	return axios.post(SIGNUP_ROUTE, request);
};

const login = async (request) => {
	return axios.post(SIGNIN_ROUTE, request);
};

const logout = async (userId) => {
	return axios.post(LOGOUT_ROUTE + userId);
};

const refresh = async (request) => {
	return axios.post(REFRESH_ROUTE, request);
};

export default {
	register,
	login,
	logout,
	refresh,
};
