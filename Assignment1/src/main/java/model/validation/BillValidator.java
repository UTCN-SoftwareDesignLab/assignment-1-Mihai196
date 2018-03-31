package model.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Account;
import model.Bill;

public class BillValidator {
	
	
	private final List<String> errors;
	private final double MIN_SUM_BILL=10;
	private final String[] COMPANIES=new String[]{"GazProm","Electrica","WaterWorks"};
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
	public boolean validateForInsertion(Bill bill)
	{
		if(bill.getSumToPay()<MIN_SUM_BILL)
		{
			errors.add("Can't create a bil with a sum smaller than 10");
		}
		if(!Arrays.asList(COMPANIES).contains(bill.getCompany()))
		{
			errors.add("We currently don't have a contract with the mentioned company");
		}
		return errors.isEmpty();
		
	}

}
