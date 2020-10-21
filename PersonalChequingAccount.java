/**
 * Program Name: PersonalChequingAccount.java
 * Purpose: a concrete subclass of BankAccount that will be the Personal Checquing Account
 * Coder: Dianne Corpuz Section B
 * Date: February 26, 2020
 */
import java.util.ArrayList;
import java.util.Random;
//extends the BanckAccount implements InterestPayable
public class PersonalChequingAccount extends BankAccount implements InterestPayable {

	//declare the variables here
	private String accountNumber;
	private int numberWithdrawals;
	private int numberDeposits;
	private double balance;
	private boolean accountActive;
	private static double INT_RATE = 0.025;
	private double SERVICE_FEE = 0.85;
	private ArrayList<Transaction> record= new ArrayList<Transaction>();
	
	//make a constructor with no args
		PersonalChequingAccount()
		{
			setAccountType(null);
			setCustomerName(null);
			accountNumber=null;
			accountActive=false;
			numberWithdrawals=0;
			numberDeposits=0;
			balance=0;
		}

	//make a constructor with multiple args and sets the values
		PersonalChequingAccount(String customerName, String month, double balance)
		{
			super(customerName,"Personal Chequing",month);
			setCustomerName(customerName);
			setMonth(month);
			this.balance=balance;
			setAccountType("Personal Chequing");
			accountNumber=generateAccountNumber();
			accountActive=isAccountActive();
		}
	
	//place setters ,getters for accountNumber,NumberWithdrwals,Balance,deposits,serviceFee and acountActive
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public int getNumberWithdrawals() {
		return numberWithdrawals;
	}
	
	public int getNumberDeposits() {
		return numberDeposits;
	}
	
	public double getBalance() {
		return balance;
	}
	/**
	* Method Name: isAccountActive()
	* Purpose: checks if Account is active using some condition
	*	Accepts: NOTHING
	*	Returns: NOTHING
	*
	*/
	public boolean isAccountActive() {
		
		if(balance<25.00)
		{
			return false;
		}
		else {
			return true;
		}
	}
	
	public double getInterestRate() {
		return INT_RATE;
	}
	
	public double getServiceFee() {
		return SERVICE_FEE;
	}
	
	/**
	* Method Name: generateAccountNumber()
	* Purpose: generate account number using for loop
	*	Accepts: NOTHING
	*	Returns: NOTHING
	*
	*/
	public String generateAccountNumber()
	{
		String startsWith="002-623490-";
	    String endsWith = "550";
	    Random generator = new Random();
	    int n=0;
	    String accountNumber="";
	    for(int i=0;i<6;++i)
	    {
		     n = generator.nextInt(10) + 0;
			 
			  accountNumber+= Integer.toString(n);
			  
			  if(i==5)
			  {
				  accountNumber+="-";
			  }
	    }
	  return startsWith+accountNumber+endsWith;
	}
	
	/**
	* Method Name: deposit()
	* Purpose: calculate deposit amount
	*	Accepts: double depositAmount, int day
	*	Returns: NOTHING
	*
	*/
	public void deposit(double depositAmount, int day)
	{
			String transaction;
			transaction="Deposit";
			balance=balance+depositAmount;
			numberDeposits++;
			accountActive=isAccountActive();
			recordTransaction(day,transaction,depositAmount,balance);
	}
	
	/**
	* Method Name: withdrawal()
	* Purpose: calculate widthrawal amount
	*	Accepts: double withdrawalAmount, int day
	*	Returns: NOTHING
	*
	*/
	public void withdrawal(double withdrawalAmount, int day)
	{
		String transaction="";
		if(balance<0||accountActive==false||balance<withdrawalAmount)
		{
			if(balance<0||balance<withdrawalAmount)
			{
				transaction="Transaction cancelled. Insufficient funds";
			}
			else if(accountActive==false)
			{
				transaction="Transaction cancelled. Account is inactive";
			}
		}
		else if(balance>0&&accountActive==true&&balance>withdrawalAmount)
		{
				balance=balance-withdrawalAmount;
				transaction="Withdrawal";
				numberWithdrawals++;
				accountActive=isAccountActive();	
		}
		recordTransaction(day,transaction,withdrawalAmount,balance);		
	}
	
