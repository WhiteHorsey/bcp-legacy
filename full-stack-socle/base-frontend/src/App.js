import React, { useCallback, useEffect } from "react";
import { Navigate, Outlet, Route, Routes } from "react-router-dom";
import "./App.scss";
import CarListPage from "./pages/cars/CarListPage";
import PassportListPage from "./pages/passports/PassportListPage";
import StudentListPage from "./pages/students/StudentListPage";
import TagListPage from "./pages/tags/TagListPage";
import { theme } from "./utils/Utils.js";
import NavBar from "./components/header/NavBar";
import { ThemeProvider } from "@mui/material";
import Container from "react-bootstrap/Container";
import StudentPassportPage from "./pages/students/StudentPassportPage";
import AddStudentPage from "./pages/students/AddStudentPage";
import UpdateStudentPage from "./pages/students/UpdateStudentPage";
import LoginPage from "./pages/auth/LoginPage";
import RegisterPage from "./pages/auth/RegisterPage";
import { useDispatch, useSelector } from "react-redux";
import { logout, selectUser } from "./features/auth/authSlice";
import EventBus from "./common/EventBus.js";
import { ToastContainer } from "react-toastify";
import PublicLayout from "./pages/global/PublicLayout";
import RequireAuth from "./features/auth/RequireAuth";
import Unauthorized from "./pages/global/Unauthorized";
import Missing from "./pages/global/Missing";

const ROLES = {
	USER: "ROLE_USER",
	MODERATOR: "ROLE_MODERATOR",
	ADMIN: "ROLE_ADMIN",
};

function App() {
	const dispatch = useDispatch();
	const user = useSelector(selectUser);
	const logOut = useCallback(() => {
		dispatch(logout());
	}, [dispatch]);

	useEffect(() => {
		EventBus.on("logout", () => {
			logOut();
		});

		return () => {
			EventBus.remove("logout");
		};
	}, [logOut]);

	return (
		<ThemeProvider theme={theme}>
			<Routes>
				<Route path="/" element={<PublicLayout />}>
					{/* public routes */}
					<Route path="login" element={<LoginPage />} />
					<Route path="register" element={<RegisterPage />} />
					<Route path="logout" element={<Navigate to="/login" />} />
					<Route path="unauthorized" element={<Unauthorized />} />

					{/* protected routes */}
					<Route
						element={
							<RequireAuth
								allowedRoles={[ROLES.USER, ROLES.MODERATOR, ROLES.ADMIN]}
							/>
						}
					>
						<Route path="passports" element={<PassportListPage />} />
						<Route path="students" element={<StudentListPage />} />
						<Route path="students/:studentId/cars" element={<CarListPage />} />
						<Route path="students/AddStudent" element={<AddStudentPage />} />
						<Route path="students/:studentId" element={<UpdateStudentPage />} />
						<Route path="students/:studentId/tags" element={<TagListPage />} />
						<Route
							path="students/:studentId/passport"
							element={<StudentPassportPage />}
						/>
					</Route>

					{/* catch all */}
					<Route path="*" element={<Missing />} />
				</Route>
			</Routes>
		</ThemeProvider>
	);
}

export default App;
