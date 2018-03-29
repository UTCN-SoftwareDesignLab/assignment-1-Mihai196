package service.client;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.Client;

public interface ClientService {
	
	boolean addClient(String name,Long idCardNr,Long persNrCode,String address);
	boolean updateClient(int id,String name,Long idCardNr,Long persNrCode,String address);
	boolean deleteClient(int id);
	Client findById(int id);
	List<Client> findAllClients();
	DefaultTableModel fillClientData();

}
