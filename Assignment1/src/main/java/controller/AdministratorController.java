package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.User;
import model.builder.UserBuilder;
import model.validation.Notification;
import service.user.AuthenticationService;
import service.user.UserService;
import view.AdministratorView;
import view.LoginView;

public class AdministratorController {

	private AdministratorView administratorView;
	private AuthenticationService authenticationService;
	private UserService userService;
	private LoginView loginView;

	public AdministratorController(AuthenticationService authenticationService, UserService userService,
			AdministratorView administratorView,LoginView loginView) {
		super();
		this.administratorView = administratorView;
		this.authenticationService = authenticationService;
		this.userService = userService;
		this.loginView=loginView;

		administratorView.setAddUserButtonActionListener(new AddButtonActionListener());
		administratorView.setBtnViewusersActionListener(new viewActionListener());
		administratorView.setBtnRemoveuserActionListner(new removeActionListener());
		administratorView.setBtnNewButtonActionListener(new LogOutActionListener());
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
				userService.removeUser(id);
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
					JOptionPane.showMessageDialog(null, "Addition of a new user done succesfull.");
			}

		}

	}

}
