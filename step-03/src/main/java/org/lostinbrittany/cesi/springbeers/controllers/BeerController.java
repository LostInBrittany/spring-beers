package org.lostinbrittany.cesi.springbeers.controllers;

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
    return "Hello beer "+id;
  }
}