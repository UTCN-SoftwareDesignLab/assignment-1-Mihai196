package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import database.Constants;
import model.Account;
import model.Bill;
import model.Client;
import model.User;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;
import model.validation.Notification;
import service.account.AccountService;
import service.activity.ActivityService;
import service.bill.BillService;
import service.client.ClientService;
import service.user.UserService;
import view.LoginView;
import view.RegularUserView;

public class RegularUserController {

	private RegularUserView regularUserView;
	private ClientService clientService;
	private AccountService accountService;
	private ActivityService activityService;
	private UserService userService;
	private BillService billService;
	private LoginView loginView;

	public RegularUserController(ClientService clientService, AccountService accountService,
			RegularUserView regularUserView, LoginView loginView, ActivityService activityService,
			UserService userService, BillService billService) {
		super();
		this.regularUserView = regularUserView;
		this.clientService = clientService;
		this.accountService = accountService;
		this.loginView = loginView;
		this.activityService = activityService;
		this.userService = userService;
		this.billService = billService;
		regularUserView.setAddClientButtonListener(new AddClientButtonListener());
		regularUserView.setRemoveClientButtonListener(new RemoveClientButtonListener());
		regularUserView.setUpdateClientButtonListener(new UpdateClientButtonListener());
		regularUserView.setViewClientsButtonListener(new ViewClientsButtonListener());
		regularUserView.setAddAccountButtonListener(new AddAccountButtonListener());
		regularUserView.setViewAccountButtonListener(new ViewAccountButtonListener());
		regularUserView.setRemoveAccountButtonListener(new RemoveAccountButtonListener());
		regularUserView.setUpdateAccountButtonListener(new UpdateAccountButtonListener());
		regularUserView.setTransferButtonListener(new transferButtonListener());
		regularUserView.setLogOutButtonListener(new LogOutButtonListener());
		regularUserView.setBtnViewBillsActionListener(new btnViewBillsActionListener());
		regularUserView.setBtnPayBillActionListener(new btnPayBillActionListener());
		regularUserView.setBtnAddBillActionListener(new addBillActionListener());
		regularUserView.setBtnViewAccountsForClientActionListener(new viewAccountsForClientListener());
	}

