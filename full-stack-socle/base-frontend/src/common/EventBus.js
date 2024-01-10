const eventBus = {
	// on() method attachs an EventListener to the document object. The callback will be called when the event gets fired
	on(event, callback) {
		document.addEventListener(event, (e) => callback(e.detail));
	},
	// dispatch() method fires an event using the CustomEvent API.
	dispatch(event, data) {
		document.dispatchEvent(new CustomEvent(event, { detail: data }));
	},
	// remove() method removes the attached event from the document object.
	remove(event, callback) {
		document.removeEventListener(event, callback);
	},
};

export default eventBus;
