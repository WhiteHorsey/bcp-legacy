import axios from "axios";
import TokenService from "../services/TokenService.js";

const baseURL = "http://localhost:8080";

const instance = axios.create({
	baseURL: baseURL,
	headers: {
		"Content-Type": "application/json",
	},
});

// Where you would set stuff like your 'Authorization' header, etc ...
instance.interceptors.request.use(
	(config) => {
		const accessToken = TokenService.getLocalAccessToken();
		console.log(accessToken);
		if (accessToken) {
			config.headers["Authorization"] = "Bearer " + accessToken;
		}
		return config;
	},
	(error) => {
		return Promise.reject(error);
	}
);

instance.interceptors.response.use(
	(res) => {
		return res;
	},
	async (err) => {
		const originalConfig = err.config;

		if (originalConfig.url !== "/api/auth/signIn" && err.response) {
			// Access Token was expired
			if (err.response.status === 401 && !originalConfig._retry) {
				originalConfig._retry = true;

				try {
					const {
						data: { accessToken, refreshToken },
					} = await instance.post("/api/auth/refreshToken", {
						refreshToken: TokenService.getLocalRefreshToken(),
					});

					TokenService.updateLocalAccessAndRefreshToken(
						accessToken,
						refreshToken
					);

					return instance(originalConfig);
				} catch (_error) {
					return Promise.reject(_error);
				}
			}
		}

		return Promise.reject(err);
	}
);

export default instance;
