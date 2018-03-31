package service.client;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.Client;
import model.validation.Notification;

public interface ClientService {
	
	Notification<Boolean> addClient(String name,Long idCardNr,Long persNrCode,String address);
	Notification<Boolean> updateClient(int id,String name,Long idCardNr,Long persNrCode,String address);
	boolean deleteClient(int id);
	Client findById(int id);
	List<Client> findAllClients();
	DefaultTableModel fillClientData();

}
