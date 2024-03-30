import React, { Component } from "react";
import SideBar from "../components/SideBar";
import RoutesComponent from "../components/RoutesComponent";

export default function Routes() {
	return (
		<div style={{ display: "flex" }}>
			<SideBar />
			<div
				style={{
					width: "100%",
					height: "100vh",
					paddingLeft: "20px",
					paddingRight: "20px",
					overflow: "scroll",
					marginLeft: "250px",
				}}
			>
				<h1 style={{ fontSize: "50px", marginBottom: "100px" }}>
					Your recent routes
				</h1>
				<div style={{ width: "100%" }}>
					<RoutesComponent self ={"me"} />
				</div>
			</div>
		</div>
	);
}
