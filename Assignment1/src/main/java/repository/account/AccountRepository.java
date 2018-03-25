package repository.account;

import java.util.List;

import model.Account;

public interface AccountRepository {

	boolean addAccount(Account account);
	boolean updateAccount(Account account);
	boolean deleteAccount(Account account);
	Account findById(int id);
	List<Account> findAllAccounts();
}
