import axios from "../hooks/privateAxios";

const ROUTE_BASE_CAR_1 = "/api/students/";
const ROUTE_BASE_CAR_2 = "/api/cars/";

// TOBE UPDATED
const GET_ALL_CARS = (studentId) => {
	return ROUTE_BASE_CAR_1 + studentId + "/cars/";
};

const GET_ALL_STUDENT_CARS = (studentId) => {
	return ROUTE_BASE_CAR_1 + studentId + "/cars/";
};
const CREATE_CAR = (studentId) => {
	return ROUTE_BASE_CAR_1 + studentId + "/cars/";
};
const UPDATE_CAR = (carId) => {
	return ROUTE_BASE_CAR_2 + "/" + carId + "/";
};
const DELETE_CAR = (carId) => {
	return ROUTE_BASE_CAR_2 + "/" + carId + "/";
};

class CarService {
	getAllCars() {
		return axios.get(GET_ALL_CARS());
	}
	getAllStudentCars(studentId) {
		return axios.get(GET_ALL_STUDENT_CARS(studentId));
	}
	createCar(request) {
		return axios.post(CREATE_CAR(request.id), request);
	}
	updateCar(request) {
		return axios.patch(UPDATE_CAR(request.id), request);
	}
	deleteCar(carId) {
		return axios.delete(DELETE_CAR(carId));
	}
}
export default new CarService();
