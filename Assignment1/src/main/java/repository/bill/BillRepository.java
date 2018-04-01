package repository.bill;

import java.util.List;

import model.Bill;

public interface BillRepository {
	
	boolean addBill(Bill bill);
	List<Bill> findAllBills();
	Bill findBillById(int id);
	boolean deleteBill(Bill bill);
	boolean removeAll();

}
