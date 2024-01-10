import React from "react";
import {
	closePopup,
	selectAction,
	selectPopup,
	selectPopupTitle,
} from "../../features/global/globalSlice";
import Popup from "./Popup";
import { useSelector, useDispatch } from "react-redux";
import * as PopupActions from "../../helpers/Actions";

function GlobalPopup() {
	const dispatch = useDispatch();
	const open = useSelector(selectPopup);
	const title = useSelector(selectPopupTitle);
	const action = useSelector(selectAction);

	// IF WE OPEN POPUP THEN CLOSE IT IMMEDIATLY WITHOUR MAKING ANY CHANGES
	const reset = () => {
		switch (action) {
			// SYSTEMS
			// case PopupActions.CREATE_SYSTEM.action:
			// case UPDATE_SYSTEM.action:
			// case DELETE_SYSTEM.action:
			// case VIEW_SYSTEM.action:
			// 	dispatch(sSystemToEdit(null));
			// 	break;

			default:
			// code block
		}
	};
	return (
		<Popup
			open={open}
			handleClose={() => {
				reset();
				dispatch(closePopup());
			}}
			title={title}
		>
			{
				{/* {
					// SYSTEMS
					CREATE_SYSTEM: <SystemForm />,
					UPDATE_SYSTEM: <SystemForm />,
					DELETE_SYSTEM: <DeleteCofirmation />,
					VIEW_SYSTEM: <ViewSystem />,
				}[action] */}
			}
		</Popup>
	);
}

export default GlobalPopup;
