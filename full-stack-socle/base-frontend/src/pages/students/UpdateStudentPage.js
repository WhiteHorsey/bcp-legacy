import React, { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { useParams } from "react-router-dom";
import UpdateStudentForm from "../../forms/students/UpdateStudentForm";
import { errorSnackBar } from "../../features/global/globalSlice";
import StudentService from "../../services/StudentService";

function UpdateStudentPage() {
	const { studentId } = useParams();
	const dispatch = useDispatch();
	const [student, setStudent] = useState({
		firstName: "",
		lastName: "",
		email: "",
	});
	useEffect(() => {
		async function getStudent() {
			try {
				const { status, data } = await StudentService.getStudent(studentId);
				if (status !== 200) {
					dispatch(errorSnackBar("error.message"));
				}
				setStudent(data);
			} catch (error) {
				dispatch(errorSnackBar(error.message));
			}
		}
		getStudent();
	}, [studentId]);
	return (
		<div>
			<div className="header-list">
				<h2>Students Form</h2>
			</div>
			<UpdateStudentForm student={student} setStudent={setStudent} />
		</div>
	);
}

export default UpdateStudentPage;
