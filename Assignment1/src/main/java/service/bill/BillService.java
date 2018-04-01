package service.bill;

import javax.swing.table.DefaultTableModel;

import model.Bill;
import model.validation.Notification;

public interface BillService {

	
	Notification<Boolean> addBill(double sumToPay,String company,int clientId);
	DefaultTableModel fillBillData();
	Notification<Boolean> processBill(int billId,int accId);
	Bill findBillById(int id);
}
