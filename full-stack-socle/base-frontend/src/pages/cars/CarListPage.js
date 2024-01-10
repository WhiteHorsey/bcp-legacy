import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useHistory, useParams } from "react-router-dom";
import { getAllCars, selectCars } from "../../features/cars/carSlice";

function CarListPage() {
	const { studentId } = useParams();
	const dispatch = useDispatch();
	useEffect(() => {
		dispatch(getAllCars(parseInt(studentId)));
	}, [dispatch, studentId]);
	const cars = useSelector(selectCars);
	return <pre>{JSON.stringify(cars, null, 2)}</pre>;
}

export default CarListPage;
