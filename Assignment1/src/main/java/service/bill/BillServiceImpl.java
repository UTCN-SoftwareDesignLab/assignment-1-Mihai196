package service.bill;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.Bill;
import model.builder.BillBuilder;
import model.validation.BillValidator;
import model.validation.Notification;
import repository.bill.BillRepository;

public class BillServiceImpl implements BillService {
	private BillRepository billRepository;
	
	public BillServiceImpl(BillRepository billRepository) {
		super();
		this.billRepository = billRepository;
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
	
	

}
