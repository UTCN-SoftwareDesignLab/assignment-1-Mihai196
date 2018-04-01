package view;

import java.awt.HeadlessException;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

public class RegularUserView extends JFrame {

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

	private JTable accTable;

	private JLabel lblType;
	private JLabel lblBalance;
	private JLabel lblNewLabel;
	private JTextField typeField;
	private JTextField balanceField;
	private JTextField clientField;
	private JTextField accIdField;

	private JButton addAccButton;
	private JButton removeAccButton;
	private JButton updateAccButton;
	private JButton viewAccButton;
	private JTextField IdAcc1Field;
	private JTextField IDAcc2Field;
	private JTextField SumField;
	private JButton btnTransfer;
	private JButton LogOutButton;
	private JButton btnViewbills;
	private JTable billsTable;
	private JTextField BillId;
	private JTextField accountIdBill;
	
	private JButton btnPaybill;
	private JLabel lblNewLabel_1;
	private JTextField sumToPayBill;
	private JLabel lblNewLabel_2;
	private JTextField CompanyBill;
	private JLabel lblNewLabel_3;
	private JTextField ClientIdBill;
	
	public JTextField getSumToPayBill() {
		return sumToPayBill;
	}

	public JTextField getCompanyBill() {
		return CompanyBill;
	}

	public JTextField getClientIdBill() {
		return ClientIdBill;
	}

	private JButton btnAddbill;
	private JButton btnViewaccountsforclient;
	
	

	public RegularUserView() throws HeadlessException {
		setTitle("RegularUserOption");
		setBounds(10, 10, 1350, 550);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		initializeFields();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(false);
	}

