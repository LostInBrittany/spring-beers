
package org.lostinbrittany.cesi.springbeers.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.lostinbrittany.cesi.springbeers.model.Beer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.util.ResourceUtils;

@RestController
public class BeerController {

  @RequestMapping("/beers")
  public List<Beer> getBeers() {
    return Beer.getBeers();
  }


	@RequestMapping(value = "/beer/{id}", method = RequestMethod.GET)
	public Beer beerDetails(@PathVariable("id") String id) {

		ObjectMapper mapper = new ObjectMapper();
		
		try {
			File file = ResourceUtils.getFile("classpath:beers/" + id + ".json");
			FileInputStream in = new FileInputStream(file);
			Beer beer = mapper.readValue(file, Beer.class);
			return beer;
		} catch (Exception e) {
			return null;
		}
	}
}