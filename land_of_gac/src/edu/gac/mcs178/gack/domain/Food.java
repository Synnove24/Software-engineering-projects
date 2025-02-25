package edu.gac.mcs178.gack.domain;

import java.util.ArrayList;
import java.util.List;

import edu.gac.mcs178.gack.Utility;

public class Food {
	
	private String name;
	private Person owner;
	private Place place;
	private List<Thing> possessions;
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public Person getOwner() { return owner; }
	public void setOwner(Person owner) { this.owner = owner; }
	public Place getPlace() { return place; }
	public List<Thing> getPossessions() { return possessions; }
	

	public Food(String name) {
		super();
		this.name = name;
		this.place = place;
		this.possessions = new ArrayList<Thing>();
		place.gain(this);
	}
	
	public void say(String text) {
		Utility.displayMessage("At " + place + ": " + this + " says -- " + text);
	}
	
	public boolean isOwned() {
		return owner != null;
	}
	
	public void becomeUnowned() {
		owner = null;
	}
	
	public void beEaten(Food food) {
		if (!equals(food.getOwner())) {
			Utility.displayMessage(this + " doesn't have " + food);
		} else {
			food.becomeUnowned();
			possessions.remove(food);
			say("I eat " + food + " Yummm");
			place.lose(food);
		}
	}
	
	@Override
	public String toString() {
		return name;
	}
}