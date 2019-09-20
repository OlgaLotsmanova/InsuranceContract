function EventEmitter() {
	this.listeners = new Array();
}

EventEmitter.prototype.subscribe = function(handler) {
	this.listeners.push(handler);
}

EventEmitter.prototype.notify = function(data1, data2) {
	for (var i = 0; i < this.listeners.length; i++) {
		this.listeners[i](data1, data2);
	}
}