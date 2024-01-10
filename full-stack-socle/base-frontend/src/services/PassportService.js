import axios from "../hooks/privateAxios";
import authHeader from "../hooks/auth-header.js";
const ROUTE_BASE_PASSPORT = "/api/passports/";

const GET_ALL_PASSPORTS = () => {
	return ROUTE_BASE_PASSPORT;
};
const GET_PASSPORT = (studentId) => {
	return "/api/students/" + studentId + "/passport/";
};
const CREATE_PASSPORT = (studentId) => {
	return "/api/students/" + studentId + "/passports/";
};
const UPDATE_PASSPORT = (passportId) => {
	return ROUTE_BASE_PASSPORT + "/" + passportId + "/";
};
const DELETE_PASSPORT = (passportId) => {
	return ROUTE_BASE_PASSPORT + "/" + passportId + "/";
};

class PassportService {
	getAllPassports() {
		return axios.get(GET_ALL_PASSPORTS());
	}
	getPassport(studentId) {
		return axios.get(GET_PASSPORT(studentId));
	}
	createPassport(request) {
		return axios.post(CREATE_PASSPORT(request.id), request);
	}
	updatePassport(request) {
		return axios.patch(UPDATE_PASSPORT(request.id), request);
	}
	deletePassport(passportId) {
		return axios.delete(DELETE_PASSPORT(passportId));
	}
}
export default new PassportService();
