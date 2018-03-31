package repository.activity;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Activity;
import model.builder.ActivityBuilder;

public class ActivityRepositoryMySQL implements ActivityRepository {

	private final Connection connection;

	public ActivityRepositoryMySQL(Connection connection) {
		this.connection = connection;
	}

	@Override
	public boolean addActivity(Activity activity) {
		try {
			PreparedStatement insertStatement = connection
					.prepareStatement("INSERT INTO activity values (null, ?, ?, ?)");
			insertStatement.setString(1, activity.getType());
			insertStatement.setDate(2, new java.sql.Date(System.currentTimeMillis()));
			insertStatement.setLong(3, activity.getUserId());
			insertStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Activity> findAll() {
		// TODO Auto-generated method stub
		List<Activity> activities = new ArrayList<>();
		try {
			PreparedStatement findStatement = connection.prepareStatement("SELECT * FROM activity");
			ResultSet rs = findStatement.executeQuery();
			while (rs.next()) {
				activities.add(getActivityFromResultSet(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return activities;
	}

	private Activity getActivityFromResultSet(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return new ActivityBuilder().setId(rs.getInt("id")).setType(rs.getString("type"))
				.setDateOfPerforming(new Date(rs.getDate("dateOfPerforming").getTime())).setUserId(rs.getLong("userId"))
				.build();
	}

	@Override
	public List<Activity> findFromDateToDate(Long userId, java.util.Date dateFrom, java.util.Date dateTo) {
		// TODO Auto-generated method stub
		List<Activity> activities = new ArrayList<>();
		try {
			PreparedStatement findStatement = connection
					.prepareStatement("Select * from activity where userId= " + userId.toString() + " and dateOfPerforming >= '"
							+ dateFrom.toString() + "' and dateOfPerforming <= '" + dateTo.toString() + "'");
			ResultSet rs = findStatement.executeQuery();
			while (rs.next()) {
				activities.add(getActivityFromResultSet(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return activities;
	}

	

}
