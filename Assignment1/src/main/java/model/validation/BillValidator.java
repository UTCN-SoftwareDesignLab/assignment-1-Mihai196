package model.validation;

import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.Bill;

public class BillValidator {
	
	
	private final List<String> errors;
	public BillValidator() {
		super();
		errors = new ArrayList<>();
	}
	public List<String> getErrors() {
		return errors;
	}
	public boolean validate(Bill bill,Account a)
	{
		if (a.getBalance()<bill.getSumToPay())
		{
			errors.add("Not enough money to pay the bill");
		}
		return errors.isEmpty();
	}

}
