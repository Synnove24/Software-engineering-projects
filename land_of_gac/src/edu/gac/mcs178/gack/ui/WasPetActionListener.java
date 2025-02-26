package edu.gac.mcs178.gack.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;

import edu.gac.mcs178.gack.domain.Cat;
import edu.gac.mcs178.gack.domain.Food;
import edu.gac.mcs178.gack.domain.Person;
import edu.gac.mcs178.gack.domain.Thing;

public class WasPetActionListener implements ActionListener {
	
	private static final Thing INTSRUCTIONS = new Thing("Pet Cat! ...");
	
	private GraphicalUserInterface gui;
	private Person player;
	private JComboBox wasPetJComboBox;
	private boolean enabled;
	private List<Thing> things;

	public WasPetActionListener(GraphicalUserInterface gui, Person player, JComboBox wasPetJComboBox) {
		super();
		this.gui = gui;
		this.player = player;
		this.wasPetJComboBox = wasPetJComboBox;
		this.enabled = true;
		cats = Cat.catsIn(player.getPlace()); // fix this issue
		wasPetJComboBox.addItem(INTSRUCTIONS);
		for (Thing thing : things) {
			wasPetJComboBox.addItem(thing);
		}
	}
	
	public void setEnabled(boolean b) {
		enabled = b;
	}
	
	public void updateJComboBox() {
		wasPetJComboBox.removeAllItems();
		cats = Cat.otherPeopleAtSamePlace(); // fix this issue
		wasPetJComboBox.addItem(INTSRUCTIONS);
		for (Cat cat : cats) {
			wasPetJComboBox.addItem(cat);
		}
	}
	
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (enabled) {
			Thing item = (Thing) wasPetJComboBox.getSelectedItem();
			if (!item.equals(INTSRUCTIONS)) {
				gui.displayMessage("\n>>> Pet the cat");
				player.pet();
				gui.playTurn();
			}
		}
	}
}
