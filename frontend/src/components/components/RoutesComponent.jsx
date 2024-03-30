import React, { useState, useEffect } from "react";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import axios from "axios";
import jwt_decode from "jwt-decode";
import moment from 'moment';

import global from "../../resources/global.json";

function createData(date, energy, seller) {
	return { date, energy, seller };
}

const rows = [
	createData("10 feb", 159, "001"),
	createData("12 feb", 237, "001"),
	createData("15 mar", 262, "002"),
	createData("16 mar", 305, "002"),
	createData("22 mar", 356, "001"),
];

export default function RoutesComponent({ bgColor,self }) {
	const [userId, setUserId] = useState("")
	const [routes, setRoutes] = useState([]);
	const [datas, setDatas] = useState([]);
	const [isLoading, setIsLoading] = useState(true);
	function getTransactions() {
		const token = localStorage.getItem("token");
		let user_mail = jwt_decode(token).sub;

		axios
			.get(global.CONNECTION.HEROKU_ENDPOINT + "api/v1/user/byEmail/" + user_mail)
			.then((res) => {
				setUserId(res.data.id);
				setIsLoading(false);
			})
			.catch((err) => {

			});
	}

	useEffect(() => {
		getTransactions();
		getRouteDatas();
		setInterval(() => {
			getRouteDatas();

		}, 10000);
	}, [userId,routes]);


	useEffect(() => {
		updateDatas();
		setInterval(() => {
			updateDatas();

		}, 10000);
	}, [routes,userId,datas]);

	function getRouteDatas(){
		axios
			.get(global.CONNECTION.HEROKU_ENDPOINT + "api/v1/routes/getAll")
			.then((res) => {
				setRoutes(res.data.reverse().slice(0, 500));
			})
			.catch((err) => {
				console.log(err);
			});


	}

	let textStyle = {
		color: bgColor ? "white" : "black",
		fontSize: "20px",
		fontWeight: bgColor ? "bold" : "initial",
	};

	function updateDatas(){
		let data = [];
		routes.forEach((r) => {
			console.log("route",r);
			if (self !== "undefined"){
				if (self ==="dashboard") {
					if (r.userId !== userId) {
						data.push(r);
					}
				}
				else {
					console.log("me",r.userId,userId)
					if (r.userId === userId) {
						data.push(r);
					}
				}
			}

		});
		console.log("last ",data);
		console.log("datas ",datas);
		setDatas(data);

	}

	let dataToDisplay = datas;

	const url = window.location.href;
	const parsedURL = new URL(url);
	const pathname = parsedURL.pathname;
	const extractedValue = pathname.split("/")[1];

	if (extractedValue !== "routes") {
		dataToDisplay = dataToDisplay.slice(0, 5);
	}

	if (isLoading && extractedValue === "routes") {
		return (
			<>
				<div
					style={{
						position: "fixed",
						left: "60%",
						top: "40%",
					}}
				>
					<div className="loading">
						<svg width="64px" height="48px">
							<polyline
								points="0.157 23.954, 14 23.954, 21.843 48, 43 0, 50 24, 64 24"
								id="back"
							></polyline>
							<polyline
								points="0.157 23.954, 14 23.954, 21.843 48, 43 0, 50 24, 64 24"
								id="front"
							></polyline>
						</svg>
					</div>
				</div>
			</>
		);
	} else {
		return (
			<TableContainer
				style={{
					height: "100%",
					backgroundColor: bgColor,
				}}
			>
				<h1
					style={{
						marginLeft: extractedValue === "routes" ? "0px" : "20px",
						color: extractedValue === "routes" ? "black" : "white",
					}}
				>
					People around you
				</h1>
				<Table>
					<TableHead>
						<TableRow>
							<TableCell style={textStyle} >Date
							</TableCell>
							<TableCell style={textStyle}  align="left">Email</TableCell>

							<TableCell style={textStyle} align="left">City
							</TableCell>
							<TableCell style={textStyle} align="left">Country
							</TableCell>
						</TableRow>
					</TableHead>
					<TableBody>
						{dataToDisplay.map((sale) => (
							<TableRow
								key={sale.id}
								sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
							>
								<TableCell component="th" scope="row" style={textStyle}>
									{sale.timestamp}
								</TableCell>
								<TableCell style={textStyle}  align="left" >
									{sale.email}
								</TableCell>
								<TableCell style={textStyle}  align="left">	{sale.city}	</TableCell>
								<TableCell style={textStyle}  align="left">	{sale.country}	</TableCell>
							</TableRow>
						))}
					</TableBody>
				</Table>
			</TableContainer>
		);
	}
}
