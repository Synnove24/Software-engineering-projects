package edu.gac.mcs178.gack.domain;

import java.util.List;

import edu.gac.mcs178.gack.Utility;

public class Cat extends AutoPerson {
	
	private Place lounge;
	
	public Cat(String name, Place place, int threshold) {
		super(name, place, threshold);
		//this.lounge = lounge;
	}
	
	
	
	public void wasPet(Person person) {
		say("Meow!! ");
	}
	
	// make a catsIn function like the one in Food
	
	
	public static void main(String[] args) {
		
	}
}
