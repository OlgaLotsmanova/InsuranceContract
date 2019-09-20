package ru.virtusystems.lotsmanova.interview.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import ru.virtusystems.lotsmanova.interview.dto.CustomerFilter;
import ru.virtusystems.lotsmanova.interview.model.Customer;

@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Customer> getCustomers(CustomerFilter filter) {
		Session session = entityManager.unwrap(Session.class);
		Query<Customer> query = session.createQuery("from Customer");
		return (List<Customer>) query.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Customer getById(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query<Customer> query = session
				.createQuery("from Customer C WHERE C.id = :customer_id");
		query.setParameter("customer_id", id);
		return (Customer) query.list().get(0);
	}

	@Override
	public Customer insert(Customer customer) {
		Session session = entityManager.unwrap(Session.class);
		session.persist(customer);
		return customer;
	}

	@Override
	public void update(Customer customer) {
		Session session = entityManager.unwrap(Session.class);
		session.replicate(customer, ReplicationMode.OVERWRITE);
	}

}
