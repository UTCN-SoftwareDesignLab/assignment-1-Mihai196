package model.validation;

import java.util.ArrayList;
import java.util.List;

import model.Account;

public class AccountValidator {
	
	private final List<String> errors;
	private static final double MIN_BALANCE=0;
	private static final String SAVINGSACCOUNT="saving";
	private static final String SPENDINGACCOUNT="spending";
	public AccountValidator()
	{
		errors=new ArrayList<>();
	}
	public List<String> getErrors() {
		return errors;
	}
	public boolean validate(Account a)
	{
		if(a.getBalance()<MIN_BALANCE)
		{
			errors.add("Can't perform operation. Balance is negative");
		}
		if (!(a.getType().equals(SAVINGSACCOUNT))&&!(a.getType().equals(SPENDINGACCOUNT)))
		{
			errors.add("The only types of accounts are saving and spending!");
		}
		return errors.isEmpty();
	}

}
