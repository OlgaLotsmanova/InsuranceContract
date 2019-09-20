package ru.virtusystems.lotsmanova.interview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ru.virtusystems.lotsmanova.interview.dto.DocumentValidator;
import ru.virtusystems.lotsmanova.interview.dto.InsuranceCalculatePremiumValidator;
import ru.virtusystems.lotsmanova.interview.model.InsurantDocument;
import ru.virtusystems.lotsmanova.interview.service.DocumentService;

@RestController
@RequestMapping("/documents")
public class DocumentController {

	@Autowired
	private DocumentService documentService;

	@GetMapping
	public List<InsurantDocument> getDocuments() {

		List<InsurantDocument> documents = documentService.getDocuments();
		return documents;

	}

	@GetMapping("/{documentId}")
	public InsurantDocument getDocumentById(
			@PathVariable("documentId") int documentId) {

		return documentService.getById(documentId);

	}

	@PostMapping
	public InsurantDocument insertDocument(
			@RequestBody InsurantDocument document, BindingResult result)
			throws BindException {

		new DocumentValidator().validate(document, result);

		if (result!= null && result.hasErrors()) {
			throw new BindException(result);
		}

		return documentService.insert(document);

	}

	@PutMapping("/{documentId}")
	@ResponseStatus(value = HttpStatus.OK)
	public InsurantDocument updateDocument(@PathVariable("documentId") int documentId,
			@RequestBody InsurantDocument document, BindingResult result)
			throws BindException {

		new DocumentValidator().validate(document, result);

		if (result.hasErrors()) {
			throw new BindException(result);
		}

		return documentService.update(document);

	}

	@PostMapping("/premiums")
	public float calculateInsurancePremium(
			@RequestBody InsurantDocument document, BindingResult result)
			throws BindException {

		new InsuranceCalculatePremiumValidator().validate(document, result);

		if (result.hasErrors()) {
			throw new BindException(result);
		}

		return documentService.calculateInsurancePremium(document);

	}
}
