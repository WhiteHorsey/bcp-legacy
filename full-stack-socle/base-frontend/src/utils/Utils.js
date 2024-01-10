import { createTheme } from "@mui/material/styles";

export const theme = createTheme({
	palette: {
		primary: {
			main: "#FF8E02",
			light: "#FFC782",
		},
		secondary: {
			main: "#2c0b07",
		},
		contrastThreshold: 3,
		tonalOffset: 0.2,
	},
	overrides: {
		MuiDrawer: {
			anchorLeft: {
				marginTop: 64,
			},
		},
	},
});