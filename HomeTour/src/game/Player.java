package game;
import fixtures.Room;
//JUST CONTAINS THE PLACE IN THE ARRAY OF ROOMS
public class Player {
	public Room currentRoom;
	private boolean key;

	public Player(RoomManager manage) {
		key = false;
		currentRoom = manage.startingRoom;
	}
	public void ChangeRoom(RoomManager manage, String exit) {
		//rooms[1];
		if(currentRoom.getExit(exit) != null) {
			currentRoom = currentRoom.getExit(exit);
		} else if(currentRoom.getExit(exit) == manage.rooms[16]) {
			ObtainKey();
		}
		else {
			
		}

	}
	public String GetRoomName() {
		return currentRoom.name;
	}
	public String GetInteractionText() {
		
		return currentRoom.GetinteractablesText();
	}
	public void ObtainKey() {
		key = true;
	}
	public boolean KeyStatus() {
		return key;
	}
}
