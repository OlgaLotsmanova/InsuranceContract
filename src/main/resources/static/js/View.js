function View(model, controller) {
	this.activeDocument = null;
	this.model = model;
	this.ctrl = controller;

	this.documentslistTable = $("#documents-list");

	this.documentform = $("#InsurantDocument");
	this.editCustomerform = $("#Customer");

	this.createdocumentbutton = $("#create-document-button");
	this.opendocumentbutton = $("#open-document-button");

	this.documentdialog = $('#document-dialog');
	this.customerchoicedialog = $('#customer-choice-dialog');
	this.newcustomerdialog = $('#new-customer-dialog');
	this.customerdialog = $('#customer-dialog');

	this.calculateinsurancepremiumbutton = $('#calculate-insurance-premium-button');
	this.customerselectbutton = $('#customer-select-button');
	this.customerbutton = $('#customer-button');

}

View.prototype.init = function() {
	var that = this;

	this.model.onUpdateDocuments.subscribe(function(documents) {
		that.updateDocuments(documents);
	});
	
	this.model.onUpdateDocument.subscribe(function(document) {
		that.updateDocument(document);
	});

	this.model.onFormError.subscribe(function(errors) {
		that.formError(errors);
	});

	this.initDocumentTable();

	this.initDialogs();

}

View.prototype.formError = function(errors) {
	var that = this;

	if (errors.length > 0) {
		var form = document.getElementById(errors[0]["objectName"]);
		errors.forEach(function(error) {
			var field = document.getElementById(error["objectName"] + "-"
					+ error["field"]);
			field.classList.add("error");
			field.placeholder = error["message"];
		});
	}
}

View.prototype.updateDocuments = function(documents) {
	var that = this;

	this.documentslistTable.empty();

	documents.forEach(function(document) {

		that.documentslistTable.append(that
				.createDocumentCatalogueItem(document));

	});

}

View.prototype.updateDocument = function(document) {
	var that = this;

	that.activeDocument = document;

	that.documentdialog.dialog("open");

}

View.prototype.createDocumentCatalogueItem = function(document) {
	var that = this;

	var element = $('<tr>').attr('id', document.id).append(
			$('<td>').text(document.documentNumber),
			$('<td>').text(document.startDate),
			$('<td>').text(
					document["customer"]["lastName"] + ' '
							+ document["customer"]["firstName"] + ' '
							+ document["customer"]["middleName"]),
			$('<td>').text(document.premium),
			$('<td>').text(document.startDate + '-' + document.endDate));

	return element;
}

View.prototype.initDocumentTable = function() {

	var that = this;

	this.documentslistTable.on('click', 'tbody tr', function(event) {
		var tr = $(this);

		tr.addClass('selecteddocument').siblings().removeClass(
				'selecteddocument');

		that.activeDocument = that.model.documents.filter(function(e) {
			return e.id == tr.attr('id');
		})[0];
	});

}

View.prototype.fillDocumentdialog = function(document) {
	var that = this;

	var $selectCountry = $('#InsurantDocument-Realty-Country');
	$selectCountry.find('option').remove();
	$.each(that.model.countries, function(key, value) {
		$selectCountry.append('<option value=' + value.id + '>' + value.title
				+ '</option>');
	});

	var $selectRealtyType = $('#InsurantDocument-Realty-realtyType');
	$selectRealtyType.find('option').remove();
	$.each(that.model.realtytypes, function(key, value) {
		for (type in value) {
			$selectRealtyType.append('<option value=' + type + '>'
					+ value[type] + '</option>');
		}
	});

	if (!$.isEmptyObject(document)) {
		$selectCountry.val(document.realty.address.country.id);
		$selectRealtyType.val(document.realty.realtyType);
		$.each(document, function(key, value) {
			$("#InsurantDocument-" + key).val(value);
		});
		$.each(document.customer, function(key, value) {
			$("#InsurantDocument-Customer-" + key).val(value);
		});
		$("#InsurantDocument-Customer-lastName-firstName-middleName").val(
				document.customer.lastName + ' ' + document.customer.firstName
						+ ' ' + document.customer.middleName);
		$.each(document.realty, function(key, value) {
			$("#InsurantDocument-Realty-" + key).val(value);
		});
		$.each(document.realty.address, function(key, value) {
			$("#InsurantDocument-Realty-Address-" + key).val(value);
		});
	} else {
		$("#InsurantDocument").find('input[type!="button"]').val('');
	}
}

View.prototype.fillCustomerchoicedialog = function(document) {
	var that = this;

	if (document) {
		$.each(document.customer, function(key, value) {
			$("#choice-Customer-" + key).val(value);
		});
	} else {
		$("#choice-Customer").find('input[type!="button"]').val('');
	}
}

View.prototype.fillNewcustomerdialog = function() {

	$("#new-Customer").find('input[type!="button"]').val('');

}

