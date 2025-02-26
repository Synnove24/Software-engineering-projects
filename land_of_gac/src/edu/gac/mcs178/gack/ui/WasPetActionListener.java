package edu.gac.mcs178.gack.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import edu.gac.mcs178.gack.domain.Cat;
import edu.gac.mcs178.gack.domain.Person;
import edu.gac.mcs178.gack.domain.Thing;

public class WasPetActionListener implements ActionListener {
    
    private static final Thing INSTRUCTIONS = new Thing("Pet Cat! ...");

    private GraphicalUserInterface gui;
    private Person player;
    private JComboBox wasPetJComboBox;
    private boolean enabled;
    private List<Thing> things;
    private List<Cat> cats;

    public WasPetActionListener(GraphicalUserInterface gui, Person player, JComboBox wasPetJComboBox) {
        super();
        this.gui = gui;
        this.player = player;
        this.wasPetJComboBox = wasPetJComboBox;
        this.enabled = true;

        this.things = player.otherThingsAtSamePlace(); 
        this.cats = getCatsAtSamePlace(); 
        
        wasPetJComboBox.addItem(INSTRUCTIONS);
        for (Thing thing : things) {
            wasPetJComboBox.addItem(thing);
        }
    }

    public void setEnabled(boolean b) {
        enabled = b;
    }

    public void updateJComboBox() {
        wasPetJComboBox.removeAllItems();
        cats = getCatsAtSamePlace(); 
        
        wasPetJComboBox.addItem(INSTRUCTIONS);
        for (Cat cat : cats) {
            wasPetJComboBox.addItem(cat);
        }
    }

    private List<Cat> getCatsAtSamePlace() {
        List<Cat> catList = new ArrayList<>();
        for (Person person : player.otherPeopleAtSamePlace()) {
            if (person instanceof Cat) {
                catList.add((Cat) person); 
            }
        }
        return catList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (enabled) {
            Cat cat = (Cat) wasPetJComboBox.getSelectedItem();
            if (!cat.equals(INSTRUCTIONS)) {
                gui.displayMessage("\n>>> Pet the cat");
                player.pet();
                gui.playTurn();
            }
        }
    }
}