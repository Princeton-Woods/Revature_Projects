package game;
import java.util.Scanner;

public class Main {
	static boolean gameLoop = true;
	public static RoomManager manage = new RoomManager();
	
	public static void main(String[] args) {
		manage.init();
		Player player = new Player(manage);
		Scanner scan = new Scanner(System.in);
		while(gameLoop) {
			PrintRoom(player, scan, manage);
		}
		scan.close();
		
	}
	public static void PrintRoom(Player player, Scanner scan, RoomManager manage){
			final String Text_Reset = "\u001B[0m";
			final String Text_Red = "\u001B[31m";
			final String Text_Green = "\u001B[32m";
			final String Text_Yellow = "\u001B[33m";
			final String Text_Purple = "\u001B[35m";
			final String Text_Cyan = "\u001B[36m";
			
			System.out.println("\n" + "You are currently in: "+ Text_Cyan + player.currentRoom.name + Text_Reset);
			System.out.println("\n" + player.currentRoom.GetShortDesc());
			System.out.println(player.currentRoom.GetLongDesc());
			//System.out.println(player.currentRoom.GetInteractText());
			System.out.println("\n" + Text_Green + "What will you do?: " + Text_Reset);
			parse(collectInput(scan), player, manage);
	}

	public static String[] collectInput(Scanner scan) {
		scan = new Scanner(System.in);
		String choice;
		String[] command = null;
		boolean validInput = false;
		
			//Input validation loop
			while(validInput != true ) {
				choice = scan.nextLine();
				String lowerCase;
				lowerCase = choice.toLowerCase();
				
				//For directions
				if(lowerCase.contains("left") || lowerCase.contains("right") || lowerCase.contains("up") || lowerCase.contains("down")     ) {
					command = lowerCase.split(" ");
					//System.out.println("Working");
					validInput = true;
				} //For directions
				else if (lowerCase.contains("west") || lowerCase.contains("east") || lowerCase.contains("north") || lowerCase.contains("south")   ) {
					command = lowerCase.split(" ");
					//System.out.println("Working");
					validInput = true;

				} //For Interactables 
				else if(lowerCase.contains("interact") || lowerCase.contains("examine")) {
					command = lowerCase.split(" ");
					//System.out.println("Working");
					validInput = true;

				} else if(lowerCase.contains("quit") || lowerCase.contains("exit") ){
					validInput = true;
					gameLoop = false;
					System.out.println("You have quit the game goodbye!");	
					command = lowerCase.split(" ");
					scan.close();
				}else {
					System.out.println("Cmon think is theres something else you can do!");
				}

			}
		return command;
	}
		
	public static void parse(String[] command, Player player, RoomManager manage) {
		for(int i = 0; i < command.length; i++) {
			if(command[i].contains("left") || command[i].contains("right") || command[i].contains("up") || command[i].contains("down")     ) {
				player.ChangeRoom(manage, command[i]);
				break;
			}
			else if (command[i].contains("west") || command[i].contains("east") || command[i].contains("north") || command[i].contains("south")   ) {
				player.ChangeRoom(manage, command[i]);

			} else if(command[i].contains("interact") || command[i].contains("examine")) { //For Interactables 
				System.out.println( player.GetInteractionText());
				if(player.currentRoom.Getinteractables().contains("Key")) {
					player.ObtainKey();
				} if(player.currentRoom.Getinteractables().contains("Gate") && (player.KeyStatus() == true)) {
					System.out.println("\u001B[33m" + "Congratz! You escaped the dungeon!" + "\u001B[0m");
					gameLoop = false;
				}
			} else {
				
			}
		}
	}
	
    
}

