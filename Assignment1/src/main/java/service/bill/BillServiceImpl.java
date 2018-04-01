package service.bill;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.Account;
import model.Bill;
import model.builder.AccountBuilder;
import model.builder.BillBuilder;
import model.validation.BillValidator;
import model.validation.Notification;
import repository.account.AccountRepository;
import repository.bill.BillRepository;

public class BillServiceImpl implements BillService {
	private BillRepository billRepository;
	private AccountRepository accountRepository;
	
	public BillServiceImpl(BillRepository billRepository,AccountRepository accountRepository) {
		super();
		this.billRepository = billRepository;
		this.accountRepository=accountRepository;
	}

	@Override
	public Notification<Boolean> addBill(double sumToPay, String company, int clientId) {
		// TODO Auto-generated method stub
		Bill bill=new BillBuilder()
				.setSumToPay(sumToPay)
				.setCompany(company)
				.setClientId(clientId)
				.build();
		BillValidator billValidator = new BillValidator();
		boolean billValidation=billValidator.validateForInsertion(bill);
		Notification<Boolean> billNotification=new Notification<>();
		if (!billValidation) {
			billValidator.getErrors().forEach(billNotification::addError);
			billNotification.setResult(Boolean.FALSE);
		}
		else
		{
			boolean result=billRepository.addBill(bill);
			billNotification.setResult(result);
		}
		return billNotification;
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
		bills = billRepository.findAllBills();
		for (Bill b : bills) {
			tablemodel.addRow(new Object[] { b.getId(), b.getCompany(), b.getSumToPay(), b.getClientId() });
		}
		return tablemodel;
	}
	@Override
	public Notification<Boolean> processBill(int billId, int accId) {
		// TODO Auto-generated method stub
		Bill bill = billRepository.findBillById(billId);
		Account account = accountRepository.findById(accId);
		BillValidator billValidator = new BillValidator();
		boolean billValidation=billValidator.validate(bill, account);
		Notification<Boolean> billNotification=new Notification<>();
		if (!billValidation) {
			billValidator.getErrors().forEach(billNotification::addError);
			billNotification.setResult(Boolean.FALSE);
		} else {
			double newSumAcc = account.getBalance() - bill.getSumToPay();
			Account updatedAccount = new AccountBuilder().setId(accId).setDateOfCreation(account.getDateOfCreation())
					.setClientId(account.getClientId()).setBalance(newSumAcc).setType(account.getType()).build();
			boolean result1=accountRepository.updateAccount(updatedAccount);
			boolean result2=billRepository.deleteBill(bill);
			billNotification.setResult(result1&result2);
		}
		return billNotification;
	}


	@Override
	public Bill findBillById(int id) {
		// TODO Auto-generated method stub
		return billRepository.findBillById(id);
	}
	
	

}
