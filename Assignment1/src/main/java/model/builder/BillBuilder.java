package model.builder;

import model.Bill;

public class BillBuilder {
	
	private Bill bill;
	
	public BillBuilder()
	{
		bill=new Bill();
	}
	
	public BillBuilder setId(int id)
	{
		bill.setId(id);
		return this;
	}
	public BillBuilder setSumToPay(double sumToPay)
	{
		bill.setSumToPay(sumToPay);
		return this;
	}
	public BillBuilder setCompany(String company)
	{
		bill.setCompany(company);
		return this;
	}
	public BillBuilder setClientId(int clientId)
	{
		bill.setClientId(clientId);
		return this;
	}
	public Bill build()
	{
		return bill;
	}
	

}
