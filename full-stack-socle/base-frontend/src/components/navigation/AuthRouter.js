import React from "react";
import { useSelector } from "react-redux";
import { Navigate, Route } from "react-router-dom";
import { selectUser } from "../../features/auth/authSlice";

function AuthRouter({ path, page }) {
  const user = useSelector(selectUser);
	return <Route path={path} element={user ? page : <Navigate to="/login" />} />;
}

export default AuthRouter;
