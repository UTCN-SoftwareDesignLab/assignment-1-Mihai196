package service.account;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.Account;
import model.Bill;
import model.validation.Notification;

public interface AccountService {
	
	boolean addAccount(String type,double balance,int clientId);
	boolean updateAccount(String type,double balance,int clientId,int id);
	boolean deleteAccount(int id);
	Account findById(int id);
	List<Account> findAllAccounts();
	DefaultTableModel fillAccountData();
	Notification<Boolean> transferMoney(int id1,int id2,double sum);
	List<String> processBill(int billId,int accId);
	DefaultTableModel fillBillData();
	Bill findBillById(int id);

}
