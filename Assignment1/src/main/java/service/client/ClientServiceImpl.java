package service.client;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.Client;
import model.builder.ClientBuilder;
import repository.client.ClientRepository;

public class ClientServiceImpl implements ClientService {
	
	private final ClientRepository clientRepository;

	public ClientServiceImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public boolean addClient(String name,Long idCardNr,Long persNrCode,String address) {
		// TODO Auto-generated method stub
		Client c = new ClientBuilder().setName(name).setIdCardNr(idCardNr).setPersNrCode(persNrCode)
				.setAddress(address).build();
		return clientRepository.addClient(c);
	}

	@Override
	public boolean updateClient(int id,String name,Long idCardNr,Long persNrCode,String address) {
		// TODO Auto-generated method stub
		Client c = new ClientBuilder().setName(name).setIdCardNr(idCardNr).setPersNrCode(persNrCode).setId(id)
				.setAddress(address).build();
		return clientRepository.updateClient(c);
	}

	@Override
	public boolean deleteClient(int id) {
		// TODO Auto-generated method stub
		Client c = new ClientBuilder().setId(id).build();
		return clientRepository.deleteClient(c);
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
