package service.account;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.Account;

public interface AccountService {
	
	boolean addAccount(String type,double balance,int clientId);
	boolean updateAccount(String type,double balance,int clientId,int id);
	boolean deleteAccount(int id);
	Account findById(int id);
	List<Account> findAllAccounts();
	DefaultTableModel fillAccountData();
	void transferMoney(int id1,int id2,double sum);

}
