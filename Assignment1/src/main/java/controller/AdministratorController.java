package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.validation.Notification;
import service.user.AuthenticationService;
import service.user.UserService;
import view.AdministratorView;

public class AdministratorController {

	private AdministratorView administratorView;
	private AuthenticationService authenticationService;
	//private UserService userService;

	public AdministratorController(AuthenticationService authenticationService) {
		super();
		this.administratorView=new AdministratorView();
		this.administratorView = administratorView;
		this.authenticationService = authenticationService;
		//this.userService = userService;

		administratorView.setAddUserButtonActionListener(new AddButtonActionListener());
	}
	public void setVisibility()
	{
		administratorView.setVisible(true);
	}

	private class AddButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
				String username=administratorView.getUserField().getText();
				String password=administratorView.getPassField().getText();
				String role=administratorView.getRoleField().getText();
				System.out.println(username);
				System.out.println(password);
				System.out.println(role);
				Notification<Boolean> regNotification=authenticationService.register(username, password, role);
				if (regNotification.hasErrors())
				{
					JOptionPane.showMessageDialog(null, regNotification.getFormattedErrors());
				}
				else
				{
					if (!regNotification.getResult())
						JOptionPane.showMessageDialog(null, "Registration not successful, please try again later.");
					else
						JOptionPane.showMessageDialog(null, "Addition of a new user done succesfull.");
				}
				
			
		}

	}

}
