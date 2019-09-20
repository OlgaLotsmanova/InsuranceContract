package ru.virtusystems.lotsmanova.interview.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ru.virtusystems.lotsmanova.interview.dao.CustomerDao;
import ru.virtusystems.lotsmanova.interview.dto.CustomerFilter;
import ru.virtusystems.lotsmanova.interview.model.Customer;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	@Qualifier("customerValidator")
	private Validator customerValidator;
	
	@InitBinder("customer")
	protected void initBinderBookForm(WebDataBinder binder) {
		binder.setValidator(customerValidator);
	}

	@GetMapping
	public List<Customer> getCustomers(CustomerFilter filter) {

		return customerDao.getCustomers(filter);

	}

	@GetMapping("/{customerId}")
	public Customer getCustomerById(
			@PathVariable("customerId") int customerId) {

		return customerDao.getById(customerId);

	}

	@PostMapping
	public Customer insertCustomer(@RequestBody @Valid Customer customer)
			throws BindException {

		return customerDao.insert(customer);

	}

	@PutMapping("/{customerId}")
	@ResponseStatus(value = HttpStatus.OK)
	public void updateCustomer(@PathVariable("customerId") int customerId,
			@RequestBody @Valid Customer customer)
			throws BindException {

		customerDao.update(customer);

	}

}
