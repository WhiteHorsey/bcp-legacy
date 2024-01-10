import axios from "../hooks/privateAxios";
import authHeader from "../hooks/auth-header.js";
const ROUTE_BASE_CAR_1 = "/api/students/";
const ROUTE_BASE_CAR_2 = "/api/tags/";

const GET_ALL_TAGS_OF_STUDENT = (studentId) => {
	return ROUTE_BASE_CAR_1 + studentId + "/tags/";
};
const CREATE_TAG = (studentId) => {
	return ROUTE_BASE_CAR_1 + studentId + "/tags/";
};
const UPDATE_TAG = (tagId) => {
	return ROUTE_BASE_CAR_2 + "/" + tagId + "/";
};
const DELETE_TAG = (tagId) => {
	return ROUTE_BASE_CAR_2 + "/" + tagId + "/";
};

class TagService {
	getAllTags(studentId) {
		return axios.get(GET_ALL_TAGS_OF_STUDENT(studentId));
	}
	createTag(request) {
		return axios.post(CREATE_TAG(request.id), request);
	}
	updateTag(request) {
		return axios.patch(UPDATE_TAG(request.id), request);
	}
	deleteTag(tagId) {
		return axios.delete(DELETE_TAG(tagId));
	}
}
export default new TagService();
