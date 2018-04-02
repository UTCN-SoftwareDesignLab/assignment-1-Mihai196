package service.activity;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.Account;
import model.Activity;
import model.builder.ActivityBuilder;
import repository.activity.ActivityRepository;

public class ActivityServiceImpl implements ActivityService {

	private ActivityRepository activityRepository;
	
	public ActivityServiceImpl(ActivityRepository activityRepository) {
		super();
		this.activityRepository = activityRepository;
	}

	@Override
	public boolean addActivity(String type,Long userId) {
		// TODO Auto-generated method stub
		Activity activity=new ActivityBuilder().setType(type).setUserId(userId).build();
		return activityRepository.addActivity(activity);
	}


	@Override
	public List<Activity> findAll() {
		// TODO Auto-generated method stub
		return activityRepository.findAll();
	}

	@Override
	public DefaultTableModel fillActivityData() {
		// TODO Auto-generated method stub
		DefaultTableModel tablemodel = new DefaultTableModel();
		tablemodel.addColumn("Id");
		tablemodel.addColumn("Type");
		tablemodel.addColumn("DateOfPerforming");
		tablemodel.addColumn("UserId");
		List<Activity> activities = new ArrayList<Activity>();
		activities = activityRepository.findAll();
		for (Activity c : activities) {
			tablemodel.addRow(
					new Object[] { c.getId(), c.getType(), c.getDateOfPerforming(),c.getUserId() });
		}
		return tablemodel;
	}

	@Override
	public void findFromDateToDate(long userId, Date dateFrom, Date dateTo) {
		// TODO Auto-generated method stub
		List<Activity> reportActivities=activityRepository.findFromDateToDate(userId, dateFrom, dateTo);
		System.out.println(reportActivities);
		String allActivities="";
		for(Activity activity:reportActivities)
		{
			allActivities+=activity.toString()+System.lineSeparator();
		}
		try (PrintWriter out = new PrintWriter("Report.txt")) {
			out.println(allActivities);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
