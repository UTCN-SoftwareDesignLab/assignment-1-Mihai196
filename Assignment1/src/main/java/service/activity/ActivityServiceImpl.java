package service.activity;

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
	public List<Activity> findFromDateToDate(long userId, Date dateFrom, Date dateTo) {
		// TODO Auto-generated method stub
		return activityRepository.findFromDateToDate(userId, dateFrom, dateTo);
	}


}
