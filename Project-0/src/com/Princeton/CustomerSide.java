package com.Princeton;

import java.sql.SQLException;
import java.util.Scanner;



public class CustomerSide {
	static UserDAO dao =  UserDAOFactory.getUserDao();
	
	final static String Reset = "\u001B[0m";
	final static String Red = "\u001B[31m";
	final static String Green = "\u001B[32m";
	final static String Yellow = "\u001B[33m";
	final static String Purple = "\u001B[35m";
	final static String Cyan = "\u001B[36m";
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

        
        
		
		//Variables
		int menuSelection = 0;
		Scanner scan = new Scanner(System.in);
		String choice;
		boolean validInput;
			
		//Console Colors Just for effect cause why not 
			
		while(menuSelection != 3) {
			//Menu variables resets every loop until Quit
			menuSelection = 0;
			choice = null;
			validInput = false;
			scan = new Scanner(System.in);
				
			System.out.println(Reset + Yellow + "Please select an option: " + Reset);
			System.out.println(Purple +"	1. Create Account " + Reset);  	
			System.out.println(Purple +"	2. Login" + Reset);  	
			System.out.println(Red +"	3. Quit" + Reset);				   //Quit
				
			System.out.println(Green + "What will you choose: " + Reset);//Selection Text
			
			//Menu input validation loop
			while(validInput != true ) {
				choice = scan.nextLine();
				if(isNumeric(choice)) {
					menuSelection = Integer.parseInt(choice);
					if(menuSelection < 1 || menuSelection > 3) {
						System.out.println(Red + "Invalid Selection Try again: " + Reset);
						menuSelection = 0;
					} else {	validInput = true;	}
				} else {	
					System.out.println(Red + "Input is not a number try again. " + Reset);	
				}
			}
			
			int id;
			String name;
			String email;
			String password;
			double balance = 0.0;
			
			//Switch case based on input
	        switch(menuSelection) {
        	case 1:
        		System.out.println("Please enter the your name: ");
        		name = scan.nextLine();
        		System.out.println("Please enter the your email: ");
        		email = scan.nextLine();
        		System.out.println("Please enter a password: ");
        		password = scan.nextLine();
        		
        		boolean min = false;
        		do {
        			System.out.println(Yellow + "Please enter the minimum $25 balance: " + Reset);
        			balance = inputCertification(scan);
        			if(balance >= 25.0) {
        				min = true;
        			}
        		}while(min != true);
        		dao.addUser(new User(name,email,password,balance));
        		//users.add(new User(name,email,password,balance));
        		System.out.println();
        		break;
        		
        	case 2:
        		System.out.println("Please enter the users email: ");
        		email = scan.nextLine();
        		System.out.println("Please enter the users password: ");	
        		password = scan.nextLine();
        		User tempLogin = dao.LogIn(email,password);
        			if(tempLogin == null) {
        				System.out.println(Red + "In correct user name and password. Try again." + Reset);
        			}
        			else {
        				System.out.println(Green + "\nYour Balance is: " + tempLogin.getBalance());
        				SecondMenu(tempLogin, scan);
        			}
        		System.out.println();
        		break;        		
        		
        	case 3:
        		System.out.println(Cyan + "You have quit. Goodbye!" + Reset);
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
	
	public static void SecondMenu(User u, Scanner scan) throws SQLException {
		int MenuSelection = 0;
		while(MenuSelection != 4) {
			System.out.println(Yellow+"Please Choose what you would like to do"+ Reset);
			System.out.println(Purple +"1)  WithDraw"+ Reset);
			System.out.println(Purple +"2)  Deposit"+ Reset);
			System.out.println(Purple +"3)  Transfer"+ Reset);
			System.out.println(Red +"4)  Log Out"+ Reset);
			System.out.println(Green + "What will you choose: "+ Reset);
			MenuSelection = (int) inputCertification(scan);
			if(MenuSelection > 4 || MenuSelection < 1) {
				MenuSelection = 0;
			}
			double amount = 0;
			User temp;
			switch(MenuSelection) {
			case 1:
				System.out.println("How much would you like to withdraw"+ Reset);
				amount = inputCertification(scan);
				if(amount <= 0) {
					System.out.println(Red +"Cant Withdraw a negative value\n"+ Reset);
				}else {
					temp = dao.ModifyBalance(u,1, amount);
					if(temp == null) {
						System.out.println(Red +"Cant withdraw more than the current balance.\n"+ Reset);
					}
					System.out.println(Green +"New balance is: " + u.getBalance()+ Reset);
				}
				break;
			case 2:
				System.out.println("How much would you like to Deposit"+ Reset);
				amount = inputCertification(scan);
				if(amount <= 0) {
					System.out.println(Red +"Cant Deposit a negative value\n"+ Reset);
				}else {
					dao.ModifyBalance(u,2, amount);
					System.out.println(Green +"New balance is: " + u.getBalance()+ Reset);
				}
				break;
			case 3:
				System.out.println("How much would you like to Transfer"+ Reset); 
				amount = inputCertification(scan);
				if(amount <= 0) {
					System.out.println(Red +"Cant Transfer a negative value\n"+ Reset);
				} else {
					System.out.println("Input the name of the user you would like to send money to: "+ Reset);
					String name = scan.nextLine();
					System.out.println("Input the users email"+ Reset);
					String email = scan.nextLine();
					temp = dao.MakeTransfer(u,name,email, amount);
					if(temp == null) {
						System.out.println(Red +"Transfer Credentials are incorrect. Try again\n"+ Reset);
					}else {
						System.out.println(Green +"Your new balance is: " + u.getBalance() + " Your transfer is Pending\n"+ Reset);
					}
				}
				break;
			case 4:
				return;
			}
			MenuSelection = 0;
			
		}
	}
}
