package model;

import java.util.Date;

public class Activity {
	
	private int id;
	private String type;
	private Date dateOfPerforming;
	private Long userId;
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
	public Date getDateOfPerforming() {
		return dateOfPerforming;
	}
	public void setDateOfPerforming(Date dateOfPerforming) {
		this.dateOfPerforming = dateOfPerforming;
	}
	public Long getUserId() {
		return userId;
	}
	@Override
	public String toString() {
		return "Activity [id=" + id + ", type=" + type + ", dateOfPerforming=" + dateOfPerforming + ", userId=" + userId
				+ "]";
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	

}
