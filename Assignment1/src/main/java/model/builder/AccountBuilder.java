package model.builder;

import java.util.Date;

import model.Account;

public class AccountBuilder {

	private Account account;
	//id type balance dateOfCreation clientId
	
	public AccountBuilder setId(int id)
	{
		account.setId(id);
		return this;
	}
	public AccountBuilder setType(String type)
	{
		account.setType(type);
		return this;
	}
	public AccountBuilder setBalance(double balance)
	{
		account.setBalance(balance);
		return this;
	}
	public AccountBuilder setDateOfCreation(Date dateOfCreation)
	{
		account.setDateOfCreation(dateOfCreation);
		return this;
	}
	public AccountBuilder setClientId(int clientId)
	{
		account.setClientId(clientId);
		return this;
	}
	public Account build()
	{
		return account;
	}
}
