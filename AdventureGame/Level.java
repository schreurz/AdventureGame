package adventureGame;
import java.util.*;
public class Level extends Element {
    private ArrayList<Item> itemsInLevel = new ArrayList<Item>();
    private ArrayList<Character> charsInLevel = new ArrayList<Character>();
    private ArrayList<Key> useableKeys = new ArrayList<Key>();
    
    private boolean locked;
    private Level north;
    private Level south;
    private Level west;
    private Level east;

    public Level(ArrayList<Level> level_List, boolean lockedArg, String nameArg) {
    	level_List.add(this);
    	this.locked = lockedArg;
    	this.setName(nameArg);
    }
    
    public Level() {
    	super();
    }
    
    public void addItem(Item item) {
        itemsInLevel.add(item);
    }
    public void removeItem(Item item) {
        itemsInLevel.remove(item);
        //return charsInRoom;
    }
    public ArrayList<Item> getItems() {
        return itemsInLevel;
    }
    
    public void addCharacter(Character character) {
        charsInLevel.add(character);
    }
    public void removeCharacter(Character character) {
        charsInLevel.remove(character);
    }
    public ArrayList<Character> getCharacters() {
        return charsInLevel;
    }

    public void setNorth(Level lvl) {
        north = lvl;
    }
    public Level getNorth() {
        return north;
    }
    
    public void setSouth(Level lvl) {
        south = lvl;
    }
    public Level getSouth() {
        return south;
    }
    
    public void setWest(Level lvl) {
        west = lvl;
    }
    public Level getWest() {
        return west;
    }
    
    public void setEast(Level lvl) {
        lvl = east;
    }
    public Level getEast() {
        return east;
    }
    
    public boolean isLocked() {
        return locked;
    }
    public void setLocked(boolean l) {
        locked = l;
    }
    public void addKey(Key k) {
        useableKeys.add(k);
    }
    public ArrayList<Key> getKeys() {
        return useableKeys;
    }
}