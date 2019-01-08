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

## Reading the JSON file and creating a `Beer` object

In order to read the JSON file we are going to use a Java JSON library. In [other](https://github.com/LostInBrittany/servlet-beers) [projects](https://github.com/LostInBrittany/Sparkjava-Beers) we use [GSON](https://github.com/google/gson), so in this one we are using the other big JSON library, [Jackson](https://github.com/FasterXML/jackson).

Let's begin by adding it to the `build.gradle` dependencies and refreshing the Gradle project:

```groovy
// https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
compile group: 'com.fasterxml.jackson.core', name: 'jackson-databind', version: '2.9.8'

```

Now in the controller, if the file exists we can read it:

```java
import com.fasterxml.jackson.databind.ObjectMapper;
/*
...
*/

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

/*
...
*/
```

And now if we test via `curl`, we obtain the beer detail information:


```shell
$ curl localhost:8080/beer/AffligemBlond
{"name":"Affligem Blond","id":"AffligemBlond","img":"img/AffligemBlond.jpg","description":"Affligem Blonde, the classic clear blonde abbey ale, with a gentle roundness and 6.8% alcohol. Low on bitterness, it is eminently drinkable.","alcohol":6.8,"availability":"Year round","brewery":"Brasserie Affligem (Heineken)","label":"img/AffligemBlond-label.png","serving":"Serve in a Snifter","style":"Belgian-Style Blonde Ale"}
```

In [next step](../step-05) we are serving our Angular or Polymer frontend as statics resources from our Spring application. 