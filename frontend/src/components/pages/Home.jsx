import React from "react";
import SideBar from "../components/SideBar";
import Header from "../components/Header";
import { Grid } from "@mui/material";
import BatteryComponent from "../components/BatteryComponent";
import global from "../../resources/global.json";
import WeatherComponent from "../components/WeatherComponent";
import GridItem from "../components/GridItem";
import RoutesComponent from "../components/RoutesComponent";
import MyLocation from "../components/LocationComponent";

export default function Home() {
	return (
		<div style={{ display: "flex" }}>
			<Header />
			<div
				style={{
					width: "100%",
					height: "100vh",
					paddingLeft: "20px",
					marginLeft: "250px",
				}}
			>
				<Grid container>
					<Grid
						item
						xs={6}
						style={{
							height: "100vh",
							display: "grid",
							margin: "100px 0"
						}}
					>
						<GridItem maxHeight="55vh" height="55vh">
							<RoutesComponent bgColor={"cornflowerblue"} self={"dashboard"} />
						</GridItem>
					</Grid>

					<Grid container item xs={6} style={{ height: "100vh" }}>
						<Grid
							item
							xs={12}
							style={{
								height: "40%",
								display: "grid",
								margin: "100px 0"
							}}
						>
							<GridItem backgroundColor={global.COLORS.ACCENT} height="38vh">
								<MyLocation />
							</GridItem>
						</Grid>
						<Grid
							item
							xs={12}
							style={{
								height: "60%",
								display: "grid",
								placeItems: "center",
							}}
						>
							<GridItem backgroundColor={global.COLORS.BLUE} height="55vh">
								<WeatherComponent />
							</GridItem>
						</Grid>
					</Grid>
				</Grid>
			</div>
		</div>
	);
}
