package org.lostinbrittany.cesi.springbeers.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="beers")
public class Beer {

	/*
	 * Each beer was defined by
	 * 
	 * { "alcohol": 6.8, "description":
	 * "Affligem Blonde, the classic clear blonde abbey ale, with a gentle roundness and 6.8% alcohol. Low on bitterness, it is eminently drinkable."
	 * , "id": "AffligemBlond", "img": "img/AffligemBlond.jpg", "name":
	 * "Affligem Blond" }
	 */
	
	private String name;
	@Id
	private String id;
	private String img;
	private String description;
	private double alcohol;

	private String availability;
	private String brewery;
	private String label;
	private String serving;
	private String style;


		
	public static List<Beer> getBeers() {
		ArrayList<Beer> list = new ArrayList<Beer>();

		Beer beer;

		beer = new Beer();
		beer.setAlcohol(6.8);
		beer.setDescription("Affligem Blonde, the classic clear blonde abbey ale, with a gentle roundness and 6.8% alcohol. Low on bitterness, it is eminently drinkable.");
		beer.setId("AffligemBlond");
		beer.setImg("img/AffligemBlond.jpg");
		beer.setName("Affligem Blond");
		list.add(beer);

		beer = new Beer();
		beer.setAlcohol(6.8);
		beer.setDescription("A reddish-brown abbey ale brewed with dark malts. The secondary fermentation gives a fruity aroma and a unique spicy character with a distinctive aftertaste. Secondary fermentation in the bottle.");
		beer.setId("AffligemDubbel");
		beer.setImg("img/AffligemDubbel.jpg");
		beer.setName("Affligem Dubbel");
		list.add(beer);

		beer = new Beer();
		beer.setAlcohol(8.5);
		beer.setDescription("The king of the abbey beers. It is amber-gold and pours with a deep head and original aroma, delivering a complex, full bodied flavour. Pure enjoyment! Secondary fermentation in the bottle.");
		beer.setId("AffligemTripel");
		beer.setImg("img/AffligemTripel.jpg");
		beer.setName("Affligem Tripel");
		list.add(beer);

		beer = new Beer();
		beer.setAlcohol(7.5);
		beer.setDescription("Trappistes Rochefort 6 Belgian Beer.");
		beer.setId("TrappistesRochefort6");
		beer.setImg("img/TrappistesRochefort6.jpg");
		beer.setName("Rochefort 6");
		list.add(beer);

		beer = new Beer();
		beer.setAlcohol(9.2);
		beer.setDescription("A dry but rich flavoured beer with complex fruity and spicy flavours.");
		beer.setId("TrappistesRochefort8");
		beer.setImg("img/TrappistesRochefort8.jpg");
		beer.setName("Rochefort 8");
		list.add(beer);

		beer = new Beer();
		beer.setAlcohol(11.3);
		beer.setDescription("The top product from the Rochefort Trappist brewery. Dark colour, full and very impressive taste. Strong plum, raisin, and black currant palate, with ascending notes of vinousness and other complexities.");
		beer.setId("TrappistesRochefort10");
		beer.setImg("img/TrappistesRochefort10.jpg");
		beer.setName("Rochefort 10");
		list.add(beer);

		beer = new Beer();
		beer.setAlcohol(6.7);
		beer.setDescription("This name became a reference. This beer is mostly pointed out with its product name: a Paterke. This Paterke is a dark, chestnut coloured beer with a high fermentation (6.7%) and a full taste");
		beer.setId("StBernardusPater6");
		beer.setImg("img/StBernardusPater6.jpg");
		beer.setName("St Bernardus Pater 6");
		list.add(beer);

		beer = new Beer();
		beer.setAlcohol(8.0);
		beer.setDescription("This beer, with high fermentation, has a pale amber colour and a flowery, fruity taste with a harmonious balance between sweet and sour. This beer has a thick and vivid froth and strikes its balanced taste with a delicate bitterness.");
		beer.setId("StBernardusTripel");
		beer.setImg("img/StBernardusTripel.jpg");
		beer.setName("St Bernardus Tripel");
		list.add(beer);

		beer = new Beer();
		beer.setAlcohol(10.5);
		beer.setDescription("The top product from the Rochefort Trappist brewery. Dark colour, full and very impressive taste. Strong plum, raisin, and black currant palate, with ascending notes of vinousness and other complexities.");
		beer.setId("StBernardusAbt12");
		beer.setImg("img/StBernardusAbt12.jpg");
		beer.setName("St Bernardus Abt 12");
		list.add(beer);

		beer = new Beer();
		beer.setAlcohol(7);
		beer.setDescription("This Trappist beer possesses a beautiful coppery colour that makes it particularly attractive. Topped with a creamy head, it gives off a slight fruity apricot smell from the fermentation. The aroma felt in the mouth is a balance confirming the fruit nuances revealed to the sense of smell. This traditional Belgian beer is best savoured at cellar temperature ");
		beer.setId("ChimayRed");
		beer.setImg("img/ChimayRed.jpg");
		beer.setName("Chimay Rouge");
		list.add(beer);

		beer = new Beer();
		beer.setAlcohol(10.5);
		beer.setDescription("Chimay Triple, with its typical golden colour, its slightly hazy appearance and its fine head is especially characterised by its aroma which results from an agreeable combination of fresh hops and yeast. The beers flavour, as sensed in the mouth, comes from the smell of hops: above all it is the fruity notes of muscat and raisins that give this beer a particularly attractive aroma. The aroma complements the touch of bitterness. There is no acidity, but an after-bitterness which melts in the mouth. This top fermented Trappist beer, refermented in the bottle, is not pasteurised.");
		beer.setId("ChimayTriple");
		beer.setImg("img/ChimayTriple.jpg");
		beer.setName("Chimay Tripel");
		list.add(beer);

		return list;
	}

