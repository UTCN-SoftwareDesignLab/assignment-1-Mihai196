package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import database.Constants;
import model.Activity;
import model.User;
import model.builder.UserBuilder;
import model.validation.Notification;
import service.activity.ActivityService;
import service.user.AuthenticationService;
import service.user.UserService;
import view.AdministratorView;
import view.LoginView;

public class AdministratorController {

	private AdministratorView administratorView;
	private AuthenticationService authenticationService;
	private UserService userService;
	private LoginView loginView;
	private ActivityService activityService;

	public AdministratorController(AuthenticationService authenticationService, UserService userService,
			AdministratorView administratorView,LoginView loginView,ActivityService activityService) {
		super();
		this.administratorView = administratorView;
		this.authenticationService = authenticationService;
		this.userService = userService;
		this.loginView=loginView;
		this.activityService=activityService;

		administratorView.setAddUserButtonActionListener(new AddButtonActionListener());
		administratorView.setBtnViewusersActionListener(new viewActionListener());
		administratorView.setBtnRemoveuserActionListner(new removeActionListener());
		administratorView.setBtnNewButtonActionListener(new LogOutActionListener());
		administratorView.setViewActivityButton(new ViewActivityListener());
		administratorView.setBtnGenerateReport(new generateReportListener());
		administratorView.setBtnUpdateuserActionListener(new updateActionListener());
	}
	private class updateActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			Long userId=Long.parseLong(administratorView.getIdField().getText());
			String username = administratorView.getUserField().getText();
			String password = administratorView.getPassField().getText();
			String role=administratorView.getRoleField().getText();
			Notification<Boolean> regNotification = userService.updateUser(userId, username, password,role);
			if (regNotification.hasErrors()) {
				JOptionPane.showMessageDialog(null, regNotification.getFormattedErrors());
			} else {
				if (!regNotification.getResult())
					JOptionPane.showMessageDialog(null, "Update not successful, please try again later.");
				else
				{
					User user=userService.findByUsername(loginView.getUsername());
					activityService.addActivity(Constants.Activities.UPDATEUSER, user.getId());
					JOptionPane.showMessageDialog(null, "Update of the user done succesful.");
				}
			}
			
		}
		
	}
	private class generateReportListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try
			{
				Long userId=Long.parseLong(administratorView.getUserIdReport().getText());
				DateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
				Date dateFrom= new java.sql.Date(simpleDateFormat.parse(administratorView.getDateFrom().getText()).getTime());
				Date dateTo=  new java.sql.Date(simpleDateFormat.parse(administratorView.getDateTo().getText()).getTime());
				activityService.findFromDateToDate(userId, dateFrom, dateTo);
				JOptionPane.showMessageDialog(null, "Activity report for user with id "+userId+" was generated succesfully");
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		
	}
	private class ViewActivityListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try
			{
				DefaultTableModel tablemodel=activityService.fillActivityData();
				administratorView.getActivitiesTable().setModel(tablemodel);
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		
	}

	private class LogOutActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			loginView.setVisible(true);
			administratorView.setVisible(false);
			loginView.getTfUsername().setText("");
			loginView.getTfPassword().setText("");
			
		}
		
	}

	private class removeActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try {
				long id = Integer.parseInt(administratorView.getIdField().getText());
				User user=userService.findByUsername(loginView.getUsername());
				userService.removeUser(id);
				activityService.addActivity(Constants.Activities.REMOVEUSER, user.getId());
				JOptionPane.showMessageDialog(null, "Deletion of the user was performed succesfully");
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,
						"An error occured when trying to delete the user. Make sure field id is given correctly");
			}

		}

	}

	public void setVisibility() {
		administratorView.setVisible(true);
	}

	private class viewActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try {
				DefaultTableModel tableModel = userService.fillUserData();
				administratorView.getUsersTable().setModel(tableModel);

			} catch (Exception e) {
				e.printStackTrace();

			}

		}

	}

	private class AddButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String username = administratorView.getUserField().getText();
			String password = administratorView.getPassField().getText();
			String role = administratorView.getRoleField().getText();
			System.out.println(username);
			System.out.println(password);
			System.out.println(role);
			Notification<Boolean> regNotification = authenticationService.register(username, password, role);
			if (regNotification.hasErrors()) {
				JOptionPane.showMessageDialog(null, regNotification.getFormattedErrors());
			} else {
				if (!regNotification.getResult())
					JOptionPane.showMessageDialog(null, "Registration not successful, please try again later.");
				else
				{
					User user=userService.findByUsername(loginView.getUsername());
					activityService.addActivity(Constants.Activities.ADDUSER, user.getId());
					JOptionPane.showMessageDialog(null, "Addition of a new user done succesfull.");
				}
			}

		}

	}

}
