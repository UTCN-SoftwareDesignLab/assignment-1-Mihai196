package view;

import java.awt.HeadlessException;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

public class RegularUserView extends JFrame{
	
	private JPanel contentPane;
	private JButton addClientButton;
	private JButton removeClientButton;
	private JButton updateClientButton;
	private JButton viewClientsButton;
	private JTextField nameField;
	private JTextField idCardField;
	private JTextField persNrCodeField;
	private JTextField address;
	private JTextField clientIdField;
	private JTable clientTable;
	
	public RegularUserView() throws HeadlessException
	{
		setTitle("RegularUserOption");
		setBounds(10,10,550,550);
		contentPane =new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		initializeFields();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
		
	}
	public void initializeFields()
	{
		addClientButton=new JButton("AddClient");
		removeClientButton=new JButton("RemoveClient");
		updateClientButton=new JButton("UpdateClient");
		viewClientsButton = new JButton("View Clients");
		clientTable=new JTable();
		addClientButton.setBounds(10, 200, 90, 20);
		removeClientButton.setBounds(120,200,120,20);
		updateClientButton.setBounds(250,200,120,20);
		viewClientsButton.setBounds(380,200 , 120, 20);
		contentPane.add(addClientButton);
		contentPane.add(removeClientButton);
		contentPane.add(updateClientButton);
		contentPane.add(viewClientsButton);
		
		JScrollPane scrollPanel2=new JScrollPane(clientTable);
		scrollPanel2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanel2.setBounds(10,220,450,230);
		contentPane.add(scrollPanel2);
		
		JLabel labelName=new JLabel("Name");
		labelName.setBounds(30,20,60,20);
		nameField=new JTextField();
		nameField.setBounds(100, 20, 100, 20);
		
		JLabel labelidCard=new JLabel("idCard");
		labelidCard.setBounds(30, 60, 60, 20);
		idCardField=new JTextField();
		idCardField.setBounds(100, 60, 100, 20);
		
		JLabel pnc=new JLabel("PNC");
		pnc.setBounds(30, 100, 60, 20);
		persNrCodeField=new JTextField();
		persNrCodeField.setBounds(100, 100, 100, 20);
		
		JLabel addr=new JLabel("Address");
		addr.setBounds(30,140,100,20);
		address=new JTextField();
		address.setBounds(100, 140, 100, 20);
		
		JLabel idd=new JLabel("Id for update/delete");
		idd.setBounds(220, 20, 150, 20);
		clientIdField=new JTextField();
		clientIdField.setBounds(350, 20, 100, 20);
		
		contentPane.add(idd);
		contentPane.add(pnc);
		contentPane.add(addr);
		contentPane.add(labelidCard);
		contentPane.add(labelName);
		contentPane.add(clientIdField);
		contentPane.add(nameField);
		contentPane.add(idCardField);
		contentPane.add(persNrCodeField);
		contentPane.add(address);
	}
	public void setAddClientButtonListener(ActionListener addClientButtonListener)
	{
		addClientButton.addActionListener(addClientButtonListener);
	}
	public void setRemoveClientButtonListener(ActionListener removeClientButtonListener)
	{
		removeClientButton.addActionListener(removeClientButtonListener);
	}
	public void setUpdateClientButtonListener(ActionListener updateClientButtonListener)
	{
		updateClientButton.addActionListener(updateClientButtonListener);
	}
	public void setViewClientsButtonListener(ActionListener viewClientsButtonListener)
	{
		viewClientsButton.addActionListener(viewClientsButtonListener);
	}
	public JTable getClientTable()
	{
		return clientTable;
	}
	public String getNameField() {
		return nameField.getText();
	}
	public String getIdCardField() {
		return idCardField.getText();
	}
	public String getPersNrCodeField() {
		return persNrCodeField.getText();
	}
	public String getAddress() {
		return address.getText();
	}
	public String getClientIdField() {
		return clientIdField.getText();
	}
	
	
	

}