	public static final double MAX_ALCOHOL = 20.0;
	public static final int MAX_NAME_LENGTH = 150;
	public static final int MAX_DESCRIPTION_LENGTH = 1500;
	public static final int MAX_PARAM_LENGTH = 300;

	private static String[] schemes = { "http", "https" };
	private static UrlValidator urlValidator = new UrlValidator(schemes);

	public static boolean isValidAlcoholParam(String param) {
		double val;
		try {
			val = Double.parseDouble(param);
		} catch (Exception ex) {
			return false;
		}
		if (val < 0 || val > MAX_ALCOHOL) {
			return false;
		}
		return true;
	}

	public static boolean isValidIdParam(String param) {
		if ((null == param) || (param.length() > MAX_NAME_LENGTH)
				|| (param.length() == 0)) {
			return false;
		}
		if (param.indexOf(' ') >= 0) {
			return false;
		}
		return true;
	}

	public static boolean isValidNameParam(String param) {
		if ((null == param) || (param.length() > MAX_NAME_LENGTH)
				|| (param.length() == 0)) {
			return false;
		}
		return true;
	}

	public static boolean isValidDescriptionParam(String param) {
		if ((null == param) || (param.length() > MAX_DESCRIPTION_LENGTH)
				|| (param.length() == 0)) {
			return false;
		}
		return true;
	}

	public static boolean isValidImgParam(String param) {
		if ((null == param) || (param.length() == 0)) {
			return false;
		}
		if (!urlValidator.isValid(param)) {
			return false;
		}
		return true;
	}
	
	public static String generateValidLabelParam(String param) {
		if ((null == param) || (param.length() == 0)) {
			return "";
		}
		if (!urlValidator.isValid(param)) {
			return "";
		}
		return param;
	}
	
	public static String generateValidGenericParam(String param) {
		if (null == param) {
			return "";
		}
		if (param.length() > MAX_PARAM_LENGTH) {
			return param.substring(0, 297)+"...";
		}
		return param;
	}
	/*  Getters and Setters */
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setId() {
		if (null != this.name && this.name.length() > 0) {
			this.id = StringUtils
					.capitalize(StringUtils.stripAccents(StringUtils.remove(this.name, " ")));
		}
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(double alcohol) {
		this.alcohol = alcohol;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getBrewery() {
		return brewery;
	}

	public void setBrewery(String brewery) {
		this.brewery = brewery;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getServing() {
		return serving;
	}

	public void setServing(String serving) {
		this.serving = serving;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
}
