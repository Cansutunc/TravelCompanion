import axios from "axios";
import React, { useEffect, useState } from "react";
import jwt_decode from "jwt-decode";
import global from "../../resources/global.json";

export default function LocationComponent() {
	const [position, setPosition] = useState({ city: null, country: null });

	function getLocation() {
		if ("geolocation" in navigator) {
			navigator.geolocation.getCurrentPosition(function (position) {
				axios
					.get("https://geocode.maps.co/reverse?lat="+position.coords.latitude+"&lon="+position.coords.longitude+"&api_key=66047447dc1a0394257330zji8d70fa")
					.then((res) => {
						console.log(res.status);
						if (res.status === 200){
							console.log()
							if (res.data.address.city !== "undefined") {
								setPosition({city:res.data.address.city,country:res.data.address.country});
								const token = localStorage.getItem("token");
								let user_mail = jwt_decode(token).sub;
								const routeData = {
									email: user_mail,
									city: res.data.address.city,
									country: res.data.address.country
								};

								axios
									.post(
										global.CONNECTION.HEROKU_ENDPOINT + "api/v1/routes/update",
										routeData
									)
									.then((res) => {
									})
									.catch((err) => {
										const status = err.response.status;

									});
							}

						}

					})
					.catch((err) => {
						console.log(err);
					});
			});


		} else {
			console.log("Geolocation is not available in your browser.");
		}


	}

	useEffect(() => {
		getLocation();
		setInterval(() => {
			getLocation();
		}, 15000);
	}, []);

	return (
		<div
			style={{
				width: "80%",
				display: "flex",
				justifyContent: "center",
				aligntems: "center",
				position: "relative",
			}}
		>
			<h style={{fontSize: "60px", color: "#fff"}}> Current Location </h>
			<p style={{fontSize: "60px", fontWeight: "bold"}}>
				{position.country} {position.city}
			</p>
		</div>
	);
}
