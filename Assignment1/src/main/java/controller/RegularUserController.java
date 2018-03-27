package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.Client;
import model.builder.ClientBuilder;
import service.client.ClientService;
import view.RegularUserView;

public class RegularUserController {
	
	private RegularUserView regularUserView;
	private ClientService clientService;
	
	public RegularUserController(ClientService clientService) {
		super();
		this.regularUserView = new RegularUserView();
		this.clientService = clientService;
		regularUserView.setAddClientButtonListener(new AddClientButtonListener());
		regularUserView.setRemoveClientButtonListener(new RemoveClientButtonListener());
		regularUserView.setUpdateClientButtonListener(new UpdateClientButtonListener());
		regularUserView.setViewClientsButtonListener(new ViewClientsButtonListener());
	}
	private class ViewClientsButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try
			{
				DefaultTableModel tablemodel=clientService.fillClientData();
				regularUserView.getClientTable().setModel(tablemodel);
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "An error occured while trying to show the clients");
			}
			
		}
		
	}
	private class AddClientButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try
			{
			String name=regularUserView.getNameField();
			Long idCardNr=Long.parseLong(regularUserView.getIdCardField());
			Long persNrCode=Long.parseLong(regularUserView.getPersNrCodeField());
			String address=regularUserView.getAddress();
			
			Client c=new ClientBuilder()
					.setName(name)
					.setIdCardNr(idCardNr)
					.setPersNrCode(persNrCode)
					.setAddress(address).build();
			clientService.addClient(c);
			JOptionPane.showMessageDialog(null, "Client with the name " + name + "was added succesfully to the database");
			}
			catch (Exception e)
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "An error occured while trying to add the client. Please make sure fields are given with the correct type");
			}
		}
		
	}
	private class RemoveClientButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try
			{
			int id=Integer.parseInt(regularUserView.getClientIdField());
			
			Client c=new ClientBuilder()
					.setId(id).build();
			clientService.deleteClient(c);
			JOptionPane.showMessageDialog(null, "Client with the Id " + id + "was deleted succesfully to the database");
			}
			catch (Exception e)
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "An error occured while trying to delete. Please make sure you give a valid client id");
				
			}
			
		}
		
	}
	private class UpdateClientButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try
			{
			String name=regularUserView.getNameField();
			Long idCardNr=Long.parseLong(regularUserView.getIdCardField());
			Long persNrCode=Long.parseLong(regularUserView.getPersNrCodeField());
			String address=regularUserView.getAddress();
			int id=Integer.parseInt(regularUserView.getClientIdField());
			
			Client c=new ClientBuilder()
					.setName(name)
					.setIdCardNr(idCardNr)
					.setPersNrCode(persNrCode)
					.setId(id)
					.setAddress(address).build();
			clientService.updateClient(c);
			JOptionPane.showMessageDialog(null, "Client data for client " + name + "were updated succesfully to the database");
			}
			catch (Exception e)
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "An error occured while trying to update the client. Please make sure fields are given with the correct type");
			}
			
		}
		
	}
	
	
	
	

}
