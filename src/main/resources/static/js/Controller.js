function Controller() {
	this.model = new Model();
	this.view = new View(this.model, this);
}

Controller.prototype.start = function() {
	this.view.init();
	this.model.init();
}



Controller.prototype.addDocument = function(document) {
	this.model.addDocument(document);
}

Controller.prototype.updateDocument = function(document) {
	this.model.updateDocument(document);
}

Controller.prototype.calculateInsurancePremium = function(document) {
	this.model.calculateInsurancePremium(document);
}



Controller.prototype.addCustomer = function(document, customer) {
	this.model.addCustomer(document, customer);
}

Controller.prototype.updateCustomer = function(document, customer) {
	this.model.updateCustomer(document, customer);
}

Controller.prototype.searchCustomer = function(lastName, firstName, middleName) {
	this.model.searchCustomer(lastName, firstName, middleName);
}