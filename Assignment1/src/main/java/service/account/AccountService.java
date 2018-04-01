package service.account;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.Account;
import model.Bill;
import model.validation.Notification;

public interface AccountService {
	
	Notification<Boolean> addAccount(String type,double balance,int clientId);
	Notification<Boolean> updateAccount(String type,double balance,int clientId,int id);
	boolean deleteAccount(int id);
	Account findById(int id);
	List<Account> findAllAccounts();
	DefaultTableModel fillAccountData();
	Notification<Boolean> transferMoney(int id1,int id2,double sum);
	List<Account> findAccountsClient(int clientId);

}
