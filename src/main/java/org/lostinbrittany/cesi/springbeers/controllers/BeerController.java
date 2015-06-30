package org.lostinbrittany.cesi.springbeers.controllers;

import java.util.List;

import org.lostinbrittany.cesi.springbeers.dao.BeerRepository;
import org.lostinbrittany.cesi.springbeers.model.Beer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeerController {

	@Autowired
	private BeerRepository repository;
	
	
	private List<Beer> beers = Beer.getBeers();
	
    @RequestMapping("/beerList")
    public List<Beer> beerList() {
    	return repository.findAll();
    }
    @RequestMapping("/beerDetails")
    public Beer beerDetails(@RequestParam(value="id", required=true) String id) {
    	    	
    	/*for (Beer beer: beers) {
    		if (beer.getId().equals(id)) {
    			return beer;
    		}
    	}*/
    	    	
    	return repository.findById(id);
    }
}
