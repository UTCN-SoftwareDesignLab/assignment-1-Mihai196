package service.client;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.Client;

public interface ClientService {
	
	boolean addClient(Client client);
	boolean updateClient(Client client);
	boolean deleteClient(Client client);
	Client findById(int id);
	List<Client> findAllClients();
	DefaultTableModel fillClientData();

}
