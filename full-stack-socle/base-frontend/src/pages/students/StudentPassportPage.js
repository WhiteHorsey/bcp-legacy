import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import { getPassport, selectPassportToEdit } from "../../features/passports/passportSlice";

function StudentPassportPage() {
	const { studentId } = useParams();
	const dispatch = useDispatch();
	useEffect(() => {
		dispatch(getPassport(parseInt(studentId)));
	}, [dispatch, studentId]);
	const passportToEdit = useSelector(selectPassportToEdit);

	return <pre>{JSON.stringify(passportToEdit, null, 2)}</pre>;
}

export default StudentPassportPage;
