import React  from 'react';
import { useState, useEffect } from "react";
import "../../resources/styles/battery-style.css";

export default function BatteryComponent() {
	const [userId, setUserId] = useState("");
	const [batteryPercentage, setBatteryPercentage] = useState(100);

	// function getBatteryPercentage(user_mail) {
	// 	axios
	// 		.get(global.CONNECTION.HEROKU_ENDPOINT + "api/v1/user/byEmail/" + user_mail)
	// 		.then((resultId) => {
	// 			setUserId(resultId.data);
	// 			console.log("USER ID = ", resultId.data);
	// 			axios
	// 				.get(
	// 					global.CONNECTION.HEROKU_ENDPOINT +
	// 						"api/v1/seller/" +
	// 						resultId.data +
	// 						"/batteryPercentage"
	// 				)
	// 				.then((res) => {
	// 					console.log("SELLER BATTERY = ", res.data);
	// 					setBatteryPercentage(res.data);
	// 				})
	// 				.catch((err) => {
	// 					console.log(err);
	// 				});
	//
	// 		})
	// 		.catch((err) => {
	//
	// 		});
	// }
	//
	// useEffect(() => {
	// 	const token = localStorage.getItem("token");
	// 	let user_mail = jwt_decode(token).sub;
	// 	getBatteryPercentage(user_mail);
	// 	setInterval(() => {
	// 		getBatteryPercentage(user_mail);
	// 	}, 5000);
	// }, []);

	useEffect(() => {
		console.log("Value = ", Math.round(360 - batteryPercentage * 360));
		document
			.querySelector(".meter-1")
			.style.setProperty(
				"--final-value",
				Math.round(370 - batteryPercentage * 360) > 0
					? Math.round(370 - batteryPercentage * 360)
					: 0
			);
	}, [batteryPercentage]);

	return (
		<div
			style={{
				backgroundColor: "#243b47",
				width: "100%",
				height: "100%",
				display: "grid",
				placeItems: "center",
				position: "relative",
			}}
		>
		
			<div className="dashboard">
				<svg className="battery_svg">
					<circle className="bg" cx="57" cy="57" r="52" />
					<circle className="meter-1" cx="57" cy="57" r="52" />
				</svg>
			</div>
			<div id="bolt" style={{ position: "absolute", zoom: 4 }}>
				<svg
					xmlns="http://www.w3.org/2000/svg"
					height="1em"
					viewBox="0 0 448 512"
				>
					<path
						fill="gold"
						d="M349.4 44.6c5.9-13.7 1.5-29.7-10.6-38.5s-28.6-8-39.9 1.8l-256 224c-10 8.8-13.6 22.9-8.9 35.3S50.7 288 64 288H175.5L98.6 467.4c-5.9 13.7-1.5 29.7 10.6 38.5s28.6 8 39.9-1.8l256-224c10-8.8 13.6-22.9 8.9-35.3s-16.6-20.7-30-20.7H272.5L349.4 44.6z"
					/>
				</svg>
			</div>
		</div>
	);
}
