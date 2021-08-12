package com.Princeton;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
 

public class Main {
	public static List<User> users = new ArrayList<User>();

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

        UserDAO dao =  UserDAOFactory.getUserDao();
        
		//Variables
		int menuSelection = 0;
		Scanner scan = new Scanner(System.in);
		String choice;
		boolean validInput;
			
		//Console Colors Just for effect cause why not 
		final String Text_Reset = "\u001B[0m";
		final String Text_Red = "\u001B[31m";
		final String Text_Green = "\u001B[32m";
		final String Text_Yellow = "\u001B[33m";
		final String Text_Purple = "\033[0;95m";
		final String Text_Cyan = "\u001B[36m";
			
		while(menuSelection != 7) {
			//Menu variables resets every loop until Quit
			menuSelection = 0;
			choice = null;
			validInput = false;
			scan = new Scanner(System.in);
				
			System.out.println(Text_Reset + Text_Yellow + "Please select an option: " + Text_Reset);
			System.out.println(Text_Purple +"	1. Add User " + Text_Reset);  	
			System.out.println(Text_Purple +"	2. Edit User" + Text_Reset);  
			System.out.println(Text_Purple +"	3. Reject Account" + Text_Reset); 
			System.out.println(Text_Purple +"	4. Get User"+ Text_Reset);	
			System.out.println(Text_Purple +"	5. Get User By ID"+ Text_Reset);	
			System.out.println(Text_Purple +"	6. Get Logs"+ Text_Reset);
			System.out.println(Text_Red +"	7. Quit" + Text_Reset);				   //Quit
				
			System.out.println(Text_Green + "What will you choose: " + Text_Reset);//Selection Text
			
			//Menu input validation loop
			while(validInput != true ) {
				choice = scan.nextLine();
				if(isNumeric(choice)) {
					menuSelection = Integer.parseInt(choice);
					if(menuSelection < 1 || menuSelection > 7) {
						System.out.println(Text_Red + "Invalid Selection Try again: " + Text_Reset);
						menuSelection = 0;
					} else {	validInput = true;	}
				} else {	
					System.out.println(Text_Red + "Input is not a number try again. " + Text_Reset);	
				}
			}
			
			int id;
			String name;
			String email;
			String password;
			double balance;
			
			//Switch case based on input
	        switch(menuSelection) {
        	case 1:
        		System.out.println("Please enter the users name: ");
        		name = scan.nextLine();
        		System.out.println("Please enter the users email: ");
        		email = scan.nextLine();
        		System.out.println("Please enter the users password: ");
        		password = scan.nextLine();
        		System.out.println("Please enter the users balance: ");
        		balance = inputCertification(scan);
        		dao.addUser(new User(name,email,password,balance));
        		//users.add(new User(name,email,password,balance));
        		System.out.println();
        		break;
        		
        	case 2:
        		System.out.println("Please enter the users ID: ");
        		id = (int)inputCertification(scan);
        		System.out.println("Please enter the users name: ");
        		name = scan.nextLine();
        		System.out.println("Please enter the users email: ");
        		email = scan.nextLine();
        		System.out.println("Please enter the users password: ");	
        		password = scan.nextLine();
        		System.out.println("Please enter the users balance: ");	
        		balance = inputCertification(scan);
        		User temp = new User(id,name,email,password,balance);
        		dao.updateUser(temp);

        		System.out.println();
        		break;
        		
        	case 3:
        		System.out.println("\nPlease enter the users ID: ");
        		id = (int)inputCertification(scan);
        		dao.deleteUser(id); ;
        		System.out.println();
        		break;
        		
        	case 4: 
        		//System.out.println("Get");
        		users = dao.getUsers();
        		for(User u : users) {
        			System.out.println("\nUser ID: " + u.getId() +"\nUser Email: " + u.getEmail()+ "\nUser Password: " 
        					+ u.getPword() + "\nUser Balance: " + u.getBalance()+ "\nUser Name: " + u.getName() + "\n");
        		}
        		System.out.println();
        		break;
        		
        	case 5: 
        		System.out.println("Enter the ID of the user: ");
        		id = (int)inputCertification(scan);
        		User tempID = (dao.userById(id));
        		if(tempID != null) {
        			System.out.println("\nUser ID: " + tempID.getId() +"\nUser Email: " + tempID.getEmail()+ "\nUser Password: " 
					+ tempID.getPword() + "\nUser Balance: " + tempID.getBalance()+ "\nUser Name: " + tempID.getName());
        		}
        		else {
        			System.out.println("A User by this ID does not exist in the database");
        		}
        		System.out.println();
        		break;
        	case 6:
        		dao.GetHistory();
        		System.out.println();
        	case 7:
        		System.out.println(Text_Cyan + "You have quit. Goodbye!" + Text_Reset);
        		break;
	        }
		}
		//Closes Scanner after leaving Menu while loop
	scan.close();
	}
	
	public static boolean isNumeric(String str){
		return str != null && str.matches("[0-9.]+");
	}
	public static double inputCertification(Scanner scan) {
    	String amount;
    	double converted;
    	boolean validInput = false;
    	while(validInput != true ) 
    	{	
    		amount = scan.nextLine();
    		if(isNumeric(amount)) {
				converted = Double.parseDouble(amount);
				validInput = true;
				return converted;

			} else {
				System.out.println("\u001B[31m" + "Input is not a number try again. " + "\u001B[0m");
			}
    	}
    	return 0.0;
    }
}


