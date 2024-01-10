import { Backdrop, Box, Fade, Modal } from "@mui/material";
import React from "react";

function Popup({ open, handleClose, title, children, sm }) {
	return (
		<Modal
			open={open}
			onClose={handleClose}
			closeAfterTransition
			BackdropComponent={Backdrop}
			BackdropProps={{
				timeout: 200,
			}}
			style={{
				backgroundColor: "rgba(0, 0, 0, 0)",
			}}
		>
			<Fade in={open}>
				<Box
					sx={{
						position: "absolute",
						top: "50%",
						left: "50%",
						transform: "translate(-50%, -50%)",
						width: sm ? "30%" : "60%",
						bgcolor: "background.paper",
						boxShadow: 24,
						borderRadius: "6px",
					}}
				>
					<header className="popup-header">
						<div className="title">{title}</div>
						<i className="bi bi-x-circle" onClick={handleClose}></i>
					</header>
					<main className="popup-main">{children}</main>
				</Box>
			</Fade>
		</Modal>
	);
}

export default Popup;
