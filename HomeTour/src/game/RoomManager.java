package game;
import fixtures.Room;

//JUST CREATES THE ROOMS
public class RoomManager {
	public RoomManager() {
		//init();
	}
	public Room[] rooms = new Room[18];
	public Room startingRoom;
	public void init(){
		//String name, String shortDescription, String longDescription, String interactables1, String interactables2
		Room start = new Room("Den" , "You start in a small den.", "The den only one exit which is forward (North) you grab a torch off the wall (INTERACT)"+ 
		" to illuminate your way. darkness is the only thing beyond the exit", "Torch","\u001B[31m" + "You pick up the torch it illuminates your surrounding area" + "\u001B[0m");
		
		Room gateRoom = new Room("Gate Room" , "You entered a room with a gate.(interact)"
				," The gate is currently locked maybe there is something in this maze to unlock it.", "Gate","\u001B[31m" + "Looks like you need a key" + "\u001B[0m");
		
		Room keyRoom = new Room("Key Room" , "You walk into an empty room."," There appears to be something shiny in the room would you like to examine it.(interact)"
				, "Key", "\u001B[33m" + "There appears to be something shiny in the room its shaped like a key! Maybe it unlocks something." + "\u001B[0m");
		
		Room room2 = new Room("Room No.2" , "You step out into the first room ","This room is empty and has nothing in it but an occasional pebble", "Theres nothing here", "Nothing to do");
		
		Room room3 = new Room("Room No.3" , "Another Empty Room...","Theres nothing to do here you can go up (SOUTH), to the left (WEST), or to the right (EAST)"
				,"Theres nothing here", "Nothing to do");
		Room room4 = new Room("Room No.4" , "Empty again..!","Theres a faint glow to the WEST or you could go EAST and return to The first room", "Theres nothing here", "Nothing to do");
		
		Room room5 = new Room("Center " , "You are in a room that has a glowing rock in the center.","The glowing rock gives off a faint BLUE light."
				+ " It seems to be some kind of landmark.", "Rock","\u001B[34m" + "There is a faint blue glow eminating from the rock" + "\u001B[0m");
		
		Room room6 = new Room("Room No.6" , "An empty four way room.","Theres nothing here for you you can proceed in all directions.", "Theres nothing here", "Nothing to do");
		Room room7 = new Room("Room No.7" , "An empty room...","Theres a fainr glow coming from the EAST there are also exits to the SOUTH and the WEST", "Theres nothing here", "Nothing to do");
		
		Room room8 = new Room("Room No.8" , "You enter a room that is a dead end. ","There is a old piece of paper in the wall. Should you examine with it? (interact)",  "Paper","\u001B[35m" + "You use the torch to illuminate the paper theres a hint on where to find the key! "
				+ "It says 'South the green glow is where freedom is bestowed'." + "\u001B[0m");
		
		Room room9 = new Room("Room No.9" , "You entered a corner room","Theres nothing here to examine theres a faint glow to the SOUTH and an exit to the EAST", "Theres nothing here", "Nothing to do");
		Room room10 = new Room("Room No.10" , "Just another empty room","Sigh* another empty Room theres an exit to the EAST but you sense it wont amount to much.",  "Theres nothing here", "Nothing to do");
		
		Room room11 =  new Room("Left Four Way ", "You are in a room that has a glowing rock in the center.","The glowing rock gives off a faint GREEN light."
				+ " It seems to be some kind of landmark.",  "Rock","\u001B[32m" + "There is a faint green glow eminating from the rock" + "\u001B[0m");
		
		Room room12 = new Room("Room No.12"  , "You enter a room that is a dead end.","Theres nothing here for you. You should go WEST to the previous room"
				,  "Theres nothing here", "Nothing to do");
		Room room13 = new Room("Room No.13" , "Its just a dead end","Head EAST to return to your previous room theres nothing here for you",  "Theres nothing here", "Nothing to do");
		Room room14 = new Room("Room No.14" , "An empty room with promise","You feel your luck. Theres a faint glow coming from the NORTH and another exit to the EAST",  "Theres nothing here", "Nothing to do");
		
		Room room15 = new Room("Room No.15" , "You enter a room that is a dead end.","Theres nothing here for you. You should go backwards (SOUTH) to the previous room"
				,  "Theres nothing here", "Nothing to do");
		
		Room room16 = new Room("Room No.16" , "You entered an empty room","Theres nothing here for you. You can exit to the SOUTH or to the EAST where you came."
				,  "Theres nothing here", "Nothing to do");
		
		this.rooms[0] = start;
		rooms[1] = room2;
		rooms[2] = room3;
		rooms[3] = room4;
		rooms[4] = room5;
		rooms[5] = room6;
		rooms[6] = room7;
		rooms[7] = room8;
		rooms[8] = room9;
		rooms[9] = room10;
		rooms[10] = room11;
		rooms[11] = room12;
		rooms[12] = room13;
		rooms[13] = room14;
		rooms[14] = room15;
		rooms[15] = room16;
		rooms[16] = keyRoom;
		rooms[17] = gateRoom;
		//					N ^		S	  E>	  	W<
		rooms[0].setExits(room2, null, null, null);
		rooms[1].setExits(room5, start, room3, room4);
		rooms[2].setExits(room6, null, room10, room2);
		rooms[3].setExits(null, null, room2, room11);
		rooms[4].setExits(gateRoom, room2, room6, room7);
		rooms[5].setExits(room16, room3, room8, room5);
		rooms[6].setExits(room15, null, room5, room9);
		rooms[7].setExits(null, null, null, room6);
		rooms[8].setExits(null, room11, room7, null);
		rooms[9].setExits(null, null, room12, room3);
		rooms[10].setExits(room9, room14, room4, room13);
		rooms[11].setExits(null, null, null, room10);
		rooms[12].setExits(null, null, null, room11);
		rooms[13].setExits(room11, null, keyRoom, null);
		rooms[14].setExits(null, room7, null, null);
		rooms[15].setExits(null, room6, null, gateRoom);
		rooms[16].setExits(null, null, null, room14);
		rooms[17].setExits(null, room5, room16, null);
		
		startingRoom = rooms[0];
	}
	
}
