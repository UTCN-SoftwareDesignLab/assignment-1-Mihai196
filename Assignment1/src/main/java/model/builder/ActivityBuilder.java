package model.builder;


import java.util.Date;

import model.Activity;

public class ActivityBuilder {
	
	private Activity activity;
	public ActivityBuilder()
	{
		activity=new Activity();
	}
	public ActivityBuilder setId(int id)
	{
		activity.setId(id);
		return this;
	}
	public ActivityBuilder setType(String type)
	{
		activity.setType(type);
		return this;
	}
	public ActivityBuilder setDateOfPerforming(Date dateOfPerforming)
	{
		activity.setDateOfPerforming(dateOfPerforming);
		return this;
	}
	public ActivityBuilder setUserId(Long userId)
	{
		activity.setUserId(userId);
		return this;
	}
	public Activity build()
	{
		return activity;
	}

}
