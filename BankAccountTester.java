/**
 * Program Name: BankAccountTester.java
 * Purpose: This program will instantiate objects of Triangle and test all of the methods of the Triangle class
 * Coder: Dianne Corpuz Section B
 * Date: February 26, 2020
 */
import java.util.ArrayList;

public class BankAccountTester {

	public static void main(String[] args) {
		//Using polymorphism, create a PersonalChequingAccount object
		BankAccount data1=new PersonalChequingAccount("Janice Joplin","March",2345);
		// Print the object at the start of the month.
		System.out.println(data1.toString());
	 	//MAKE transactions to Janice’s chequing account for the month of March
		data1.deposit(25.98, 5);
		data1.withdrawal(1300,6); 
		data1.withdrawal(1700, 10); 
		data1.deposit(1050, 11); 
		data1.withdrawal(1700, 11);
		data1.deposit(25.98, 25);
		data1.withdrawal(400.00, 26); 
		data1.withdrawal(27.00,28);
		data1.withdrawal(7.50,28);
		
		//Prepare the monthly proces and print all transactions,Then re-print the object after the transactions have been processed.
		data1.monthlyProcess();
		System.out.println("\n" + data1.toString());
		
		//Using polymorphism, create a SavingAccount object. 
		BankAccount data2=new SavingAccount("Elvis Presley","March",6100);
		//Print the object at the beginning of the month. 
		System.out.println("\n" + data2.toString());
		
		//make transactions to Elvis’s saving account
		data2.deposit(500, 3);
		data2.withdrawal(1000, 6);
		data2.deposit(250, 15);
		data2.withdrawal(3000, 21);
		data2.withdrawal(825, 28);
		data2.deposit(250, 29);
		
		//Prepare the monthly process, and print the transactions,Then re-print the object after the transactions have been processed.
		data2.monthlyProcess();
		System.out.println("\n" + data2.toString());	
		
		
	}
}
