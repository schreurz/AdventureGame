package adventureGame;

import java.util.ArrayList;

public class Weapon extends Item {
    private int weaponStrength;
    
    public Weapon() {
    	super();
    }
    
    public Weapon(ArrayList<Item> itemList, int strengthArg, int reqLevel, int weight, String nameArg) {
    	super(itemList, weight, reqLevel);
    	this.weaponStrength = strengthArg;
    }
    public int getStrength() {
        return weaponStrength;
    }
    public void setStrength(int s) {
        weaponStrength = s;
    }
}