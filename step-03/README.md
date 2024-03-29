# Step 03 - Using the `Beer` class

## Refactoring

We are going to add new elements to the application, it seems a good moment to better structure it.
Let's begin by putting the controllers in a `controllers` package.

## Let's create the beer list 

We are going to take the beer list from the [Stencil Beers](https://github.com/LostInBrittany/stencil-beers) project and serve it from the Spring server. 
To do it, we begin by creating a `Beer` class that keeps the same information than the `Beer` JSON in JavaScript side.


```java
package org.lostinbrittany.cesi.springbeers.model;
		
public class Beer {
  
  /*
    ...
    */
  
  private String name;
  private String id;
  private String img;
  private String description;
  private double alcohol;
    
  /*
    ...
    */
}
```

We can add some business logic to our beer model, for example limits for the alcohol value (e.g. between 0% and 20% of alcohol) using the setters of the `Beer` bean:

```java
  /*
    ...
    */

	public void setAlcohol(double alcohol) {
		if (alcohol > MAX_ALCOHOL) ?  {
			alcohol = MAX_ALCOHOL;
		}
		if (alcohol < 0) ?  {
			alcohol = 0;
		}
		this.alcohol = alcohol;
	}
  /*
    ...
    */
```

And then, quite manually for now, we create our *BeerList* with a static method:

```java
public static List<Beer> getBeers() {
  ArrayList<Beer> list = new ArrayList<Beer>();

  Beer beer;

  beer = new Beer();
  beer.setAlcohol(6.8);
  beer.setDescription("Affligem Blonde, the classic clear blonde abbey ale, with a gentle roundness and 6.8% alcohol. Low on bitterness, it is eminently drinkable.");
  beer.setId("AffligemBlond");
  beer.setImg("beers/img/AffligemBlond.jpg");
  beer.setName("Affligem Blond");
  list.add(beer);
  
  /*
  ...
  */
  
  return list;
}
```


## We want an API so we need JSON 

So now we have a nice Java list of beers. In order to send it to the client in our Spring Boot API, we need to transform it in JSON (REST APIs speak JSON). With other frameworks, we should now look for a JSON Java library (usually GSON or Jackson) and manually call them to generate JSON from the `List<Beer>` or `Beer` objects. 

But in Spring all that is simpler. As we are using the `@RestController` annotation in the controller, Spring supposes we are using vanilla REST and sending back JSON,  so if the response isn't JSON, it serializes it in the most direct way...

So now we can modify `BeerController` to send back the list of beers:

```java
@RequestMapping("/beers")
public List<Beer> getBeers() {
  return Beer.getBeers();
}
```

Testing via `curl` we see that the `List<Beer>` is indeed serialized in JSON:

```shell
$ curl localhost:8080/beers/
[{"name":"Affligem Blond","id":"AffligemBlond","img":"img/AffligemBlond.jpg","description":"Affligem Blonde, the classic clear blonde abbey ale, with a gentle roundness and 6.8% alcohol. Low on bitterness, it is eminently drinkable.","alcohol":6.8,"availability":null,"brewery":null,"label":null,"serving":null,"style":null},{"name":"Affligem Dubbel","id":"AffligemDubbel","img":"img/AffligemDubbel.jpg","description":"A reddish-brown abbey ale brewed with dark malts. The secondary fermentation gives a fruity aroma and a unique spicy character with a distinctive aftertaste. Secondary fermentation in the bottle.","alcohol":6.8,"availability":null,"brewery":null,"label":null,"serving":null,"style":null},{"name":"Affligem Tripel","id":"AffligemTripel","img":"img/AffligemTripel.jpg","description":"The king of the abbey beers. It is amber-gold and pours with a deep head and original aroma, delivering a complex, full bodied flavour. Pure enjoyment! Secondary fermentation in the bottle.","alcohol":8.5,"availability":null,"brewery":null,"label":null,"serving":null,"style":null},{"name":"Rochefort 6","id":"TrappistesRochefort6","img":"img/TrappistesRochefort6.jpg","description":"Trappistes Rochefort 6 Belgian Beer.","alcohol":7.5,"availability":null,"brewery":null,"label":null,"serving":null,"style":null},{"name":"Rochefort 8","id":"TrappistesRochefort8","img":"img/TrappistesRochefort8.jpg","description":"A dry but rich flavoured beer with complex fruity and spicy flavours.","alcohol":9.2,"availability":null,"brewery":null,"label":null,"serving":null,"style":null},{"name":"Rochefort 10","id":"TrappistesRochefort10","img":"img/TrappistesRochefort10.jpg","description":"The top product from the Rochefort Trappist brewery. Dark colour, full and very impressive taste. Strong plum, raisin, and black currant palate, with ascending notes of vinousness and other complexities.","alcohol":11.3,"availability":null,"brewery":null,"label":null,"serving":null,"style":null},{"name":"St Bernardus Pater 6","id":"StBernardusPater6","img":"img/StBernardusPater6.jpg","description":"This name became a reference. This beer is mostly pointed out with its product name: a Paterke. This Paterke is a dark, chestnut coloured beer with a high fermentation (6.7%) and a full taste","alcohol":6.7,"availability":null,"brewery":null,"label":null,"serving":null,"style":null},{"name":"St Bernardus Tripel","id":"StBernardusTripel","img":"img/StBernardusTripel.jpg","description":"This beer, with high fermentation, has a pale amber colour and a flowery, fruity taste with a harmonious balance between sweet and sour. This beer has a thick and vivid froth and strikes its balanced taste with a delicate bitterness.","alcohol":8.0,"availability":null,"brewery":null,"label":null,"serving":null,"style":null},{"name":"St Bernardus Abt 12","id":"StBernardusAbt12","img":"img/StBernardusAbt12.jpg","description":"The top product from the Rochefort Trappist brewery. Dark colour, full and very impressive taste. Strong plum, raisin, and black currant palate, with ascending notes of vinousness and other complexities.","alcohol":10.5,"availability":null,"brewery":null,"label":null,"serving":null,"style":null},{"name":"Chimay Rouge","id":"ChimayRed","img":"img/ChimayRed.jpg","description":"This Trappist beer possesses a beautiful coppery colour that makes it particularly attractive. Topped with a creamy head, it gives off a slight fruity apricot smell from the fermentation. The aroma felt in the mouth is a balance confirming the fruit nuances revealed to the sense of smell. This traditional Belgian beer is best savoured at cellar temperature ","alcohol":7.0,"availability":null,"brewery":null,"label":null,"serving":null,"style":null},{"name":"Chimay Tripel","id":"ChimayTriple","img":"img/ChimayTriple.jpg","description":"Chimay Triple, with its typical golden colour, its slightly hazy appearance and its fine head is especially characterised by its aroma which results from an agreeable combination of fresh hops and yeast. The beers flavour, as sensed in the mouth, comes from the smell of hops: above all it is the fruity notes of muscat and raisins that give this beer a particularly attractive aroma. The aroma complements the touch of bitterness. There is no acidity, but an after-bitterness which melts in the mouth. This top fermented Trappist beer, refermented in the bottle, is not pasteurised.","alcohol":10.5,"availability":null,"brewery":null,"label":null,"serving":null,"style":null}]
```

In [next step](../step-04/) we are answering to the beer details requests by sending the JSON file corresponding to the required beer.