package edu.gac.mcs178.gack.domain;

import java.util.List;
import java.util.ArrayList;
import java.util.List;

import edu.gac.mcs178.gack.Utility;

public class Cat extends AutoPerson {
	
	private Place lounge;
	
	public Cat(String name, Place place, int threshold) {
		super(name, place, threshold);
		
	}
	
	
	
	public void wasPet(Person person) {
		say("Meow!! ");
	}
	
	
	
	// make a catsIn function like the one in Food
	
	public static List<Cat> catsIn(Place place) {
	    List<Cat> catsIn = new ArrayList<>();
	    
	    
	    for (Person person : place.getOccupants()) {
	        if (person instanceof Cat) { // Check if it's a Cat
	            catsIn.add((Cat) person);
	        }
	    }
	    return catsIn;
	}
	
	
	
	
	
	public static void main(String[] args) {
		
	}
}
