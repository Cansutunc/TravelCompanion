import React, { useState, useEffect } from "react";
import Login from "./components/pages/Login";
import Register from "./components/pages/Register";
import Home from "./components/pages/Home";
import Profile from "./components/pages/Profile";
import LogoutComponent from "./components/components/LogoutComponent";
import {
	createBrowserRouter,
	Outlet,
	RouterProvider,
	Navigate,
} from "react-router-dom";
import Protected from "./components/components/Protected";
import Routes from "./components/pages/Routes";
import jwt_decode from "jwt-decode";
import axios from "axios";
import global from "./resources/global.json";

function App() {
	const [isSignedIn, setIsSignedIn] = useState(true);

	useEffect(() => {
		const token = localStorage.getItem("token");
		if (token && token !== "null") {
			setIsSignedIn(true);
		} else {
			setIsSignedIn(false);
		}
	}, []);

	const router = createBrowserRouter([
		{
			path: "/",
			element: <Navigate to="/dashboard" replace />,
		},
		{
			path: "/dashboard",
			element: (
				<Protected isSignedIn={isSignedIn}>
					<Home/>
				</Protected>
			),
		},
		{
			path: "/login",
			element: <Login isSignedIn={isSignedIn} />,
		},
		{
			path: "/register",
			element: <Register />,
		},
		{
			path: "/profile",
			element: (
				<Protected isSignedIn={isSignedIn}>
					<Profile />
				</Protected>
			),
		},
		{
			path: "/routes",
			element: (
				<Protected isSignedIn={isSignedIn}>
					<Routes />
				</Protected>
			),
		},
		{
			path: "/logout",
			element: <LogoutComponent />,
		},
	]);

	return (
		<>
			<RouterProvider router={router} />
		</>
	);
}

export default App;
