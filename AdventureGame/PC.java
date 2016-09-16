package adventureGame;

import java.util.*;

// Class characters
// Will later make subclasses of it
class PC extends Character {
    private ArrayList<Item> inventory = new ArrayList<Item>();
    private int experience;
    private int level;
    private int carryWeight; // total possible carrying weight
    private int health;
    private Weapon equippedWeapon;
    
    public int getHealth() {
    	return health;
    }
    public void setHealth(int h) {
    	health = h;
    }
    
    public int getExperience() {
        return experience;
    }
    public void setExperience(int xp) {
        experience = xp;
    }
    
    public void setLevel() {
    	int xpInt = (experience/10);
    	switch (xpInt) {
    	case (0) : level = 1;
    	break;
    	case (1) : level = 2;
    	break;
    	case (2) : level = 3;
    	break;
    	default : break;
    	}
    }
    public int getLevel() {
        return level;
    }
    
    public int getCarryWeight() {
        return carryWeight;
    }
    public void setCarryWeight(int weight) {
        carryWeight = weight;
    }
    
    public ArrayList<Item> getInventory() {
        return inventory;
    }
    public void setInventory(Item item) {
        inventory.add(item);
    }
    public int getInventoryWeight() {
        int totalWeight = 0;
        int weight;
        if (inventory != null) {
            for (Item item : inventory) {
                weight = item.getWeight();
                totalWeight = (totalWeight + weight);
            }
        }
        return totalWeight;
    }
        
    // method for picking up items and placing them in your inventory
    public void pickUp(Item item, Level lvl) {
    	try {
    		if (lvl.getItems().contains(item)) {
	    		inventory.add(item);
	    		lvl.removeItem(item);
	    		System.out.println("You picked up a " + item.getName());
    		} else {
    			System.out.println("You didn't pick up anything");
    		}
    	} catch (NullPointerException e) {
    		System.out.println("You didn't pick up anything");
    	}
    }
    
    // method for moving your character
    public void move(String direction, ArrayList<Level> listOfLevels, PC character) {
        Level nextLevel;
        Level currentLevel = currentLevel(listOfLevels, character);
        nextLevel = currentLevel;
        
        try {
			switch(direction) {
			case "north" :
			    nextLevel = currentLevel.getNorth();
			    break;
			case "south" : 
			    nextLevel = currentLevel.getSouth();
			    break;
			case "west" :
			    nextLevel = currentLevel.getWest();
			    break;
			case "east" :
			    nextLevel = currentLevel.getEast();
			    break;
			default :
			    System.out.println("Illegal direction");
			    break;
			}
		} catch (NullPointerException e) {
			nextLevel = null;
		}
	    if (nextLevel != null) {
	        if (!nextLevel.isLocked()) {
	          nextLevel.addCharacter(character);
	          currentLevel.removeCharacter(character);
	          System.out.println("You are now in " + nextLevel.getName());
	      } else {
	          System.out.println("The room is locked");
	      }
	    } else {
	    	System.out.println("There is no room there");
	    }
    }
    
    // method for dropping an Item in you inventory
    public void dropItem(Item item, Level lvl) {
        try {
        	if (inventory.contains(item)) {
				lvl.addItem(item);
				inventory.remove(item);
				System.out.println(item.getName() + " was removed from you inventory");
        	} else {
        		System.out.println("You don't have that");
        	}
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			System.out.println("You didn't drop anything");
		}
    }
    
    // method for looking in level
    public void look(Level currentLevel) {
        System.out.println("You are currenty in " + currentLevel.getName());
        System.out.println(currentLevel.getName() + " contains: ");
        ArrayList<Item> lvlItems = new ArrayList<Item>();
        lvlItems = currentLevel.getItems();
        for (Item item : lvlItems) {
            System.out.print(item.getName() + "\n");
        }
        if (lvlItems.isEmpty()) {
        	System.out.println("nothing");
        }
        System.out.print("Surrounding levels:");
        if (currentLevel.getSouth() != null) {
            System.out.print(" south");
        } if (currentLevel.getNorth() != null) {
            System.out.print(" north");
        } if (currentLevel.getWest() != null) {
            System.out.print(" west");
        } if (currentLevel.getEast() != null) {
            System.out.print(" east");
        }
        System.out.println("Your current health is " + this.getHealth());
        System.out.println();
    }
    
    // method for printing out inventory
    public void printInventory() {
    	ArrayList<Item> myItems = new ArrayList<Item>();
    	myItems = getInventory();
    	System.out.println("You currently have:");
    	try {
			for (int i = 0; i < myItems.size(); i++) {
				System.out.println(myItems.get(i).getName());
			}
		} catch (NullPointerException e) {
			System.out.println("nothing");
		}
    }
    
    // method for unlocking levels
    public boolean unlockLevel(Level nextLevelArg, Key keyArg) {
        boolean locked;
        locked = nextLevelArg.isLocked();
        ArrayList<Key> useableKeys = new ArrayList<>();
        useableKeys = nextLevelArg.getKeys();
        if (useableKeys.contains(keyArg)) {
            locked = false;
            inventory.remove(keyArg);
            System.out.println(nextLevelArg.getName() + " is unlocked");
        } else if (!nextLevelArg.isLocked()){
        	System.out.println("Already unlocked");
        }
        return locked;
    }
    
    // Tells user what level they are in
    public Level currentLevel(ArrayList<Level> levelListArg, PC characterArg) {
        Level currentLevel = null;
        for (Level levelArg : levelListArg) {
            if ((levelArg.getCharacters()).contains(characterArg)) {
                currentLevel = levelArg;
            }
        }
        return currentLevel;
    }
   
    public Weapon getEquippedWeapon() {
    	return equippedWeapon;
    }
    public void attackEnemy(NPC enemy) {
    	int eHealth = enemy.getHealth();
    	int wDamage = equippedWeapon.getStrength();
    	enemy.setHealth(eHealth - wDamage);
    }
    public void equipWeapon(Weapon w) {
    	equippedWeapon = w;
    }
}
