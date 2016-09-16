package adventureGame;
import java.util.ArrayList;
abstract public class Item extends Element{
    private int weight;
    private int requiredLevel;
    
    public Item(ArrayList<Item> itemList, int weightArg, int levelArg) {
    	itemList.add(this);
    	this.weight = weightArg;
    	this.requiredLevel = levelArg;
    }
    
    public Item() {
    	super();
    }
    
    public int getWeight() {
        return weight;
    }
    public void setWeight(int w) {
        weight = w;
    }
    public int getReqLevel() {
        return requiredLevel;
    }
    public void setReqLevel(int lvl) {
        requiredLevel = lvl;
    }
} 