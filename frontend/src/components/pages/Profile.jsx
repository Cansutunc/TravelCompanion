import React, { useEffect, useState } from "react";
import SideBar from "../components/SideBar";
import TextField from "@mui/material/TextField";
import Input from "@mui/material/Input";
import Button from "@mui/material/Button";
import Radio from "@mui/material/Radio";
import RadioGroup from "@mui/material/RadioGroup";
import FormControlLabel from "@mui/material/FormControlLabel";
import FormControl from "@mui/material/FormControl";
import FormLabel from "@mui/material/FormLabel";
import axios from "axios";
import jwt_decode from "jwt-decode";
import global from "../../resources/global.json";

export default function Profile() {
	const [email, setEmail] = useState("");
	const [address, setAddress] = useState("");
	const [name, setName] = useState("");
	const [isLoading, setIsLoading] = useState(true);

	useEffect(() => {
		const token = localStorage.getItem("token");
		let user_mail = jwt_decode(token).sub;
		console.log("User mail: ", user_mail);
        axios
            .get(global.CONNECTION.HEROKU_ENDPOINT + "api/v1/user/byEmail/" + user_mail)
            .then((res) => {
                setName(res.data.name);
                setAddress(res.data.name);
                setEmail(res.data.email);
                setIsLoading(false);
            });

	});

	if (isLoading) {
		return (
			<>
				<SideBar />
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
			<>
				<div style={{ display: "flex" }}>
					<SideBar />
					<div
						style={{
							width: "100%",
							height: "100vh",
							paddingLeft: "20px",
							marginLeft: "250px",
						}}
					>
						<h1 style={{ fontSize: "50px", marginBottom: "50px" }}>
							Welcome back, {name}!
						</h1>

						<section>
							<div
								style={{
									display: "flex",
									gap: "20px",
									marginBottom: "50px",
								}}
							>
								<TextField
									id="outlined-read-only-input"
									label="Password"
									defaultValue="*********"
									InputProps={{
										readOnly: true,
									}}
								/>
								<TextField
									id="outlined-read-only-input"
									label="Email"
									value={email}
									InputProps={{
										readOnly: true,
									}}
								/>
							</div>

							<div
								style={{
									display: "flex",
									gap: "20px",
									marginBottom: "50px",
								}}
							>
								<TextField
									id="outlined-read-only-input"
									label="Address"
									InputProps={{
										readOnly: true,
									}}
									value={address}
								/>
								<TextField
									id="outlined-read-only-input"
									label="State"
									defaultValue="Italy"
									InputProps={{
										readOnly: true,
									}}
								/>
							</div>
							<div
								style={{
									display: "flex",
									gap: "20px",
									marginBottom: "50px",
								}}
							>
								<TextField
									id="outlined-read-only-input"
									label="City"
									defaultValue="Rome"
									InputProps={{
										readOnly: true,
									}}
								/>
								<TextField
									id="outlined-read-only-input"
									label="ZipCode"
									defaultValue="00142"
									InputProps={{
										readOnly: true,
									}}
								/>
							</div>
						</section>
					</div>
				</div>
			</>
		);
	}
}
