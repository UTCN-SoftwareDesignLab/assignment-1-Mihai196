package model;
import java.util.Date;



public class Account {
	
	private int id;
	private String type;
	private double balance;
	private Date dateOfCreation;
	private int clientId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Date getDateOfCreation() {
		return dateOfCreation;
	}
	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
	public int getClientId() {
		return clientId;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", type=" + type + ", balance=" + balance + ", dateOfCreation=" + dateOfCreation
				+ ", clientId=" + clientId + "]";
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	

}
