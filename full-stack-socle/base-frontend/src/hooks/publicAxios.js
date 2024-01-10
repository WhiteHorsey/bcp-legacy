import axios from "axios";
// import TokenService from "../services/TokenService.js";

const baseURL = "http://localhost:8080";

export default axios.create({
	baseURL: baseURL,
	headers: {
		"Content-Type": "application/json",
	},
});
