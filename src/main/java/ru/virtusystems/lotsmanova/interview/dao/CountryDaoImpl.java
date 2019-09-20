package ru.virtusystems.lotsmanova.interview.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import ru.virtusystems.lotsmanova.interview.model.Country;

@Repository
@Transactional
public class CountryDaoImpl implements CountryDao {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Country> getCountries() {
		Session session = entityManager.unwrap(Session.class);
		Query<Country> query = session.createQuery("from Country");
		return (List<Country>) query.list();
	}

}
