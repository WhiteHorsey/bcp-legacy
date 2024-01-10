import jwt_decode from "jwt-decode";

class TokenService {
	getUser() {
		return JSON.parse(localStorage.getItem("user"));
	}

	getLocalRefreshToken() {
		return getUser()?.refreshToken;
	}

	getLocalAccessToken() {
		return getUser()?.accessToken;
	}

	updateLocalAccessAndRefreshToken(newAccessToken, newRefreshToken) {
		let user = getUser();
		user.accessToken = newAccessToken;
		user.refreshToken = newRefreshToken;
		localStorage.setItem("user", JSON.stringify(user, null, 2));
	}

	setUser(user) {
		localStorage.setItem("user", JSON.stringify(user));
	}

	removeUser() {
		localStorage.removeItem("user");
	}

	isRefreshTokenExpired() {
		return jwt_decode(getUser()?.refreshToken).exp < Date.now() / 1000;
	}
}

export default new TokenService();
