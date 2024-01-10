import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import { getAllTags, selectTags } from "../../features/tags/tagSlice";

function TagListPage() {
	const { studentId } = useParams();
	const dispatch = useDispatch();
	useEffect(() => {
		dispatch(getAllTags(parseInt(studentId)));
	}, [dispatch, studentId]);
	const tags = useSelector(selectTags);
	return <pre>{JSON.stringify(tags, null, 2)}</pre>;
}

export default TagListPage;
