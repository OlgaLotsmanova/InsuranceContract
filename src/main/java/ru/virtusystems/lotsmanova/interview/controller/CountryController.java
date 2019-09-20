package ru.virtusystems.lotsmanova.interview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.virtusystems.lotsmanova.interview.dao.CountryDao;
import ru.virtusystems.lotsmanova.interview.model.Country;

@RestController
@RequestMapping("/countries")
public class CountryController {

	@Autowired
	private CountryDao countryDao;

	@GetMapping
	public List<Country> getCountries() {

		return countryDao.getCountries();

	}

}
