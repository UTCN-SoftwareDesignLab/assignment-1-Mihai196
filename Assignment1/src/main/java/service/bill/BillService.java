package service.bill;

import javax.swing.table.DefaultTableModel;

import model.validation.Notification;

public interface BillService {

	
	Notification<Boolean> addBill(double sumToPay,String company,int clientId);
	DefaultTableModel fillBillData();
}
