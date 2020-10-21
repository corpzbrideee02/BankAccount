/**
 * Program Name: Transaction.java
 * Purpose: an aggregate class whose objects will be instantiated in the two subclasses and stored 
 * in an ArrayList structure in order to keep track of the monthly transactions against each account object. 
* Coder: Dianne Corpuz Section B
 * Date: February 26, 2020
 */
import java.util.ArrayList;

public class Transaction {

	
	private String month;
	private int day;
	private String transaction;
	private double amount;
	private double balance;
	
	//make a constuctor with no args, and sets its values to null or zero
	Transaction()
	{
		this.month=null;
		this.day=0;
		this.transaction=null;
		this.amount=0;
		this.balance=0;
	}
	//place getters for month, day, transaction,amount, balance
	public String getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public String getTransaction() {
		return transaction;
	}

	public double getAmount() {
		return amount;
	}

	public double getBalance() {
		return balance;
	}
	
	/**
	* Method Name: set()
	* Purpose: sets the dates, transaction and balance to its values
	*	Accepts:String month,int day,String transaction,double amount, double balance
	*	Returns: NOTHING
	*
	*/
	public void set(String month,int day,String transaction,double amount, double balance)
	{
		this.month=month;
		this.day=day;
		this.transaction=transaction;
		this.amount=amount;
		this.balance=balance;
	}
	
	/**
	* Method Name: toString
	* Purpose:  to output all variables
	*	Accepts: NOTHING. 
	*	Returns: String
	*
	*/
	public String toString()
	{
		String output = "";	
		output=month+"\t"+ day+ "\t"+ String.format("%-12s",transaction)+ "\t"+
				 String.format("$%-7s",String.format("%.2f",amount))+"\t"
				+ "Balance: $"+ String.format("%.2f",balance)+"\n";
    	return output;	
	}
}
