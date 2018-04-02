package model.validation;

import java.util.ArrayList;
import java.util.List;

import model.Client;

public class ClientValidator {
	
	private final List<String> errors;
	private static final int NR_DIGITS_PNC=10;
	private static final int NR_DIGITS_IDCARD=4;
	public ClientValidator()
	{
		super();
		errors=new ArrayList<>();
	}
	public List<String> getErrors() {
		return errors;
	}
	
	public boolean validate(Client c)
	{
		if (String.valueOf(c.getIdCardNr()).length()!=NR_DIGITS_IDCARD)
		{
			errors.add("Client ID Card number must contain exactly 4 digits");
		}
		if (String.valueOf(c.getPersNrCode()).length()!=NR_DIGITS_PNC)
		{
			errors.add("Client PNC must contain exacly 10 digits");
		}
		return errors.isEmpty();
	}

}
