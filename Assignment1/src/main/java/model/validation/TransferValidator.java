package model.validation;

import java.util.ArrayList;
import java.util.List;

import model.Account;

public class TransferValidator {
	
	private static final double MIN_SUM_TRANSFER=15.0;
	
	private final List<String> errors;

	public TransferValidator() {
		super();
		errors = new ArrayList<>();
	}
	public List<String> getErrors() {
		return errors;
	}
	public boolean validate(Account a,double sum)
	{
		if (a.getBalance()<sum)
			errors.add("Not enough money on the account");
		else
			if (sum<MIN_SUM_TRANSFER)
			{
				errors.add("The minimum sum for transferring is at least 15.0");
			}
		return errors.isEmpty();
	}
	
	
	

}
