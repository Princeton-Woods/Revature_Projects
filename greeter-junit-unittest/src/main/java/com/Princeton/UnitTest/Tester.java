package com.Princeton.UnitTest;
import org.junit*;;

import java.util.Scanner;

public class Tester {
	
	@Test
	public static double inputCertificationTEST(Scanner scan) {
    	String amount;
    	double converted;
    	boolean validInput = false;
    	while(validInput != true ) 
    	{	
    		amount = scan.nextLine();
    		if(isNumericTEST(amount)) {
				converted = Double.parseDouble(amount);
				validInput = true;
				return converted;

			} else {
				System.out.println("\u001B[31m" + "Input is not a number try again. " + "\u001B[0m");
			}
    	}
    	return 0.0;
    }
	public static boolean isNumericTEST(String str){
		return str != null && str.matches("[0-9.]+");
	}
}
