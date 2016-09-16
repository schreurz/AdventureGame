package adventureGame;
import java.util.ArrayList;
public class Consumable extends Item {
    private int healthReturn;
    private boolean poisoned;
    private boolean rotten;
    
    public Consumable(ArrayList<Item> itemList, int h, String n) {
    	super(itemList, 1, 1);
    	this.healthReturn = h;
    	this.setName(n);
    }
    
    public Consumable() {
    	super();
    }
    
    public int getHealthReturn() {
        return healthReturn;
    }
    public void setHealthReturn(int health) {
        healthReturn = health;
    }
    public boolean getPoisoned() {
        return poisoned;
    }
    public void setPoisoned(boolean p) {
        poisoned = p;
    }
    public boolean getRotten() {
        return rotten;
    }
    public void setRotten(boolean r) {
        rotten = r;
    }
}