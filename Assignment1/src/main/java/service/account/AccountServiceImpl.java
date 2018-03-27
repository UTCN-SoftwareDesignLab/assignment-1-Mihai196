package service.account;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.Account;
import repository.account.AccountRepository;

public class AccountServiceImpl implements AccountService{
	
	private AccountRepository accountRepository;
	public AccountServiceImpl(AccountRepository accountRepository) {
			this.accountRepository = accountRepository;
	}

	@Override
	public boolean addAccount(Account account) {
		// TODO Auto-generated method stub
		System.out.println("Hello from account service");
		System.out.println(account.getBalance());
		System.out.print(account.getType());
		System.out.println(account.getClientId());
		System.out.println(account.getId());
		System.out.println(account.getDateOfCreation());
		return accountRepository.addAccount(account);
	}

	@Override
	public boolean updateAccount(Account account) {
		// TODO Auto-generated method stub
		return accountRepository.updateAccount(account);
	}

	@Override
	public boolean deleteAccount(Account account) {
		// TODO Auto-generated method stub
		return accountRepository.deleteAccount(account);
	}

	@Override
	public Account findById(int id) {
		// TODO Auto-generated method stub
		return accountRepository.findById(id);
	}

	@Override
	public List<Account> findAllAccounts() {
		// TODO Auto-generated method stub
		return accountRepository.findAllAccounts();
	}
	@Override
	public DefaultTableModel fillAccountData()
	{
		DefaultTableModel tablemodel=new DefaultTableModel();
		tablemodel.addColumn("Id");
		tablemodel.addColumn("Type");
		tablemodel.addColumn("Balance");
		tablemodel.addColumn("DateOfCreation");
		tablemodel.addColumn("ClientId");
		List<Account> accounts=new ArrayList<Account>();
		accounts=accountRepository.findAllAccounts();
		for (Account c: accounts)
		{
			tablemodel.addRow(new Object[]{c.getId(),c.getType(),c.getBalance(),c.getDateOfCreation(),c.getClientId()});
		}
		return tablemodel;
	}
	
	

}
