/**
 * Program Name: SavingAccount.java
 * Purpose: another concrete subclass of BankAccount that will be the savings Account
 * Coder: Dianne Corpuz Section B
 * Date: February 26, 2020
 */
import java.util.ArrayList;
import java.util.Random;

public class SavingAccount extends BankAccount implements InterestPayable{

	private String accountNumber;
	private int numberWithdrawals;
	private int numberDeposits;
	private double balance;
	private boolean accountActive;
	private static double INT_RATE = 0.03;
	private ArrayList<Transaction> record= new ArrayList<Transaction>();
	
	//make a constructor with no args
	SavingAccount()
	{
		setAccountType(null);
		setCustomerName(null);
		accountActive=false;
		numberWithdrawals=0;
		numberDeposits=0;
		balance=0;
	}

	//make a constructor with multiple args
	SavingAccount(String customerName, String month, double balance)
	{
		setCustomerName(customerName);
		setMonth(month);
		this.balance=balance;
		setAccountType("Saving");
		accountNumber=generateAccountNumber();
		accountActive=isAccountActive();
		
	}
	//place setters ,getters for AccountNumber,NumberWithdrawals,NumberDeposit,balance,AccountActive
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
	* Method Name:isAccountActive()
	* Purpose: to determine if account is active or not
	*	Accepts: NOTHING
	*	Returns: boolean
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

	public void setAccountActive(boolean accountActive) {
		this.accountActive = accountActive;
	}

	public double getINT_RATE() {
		return INT_RATE;
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
	    String endsWith = "575";
	    Random generator = new Random();
	    int n=0;
	    String accountNumber="";;
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
				//balance<0 or account is not Active or balance<withdrawalAmount, 
				//transaction will be cancelled, else withdraw certain amount
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
	* Purpose: calculate monthly Interest
	*	Accepts: NOTHING
	*	Returns: NOTHING
	*
	*/
	public void calcInterest()
	{
		double monthlyInterest=0;
		double increase_INT_RATE=0.0075;//declare the increase interest rate
		String transaction=""; //make a String variable to hold transaction
		int lastday=0; //determine the lastday of every month
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
		//if balance is >=25, calculate montlyInterest by (INT_RATE/12)*balance, otherwise, interest is 0
				if(balance>=25)
				{
					monthlyInterest=(INT_RATE/12)*balance;
				}
				else
				{
					monthlyInterest=0;
				}
		//Traverse
		for(Transaction tr:record)
		{
			balance=tr.getBalance();
			if(balance>=2000)
			{
				monthlyInterest =((INT_RATE+increase_INT_RATE)/12)*balance;
			}
		}//end for	 
		
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
		{
			System.out.print("*");
		}
			System.out.println("\nTransaction Record for the Month of "+getMonth());
		for(int i=0;i<45;++i)
		{
			System.out.print("*");
		}
		System.out.println("\n");
		
		
		for(Transaction tr:record)
		{
			System.out.print(tr.toString());
		}//end for
	}
	
	/**
	* Method Name: monthlyProcess()
	* Purpose:  to calculate the monthly transaction
	*	Accepts: NOTHING. 
	*	Returns: NOTHING
	*
	*/
	public void monthlyProcess()
	{
		calcInterest();
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
		+"Annual Interest Rate: "+	INT_RATE*100+"%\n";
		return super.toString()+output;

	}


	@Override
	public void recordTransaction() {
		// TODO Auto-generated method stub
		
	}

	
}
