package ru.virtusystems.lotsmanova.interview.dao;

import java.util.List;

import ru.virtusystems.lotsmanova.interview.dto.CustomerFilter;
import ru.virtusystems.lotsmanova.interview.model.Customer;

public interface CustomerDao {

	public List<Customer> getCustomers(CustomerFilter filter);

	public Customer getById(int id);

	public Customer insert(Customer customer);

	public void update(Customer customer);

}
