# Step 06 - Mongo beers

In this step we are going to quit the JSON files and use a MongoDB database. 

> In order to do this step you need to have your beer data in a MongoDB database.
> How to do it is outside the scope of this tutorial, but if you only want to do a quicktest, you could:
>
> - Install MongoDB server and MongoDB Database Tools (see http://mongodb.com/)
> - Start the MongoDB daemon (usually with the command `mongod`)
> - Use `mongoimport` command line tool to import the detailed JSON datafiles
>
>    ```
>      mongoimport --db beers --collection beers data/beers/AffligemBlond.json
>      mongoimport --db beers --collection beers data/beers/AffligemDubbel.json
>      ...
>   ```   

## Adding the Spring Boot MongoDB starter

> To better understand how to add MongoDB support in Spring Boot, you can follow the official [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/) guide.

The easiest way to add Mongo support to a Spring application is to add the `spring-boot-starter-data-mongodb` Spring Boot module to Gradle dependencies:

```groovy
dependencies {
  implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation 'org.springframework.boot:spring-boot-starter-data-mongodb' 
   
}
```
And then refresh the Gradle project to recover the dependencies.

## Annotating our model

MongoDB is a NoSQL document store. In this example, we are storing `Beer` objects.
We need to annotate the `Beer` class to add some information, for example which is the `id` field for the document, or the collection to store the documents:

```java
package org.lostinbrittany.cesi.springbeers.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="beers")
public class Beer {

	@Id
	private String id;
	private String name;
```

The other properties are left unannotated. It is assumed that they’ll be mapped to fields that share the same name as the properties themselves. 

MongoDB stores data in collections. Spring Data MongoDB will map the class `Beer` into a collection called `beer`. We use Spring Data MongoDB’s `@Document` annotation on the class to change it to `beers`.


## Create simple queries

Spring Data MongoDB focuses on storing data in MongoDB. It also inherits functionality from the Spring Data Commons project, such as the ability to derive queries. Essentially, you don’t have to learn the query language of MongoDB; you can simply write a handful of methods and the queries are written for you.

To see how this works, create a repository interface that queries `Beer` documents, `BeerRepository`.

```java
package org.lostinbrittany.cesi.springbeers.dao;

import java.util.Optional;
import org.lostinbrittany.cesi.springbeers.model.Beer;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface BeerRepository extends MongoRepository<Beer, String> {

    public Optional<Beer> findById(String id);
}
```

`BeerRepository` extends the `MongoRepository` interface and plugs in the type of values and id it works with: `Beer` and `String`. Out-of-the-box, this interface comes with many operations, including standard CRUD operations (create-read-update-delete). You can define other queries as needed by simply declaring their method signature. 

In a typical Java application, you write a class that implements `BeerRepository` and craft the queries yourself. What makes Spring Data MongoDB so useful is the fact that you don’t have to create this implementation. Spring Data MongoDB creates it on the fly when you run the application.

Let’s wire this up and see what it looks like!


## Edit `BeerController` 

Now you need to edit `BeerController` to use the repository instead of the JSON files or the harcoded beer list:

```java
package org.lostinbrittany.cesi.springbeers.controllers;

import java.util.List;
import java.util.Optional;

import org.lostinbrittany.cesi.springbeers.dao.BeerRepository;
import org.lostinbrittany.cesi.springbeers.model.Beer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
}
```

## Wiring your Mongo instance

Spring Boot by default attempts to connect to a locally hosted instance of MongoDB. Read the [reference docs](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-mongodb) for details on pointing your app to an instance of MongoDB hosted elsewhere.

We want to use the `beers` database in our MongoDB instance. We can configure that by putting an `application.properties` file inside `src/main/resources`:

```properties
# http://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html
spring.data.mongodb.uri=mongodb://localhost/beers
```


And now you should have all your data coming from MongoDB!