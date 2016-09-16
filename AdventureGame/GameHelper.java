package adventureGame;
import java.util.*;

public class GameHelper {
    Scanner in = new Scanner(System.in);
    private String input;
    private String commands = "move_pick up_drop_drop item_look_unlock_attack_help_quit_inventory_";
    private String[] commandsList;
    private String[] directionList;
    private String directions = "north_south_east_west";
    private boolean sysQuit;
    private String command;
    private Item target;
    private String direction;
    private NPC enemy;
    
    public String[] getCommands() {
        commandsList = commands.split("_");
        return commandsList;
    }
    public void setDirections() {
    	directionList = directions.split("_");
    }
    public void addCommands(String cmnd) {
        commands.concat(cmnd);
        System.out.println(commands);
    }

    public String getInput() {
        return input.toLowerCase();
    }
    
    public void promptInput(String prompt) {
        System.out.println(prompt);
        input = in.nextLine();
    }
    public boolean getQuit() {
            return sysQuit;
    }
    
    public String[] getDirectionList() {
    	return directionList;
    }
    
    public void playGame(PC character, ArrayList<Level> levels, ArrayList<Item> list_of_items, ArrayList<NPC> npcList) {
    	System.out.println("Type \"help\" for a list of commands");
    	boolean quit = getQuit();
        assert(quit == false) : "Quit is true";
        while(!quit) {
            promptInput("Type a command");
            input = getInput();
            setCommand();
            setDirections();
            command = getCommand();
            setTarget(list_of_items, npcList);
            if (target != null) {
            	target = getTargetItem();
            } else {
            	direction = getTargetDirection();
            }
            doCommand(getCommands(), character, levels);
            quit = getQuit();
        }
    }
    
    public String getCommand() {
    	return command;
    }
    public void setCommand() {
    	String[] cmdList = getCommands();
    	for (String cmd : cmdList) {
    		if (input.startsWith(cmd)) {
    			command = cmd;
    			break;
    		} else {
    			command = null;
    		}
    	}
    }
    
    public Item getTargetItem() {
    	return target;
    }
    public String getTargetDirection() {
    	return direction;
    }
    public NPC getTargetNPC() {
    	return enemy;
    }
    
    public void setTarget(ArrayList<Item> item_list, ArrayList<NPC> npcListArg) {
    	for (Item item : item_list) {
    		String item_name = item.getName().toLowerCase();
    		if (input.endsWith(item_name)) {
    			target = item;
    			break;
    		//} else {
    		} else {
    			target = null;
    		}
    	} if (target == null) {
    		for (String dir : directionList) {
				if (input.endsWith(dir)) {
					direction = dir;
					break;
				} else {
					for (NPC npc_arg : npcListArg) {
		    			if (input.endsWith(npc_arg.getName().toLowerCase())) {
		    				enemy = npc_arg;
		    				break;
		    			} else {
		    				direction = null;
		    				target = null;
		    				enemy = null;
		    			}
		    		}
    			}
    		}
    	}
    }
    
    public void doCommand(String[] cmdList, PC character, ArrayList<Level> levelList) {
    	// ToDo solve for picking up items that are not in the level
        ArrayList<Item> charInventory = new ArrayList<>();
        charInventory = character.getInventory();
        Level currentLevel = character.currentLevel(levelList, character);
        Level nextLevel = null;
        Key key = null;
        ArrayList<Key> keys = new ArrayList<>();
        
        if (!((getCommand() == null) && ((getTargetDirection() == null) || (getTargetItem() == null) || (getTargetNPC() == null)))) {
            
            switch (command) {
                case "move" :
                    if (levelList == null) {
                    	System.out.println("You have no where to go");
                    } else {
                        character.move(direction, levelList, character);
                        break;
                    }
                case "pick up" :
                    character.pickUp(target, currentLevel);
                    break;
                case "drop" :
                    character.dropItem(target, currentLevel);
                    break;
                case "drop item" :
                    character.dropItem(target, currentLevel);
                    break;
                case "look" :
                    character.look(currentLevel);
                    break;
                case "unlock" :
                	if (direction != null){
	                	try {
							switch (direction) {
							    case "north" :
							        nextLevel = currentLevel.getNorth();
							        
							        keys = nextLevel.getKeys();
							        for (Key k : keys) {
							            if (charInventory.contains(k)) {
							                key = k;
							            } else if (!nextLevel.isLocked()){
							            	break;
							            } else {
							            	System.out.println("You need the correct key");
							            }
							        }
							        nextLevel.setLocked(character.unlockLevel(nextLevel, key));
							        break;
							    case "south" :
							        nextLevel = currentLevel.getSouth();
							        keys = nextLevel.getKeys();
							        for (Key k : keys) {
							            if (charInventory.contains(k)) {
							                key = k;
							            } else if (!nextLevel.isLocked()){
							            	break;
							            } else {
							            	System.out.println("You need the correct key");
							            }
							        }
							        nextLevel.setLocked(character.unlockLevel(nextLevel, key));
							        break;
							    case "west" :
							        nextLevel = currentLevel.getWest();
							        keys = nextLevel.getKeys();
							        for (Key k : keys) {
							            if (charInventory.contains(k)) {
							                key = k;
							            } else if (!nextLevel.isLocked()){
							            	break;
							            } else {
							            	System.out.println("You need the correct key");
							            }
							        }
							        nextLevel.setLocked(character.unlockLevel(nextLevel, key));
							        break;
							    case "east" :
							        nextLevel = currentLevel.getEast();
							        keys = nextLevel.getKeys();
							        for (Key k : keys) {
							            if (charInventory.contains(k)) {
							                key = k;
							            } else if (!nextLevel.isLocked()){
							            	break;
							            } else {
							            	System.out.println("You need the correct key");
							            }
							        }
							        nextLevel.setLocked(character.unlockLevel(nextLevel, key));
							        break;
							    default :
							        System.out.println("Invalid direction");
							}
						} catch (NullPointerException e) {
							System.out.println("There is no room there");
						}
                	} else {
                		System.out.println("You need a direction");
                	}
                    break;
                case "attack" :
                	character.attackEnemy(enemy);
                	enemy.attack(character);
                    break;
                case "help" :
                    System.out.println("Commands:\nmove (direction)\npick up (item) \ndrop (item) \nlook \nunlock (direction) \nattack (enemy) \ninventory \nhelp \nquit");                    
                    break;
                case "quit" :
                    sysQuit = true;
                    break;
                case "inventory" :
                    character.printInventory();
                    break;
                case "equip" :
                	if (target.getClass().equals(Weapon.class)) {
                		character.equipWeapon((Weapon) target);
                	}
                default :
                    break;
           }
        } else {
            System.out.println("Invalid command");
        }
    }
}