View.prototype.fillCustomerdialog = function(document) {
	var that = this;

	if (document) {
		$.each(document.customer, function(key, value) {
			$("#Customer-" + key).val(value);
		});
	} else {
		$("#Customer").find('input[type!="button"]').val('');
	}
}

View.prototype.initDialogs = function() {

	var that = this;

	this.documentdialog.dialog({
		autoOpen : false,
		maxWidth : 1000,
		maxHeight : 560,
		width : 1000,
		height : 560,
		open : function(event, ui) {
			$(".ui-dialog-titlebar-close", ui.dialog | ui).hide();
			that.fillDocumentdialog(that.activeDocument);
		},
		modal : true,
		show : "blind",
		hide : "blind",
		dialogClass : "no-close",
		buttons : [ {
			text : "Сохранить",
			click : function() {
				var document = that.documentform.serializeJSON();
				if (that.activeDocument["customer"]) {
					document["customer"] = that.activeDocument.customer;
				}
				if (that.activeDocument["realty"]) {
					document["realty"]["id"] = that.activeDocument.realty.id;
				}
				if (that.activeDocument["id"]) {
					document["id"] = that.activeDocument.id;
					that.ctrl.updateDocument(document);
				} else {
					that.ctrl.addDocument(document);
				}
				$(this).dialog("close");
			}
		}, {
			text : "К списку договоров",
			click : function() {
				$(this).dialog("close");
			}
		} ]
	});

	this.customerchoicedialog.dialog({
		autoOpen : false,
		maxWidth : 800,
		maxHeight : 500,
		width : 800,
		height : 500,
		open : function(event, ui) {
			$(".ui-dialog-titlebar-close", ui.dialog | ui).hide();
			that.fillCustomerchoicedialog(that.activeDocument);
		},
		modal : true,
		show : "blind",
		hide : "blind",
		dialogClass : "no-close",
		buttons : [ {
			text : "Выбрать",
			click : function() {
				$(this).dialog("close");
			}
		}, {
			text : "Новый",
			click : function() {
				that.newcustomerdialog.dialog("open");
			}
		}, {
			text : "Закрыть",
			click : function() {
				$(this).dialog("close");
			}
		} ]
	});

	this.newcustomerdialog
			.dialog({
				autoOpen : false,
				maxWidth : 600,
				maxHeight : 400,
				width : 600,
				height : 400,
				open : function(event, ui) {
					$(".ui-dialog-titlebar-close", ui.dialog | ui).hide();
					that.fillNewcustomerdialog();
				},
				modal : true,
				show : "blind",
				hide : "blind",
				dialogClass : "no-close",
				buttons : [
						{
							text : "Сохранить",
							click : function() {
								var customer = that.editCustomerform
										.serializeJSON();
								if (that.activeDocument["customer"]
										&& that.activeDocument["customer"]["id"]) {
									customer["id"] = that.activeDocument.customer.id;
									that.ctrl.updateCustomer(
											that.activeDocument, customer);
								} else {
									that.ctrl.addCustomer(that.activeDocument,
											customer);
								}
								$(this).dialog("close");
							}
						}, {
							text : "Отменить",
							click : function() {
								$(this).dialog("close");
							}
						} ]
			});

	this.customerdialog
			.dialog({
				autoOpen : false,
				maxWidth : 800,
				maxHeight : 500,
				width : 800,
				height : 500,
				open : function(event, ui) {
					$(".ui-dialog-titlebar-close", ui.dialog | ui).hide();
					that.fillCustomerdialog(that.activeDocument);
				},
				modal : true,
				show : "blind",
				hide : "blind",
				dialogClass : "no-close",
				buttons : [
						{
							text : "Сохранить",
							click : function() {
								var customer = that.editCustomerform
										.serializeJSON();
								if (that.activeDocument["customer"]
										&& that.activeDocument["customer"]["id"]) {
									customer["id"] = that.activeDocument.customer.id;
									that.ctrl.updateCustomer(
											that.activeDocument, customer);
								} else {
									that.ctrl.addCustomer(that.activeDocument,
											customer);
								}
								$(this).dialog("close");
							}
						}, {
							text : "Отменить",
							click : function() {
								$(this).dialog("close");
							}
						} ]
			});

	this.createdocumentbutton.click(function() {
		that.activeDocument = new Object();
		that.documentslistTable.find('tr').removeClass('selecteddocument');
		that.documentdialog.dialog("open");
		return false;
	});

	this.opendocumentbutton.click(function() {
		that.documentdialog.dialog("open");
		return false;
	});

	this.customerselectbutton.click(function() {
		that.customerchoicedialog.dialog("open");
		return false;
	});

	this.customerbutton.click(function() {
		that.customerdialog.dialog("open");
		return false;
	});

}
