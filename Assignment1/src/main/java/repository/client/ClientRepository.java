package repository.client;

import java.util.List;

import model.Client;

public interface ClientRepository {
	
	boolean addClient(Client client);
	boolean updateClient(Client client);
	boolean deleteClient(Client client);
	Client findById(int id);
	List<Client> findAllClients();
	boolean removeAll();
	
}
