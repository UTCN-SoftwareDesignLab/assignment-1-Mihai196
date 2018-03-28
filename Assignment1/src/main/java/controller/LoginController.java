package controller;

import model.Role;
import model.User;
import model.validation.Notification;
import repository.user.AuthenticationException;
import service.user.AuthenticationService;
import view.AdministratorView;
import view.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Alex on 18/03/2017.
 */
public class LoginController {
	private final LoginView loginView;
	private final AdministratorController administratorController;
	private final AuthenticationService authenticationService;

	private RegularUserController regularUserController;

	public LoginController(LoginView loginView, AuthenticationService authenticationService,
			RegularUserController regularUserController, AdministratorController administratorController) {
		this.loginView = loginView;
		this.authenticationService = authenticationService;
		this.regularUserController = regularUserController;
		this.administratorController = administratorController;
		loginView.setLoginButtonListener(new LoginButtonListener());
		// loginView.setRegisterButtonListener(new RegisterButtonListener());
	}

	private class LoginButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String username = loginView.getUsername();
			String password = loginView.getPassword();

			Notification<User> loginNotification = null;
			String role = "";
			try {
				loginNotification = authenticationService.login(username, password);
				role = loginNotification.getResult().getRoles().get(0).getRole();
				// we take the role of the user who is trying to login
			} catch (AuthenticationException e1) {
				e1.printStackTrace();
			}

			if (loginNotification != null) {
				if (loginNotification.hasErrors()) {
					JOptionPane.showMessageDialog(loginView.getContentPane(), loginNotification.getFormattedErrors());
				} else {
					JOptionPane.showMessageDialog(loginView.getContentPane(), "Login successful!");
					if (role.equals("employee")) {
						// make view visible for employee
						regularUserController.setVisibilityforView();
					} else if (role.equals("administrator")) {
						// make view visible for administrator
						administratorController.setVisibility();
					}
					loginView.setVisible(false);
				}
			}
		}
	}

	/*
	 * private class RegisterButtonListener implements ActionListener {
	 * 
	 * @Override public void actionPerformed(ActionEvent e) { String username =
	 * loginView.getUsername(); String password = loginView.getPassword();
	 * 
	 * String role="Admin"; Notification<Boolean> registerNotification =
	 * authenticationService.register(username, password,role); if
	 * (registerNotification.hasErrors()) {
	 * JOptionPane.showMessageDialog(loginView.getContentPane(),
	 * registerNotification.getFormattedErrors()); } else { if
	 * (!registerNotification.getResult()) {
	 * JOptionPane.showMessageDialog(loginView.getContentPane(),
	 * "Registration not successful, please try again later."); } else {
	 * JOptionPane.showMessageDialog(loginView.getContentPane(),
	 * "Registration successful!"); //set vsibility for regular user view
	 * regularUserController.setVisibilityforView(); } } } }
	 */

}
