import React from "react";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import { useDispatch, useSelector } from "react-redux";
import { NavLink } from "react-router-dom";
import { logout, selectIsLoggedIn } from "../../features/auth/authSlice";

function NavBar() {
	const isLoggedIn = useSelector(selectIsLoggedIn);
	const dispatch = useDispatch();
	const logOut = () => {
		dispatch(logout());
	};
	return (
		<Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
			<Container>
				<Navbar.Brand style={{ paddingTop: 0 }}>
					<NavLink className="custom-nav-link" to="/">
						BaseSocle
					</NavLink>
				</Navbar.Brand>
				<Navbar.Toggle aria-controls="responsive-navbar-nav" />
				<Navbar.Collapse id="responsive-navbar-nav">
					<Nav className="me-auto">
						<NavLink className="custom-nav-link" to="/students">
							Students
						</NavLink>

						<NavLink className="custom-nav-link" to="/passports">
							Passports
						</NavLink>

						<NavLink className="custom-nav-link" to="/cars">
							Cars
						</NavLink>

						<NavLink className="custom-nav-link" to="/tags">
							Tags
						</NavLink>
					</Nav>

					<Nav>
						{isLoggedIn ? (
							<NavLink
								className="custom-nav-link"
								to="/logout"
								onClick={logOut}
							>
								Logout
							</NavLink>
						) : (
							<>
								<NavLink className="custom-nav-link" to="/login">
									Login
								</NavLink>

								<NavLink className="custom-nav-link" to="/register">
									Register
								</NavLink>
							</>
						)}
					</Nav>
				</Navbar.Collapse>
			</Container>
		</Navbar>
	);
}

export default NavBar;
