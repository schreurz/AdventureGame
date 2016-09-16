package adventureGame;

import java.util.ArrayList;

public class NPC extends Character{
	private int health;
	private ArrayList<Item> itemList = new ArrayList<>();
	private Weapon equippedWeapon;
	
	public int getHealth() {
		return health;
	}
	public void setHealth(int h) {
		health = h;
	}
	
	public ArrayList<Item> getItems() {
		return itemList;
	}
	public void addItem(Item item) {
		itemList.add(item);
	}
	
	public Weapon getEquippedWeapon() {
		return equippedWeapon;
	}
	public void setEquippedWeapon(Weapon weapon) {
		equippedWeapon = weapon;
	}
	
	public void attack(PC target) {
		int cHealth = target.getHealth();
		int wDamage = equippedWeapon.getStrength();
		target.setHealth(cHealth - wDamage);
	}
	
	public NPC(ArrayList<Character> charList, int healthArg, int xpReturn, String name) {
		//TODO make something with xpReturn
		this.setName(name);
		charList.add(this);
		this.health = healthArg;
	}
	public NPC() {
		super();
	}
}
