package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionListener;

import javax.swing.JButton;

public class AdministratorView extends JFrame{
	
	private JPanel contentPane;
	private JTextField userField;
	private JTextField passField;
	private JButton AddUserButton;
	private JButton btnRemoveuser;
	private JButton btnUpdateuser;
	private JButton btnViewusers;
	private JLabel lblIdToPerform;
	private JTextField idField;
	private JLabel lblRole;
	private JTextField roleField;
	private JTable usersTable;
	private JButton btnNewButton;
	private JButton viewActivityButton;
	private JTable activitiesTable;
	private JTextField DateFrom;
	private JTextField DateTo;
	public JTextField getDateFrom() {
		return DateFrom;
	}

	public JTextField getUserIdReport() {
		return userIdReport;
	}

	public JTextField getDateTo() {
		return DateTo;
	}
	private JButton btnGeneratereport;
	private JTextField userIdReport;
	private JLabel lblIdForUser;
	
	public void setBtnGenerateReport(ActionListener generateReportListener)
	{
		btnGeneratereport.addActionListener(generateReportListener);
	}
	
	public JTable getActivitiesTable() {
		return activitiesTable;
	}
	public void setViewActivityButton(ActionListener viewActivityListener)
	{
		viewActivityButton.addActionListener(viewActivityListener);
	}
	public void setAddUserButtonActionListener(ActionListener addButtonActionListener)
	{
		AddUserButton.addActionListener(addButtonActionListener);
	}
	public void setBtnRemoveuserActionListner(ActionListener removeActionListener)
	{
		btnRemoveuser.addActionListener(removeActionListener);
	}
	public void setBtnUpdateuserActionListener(ActionListener updateActionListner)
	{
		btnUpdateuser.addActionListener(updateActionListner);
	}
	public void setBtnViewusersActionListener(ActionListener viewActionListener)
	{
		btnViewusers.addActionListener(viewActionListener);;
	}
	
	public JTextField getUserField() {
		return userField;
	}

	public JTextField getPassField() {
		return passField;
	}

	public JTextField getIdField() {
		return idField;
	}

	public AdministratorView()
	{
		setTitle("AdministratorOptions");
		setBounds(10, 10, 1000, 550);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		initializeFields();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(false);
	}

	public JTextField getRoleField() {
		return roleField;
	}
	private void initializeFields() {
		
		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setBounds(43, 36, 62, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(43, 82, 46, 14);
		contentPane.add(lblPassword);
		
		userField = new JTextField();
		userField.setBounds(115, 33, 86, 20);
		contentPane.add(userField);
		userField.setColumns(10);
		
		passField = new JTextField();
		passField.setBounds(115, 79, 86, 20);
		contentPane.add(passField);
		passField.setColumns(10);
		
		AddUserButton = new JButton("AddUser");
		AddUserButton.setBounds(43, 149, 89, 23);
		contentPane.add(AddUserButton);
		
		btnRemoveuser = new JButton("RemoveUser");
		btnRemoveuser.setBounds(162, 149, 108, 23);
		contentPane.add(btnRemoveuser);
		
		btnUpdateuser = new JButton("UpdateUser");
		btnUpdateuser.setBounds(295, 149, 89, 23);
		contentPane.add(btnUpdateuser);
		
		btnViewusers = new JButton("ViewUsers");
		btnViewusers.setBounds(405, 149, 89, 23);
		contentPane.add(btnViewusers);
		
		lblIdToPerform = new JLabel("Id to perform delete/update");
		lblIdToPerform.setBounds(257, 36, 156, 14);
		contentPane.add(lblIdToPerform);
		
		idField = new JTextField();
		idField.setBounds(295, 61, 86, 20);
		contentPane.add(idField);
		idField.setColumns(10);
		
		lblRole = new JLabel("Role");
		lblRole.setBounds(43, 124, 46, 14);
		contentPane.add(lblRole);
		
		roleField = new JTextField();
		roleField.setBounds(115, 118, 86, 20);
		contentPane.add(roleField);
		roleField.setColumns(10);
		
		usersTable=new JTable();
		JScrollPane scrollPanel2 = new JScrollPane(usersTable);
		scrollPanel2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanel2.setBounds(10, 220, 450, 230);
		contentPane.add(scrollPanel2);
		
		activitiesTable=new JTable();
		
		btnNewButton = new JButton("LogOut");
		btnNewButton.setBounds(783, 461, 118, 39);
		contentPane.add(btnNewButton);
		
		viewActivityButton=new JButton("viewActivityTable");
		viewActivityButton.setBounds(550, 149, 133, 25);
		contentPane.add(viewActivityButton);		
		
		JScrollPane scrollPane = new JScrollPane(activitiesTable);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(508, 220, 393, 230);
		contentPane.add(scrollPane);
		
		JLabel lblDateFromWhich = new JLabel("Date From Which you want to generate a report");
		lblDateFromWhich.setBounds(535, 36, 239, 14);
		contentPane.add(lblDateFromWhich);
		
		DateFrom = new JTextField();
		DateFrom.setBounds(786, 33, 86, 20);
		contentPane.add(DateFrom);
		DateFrom.setColumns(10);
		
		JLabel lblDateToWhich = new JLabel("Date To Which you want to generate a report");
		lblDateToWhich.setBounds(535, 79, 239, 14);
		contentPane.add(lblDateToWhich);
		
		DateTo = new JTextField();
		DateTo.setBounds(786, 79, 86, 20);
		contentPane.add(DateTo);
		DateTo.setColumns(10);
		
		btnGeneratereport = new JButton("GenerateReport");
		btnGeneratereport.setBounds(716, 149, 133, 23);
		contentPane.add(btnGeneratereport);
		
		userIdReport = new JTextField();
		userIdReport.setBounds(786, 121, 86, 20);
		contentPane.add(userIdReport);
		userIdReport.setColumns(10);
		
		lblIdForUser = new JLabel("Id for user you want to generate a report");
		lblIdForUser.setBounds(535, 124, 239, 14);
		contentPane.add(lblIdForUser);
	}
	public void setBtnNewButtonActionListener(ActionListener logoutListener)
	{
		btnNewButton.addActionListener(logoutListener);
	}
	public JTable getUsersTable() {
		return usersTable;
	}
}
