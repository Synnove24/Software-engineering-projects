package edu.gac.mcs178.gack.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JPopupMenu;

import edu.gac.mcs178.gack.domain.Food;
import edu.gac.mcs178.gack.domain.Person;
import edu.gac.mcs178.gack.domain.Thing;

public class BeEatenActionListener implements ActionListener {
	
	private static final Thing INTSRUCTIONS = new Thing("Eat ...");
	
	private GraphicalUserInterface gui;
	private Person player;
	private JComboBox beEatenJComboBox;
	private boolean enabled;
	private List<Thing> things;
	private List<Food> foods;
	private List<Person> people;

	public BeEatenActionListener(GraphicalUserInterface gui, Person player, JComboBox beEatenJComboBox) {
		super();
		this.gui = gui;
		this.player = player;
		this.beEatenJComboBox = beEatenJComboBox;
		this.enabled = true;
		things = player.getPossessions();
		people = player.otherPeopleAtSamePlace();
		beEatenJComboBox.addItem(INTSRUCTIONS);
		if ((!things.isEmpty()) && (!people.isEmpty())) {
			for (Thing thing : things) {
				beEatenJComboBox.addItem(thing);
			}
		}
	}
	
	public void setEnabled(boolean b) {
		enabled = b;
	}
	
	public void updateJComboBox() {
		beEatenJComboBox.removeAllItems();
		things = player.getPossessions();
		people = player.otherPeopleAtSamePlace();
		beEatenJComboBox.addItem(INTSRUCTIONS);
		if ((!things.isEmpty()) && (!people.isEmpty())) {
			for (Thing thing : things) {
				beEatenJComboBox.addItem(thing);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (enabled) {
			Thing item = (Thing) beEatenJComboBox.getSelectedItem();
			if (!item.equals(INTSRUCTIONS)) {
				JPopupMenu popup = new JPopupMenu();
				for (Person person : people) {
					popup.add(new BeEatenAction("Eat " + item, player, item, person, gui));
				}
				popup.show(beEatenJComboBox, 0, 0);
			}
		}
	}
}

/**
 * A sample action that prints the action name to System.out
 */
@SuppressWarnings("serial")
class BeEatenAction extends AbstractAction  {
	
	private Person player;
	private Thing item;
	private Person recipient;
	private GraphicalUserInterface gui;
	private Food food;
	
	public BeEatenAction(String name, Person player, Thing item, Person recipient, GraphicalUserInterface gui) {
		super(name);
		this.player = player;
		this.item = item;
		this.recipient = recipient;
		this.gui = gui;
		this.food = food;
	}

	public void actionPerformed(ActionEvent event) {
		if (enabled) {
			if (food.getName() == "Chocolate") {
				player.eat(food);	
				gui.displayMessage(" I ate " + food);
			}
		}
		gui.playTurn();
	}
}