	private class viewAccountsForClientListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try {
				int id = Integer.parseInt(regularUserView.getClientField());
				List<Account> accountsForClient = accountService.findAccountsClient(id);
				String allAccounts = "";
				for (Account a : accountsForClient) {
					allAccounts += a.toString() + System.lineSeparator();
				}
				JOptionPane.showMessageDialog(null, allAccounts);
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Make sure you give a valid clientId to see his accounts");
			}
		}

	}

	private class addBillActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try {
				double sumToPay = Double.parseDouble(regularUserView.getSumToPayBill().getText());
				String company = regularUserView.getCompanyBill().getText();
				int clientId = Integer.parseInt(regularUserView.getClientIdBill().getText());
				Notification<Boolean> billNotification = billService.addBill(sumToPay, company, clientId);
				if (!billNotification.hasErrors()) {
					JOptionPane.showMessageDialog(null, "The new bill was added succesfully to the database");
				} else {
					JOptionPane.showMessageDialog(null, billNotification.getFormattedErrors());
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	private class btnPayBillActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try {
				int billId = Integer.parseInt(regularUserView.getBillId().getText());
				int accBillId = Integer.parseInt(regularUserView.getAccountIdBill().getText());
				Bill bill = billService.findBillById(billId);
				Client client = clientService.findById(bill.getClientId());
				List<Account> accountsForClient = accountService.findAccountsClient(bill.getClientId());
				boolean search = false;
				String allAccounts="";
				for (Account a : accountsForClient) {
					if (a.getId() == accBillId)
						search = true;
					allAccounts += a.toString() + System.lineSeparator();
				}
				if (!search ) {
					JOptionPane.showMessageDialog(null,
							"Please provide an account id owned by the client which pays the bill.Available Options: "+System.lineSeparator()+allAccounts);
				} else {
					Notification<Boolean> billNotification = billService.processBill(billId, accBillId);
					if (!billNotification.hasErrors()) {
						User user = userService.findByUsername(loginView.getUsername());
						activityService.addActivity(Constants.Activities.BILLPAYMENT, user.getId());
						JOptionPane.showMessageDialog(null, "The process of the bill with id " + billId
								+ " was done successfully.Receipt generated");
						String text1 = "S.C. " + bill.getCompany() + " S.R.L. \n";
						String text2 = "Receipt for the bill with id " + bill.getId() + " was paid at the date "
								+ new java.sql.Date(System.currentTimeMillis());
						String text3 = "Client : " + client.getName() + " having the personal numerical code "
								+ client.getPersNrCode();
						String text4 = "Total amount paid " + bill.getSumToPay() + " dollars $";
						String text = text1 + System.lineSeparator() + text2 + System.lineSeparator() + text3
								+ System.lineSeparator() + text4;
						try (PrintWriter out = new PrintWriter("ReceiptBill.txt")) {
							out.println(text);
						}
					} else {
						JOptionPane.showMessageDialog(null, billNotification.getFormattedErrors());
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,
						"An error occured. Please make sure you give the requested fields with the right types");
			}
		}

	}

	private class btnViewBillsActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try {
				DefaultTableModel tablemodel = billService.fillBillData();
				regularUserView.getBillsTable().setModel(tablemodel);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	private class LogOutButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			loginView.setVisible(true);
			regularUserView.setVisible(false);
			loginView.getTfUsername().setText("");
			//loginView.getTfPassword().setText("");

		}

	}

	private class transferButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try {
				int id1 = Integer.parseInt(regularUserView.getIdAcc1Field().getText());
				int id2 = Integer.parseInt(regularUserView.getIDAcc2Field().getText());
				double sum = Double.parseDouble(regularUserView.getSumField().getText());
				Notification<Boolean> transferNotification = accountService.transferMoney(id1, id2, sum);
				if (transferNotification.hasErrors()) {
					JOptionPane.showMessageDialog(null, transferNotification.getFormattedErrors());
				} else {
					User user = userService.findByUsername(loginView.getUsername());
					activityService.addActivity(Constants.Activities.MONEYTRANSFER, user.getId());
					JOptionPane.showMessageDialog(null, "Transfer between accounts was done succesfully");
				}
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "An error occured when trying to do the transfer.Please"
						+ " make sure you give valid ids and sums of the correct types");
			}

		}

	}

	private class UpdateAccountButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

			try {
				String type = regularUserView.getTypeField();

				double balance = Double.parseDouble(regularUserView.getBalanceField());

				int clientId = Integer.parseInt(regularUserView.getClientField());
				// System.out.println(clientId);
				int id = Integer.parseInt(regularUserView.getAccIdField());
				Notification<Boolean> accountNotification = accountService.updateAccount(type, balance, clientId, id);

				if (accountNotification.hasErrors()) {
					JOptionPane.showMessageDialog(null, accountNotification.getFormattedErrors());
				} else {
					User user = userService.findByUsername(loginView.getUsername());
					activityService.addActivity(Constants.Activities.UPDATEDACCOUNT, user.getId());
					JOptionPane.showMessageDialog(null,
							"Account data for account with id " + id + " were updated succesfully to the database");
				}

			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "An error occured when trying to update the account");
			}
		}

	}

	public void setVisibilityforView() {
		regularUserView.setVisible(true);
	}

	private class RemoveAccountButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try {
				int id = Integer.parseInt(regularUserView.getAccIdField());
				accountService.deleteAccount(id);
				User user = userService.findByUsername(loginView.getUsername());
				activityService.addActivity(Constants.Activities.REMOVEACCOUNT, user.getId());
				JOptionPane.showMessageDialog(null,
						"Account with id " + id + " was deleted succesfully from the database");
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,
						"An error occured when trying to delete the account. Make sure you use the right types for id");
			}

		}

	}

	private class ViewAccountButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try {
				DefaultTableModel tablemodel = accountService.fillAccountData();
				regularUserView.getAccTable().setModel(tablemodel);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	private class AddAccountButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

			try {
				String type = regularUserView.getTypeField();
				// System.out.println("The type is" + type);
				double balance = Double.parseDouble(regularUserView.getBalanceField());
				// System.out.println("The balance is" + balance);
				int clientId = Integer.parseInt(regularUserView.getClientField());
				// System.out.println(clientId);
				Notification<Boolean> accountNotification = accountService.addAccount(type, balance, clientId);

				if (accountNotification.hasErrors()) {
					JOptionPane.showMessageDialog(null, accountNotification.getFormattedErrors());
				} else {
					User user = userService.findByUsername(loginView.getUsername());
					activityService.addActivity(Constants.Activities.ADDACCOUNT, user.getId());
					JOptionPane.showMessageDialog(null,
							"Account for client with id" + clientId + "was added succesfully to the database");
				}
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,
						"An error occured when trying to add the account. Make sure you use the right type for fields");
			}

		}

	}

	private class ViewClientsButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try {
				DefaultTableModel tablemodel = clientService.fillClientData();
				regularUserView.getClientTable().setModel(tablemodel);

			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "An error occured while trying to show the clients");
			}

		}

	}

	private class AddClientButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try {
				String name = regularUserView.getNameField();
				Long idCardNr = Long.parseLong(regularUserView.getIdCardField());
				Long persNrCode = Long.parseLong(regularUserView.getPersNrCodeField());
				String address = regularUserView.getAddress();
				Notification<Boolean> clientNotification = clientService.addClient(name, idCardNr, persNrCode, address);

				if (clientNotification.hasErrors()) {
					JOptionPane.showMessageDialog(null, clientNotification.getFormattedErrors());
				} else {
					User user = userService.findByUsername(loginView.getUsername());
					activityService.addActivity(Constants.Activities.ADDCLIENT, user.getId());
					JOptionPane.showMessageDialog(null,
							"Client with the name " + name + " was added succesfully to the database");
				}
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,
						"An error occured while trying to add the client. Please make sure fields are given with the correct type");
			}
		}

	}

	private class RemoveClientButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try {
				int id = Integer.parseInt(regularUserView.getClientIdField());
				clientService.deleteClient(id);
				User user = userService.findByUsername(loginView.getUsername());
				activityService.addActivity(Constants.Activities.REMOVECLIENT, user.getId());
				JOptionPane.showMessageDialog(null,
						"Client with the Id " + id + " was deleted succesfully from the database");
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,
						"An error occured while trying to delete. Please make sure you give a valid client id");

			}

		}

	}

	private class UpdateClientButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try {
				String name = regularUserView.getNameField();
				Long idCardNr = Long.parseLong(regularUserView.getIdCardField());
				Long persNrCode = Long.parseLong(regularUserView.getPersNrCodeField());
				String address = regularUserView.getAddress();
				int id = Integer.parseInt(regularUserView.getClientIdField());
				Notification<Boolean> clientNotification = clientService.updateClient(id, name, idCardNr, persNrCode,
						address);

				if (clientNotification.hasErrors()) {
					JOptionPane.showMessageDialog(null, clientNotification.getFormattedErrors());
				} else {
					User user = userService.findByUsername(loginView.getUsername());
					activityService.addActivity(Constants.Activities.UPDATEDCLIENT, user.getId());
					JOptionPane.showMessageDialog(null,
							"Client data for client " + name + " were updated succesfully to the database");
				}
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,
						"An error occured while trying to update the client. Please make sure fields are given with the correct type");
			}

		}

	}

}
