package org.lostinbrittany.cesi.springbeers.controllers;

import java.util.List;
import java.util.Optional;

import org.lostinbrittany.cesi.springbeers.dao.BeerRepository;
import org.lostinbrittany.cesi.springbeers.model.Beer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeerController {

	@Autowired
	private BeerRepository repository;
	
	@RequestMapping("/beers")
	public List<Beer> getBeers() {
    	return repository.findAll();
	}

	@RequestMapping(value = "/beer/{id}", method = RequestMethod.GET)
	public Optional<Beer> beerDetails(@PathVariable("id") String id) {
    	return repository.findById(id);
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public Beer beerCreate(@RequestBody Beer beer) {    	
		repository.save(beer);
		return beer;    	
	}
	
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	public void beerDelete(@PathVariable("id") String id) {
		repository.deleteById(id);
		return;	
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public Beer beerEdit(@RequestBody Beer beer) {
		if (repository.existsById(beer.getId())) {
			repository.save(beer);
			return beer;
		}
		return null;
	}
}