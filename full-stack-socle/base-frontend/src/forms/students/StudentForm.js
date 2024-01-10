import { Form as FormikForm, Formik } from "formik";
import React, { useEffect, useState } from "react";
import FormTextField from "../../components/global/form-field.js";
import { Col, Row, Button } from "react-bootstrap";
import * as yup from "yup";
import { useDispatch, useSelector } from "react-redux";
import {
	createStudent,
	selectStudentToEdit,
	updateStudent,
} from "../../features/students/studentSlice.js";

const studentFormSchema = yup.object({
	firstName: yup.string().min(5).max(15).required(),
	lastName: yup.string().min(5).max(15).required(),
	email: yup
		.string()
		.email("Field should contain a valid e-mail")
		.max(255)
		.required("E-mail is required"),
});
const studentFormInitialValues = {
	firstName: "",
	lastName: "",
	email: "",
};

function StudentForm() {
	const dispatch = useDispatch();
	const studentToEdit = useSelector(selectStudentToEdit);
	const [formValues, setFormValues] = useState(studentFormInitialValues);
	useEffect(() => {
		if (studentToEdit !== null) {
			setFormValues(studentToEdit);
		}
	}, [studentToEdit]);

	return (
		<Formik
			enableReinitialize
			validationSchema={studentFormSchema}
			initialValues={formValues}
			onSubmit={(values, { resetForm, setSubmitting }) => {
				setSubmitting(true);
				studentToEdit === null
					? dispatch(createStudent(values))
					: dispatch(updateStudent(values));
				setSubmitting(false);
				resetForm();
			}}
		>
			{({
				handleSubmit,
				handleChange,
				values,
				errors,
				isValid,
				isSubmitting,
			}) => (
				<FormikForm onSubmit={handleSubmit}>
					<Row>
						<FormTextField
							as={Col}
							sm="4"
							controlId="validationFormik01"
							label="First name"
							type="text"
							name="firstName"
						/>
						<FormTextField
							as={Col}
							sm="4"
							controlId="validationFormik02"
							label="Last name"
							type="text"
							name="lastName"
						/>
						<FormTextField
							as={Col}
							sm="4"
							controlId="validationFormik03"
							label="Email"
							type="text"
							name="email"
						/>
					</Row>

					<Button
						style={{ marginTop: 30 }}
						disabled={!isValid || isSubmitting}
						variant="success"
						as="input"
						type="submit"
						value={studentToEdit !== null ? "Update Passport" : "Add Passport"}
					/>
					<pre style={{ margin: "0 auto", marginTop: 30 }}>
						{JSON.stringify({ ...values }, null, 2)}
					</pre>
				</FormikForm>
			)}
		</Formik>
	);
}

export default StudentForm;
