package fixtures;

public class Room extends Fixture {
	
	Room[] exits;
	
	public Room(String name, String shortDescription, String longDescription, String interactables, String interactablesText) {
		
		super(name, shortDescription, longDescription, interactables, interactablesText);
		this.exits = new Room[4];
	}
	public String GetName() {
		return this.name;
	}
	public String GetShortDesc() {
		return this.shortDescription;
	}
	public String GetLongDesc() {
		return this.longDescription;
	}
	//public String GetInteract() {
		//return this.interactables;
	//}
	public String GetInteractText() {
		return this.interactablesText;
	}
	public Room[] getExits() {
		for( int i = 0; i < exits.length; i++) {
			if(exits[i] != null) {
				//REMOVE
			}
		}
		return exits;
	}
	public void setExits(Room north, Room south, Room east, Room west) {
		exits[0] = north;
		exits[1] = south;
		exits[2] = east;
		exits[3] = west;
	}
	public Room getExit(String direction) {
		if(direction.equals("north") || direction.equals("up")) {
			if(exits[0] == null) {
				System.out.println("Theres nothing here but wall!");
				return exits[0];
			}
			return exits[0];
		}else if(direction.equals("south") || direction.equals("down")) {
			if(exits[1] == null) {
				System.out.println("Theres nothing here but wall!");
				return exits[1];
			}
			return exits[1];

		}else if(direction.equals("east") || direction.equals("right")) {
			if(exits[2] == null) {
				System.out.println("Theres nothing here but wall!");
				return exits[2];
			}
			return exits[2];

		} else {
			if(exits[3] == null) {
				System.out.println("Theres nothing here but wall!");
				return exits[3];
			}
			return exits[3];

		}
	}
	
}
