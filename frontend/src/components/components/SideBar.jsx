import React from "react";
import {
	faUser,
	faChartLine,
	faWallet,
	faRightFromBracket,
} from "@fortawesome/free-solid-svg-icons";
import SidebarLink from "./SidebarLink";

import global from "../../resources/global.json";
import "../../resources/styles/sidebar-style.css";
import Line from "./Line";

export default function SideBar() {
	return (
		<>
			<nav
				style={{
					width: "250px",
					height: "100",
					backgroundColor: global.COLORS.GREEN_LIGHT,
					display: "flex",
					flexDirection: "column",
					position: "fixed",
					marginTop: "95px",
					padding: "10px",
					borderRadius: "8px"
				}}
			>
				
				<div style={{ display: "flex", flexDirection: "column", gap: "35px" }}>
					<SidebarLink name={"Dashboard"} route={"/"} icon={faChartLine} />
					<Line />
					<SidebarLink
						name={"Routes"}
						route={"/routes"}
						icon={faWallet}
					/>
					<Line />
					<SidebarLink name={"Profile"} route={"/profile"} icon={faUser} />
					<Line />
					<SidebarLink
						name={"Logout"}
						route={"/logout"}
						icon={faRightFromBracket}
					/>
				</div>
			</nav>
		</>
	);
}
