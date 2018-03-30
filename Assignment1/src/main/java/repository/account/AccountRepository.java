package repository.account;

import java.util.List;

import model.Account;
import model.Bill;
import repository.EntityNotFoundException;

public interface AccountRepository {

	boolean addAccount(Account account);
	boolean updateAccount(Account account);
	boolean deleteAccount(Account account);
	Account findById(int id) ;
	List<Account> findAllAccounts();
	Bill findBillById(int id);
	boolean deleteBill(Bill bill);
	List<Bill> findAllBills();
}
