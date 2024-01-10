import TokenService from "../services/TokenService";

export default function authHeader() {
	const user = TokenService.getUser();
	if (user && user.accessToken) {
		return { Authorization: "Bearer " + user.accessToken };
	}
	return {};
}
