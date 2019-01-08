# ExpressJ# Spring Boot -  - Step 02 - Basic routing


## Defining the routes

We are going to define the routes we need for our API application.

As we want to build a backend for [Angular Beers]() or [Polymer Beers]() applications,
we need to define:

- `GET /beers`: the list of beers, with name, description, alcohol content and image URL for each beers
- `GET /beer/<beerId>`: to get the detail of a beer

Let's begin by defining the routes on one new controller, `BeerController.java`:

```java
package org.lostinbrittany.cesi.springbeers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeerController {

  @RequestMapping("/beers")
  public String getBeers() {
    return "Hello beers";
  }

  @RequestMapping(value = "/beer/{id}", method = RequestMethod.GET)
    public Beer beerDetails(@PathVariable("id") String id) {
    return "Hello beer "+id;
  }
}
```

If you run `SpringBeersBackendApplication` again now, Spring will find the new controller in the classpath and inject it in the app *automagically*. The two new routes are now available in your application.  

Now you can test both routes with `curl`:

```shell
$ curl localhost:8080/beers
Hello beers
$ curl localhost:8080/beer/AffligemBlond
Hello beer AffligemBlond
```

In the [next step](../step-03) your API is going to serve the static JSON files as response to the requests.