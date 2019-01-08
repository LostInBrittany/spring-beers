package org.lostinbrittany.cesi.springbeers.controllers;

import java.util.List;

import org.lostinbrittany.cesi.springbeers.dao.BeerRepository;
import org.lostinbrittany.cesi.springbeers.model.Beer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeerController {

	@Autowired
	private BeerRepository repository;
	
	
	private List<Beer> beers = Beer.getBeers();
	
    @RequestMapping("/beer/list")
    public List<Beer> beerList() {
    	return repository.findAll();
    }
    @RequestMapping("/beer/details")
    public Beer beerDetails(@RequestParam(value="id", required=true) String id) {
    	    	
    	/*for (Beer beer: beers) {
    		if (beer.getId().equals(id)) {
    			return beer;
    		}
    	}*/
    	    	
    	return repository.findById(id);
    }
    
    @RequestMapping("/beer/edit")
    public Beer beerEdit(@RequestBody Beer beer) {    	
		
			repository.save(beer);
			return beer;
	
    }
    @RequestMapping("/beer/create")
    public Beer beerCreate(@RequestBody Beer beer) {    	
    		
    	repository.save(beer);
    	return beer;
    	
    }
    

    @RequestMapping("/beer/delete")
    public void beerDelete(@RequestBody Beer beer) {
    	repository.delete(beer);
    	return;
    	
    }
}