	public void initializeFields() {
		addClientButton = new JButton("AddClient");
		removeClientButton = new JButton("RemoveClient");
		updateClientButton = new JButton("UpdateClient");
		viewClientsButton = new JButton("View Clients");
		clientTable = new JTable();
		accTable = new JTable();
		billsTable=new JTable();
		addClientButton.setBounds(10, 200, 90, 20);
		removeClientButton.setBounds(120, 200, 100, 20);
		updateClientButton.setBounds(250, 200, 100, 20);
		viewClientsButton.setBounds(380, 200, 100, 20);
		contentPane.add(addClientButton);
		contentPane.add(removeClientButton);
		contentPane.add(updateClientButton);
		contentPane.add(viewClientsButton);

		JScrollPane scrollPanel2 = new JScrollPane(clientTable);
		scrollPanel2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanel2.setBounds(10, 220, 450, 230);
		contentPane.add(scrollPanel2);
		
		JScrollPane scrollPanel3 = new JScrollPane(billsTable);
		scrollPanel3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanel3.setBounds(1000, 220, 300, 230);
		contentPane.add(scrollPanel3);

		JLabel labelName = new JLabel("Name");
		labelName.setBounds(30, 20, 60, 20);
		nameField = new JTextField();
		nameField.setBounds(100, 20, 100, 20);

		JLabel labelidCard = new JLabel("idCard");
		labelidCard.setBounds(30, 60, 60, 20);
		idCardField = new JTextField();
		idCardField.setBounds(100, 60, 100, 20);

		JLabel pnc = new JLabel("PNC");
		pnc.setBounds(30, 100, 60, 20);
		persNrCodeField = new JTextField();
		persNrCodeField.setBounds(100, 100, 100, 20);

		JLabel addr = new JLabel("Address");
		addr.setBounds(30, 140, 100, 20);
		address = new JTextField();
		address.setBounds(100, 140, 100, 20);

		JLabel idd = new JLabel("Id for update/delete");
		idd.setBounds(220, 20, 150, 20);
		clientIdField = new JTextField();
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

		lblType = new JLabel("Type");
		lblType.setBounds(500, 23, 46, 14);
		contentPane.add(lblType);

		lblBalance = new JLabel("Balance");
		lblBalance.setBounds(500, 63, 46, 14);
		contentPane.add(lblBalance);

		lblNewLabel = new JLabel("ClientId");
		lblNewLabel.setBounds(500, 103, 46, 14);
		contentPane.add(lblNewLabel);

		typeField = new JTextField();
		typeField.setBounds(605, 20, 86, 20);
		contentPane.add(typeField);
		typeField.setColumns(10);

		balanceField = new JTextField();
		balanceField.setBounds(605, 60, 86, 20);
		contentPane.add(balanceField);
		balanceField.setColumns(10);

		clientField = new JTextField();
		clientField.setBounds(605, 100, 86, 20);
		contentPane.add(clientField);
		clientField.setColumns(10);

		accIdField = new JTextField();
		accIdField.setBounds(746, 48, 86, 20);
		contentPane.add(accIdField);
		accIdField.setColumns(10);

		JLabel lblAccountIdDeletioncreation = new JLabel("Account id deletion/creation");
		lblAccountIdDeletioncreation.setBounds(746, 23, 172, 14);
		contentPane.add(lblAccountIdDeletioncreation);

		addAccButton = new JButton("AddAccount");
		addAccButton.setBounds(500, 200, 110, 20);
		contentPane.add(addAccButton);

		removeAccButton = new JButton("RemoveAcc");
		removeAccButton.setBounds(620, 200, 110, 20);
		contentPane.add(removeAccButton);

		updateAccButton = new JButton("UpdateAcc");
		updateAccButton.setBounds(740, 200, 100, 20);
		contentPane.add(updateAccButton);

		viewAccButton = new JButton("ViewAccs");
		viewAccButton.setBounds(850, 200, 100, 20);
		contentPane.add(viewAccButton);

		JScrollPane scrollPanel1 = new JScrollPane(accTable);
		scrollPanel1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanel1.setBounds(510, 220, 450, 230);
		contentPane.add(scrollPanel1);
		
		JLabel lblTransferMoneyBetween = new JLabel("Transfer Money between 2 accounts");
		lblTransferMoneyBetween.setBounds(746, 103, 274, 14);
		contentPane.add(lblTransferMoneyBetween);
		
		JLabel lblIdacc = new JLabel("IDAcc1");
		lblIdacc.setBounds(705, 143, 46, 14);
		contentPane.add(lblIdacc);
		
		IdAcc1Field = new JTextField();
		IdAcc1Field.setBounds(746, 140, 34, 20);
		contentPane.add(IdAcc1Field);
		IdAcc1Field.setColumns(10);
		
		JLabel lblIdacc_1 = new JLabel("IDAcc2");
		lblIdacc_1.setBounds(790, 143, 46, 14);
		contentPane.add(lblIdacc_1);
		
		IDAcc2Field = new JTextField();
		IDAcc2Field.setBounds(833, 140, 34, 20);
		contentPane.add(IDAcc2Field);
		IDAcc2Field.setColumns(10);
		
		JLabel lblSum = new JLabel("Sum");
		lblSum.setBounds(872, 143, 46, 14);
		contentPane.add(lblSum);
		
		SumField = new JTextField();
		SumField.setBounds(898, 140, 34, 20);
		contentPane.add(SumField);
		SumField.setColumns(10);
	    
		btnTransfer = new JButton("Transfer");
		btnTransfer.setBounds(743, 166, 89, 23);
		contentPane.add(btnTransfer);
		
		LogOutButton = new JButton("LogOut");
		LogOutButton.setBounds(1242, 461, 110, 39);
		contentPane.add(LogOutButton);
		
		btnViewbills = new JButton("ViewBills");
		btnViewbills.setBounds(1000, 166, 89, 23);
		contentPane.add(btnViewbills);
		
		JLabel lblIdForBill = new JLabel("Id for Bill you want to Pay");
		lblIdForBill.setBounds(1000, 19, 150, 23);
		contentPane.add(lblIdForBill);
		
		BillId = new JTextField();
		BillId.setBounds(1032, 48, 86, 20);
		contentPane.add(BillId);
		BillId.setColumns(10);
		
		JLabel lblAccountIdFrom = new JLabel("Account id for payment");
		lblAccountIdFrom.setBounds(1003, 78, 147, 14);
		contentPane.add(lblAccountIdFrom);
		
		accountIdBill = new JTextField();
		accountIdBill.setBounds(1035, 100, 86, 20);
		contentPane.add(accountIdBill);
		accountIdBill.setColumns(10);
		
		btnPaybill = new JButton("PayBill");
		btnPaybill.setBounds(1000, 131, 89, 23);
		contentPane.add(btnPaybill);
		
		lblNewLabel_1 = new JLabel("sumToPay");
		lblNewLabel_1.setBounds(1158, 23, 60, 14);
		contentPane.add(lblNewLabel_1);
		
		sumToPayBill = new JTextField();
		sumToPayBill.setBounds(1189, 48, 86, 20);
		contentPane.add(sumToPayBill);
		sumToPayBill.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Company");
		lblNewLabel_2.setBounds(1158, 78, 60, 14);
		contentPane.add(lblNewLabel_2);
		
		CompanyBill = new JTextField();
		CompanyBill.setBounds(1189, 100, 86, 20);
		contentPane.add(CompanyBill);
		CompanyBill.setColumns(10);
		
		lblNewLabel_3 = new JLabel("ClientId");
		lblNewLabel_3.setBounds(1158, 135, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		ClientIdBill = new JTextField();
		ClientIdBill.setBounds(1189, 160, 86, 20);
		contentPane.add(ClientIdBill);
		ClientIdBill.setColumns(10);
		
		btnAddbill = new JButton("AddBill");
		btnAddbill.setBounds(1189, 191, 89, 23);
		contentPane.add(btnAddbill);
		
		btnViewaccountsforclient = new JButton("ViewAccountsForClient");
		btnViewaccountsforclient.setBounds(500, 159, 191, 23);
		contentPane.add(btnViewaccountsforclient);
	}
	public void setBtnViewAccountsForClientActionListener(ActionListener viewAccountsForClientListener)
	{
		btnViewaccountsforclient.addActionListener(viewAccountsForClientListener);
	}
	public void setBtnAddBillActionListener(ActionListener addBillActionListener)
	{
		btnAddbill.addActionListener(addBillActionListener);
	}
	public void setBtnPayBillActionListener (ActionListener btnPayBIllActionListenr)
	{
		btnPaybill.addActionListener(btnPayBIllActionListenr);
	}
	public void setBtnViewBillsActionListener(ActionListener btnViewBillsActionListener)
	{
		btnViewbills.addActionListener(btnViewBillsActionListener);
	}
	public void setTransferButtonListener(ActionListener transferButtonListener)
	{
		btnTransfer.addActionListener(transferButtonListener);
	}
	public void setLogOutButtonListener(ActionListener transferButtonListener)
	{
		LogOutButton.addActionListener(transferButtonListener);
	}

	public void setAddClientButtonListener(ActionListener addClientButtonListener) {
		addClientButton.addActionListener(addClientButtonListener);
	}

	public void setRemoveClientButtonListener(ActionListener removeClientButtonListener) {
		removeClientButton.addActionListener(removeClientButtonListener);
	}

	public void setUpdateClientButtonListener(ActionListener updateClientButtonListener) {
		updateClientButton.addActionListener(updateClientButtonListener);
	}

	public void setViewClientsButtonListener(ActionListener viewClientsButtonListener) {
		viewClientsButton.addActionListener(viewClientsButtonListener);
	}

	public void setAddAccountButtonListener(ActionListener addAccBtnListener) {
		addAccButton.addActionListener(addAccBtnListener);
	}

	public void setRemoveAccountButtonListener(ActionListener remAccbtnListener) {
		removeAccButton.addActionListener(remAccbtnListener);
	}

	public void setUpdateAccountButtonListener(ActionListener updateAccbtnListener) {
		updateAccButton.addActionListener(updateAccbtnListener);
	}

	public void setViewAccountButtonListener(ActionListener viewAccbtnListener) {
		viewAccButton.addActionListener(viewAccbtnListener);
	}
	
	public JTextField getIdAcc1Field() {
		return IdAcc1Field;
	}

	public JTextField getIDAcc2Field() {
		return IDAcc2Field;
	}

	public JTextField getSumField() {
		return SumField;
	}

	public JTable getClientTable() {
		return clientTable;
	}

	public JTable getAccTable() {
		return accTable;
	}
	public JTable getBillsTable()
	{
		return billsTable;
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

	public String getAccIdField() {
		return accIdField.getText();
	}

	public String getTypeField() {
		return typeField.getText();
	}

	public String getBalanceField() {
		return balanceField.getText();
	}

	public String getClientField() {
		return clientField.getText();
	}

	public JTextField getBillId() {
		return BillId;
	}

	public JTextField getAccountIdBill() {
		return accountIdBill;
	}
}
