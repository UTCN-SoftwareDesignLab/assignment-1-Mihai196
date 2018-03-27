package service.account;

import java.util.List;

import model.Account;

public interface AccountService {
	
	boolean addAccount(Account account);
	boolean updateAccount(Account account);
	boolean deleteAccount(Account account);
	Account findById(int id);
	List<Account> findAllAccounts();

}
