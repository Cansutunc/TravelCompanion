import React, { useEffect, useState } from "react";
import axios from "axios";
import global from "../../resources/global.json";
import jwt_decode from "jwt-decode";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
export default function WeatherComponent() {
	const [hobbies, setHobbies] = useState([]);
	const [datas, setDatas] = useState([]);
	const [isLoading, setIsLoading] = useState(true);
	function getTransactions() {
		const token = localStorage.getItem("token");
		let user_mail = jwt_decode(token).sub;

		axios
			.get(global.CONNECTION.HEROKU_ENDPOINT + "api/v1/user/getHobbies/" + user_mail)
			.then((res) => {
				setHobbies(res.data)
				setIsLoading(false);
			})
			.catch((err) => {

			});
	}

	useEffect(() => {
		getTransactions();
		setInterval(() => {

		}, 10000);
	}, [hobbies]);


	let textStyle = {
		color:  "black",
		fontSize: "20px",
		fontWeight: "initial",
	};



	let dataToDisplay = hobbies;

	const url = window.location.href;
	const parsedURL = new URL(url);
	const pathname = parsedURL.pathname;
	const extractedValue = pathname.split("/")[1];

	if (extractedValue !== "routes") {
		dataToDisplay = dataToDisplay.slice(0, 5);
	}

	return (
		<TableContainer
			style={{
				height: "100%",
				backgroundColor: "green",
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
						<TableCell style={textStyle} >Name
						</TableCell>
						<TableCell style={textStyle} align="left">Total
						</TableCell>
					</TableRow>
				</TableHead>
				<TableBody>
					{dataToDisplay.map((sale) => (
						<TableRow
							key={sale.id}
							sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
						>
							<TableCell style={textStyle}  align="left">	{sale.secondUserId}	</TableCell>
							<TableCell style={textStyle}  align="left">	{sale.total}	</TableCell>
						</TableRow>
					))}
				</TableBody>
			</Table>
		</TableContainer>
	);
}
