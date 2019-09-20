package ru.virtusystems.lotsmanova.interview.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import ru.virtusystems.lotsmanova.interview.model.InsurantDocument;

@Repository
@Transactional
public class DocumentDaoImpl implements DocumentDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@SuppressWarnings("unchecked")
	public List<InsurantDocument> getDocuments() {
		Session session = entityManager.unwrap(Session.class);
		Query<InsurantDocument> query = session
				.createQuery("from InsurantDocument");
		return (List<InsurantDocument>) query.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public InsurantDocument getById(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query<InsurantDocument> query = session.createQuery(
				"from InsurantDocument D WHERE D.id = :document_id");
		query.setParameter("document_id", id);
		return (InsurantDocument) query.list().get(0);
	}

	@Override
	public InsurantDocument insert(InsurantDocument document) {
		Session session = entityManager.unwrap(Session.class);
		session.persist(document);
		return document;
	}

	@Override
	public InsurantDocument update(InsurantDocument document) {
		Session session = entityManager.unwrap(Session.class);
		session.replicate(document, ReplicationMode.OVERWRITE);
		return document;
	}

}
