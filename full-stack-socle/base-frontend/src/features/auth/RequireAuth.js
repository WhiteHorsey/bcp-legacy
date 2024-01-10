import React from "react";
import { Container } from "react-bootstrap";
import { Navigate, Outlet, useLocation } from "react-router-dom";
import NavBar from "../../components/header/NavBar";
import TokenService from "../../services/TokenService";
import { ToastContainer } from "react-toastify";

function RequireAuth({ allowedRoles }) {
	const user = TokenService.getUser();
	const location = useLocation();

	const Main = () => {
		return (
			<div>
				<NavBar />
				<Container style={{ marginTop: 20 }}>
					<Outlet />
				</Container>
				<ToastContainer
					position="top-right"
					autoClose={1000}
					hideProgressBar={false}
					newestOnTop={false}
					closeOnClick
					rtl={false}
					pauseOnFocusLoss
					draggable={false}
					pauseOnHover={false}
					theme="dark"
				/>
			</div>
		);
	};

	return user?.roles?.find((role) => allowedRoles?.includes(role)) ? (
		<Main />
	) : user ? (
		<Navigate to="/unauthorized" state={{ from: location }} replace />
	) : (
		<Navigate to="/login" state={{ from: location }} replace />
	);
}

export default RequireAuth;
