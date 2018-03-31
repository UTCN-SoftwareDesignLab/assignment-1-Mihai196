package repository.activity;

import java.util.Date;
import java.util.List;

import model.Activity;

public interface ActivityRepository {
	
	boolean addActivity(Activity activity);
	List<Activity> findAll();
	List<Activity> findFromDateToDate(Long userId, Date dateFrom, Date dateTo);

}
