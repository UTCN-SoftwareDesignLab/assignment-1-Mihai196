package model;

public class Bill {
	
	private int id;
	private double sumToPay;
	private String company;
	private int clientId;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getSumToPay() {
		return sumToPay;
	}
	public void setSumToPay(double sumToPay) {
		this.sumToPay = sumToPay;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	
	

}
