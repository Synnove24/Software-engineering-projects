package edu.gac.mcs178.gack.domain;

import java.util.ArrayList;
import java.util.List;

import edu.gac.mcs178.gack.Utility;

public class Food extends Thing {
	
	public Food(String food) {
		super(food);
	}

	
	//be eaten function allows food to beEaten by the owner
	public void beEaten() {
		Person owner = getOwner();
		if (owner == null) {
			Utility.displayMessage(this + " doesn't have " + getName());
		} 
		else {
			owner.say("I eat " + getName() + " Yummm");
		}
	}
	
	public static List<Food> foodsIn(Place place) {
		ArrayList<Food> foodsIn = new ArrayList<Food>();
		for (Thing thing : place.getContents()) {
			if (thing instanceof Food) {
				foodsIn.add((Food) thing);
			}
		}
		return foodsIn;
	}
}
	

	