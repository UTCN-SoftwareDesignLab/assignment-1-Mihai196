package service.client;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.Client;
import repository.client.ClientRepository;

public class ClientServiceImpl implements ClientService {
	
	private final ClientRepository clientRepository;

	public ClientServiceImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public boolean addClient(Client client) {
		// TODO Auto-generated method stub
		return clientRepository.addClient(client);
	}

	@Override
	public boolean updateClient(Client client) {
		// TODO Auto-generated method stub
		return clientRepository.updateClient(client);
	}

	@Override
	public boolean deleteClient(Client client) {
		// TODO Auto-generated method stub
		return clientRepository.deleteClient(client);
	}

	@Override
	public Client findById(int id) {
		// TODO Auto-generated method stub
		return clientRepository.findById(id);
	}

	@Override
	public List<Client> findAllClients() {
		// TODO Auto-generated method stub
		return clientRepository.findAllClients();
	}
	public DefaultTableModel fillClientData()
	{
		DefaultTableModel tablemodel=new DefaultTableModel();
		tablemodel.addColumn("Id");
		tablemodel.addColumn("Name");
		tablemodel.addColumn("IdCardNr");
		tablemodel.addColumn("PersNrCode");
		tablemodel.addColumn("Address");
		List<Client> clients=new ArrayList<Client>();
		clients=clientRepository.findAllClients();
		for (Client c: clients)
		{
			tablemodel.addRow(new Object[]{c.getId(),c.getName(),c.getIdCardNr(),c.getPersNrCode(),c.getAddress()});
		}
		return tablemodel;
	}
	
	
	

}