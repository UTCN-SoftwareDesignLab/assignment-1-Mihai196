package service.activity;

import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.Activity;

public interface ActivityService {
	
	boolean addActivity(String type,Long userId);
	List<Activity> findFromDateToDate(long userId,Date dateFrom,Date dateTo);
	List<Activity> findAll();
	DefaultTableModel fillActivityData();

}
