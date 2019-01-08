package org.lostinbrittany.cesi.springbeers.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.lostinbrittany.cesi.springbeers.model.Beer;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeerController {

	@RequestMapping("/beers")
	public List<Beer> getBeers() {
		return Beer.getBeers();
	}

	@RequestMapping(value = "/beer/{id}", method = RequestMethod.GET)
	public String beerDetails(@PathVariable("id") String id) {
		try {
			File file = ResourceUtils.getFile("classpath:beers/" + id + ".json");
		} catch (FileNotFoundException e) {
			return "Beer "+ id + " not found";
		}

		return "Beer " + id + " found";
	}
}