/**
 * Program Name: BankAccount.java
 * Purpose: an abstract superclass which holds features that are common to it subclasses
 * Coder: Dianne Corpuz Section B
 * Date: February 26, 2020
 */
public abstract class BankAccount {

	private String customerName;
	private String accountType;
	private String month;
	
	BankAccount()
	{
		
		this.customerName=null;
		this.accountType=null;
		this.month=null;
	}

	BankAccount(String customerName,String accountType,String month)
	{
		this.customerName=customerName;
		this.accountType=accountType;
		this.month=month;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	
	public abstract String generateAccountNumber();
	public abstract void deposit(double depositAmount,int day);
	public abstract void withdrawal(double withdrawalAmount,int day); 
	public abstract void recordTransaction();
	public abstract void monthlyProcess();
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
		output=
				
		"***************************************\n"+
		"Customer Name: "+this.customerName+"\n"+
		"Account Type: "+this.accountType+"\n"+
		"Current Month: "+  this.month+"\n"+

		"***************************************\n";
    	return output;	
	}
}

