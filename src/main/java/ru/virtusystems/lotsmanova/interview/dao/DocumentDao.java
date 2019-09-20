package ru.virtusystems.lotsmanova.interview.dao;

import java.util.List;

import ru.virtusystems.lotsmanova.interview.model.InsurantDocument;

public interface DocumentDao {

	public List<InsurantDocument> getDocuments();

	public InsurantDocument getById(int id);

	public InsurantDocument insert(InsurantDocument document);

	public InsurantDocument update(InsurantDocument document);

}
