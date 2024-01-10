import axios from "../hooks/privateAxios";

const ROUTE_BASE_STUDENT = "/api/students/";

const GET_STUDENT = (studentId) => {
	return ROUTE_BASE_STUDENT + studentId;
};
const UPDATE_STUDENT = (studentId) => {
	return ROUTE_BASE_STUDENT + studentId;
};
const DELETE_STUDENT = (studentId) => {
	return ROUTE_BASE_STUDENT + "/" + studentId;
};

class StudentService {
	getAllStudents() {
		return axios.get(ROUTE_BASE_STUDENT);
	}
	getStudent(studentId) {
		return axios.get(GET_STUDENT(studentId));
	}
	createStudent(request) {
		return axios.post(ROUTE_BASE_STUDENT, request);
	}
	updateStudent(request) {
		return axios.patch(UPDATE_STUDENT(request.id), {
			firstName: request.firstName,
			lastName: request.lastName,
			email: request.email,
		});
	}
	deleteStudent(studentId) {
		return axios.delete(DELETE_STUDENT(studentId));
	}
}
export default new StudentService();
