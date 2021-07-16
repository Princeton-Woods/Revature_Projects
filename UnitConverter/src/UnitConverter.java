import java.util.Scanner;

public class UnitConverter {
	public static void main(String[] args) {
		//Universal variables 
		UnitConverter M = new UnitConverter();
		int menuSelection = 0;
		Scanner scan = new Scanner(System.in);
		String choice;
		boolean validInput;
		
		//Console Colors Just for effect cause why not 
		final String Text_Reset = "\u001B[0m";
		final String Text_Red = "\u001B[31m";
		final String Text_Green = "\u001B[32m";
		final String Text_Yellow = "\u001B[33m";
		final String Text_Purple = "\u001B[35m";
		final String Text_Cyan = "\u001B[36m";
		
		while(menuSelection != 5) {
			//Menu variables resets every loop until Quit
			menuSelection = 0;
			choice = null;
			validInput = false;
			scan = new Scanner(System.in);
			
			System.out.println(Text_Reset + Text_Yellow + "Please select an option: " + Text_Reset);   //Selection Text
			System.out.println(Text_Purple +"	1. Cups to Galons" + Text_Reset);  	   //cups to gallons
			System.out.println(Text_Purple +"	2. Miles to Kilometers" + Text_Reset);  //Miles to Kilos
			System.out.println(Text_Purple +"	3. Pounds to Kilograms" + Text_Reset);  //Pounds to kilos
			System.out.println(Text_Purple +"	4. Cups to Liters"+ Text_Reset);	   //cups to liters
			System.out.println(Text_Red +"	5. Quit" + Text_Reset);				   //Quit
			
			System.out.println(Text_Green + "What will you choose: " + Text_Reset);//Selection Text

			//Input validation loop
			while(validInput != true ) {
				choice = scan.nextLine();
				if(isNumeric(choice)) {
					menuSelection = Integer.parseInt(choice);
				
					if(menuSelection < 1 || menuSelection > 5) {
						System.out.println(Text_Red + "Invalid Selection Try again: " + Text_Reset);
						menuSelection = 0;
					} else {
						validInput = true;
					}
				} else {
					
					System.out.println(Text_Red + "Input is not a number try again. " + Text_Reset);
				}

			}
			
			//Switch case based on input
	        switch(menuSelection) {
        	case 1:
        		System.out.println("How many Cups would you like to convert: ");
        		double returned = M.convertCupsToGallons(inputCertification(scan));
        		System.out.println(Text_Green + "A Gallon Consists of: " + returned + " Cups" + Text_Reset);
        		System.out.println();
        		break;
        	case 2:
        		System.out.println("How many Miles would you like to convert: ");
        		double returned1 = M.convertMilesToKilometers(inputCertification(scan));
        		System.out.println(Text_Green + "A Kilometer Consists of: " + returned1 + " Miles" + Text_Reset);
        		System.out.println();
        		break;
        	case 3:
        		System.out.println("How many Pounds would you like to convert: ");
        		double returned2 = M.convertPoundsToKilograms(inputCertification(scan));
        		System.out.println(Text_Green + "A Kilogram Consists of: " + returned2 + " Pounds" + Text_Reset);
        		System.out.println();
        		break;
        	case 4: 
        		System.out.println("How many Cups would you like to convert: ");
        		double returned3 = M.convertCupsToLiters(inputCertification(scan));
        		System.out.println(Text_Green +"A Liter Consists of: " + returned3 + " Cups" + Text_Reset);
        		System.out.println();
        		break;
        	case 5:
        		System.out.println(Text_Cyan + "You have quit. Goodbye!" + Text_Reset);
        		break;
	        }
	        
		}
		//Closes Scanner after leaving Menu while loop
		scan.close();

	}
	//Cups -> Gallons
	public double convertCupsToGallons(double cups) {
		double converted = cups * (double)16;
		return converted;
	}
	//Miles -> Kilometers
	public double convertMilesToKilometers(double miles) {
		double converted = 1.60934 * (double)miles;
		return converted;
	}
	//Pounds -> Kilograms
	public double convertPoundsToKilograms(double pounds) {
		double converted = 0.453592 * (double)pounds;
		return converted;
	}
	//Cups -> Liters
	public double convertCupsToLiters(double cups) {
		double converted = 0.236588 * (double)cups;
		return converted;
	}
	//Checks String if it contains proper values
    public static boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }
    //Verifies input uses While loop and calls isNumeric func.
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






