import React from "react";
import Button from "@mui/material/Button";
import IconButton from "@mui/material/IconButton";
import {
	faUser,
	faChartLine,
	faWallet,
	faListSquares
} from "@fortawesome/free-solid-svg-icons";
import SidebarLink from "./SidebarLink";
import travel from "../../resources/images/travel-logo.png";
import global from "../../resources/global.json";
import "../../resources/styles/sidebar-style.css";
import { useState } from "react";
import SideBar from "./SideBar";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";


export default function Header() {

	let [openSideBar, setOpenSideBar] = useState(false);

	const getSidebar = () => {
		if (openSideBar)
			return (
				<SideBar />
			)
	}

	const manageToggle = () => {
		setOpenSideBar(!openSideBar)
	}

	return (
		<>
			<nav
				style={{
					width: '100%',
					height: "100px",
					backgroundColor: global.COLORS.GREEN_LIGHT,
					display: "flex",
					flexDirection: "row",
					position: "fixed",
				}}
			>
				<h1
					style={{ fontSize: "25px", marginBottom: "200px", marginTop: "30px", marginRight: "20px" }}
				>
					TRAVEL COMPANION
				</h1>
				<img src={travel} />
				<div class="font-icon-wrapper" onClick={() => manageToggle()}>
					<IconButton iconClassName="muidocs-icon-custom-github" />
				</div>
				<Button style={{ display: "flex", flexDirection: "row", position: "fixed", right: '15px', top: "30px" }}
					onClick={() => manageToggle()} >
					<FontAwesomeIcon icon={faListSquares} style={{ marginRight: "20px", color: 'black', height: '25px' }} />
				</Button>
				{getSidebar()}
			</nav>
		</>
	);
}