	/**
	* Method Name: calcInterest()
	* Purpose: calculate monthly Interest, if balance > 1000, then calculate monthlyInterest by INT_RATE/12
	*	Accepts: NOTHING
	*	Returns: NOTHING
	*
	*/
	public void calcInterest()
	{
		String transaction=""; //make a String variable to hold transaction
		int lastday=0;	//determine the lastday of every month
		double monthlyInterest;
		if(getMonth().equals("January")||getMonth().equals("March")||getMonth().equals("May")||getMonth().equals("July")
				||getMonth().equals("August")||getMonth().equals("October")||getMonth().equals("December"))
			{
				lastday=31;
			}
		else if(getMonth().equals("February"))	
			{
				lastday=29;
			}
		else 
			{
				lastday=30;
			}	
		
		if(getBalance()>=1000)
		{
			monthlyInterest=INT_RATE/12;
		}
		else
		{
			monthlyInterest=0;
		}
		transaction="Interest";
		balance+=monthlyInterest;
		recordTransaction(lastday,transaction,monthlyInterest,balance);
	}
	
	/**
	* Method Name: recordTransaction()
	* Purpose: record every transactions made
	*	Accepts: int day, String transaction, double amount, double balance
	*	Returns: NOTHING
	*
	*/
	public void recordTransaction(int day, String transaction, double amount, double balance)
	{
        Transaction trans = new Transaction();
        trans.set(getMonth(), day, transaction, amount, balance);
		record.add(trans);
	}
	
	/**
	* Method Name: printTransactions()
	* Purpose:  to print all transactions
	*	Accepts: NOTHING. 
	*	Returns: NOTHING
	*
	*/
	public void printTransactions()
	{
		for(int i=0;i<45;++i)
		{System.out.print("*");}
			System.out.println("\nTransaction Record for the Month of "+getMonth());
		for(int i=0;i<45;++i)
		{System.out.print("*");}
			System.out.println("\n");
		for(Transaction tr:record)
		{
			System.out.print(tr.toString());
		}//end for	
	}
	
	/**
	* Method Name: monthlyProcess()
	* Purpose:  to calculate the monthly transaction,serviceFee
	*	Accepts: NOTHING
	*	Returns: NOTHING
	*
	*/
	public void monthlyProcess()
	{
		String transaction=""; 
		int lastday=0;
		double service_fee;
		if(getMonth().equals("January")||getMonth().equals("March")||getMonth().equals("May")||getMonth().equals("July")
				||getMonth().equals("August")||getMonth().equals("October")||getMonth().equals("December"))
			{
				lastday=31;
			}
		else if(getMonth()=="February")	
			{
				lastday=29;
			}
		else 
			{
				lastday=30;
			}	
		
		if(numberWithdrawals>4)
		{
			service_fee=numberWithdrawals*SERVICE_FEE;
		}
		else
		{
			service_fee=0;
		}

		transaction="Service Fee";
		balance+=service_fee;
		calcInterest();
		recordTransaction(lastday,transaction,service_fee,balance);
		printTransactions();
		isAccountActive();
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
		output="Account Number: "+accountNumber+"\n"
		+"Number of Withdrawals: "+numberWithdrawals+"\n"
		+"Number of Deposits: "+	numberDeposits+"\n"
		+"Balance: $"+String.format("%.2f", balance)+"\n"
		+"Account Active: "+	accountActive+"\n"
		//+"Annual Interest Rate: "+String.format("%f%%%n", INT_RATE*100)
		//+"Annual Interest Rate: "+ String.format("%f%%", INT_RATE*100)+"\n"
		+"Annual Interest Rate: "+INT_RATE*100+"%"+"\n"
		+"Monthly Service Fee Rate: $"+	 String.format("%.2f", SERVICE_FEE)+"\n";
		return super.toString()+ output;	
	}
	
	@Override
	public void recordTransaction() {
		// TODO Auto-generated method stub
		
	}
}
