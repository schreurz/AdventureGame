package adventureGame;
import java.util.*;
public class Key extends Item {
	public Key(ArrayList<Item> itemListArg, int weightArg, int levelArg, String nameArg) {
		super(itemListArg, weightArg, levelArg);
		this.setName(nameArg);
	}
	public Key() {
		super();
	}
}