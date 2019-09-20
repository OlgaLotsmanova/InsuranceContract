function Model() {
    this.documents = new Array();
    this.countries = new Array();
    this.realtytypes = new Array();
    
    this.onUpdateDocuments = new EventEmitter();
    this.onUpdateDocument = new EventEmitter();
    this.onUpdateSearchCustomers = new EventEmitter();
    
    this.onFormError = new EventEmitter();
}

Model.prototype.init = function () {
	this.getCountries();
	this.getRealtytypes();
	this.getDocuments();
}

Model.prototype.getCountries = function () {
	var that = this;
	that.countries = new Array();
	jQuery.ajax({
        url: "countries",
        method: "GET",
        success: function (data) {
            data.forEach(function(element) {
            	that.countries.push(element);
            });
        },
        error: function(data) {
        	that.errorProcessing(data);
        }
    }); 
}

Model.prototype.getRealtytypes = function () {
	var that = this;
	that.realtytypes = new Array();
	jQuery.ajax({
        url: "realtytypes",
        method: "GET",
        success: function (data) {
        	data.forEach(function(element) {
            	that.realtytypes.push(element);
            });
        },
        error: function(data) {
        	that.errorProcessing(data);
        }
    }); 
}

Model.prototype.getDocuments = function () {
	var that = this;
	that.documents = new Array();
	jQuery.ajax({
        url: "documents",
        method: "GET",
        success: function (data) {
            data.forEach(function(element) {
            	that.documents.push(element);
            });
            that.onUpdateDocuments.notify(that.documents);
        },
        error: function(data) {
        	that.errorProcessing(data);
        }
    }); 
}

Model.prototype.addDocument = function (document){
	var that = this;
	
	jQuery.ajax({
        url: "documents",
        method: "POST",
        contentType: 'application/json',
		data: JSON.stringify(document),
        success: function (data) {
        	
        	that.getDocuments();
        	
        },
        error: function (data) {
        	that.errorProcessing(data);
        }
    }); 
}

Model.prototype.updateDocument = function (document){
	var that = this;
	
	jQuery.ajax({
		url: "documents/" + document["id"],
		method: "PUT",
		contentType: 'application/json',
		data: JSON.stringify(document),
		success: function (data) {
	        	
			that.getDocuments();
	            
		},
		error: function (data) {
			that.errorProcessing(data);
		}
	}); 
}

Model.prototype.calculateInsurancePremium = function (document){
	var that = this;
	
	jQuery.ajax({
		url: "documents/premiums",
		method: "POST",
		contentType: 'application/json',
		data: JSON.stringify(document),
		success: function (data) {
	        	
			document.premium = data;
			that.onUpdateDocument.notify(document);
	            
		},
		error: function (data) {
			that.errorProcessing(data);
		}
	}); 
}

Model.prototype.addCustomer = function (document, customer){
	var that = this;
	
	jQuery.ajax({
        url: "customers",
        method: "POST",
        contentType: 'application/json',
		data: JSON.stringify(customer),
        success: function (data) {
        	
        	document.customer = data;
        	that.onUpdateDocument.notify(document);
        	
        },
        error: function (data) {
        	that.errorProcessing(data);
        }
    }); 
}

Model.prototype.updateCustomer = function (document, customer){
	var that = this;
	
	jQuery.ajax({
		url: "customers/" + customer["id"],
		method: "PUT",
		contentType: 'application/json',
		data: JSON.stringify(customer),
		success: function (data) {
	        	
			document.customer = data;
        	that.onUpdateDocument.notify(document);
	            
		},
		error: function (data) {
			that.errorProcessing(data);
		}
	}); 
}

Model.prototype.searchCustomer = function (lastName, firstName, middleName){
	var that = this;
	
	jQuery.ajax({
        url: "customers/?lastName=" + lastName + "&firstName=" + firstName + "&middleName=" + middleName,
        method: "GET",
        success: function (data) {
        	
            that.onUpdateSearchCustomers.notify(data);

        },
        error: function (data) {
        	that.errorProcessing(data);
        }
    });
}

Model.prototype.errorProcessing = function (error){
	if (error["status"] == 400){
		console.log(error["responseJSON"]);
    	this.onFormError.notify(error["responseJSON"]);
	}
}