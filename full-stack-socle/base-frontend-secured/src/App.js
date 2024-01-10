import React from "react";
import "./App.scss";
import { Navigate, Outlet, Route, Routes } from "react-router-dom";
import PublicLayout from "./pages/global/PublicLayout";
import LoginPage from "./pages/auth/LoginPage";
import RegisterPage from "./pages/auth/RegisterPage";

function App() {
	return (
		<Routes>
			<Route path="/" element={<PublicLayout />}>
				{/* public routes */}
				<Route path="login" element={<LoginPage />} />
				<Route path="register" element={<RegisterPage />} />
			</Route>
		</Routes>
	);
}

export default App;
