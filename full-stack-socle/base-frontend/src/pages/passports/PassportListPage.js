import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import MyTable from "../../components/tables/MyTable";
import {
	getAllPassports,
	selectPassports,
} from "../../features/passports/passportSlice";
import Button from "react-bootstrap/Button";

const passportHead = ["#", "Passport Number", "Student", "Actions"];

function PassportListPage() {
	const dispatch = useDispatch();
	useEffect(() => {
		dispatch(getAllPassports());
	}, []);

	const passports = useSelector(selectPassports);

	return (
		<div>
			<div className="header-list">
				<h2>Passport List</h2>
			</div>
			<MyTable head={passportHead}>
				{passports.map((item, index) => (
					<tr key={index}>
						<td>{item.id}</td>
						<td>{item.number}</td>
						<td>
							<Button variant="primary">
								<i class="bi bi-eye"></i>
							</Button>
						</td>
						<td>
							<Button variant="warning" style={{ marginRight: 20 }}>
								<i class="bi bi-pen"></i>
							</Button>
							<Button variant="danger">
								<i class="bi bi-x"></i>
							</Button>
						</td>
					</tr>
				))}
			</MyTable>
		</div>
	);
}

export default PassportListPage;
