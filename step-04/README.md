# Step 04 - Reading the JSON files

In this step we are going to answer to the beer details requests by reading the data from the JSON files and sending it back to the client.

Copy the JSON files from the `/data/beers` folder to a `beers` folder inside the resources folder of your application (i.e. `src/main/resources/beers`).

## Reading JSON files from the resources folder

To read files in the resources folder of our Spring application we are using an utility method from Spring, `ResourceUtils.getFile()`. 

Lets begin by modifying our `BeerController` to try to read the JSON file for the requested beer, and sending back if the file exists or not:

```java
package org.lostinbrittany.cesi.springbeers.controllers;

/*
...
*/

import org.springframework.util.ResourceUtils;

/*
...
*/

  @RequestMapping(value = "/beer/{id}", method = RequestMethod.GET)
  public String beerDetails(@PathVariable("id") String id) {
    try {
      File file = ResourceUtils.getFile("classpath:beers/" + id + ".json");
    } catch (FileNotFoundException e) {
      return "Beer "+ id + " not found";
    }

    return "Beer " + id + " found";
  }

/*
...
*/
```

We can now verify with some `curl` tests that we can indeed find the files:

```shell
$ curl localhost:8080/beer/rbebntrn
Beer rbebntrn not found
$ curl localhost:8080/beer/AffligemBlond
Beer AffligemBlond found
```