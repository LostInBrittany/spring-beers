package org.lostinbrittany.cesi.springbeers.model;

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
	
	@Id
	private String id;
	private String name;	
	private String img;
	private String description;
	private double alcohol;

	private String availability;
	private String brewery;
	private String label;
	private String serving;
	private String style;



	public static final double MAX_ALCOHOL = 20.0;
	public static final int MAX_NAME_LENGTH = 150;
	public static final int MAX_DESCRIPTION_LENGTH = 1500;
	public static final int MAX_PARAM_LENGTH = 300;


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
		if (alcohol > MAX_ALCOHOL) {
			alcohol = MAX_ALCOHOL;
		}
		if (alcohol < 0)  {
			alcohol = 0;
		}
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
