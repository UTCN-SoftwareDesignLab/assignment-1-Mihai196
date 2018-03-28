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
		
	}
	public JTable getUsersTable() {
		return usersTable;
	}
}
