# Step 07 - Going CRUD

For the moment we can read data from the database. In this step we are going to add a true [CRUD](https://en.wikipedia.org/wiki/Create,_read,_update_and_delete) support to our Spring application, adding routes to add, modify and delete a Beer.


## Adding the routes 

We are going to add three new routes to `BeerController`: create a beer, modify a beer and delete a beer.

Let's begin with the creation. We want to listen to POST request on `/create`, having the new beer information in the request body. We add `RequestMethod.POST` as `method` in the `@RequestMapping` annotation, and the annotate the `Beer` parameter with `@RequestBody` to make Spring read the beer from the request body and inject it to the method.

Inside the method, we simple tell to `BeerRepository` to save the new beer in the database, easy and painless.


```java
@RequestMapping(value = "create", method = RequestMethod.POST)
public Beer beerCreate(@RequestBody Beer beer) {    	
	repository.save(beer);
	return beer;    	
}
```

For deleting a beer, we listen for the DELETE method on `/delete` and we extract the id of the beer to delete from the URL:

```java
@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
public void beerDelete(@PathVariable("id") String id) {
	repository.deleteById(id);
	return;	
}
```

And for the beer edition,  want to listen to POST request on `/edit`, having the new beer information in the request body. We verify that the beer exists, and if it does we update it.

```java
@RequestMapping(value = "edit", method = RequestMethod.POST)
public Beer beerEdit(@RequestBody Beer beer) {
	if (repository.existsById(beer.getId())) {
		repository.save(beer);
		return beer;
	}
	return null;
}
```

Now you can test your API creating some beers, editing and deleting them using your favorite request editor (I suggest [Insomnia](https://github.com/getinsomnia/insomnia)).

