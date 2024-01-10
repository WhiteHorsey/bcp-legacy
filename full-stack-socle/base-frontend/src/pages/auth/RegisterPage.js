import React, { useEffect } from "react";
import { Button, Col, Row } from "react-bootstrap";
import FormTextField from "../../components/global/form-field";
import "./AuthCss.scss";
import { Form as FormikForm, Formik } from "formik";
import * as yup from "yup";
import { NavLink, useNavigate } from "react-router-dom";
import { register } from "../../features/auth/authSlice";
import { useDispatch, useSelector } from "react-redux";

import { toast } from "react-toastify";

const loginFormSchema = yup.object({
	username: yup.string().min(5).max(15).required(),
	password: yup.string().min(5).max(15).required(),
	email: yup
		.string()
		.email("Field should contain a valid e-mail")
		.max(255)
		.required("E-mail is required"),
});
const loginInitialValues = {
	username: "",
	password: "",
	email: "",
};

function RegisterPage() {
	let navigate = useNavigate();
	const dispatch = useDispatch();

	const { user, isLoggedIn, isError, isSuccess, isLoading, message } =
		useSelector((state) => state.authStore);

	useEffect(() => {
		if (isError) {
			toast.error(message);
		}
		if (isSuccess) {
			navigate("/login");
			toast.success("Bonjour");
		}
	}, [user, isLoggedIn, isError, isSuccess, isLoading, navigate, dispatch]);

	return (
		<div className="Auth-form-container">
			<Formik
				validationSchema={loginFormSchema}
				initialValues={loginInitialValues}
				onSubmit={(values, { resetForm }) => {
					dispatch(register(values));
					resetForm();
				}}
			>
				{({ handleSubmit, values, isValid, isSubmitting }) => (
					<FormikForm onSubmit={handleSubmit} className="Auth-form">
						<div className="Auth-form-content">
							<h3 className="Auth-form-title">Sign In</h3>
							<div className="text-center">
								Already registered?{" "}
								<NavLink className="custom-nav-link" to="/login">
									<span className="link-primary">Sign In</span>
								</NavLink>
							</div>
							<div className="form-group mt-3">
								<FormTextField
									controlId="validationFormik01"
									label="Username"
									type="text"
									name="username"
									placeholder="Enter Username"
								/>
							</div>
							<div className="form-group mt-3">
								<FormTextField
									controlId="validationFormik03"
									label="Email"
									type="email"
									name="email"
									placeholder="Enter Email"
								/>
							</div>
							<div className="form-group mt-3">
								<FormTextField
									controlId="validationFormik02"
									label="Password"
									type="password"
									name="password"
									placeholder="Enter password"
								/>
							</div>
							<div className="d-grid gap-2 mt-3">
								<Button
									disabled={!isValid || isSubmitting}
									variant="primary"
									as="input"
									type="submit"
									value="Register"
								/>
							</div>
							<p className="forgot-password text-right mt-2">
								Forgot <a href="#">password?</a>
							</p>
						</div>
					</FormikForm>
				)}
			</Formik>
		</div>
	);
}

export default RegisterPage;
