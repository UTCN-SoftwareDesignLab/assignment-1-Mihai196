package service.account;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.Account;
import model.Bill;
import model.builder.AccountBuilder;
import model.validation.BillValidator;
import model.validation.Notification;
import model.validation.TransferValidator;
import repository.account.AccountRepository;

public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepository;

	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public boolean addAccount(String type, double balance, int clientId) {
		// TODO Auto-generated method stub
		/*
		 * System.out.println("Hello from account service");
		 * System.out.println(account.getBalance());
		 * System.out.print(account.getType());
		 * System.out.println(account.getClientId());
		 * System.out.println(account.getId());
		 * System.out.println(account.getDateOfCreation());
		 */
		Account a = new AccountBuilder().setType(type).setBalance(balance).setClientId(clientId).build();
		return accountRepository.addAccount(a);
	}

	@Override
	public boolean updateAccount(String type, double balance, int clientId, int id) {
		// TODO Auto-generated method stub
		Account a = new AccountBuilder().setType(type).setBalance(balance).setClientId(clientId).setId(id).build();
		return accountRepository.updateAccount(a);
	}

	@Override
	public boolean deleteAccount(int id) {
		// TODO Auto-generated method stub
		Account a = new AccountBuilder().setId(id).build();
		return accountRepository.deleteAccount(a);
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
	public DefaultTableModel fillAccountData() {
		DefaultTableModel tablemodel = new DefaultTableModel();
		tablemodel.addColumn("Id");
		tablemodel.addColumn("Type");
		tablemodel.addColumn("Balance");
		tablemodel.addColumn("DateOfCreation");
		tablemodel.addColumn("ClientId");
		List<Account> accounts = new ArrayList<Account>();
		accounts = accountRepository.findAllAccounts();
		for (Account c : accounts) {
			tablemodel.addRow(
					new Object[] { c.getId(), c.getType(), c.getBalance(), c.getDateOfCreation(), c.getClientId() });
		}
		return tablemodel;
	}

	@Override
	public Notification<Boolean> transferMoney(int id1, int id2, double sum) {
		// TODO Auto-generated method stub
		Account a1 = accountRepository.findById(id1);
		Account a2 = accountRepository.findById(id2);
		TransferValidator transferValidator = new TransferValidator();
		boolean transferValidation = transferValidator.validate(a1, sum);
		Notification<Boolean> transferNotification = new Notification<>();
		if (!transferValidation) {
			transferValidator.getErrors().forEach(transferNotification::addError);
			transferNotification.setResult(Boolean.FALSE);
		} else {
			double suma1 = a1.getBalance() - sum;
			double suma2 = a2.getBalance() + sum;
			Account updatedA1 = new AccountBuilder().setId(id1).setType(a1.getType()).setBalance(suma1)
					.setDateOfCreation(a1.getDateOfCreation()).setClientId(a1.getClientId()).build();
			Account updatedA2 = new AccountBuilder().setId(id2).setType(a2.getType()).setBalance(suma2)
					.setClientId(a2.getClientId()).setDateOfCreation(a2.getDateOfCreation()).build();
			boolean var1 = accountRepository.updateAccount(updatedA1);
			boolean var2 = accountRepository.updateAccount(updatedA2);
			boolean var3 = var1 & var2;
			transferNotification.setResult(var3);
		}
		return transferNotification;
	}

	@Override
	public List<String> processBill(int billId, int accId) {
		// TODO Auto-generated method stub
		Bill bill = accountRepository.findBillById(billId);
		Account account = accountRepository.findById(accId);
		BillValidator billValidator = new BillValidator();
		if (!billValidator.validate(bill, account)) {
			return billValidator.getErrors();
		} else {
			double newSumAcc = account.getBalance() - bill.getSumToPay();
			Account updatedAccount = new AccountBuilder().setId(accId).setDateOfCreation(account.getDateOfCreation())
					.setClientId(account.getClientId()).setBalance(newSumAcc).setType(account.getType()).build();
			accountRepository.updateAccount(updatedAccount);
			accountRepository.deleteBill(bill);
			return Collections.emptyList();
		}
	}

	@Override
	public DefaultTableModel fillBillData() {
		// TODO Auto-generated method stub
		DefaultTableModel tablemodel = new DefaultTableModel();
		tablemodel.addColumn("Id");
		tablemodel.addColumn("Company");
		tablemodel.addColumn("SumToPay");
		tablemodel.addColumn("ClientId");
		List<Bill> bills = new ArrayList<Bill>();
		bills = accountRepository.findAllBills();
		for (Bill b : bills) {
			tablemodel.addRow(new Object[] { b.getId(), b.getCompany(), b.getSumToPay(), b.getClientId() });
		}
		return tablemodel;
	}

	@Override
	public Bill findBillById(int id) {
		// TODO Auto-generated method stub
		return accountRepository.findBillById(id);
	}

}
