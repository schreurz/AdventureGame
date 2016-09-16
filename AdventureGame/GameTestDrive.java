package adventureGame;
import java.util.*;

public class GameTestDrive {
    public static void main(String[] args) {
        Level level1 = new Level();
        Level level2 = new Level();
        Level level3 = new Level();
        Level window = new Level();
        Key key1 = new Key();
        Key key2 = new Key();
        Consumable food = new Consumable();
        Weapon sword = new Weapon();
        Weapon wings = new Weapon();
        PC zack = new PC();
        ArrayList<Item> itemList = new ArrayList<>();
        ArrayList<PC> charList = new ArrayList<>();
        ArrayList<Level> levelList = new ArrayList<>();
        ArrayList<NPC> npc_list = new ArrayList<>();
        GameHelper helper = new GameHelper();
        NPC enemy = new NPC();
        
        level1.setName("World");
        level1.setNorth(null);
        level1.setSouth(level2);
        level1.setWest(null);
        level1.setEast(null);
        level1.setLocked(false);
        level1.addCharacter(zack);
        level1.addItem(food);
        level1.addItem(key1);
        
        level2.setName("Room");
        level2.setNorth(level1);
        level2.setSouth(null);
        level2.setWest(level3);
        level2.setEast(null);
        level2.setLocked(true);
        level2.addKey(key1);
        level2.addItem(sword);
        level2.addItem(key2);
        level2.addCharacter(enemy);
        
        level3.setName("Hell");
        level3.setNorth(null);
        level3.setSouth(null);
        level3.setEast(level2);
        level3.setWest(window);
        level3.setLocked(true);
        level3.addKey(key2);
        level3.addItem(wings);
        
        window.setName("A window you should jump out of");
        window.setEast(level3);
        window.setNorth(null);
        window.setSouth(null);
        window.setWest(null);
        
        key1.setName("key");
        key1.setWeight(1);
        key1.setReqLevel(1);
        
        key2.setName("herm");
        key2.setWeight(1);
        key2.setReqLevel(1);
        
        food.setName("Bread");
        food.setWeight(1);
        food.setReqLevel(1);
        food.setHealthReturn(20);
        food.setRotten(false);
        food.setPoisoned(false);
        
        sword.setName("sword");
        sword.setWeight(10);
        sword.setStrength(50);
        sword.setReqLevel(1);
        
        wings.setName("Lead Wings");
        wings.setWeight(1);
        wings.setReqLevel(1);
        wings.setStrength(0);
        
        zack.setName("Zack");
        zack.setExperience(1);
        zack.setLevel();
        zack.setCarryWeight(20);
        zack.setHealth(100);
        
        enemy.setEquippedWeapon(sword);
        enemy.setName("Enemy");
        
        levelList.add(level1);
        levelList.add(level2);
        levelList.add(level3);
        
        charList.add(zack);
        
        itemList.add(key1);
        itemList.add(food);
        itemList.add(sword);
        itemList.add(key2);
        
        npc_list.add(enemy);
        
        System.out.println(zack.getHealth());
        enemy.attack(zack);
        System.out.println(zack.getHealth());
        
        helper.playGame(zack, levelList, itemList, npc_list);
    }
}