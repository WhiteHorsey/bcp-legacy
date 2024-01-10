import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import MyTable from "../../components/tables/MyTable";
import {
	getAllStudents,
	selectStudents,
} from "../../features/students/studentSlice";
import Button from "react-bootstrap/Button";
import { NavLink } from "react-router-dom";

const studentHead = [
	"#",
	"First Name",
	"Last Name",
	"Email",
	"Passport",
	"Cars",
	"Tags",
	"Actions",
];

function StudentListPage() {
	const dispatch = useDispatch();

	useEffect(() => {
		dispatch(getAllStudents());
	}, []);
	const students = useSelector(selectStudents);
	return (
		<div>
			<div className="header-list">
				<h2>Students List</h2>
				<div className="header-list-2">
					<NavLink to={`/students/AddStudent`}>
						<Button variant="secondary" style={{ marginRight: 20 }}>
							Add Student
						</Button>
					</NavLink>
					<NavLink to={`/addPassport`}>
						<Button variant="secondary" style={{ marginRight: 20 }}>
							Add Passport
						</Button>
					</NavLink>
					<NavLink to={`/addCar`}>
						<Button variant="secondary" style={{ marginRight: 20 }}>
							Add Car
						</Button>
					</NavLink>
					<NavLink to={`/addTag`}>
						<Button variant="secondary">Add Tag</Button>
					</NavLink>
				</div>
			</div>
			<MyTable head={studentHead}>
				{students?.map((item, index) => (
					<tr key={index}>
						<td>{item.id}</td>
						<td>{item.firstName}</td>
						<td>{item.lastName}</td>
						<td>{item.email}</td>
						<td>
							<NavLink to={`/students/${item.id}/passport`}>
								<Button variant="secondary">
									<i className="bi bi-pass-fill"></i>
								</Button>
							</NavLink>
						</td>
						<td>
							<NavLink to={`/students/${item.id}/cars`}>
								<Button variant="info">
									<i className="bi bi-car-front"></i>
								</Button>
							</NavLink>
						</td>
						<td>
							<NavLink to={`/students/${item.id}/tags`}>
								<Button variant="primary">
									<i className="bi bi-bookmark-heart"></i>
								</Button>
							</NavLink>
						</td>
						<td>
							<NavLink to={`/students/${item.id}`}>
								<Button variant="warning" style={{ marginRight: 20 }}>
									<i className="bi bi-pen"></i>
								</Button>
							</NavLink>
							<Button variant="danger">
								<i className="bi bi-x"></i>
							</Button>
						</td>
					</tr>
				))}
			</MyTable>
		</div>
	);
}

export default StudentListPage;
