package ru.virtusystems.lotsmanova.interview.service;

import java.util.List;

import ru.virtusystems.lotsmanova.interview.model.InsurantDocument;

public interface DocumentService {

	public List<InsurantDocument> getDocuments();

	public InsurantDocument getById(int id);

	public float calculateInsurancePremium(InsurantDocument document);

	public InsurantDocument insert(InsurantDocument document);

	public InsurantDocument update(InsurantDocument document);

}
