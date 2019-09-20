package ru.virtusystems.lotsmanova.interview.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.virtusystems.lotsmanova.interview.model.RealtyType;

@RestController
@RequestMapping("/realtytypes")
public class RealtyTypesController {

	@GetMapping
	public Set<Entry<String, String>> getCountries() {
		Map<String, String> mapResult = new HashMap<String, String>();
		List<String> result = new LinkedList<String>();
		for (RealtyType type : RealtyType.values()) {
			result.add("{'id': '" + type.name() + "', 'value': '"
					+ type.getTitie() + "'}");
			mapResult.put(type.name(), type.getTitie());
		}

		return mapResult.entrySet();
	}
}
