package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.Account;
import model.Client;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;
import service.account.AccountService;
import service.client.ClientService;
import view.RegularUserView;

public class RegularUserController {

	private RegularUserView regularUserView;
	private ClientService clientService;
	private AccountService accountService;

	public RegularUserController(ClientService clientService, AccountService accountService) {
		super();
		this.regularUserView = new RegularUserView();
		this.clientService = clientService;
		this.accountService = accountService;
		regularUserView.setAddClientButtonListener(new AddClientButtonListener());
		regularUserView.setRemoveClientButtonListener(new RemoveClientButtonListener());
		regularUserView.setUpdateClientButtonListener(new UpdateClientButtonListener());
		regularUserView.setViewClientsButtonListener(new ViewClientsButtonListener());
		regularUserView.setAddAccountButtonListener(new AddAccountButtonListener());
		regularUserView.setViewAccountButtonListener(new ViewAccountButtonListener());
		regularUserView.setRemoveAccountButtonListener(new RemoveAccountButtonListener());
		regularUserView.setUpdateAccountButtonListener(new UpdateAccountButtonListener());
	}

	private class UpdateAccountButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

			try {
				String type = regularUserView.getTypeField();

				double balance = Double.parseDouble(regularUserView.getBalanceField());

				int clientId = Integer.parseInt(regularUserView.getClientField());
				System.out.println(clientId);
				int id = Integer.parseInt(regularUserView.getAccIdField());
				Account a = new AccountBuilder().setType(type).setBalance(balance).setClientId(clientId).setId(id).build();
				accountService.updateAccount(a);
				JOptionPane.showMessageDialog(null,
						"Account data for account with id " + id + "were updated succesfully to the database");

			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,
						"An error occured when trying to update the account");
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
				Account a = new AccountBuilder().setId(id).build();
				accountService.deleteAccount(a);
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
				System.out.println("The type is" + type);
				double balance = Double.parseDouble(regularUserView.getBalanceField());
				System.out.println("The balance is" + balance);
				int clientId = Integer.parseInt(regularUserView.getClientField());
				System.out.println(clientId);
				Account a = new AccountBuilder().setType(type).setBalance(balance).setClientId(clientId).build();
				System.out.println("Hello from regular user controller");
				System.out.println("Hello from account repository");
				System.out.println(a.getBalance());
				System.out.print(a.getType());
				System.out.println(a.getClientId());
				System.out.println(a.getId());
				System.out.println(a.getDateOfCreation());
				if (accountService != null) {
					System.out.println("Service working perfectly");
				}
				accountService.addAccount(a);
				JOptionPane.showMessageDialog(null,
						"Account for client with id" + clientId + "was added succesfully to the database");
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

				Client c = new ClientBuilder().setName(name).setIdCardNr(idCardNr).setPersNrCode(persNrCode)
						.setAddress(address).build();
				clientService.addClient(c);
				JOptionPane.showMessageDialog(null,
						"Client with the name " + name + "was added succesfully to the database");
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

				Client c = new ClientBuilder().setId(id).build();
				clientService.deleteClient(c);
				JOptionPane.showMessageDialog(null,
						"Client with the Id " + id + "was deleted succesfully from the database");
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

				Client c = new ClientBuilder().setName(name).setIdCardNr(idCardNr).setPersNrCode(persNrCode).setId(id)
						.setAddress(address).build();
				clientService.updateClient(c);
				JOptionPane.showMessageDialog(null,
						"Client data for client " + name + "were updated succesfully to the database");
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,
						"An error occured while trying to update the client. Please make sure fields are given with the correct type");
			}

		}

	}

}
