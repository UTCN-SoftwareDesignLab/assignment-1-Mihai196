package repository.bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Bill;
import model.builder.BillBuilder;

public class BillRepositoryMySQL implements BillRepository {
	
	private final Connection connection;
	
	
	public BillRepositoryMySQL(Connection connection) {
		super();
		this.connection = connection;
	}
	@Override
	public List<Bill> findAllBills() {
		// TODO Auto-generated method stub
		List<Bill> bills=new ArrayList<>();
		try 
		{
			PreparedStatement findStatement=connection.prepareStatement("SELECT * FROM bill");
			ResultSet rs=findStatement.executeQuery();
			while(rs.next())
			{
				bills.add(getBillFromResultSet(rs));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return bills;
	}
	@Override
	public boolean addBill(Bill bill) {
		// TODO Auto-generated method stub
		try 
		{
			PreparedStatement insertStatement=connection.prepareStatement("INSERT INTO bill values (null, ?, ?, ?)");
			insertStatement.setDouble(1, bill.getSumToPay());
			insertStatement.setString(2,bill.getCompany());
			insertStatement.setInt(3, bill.getClientId());
			insertStatement.executeUpdate();
			return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	private Bill getBillFromResultSet(ResultSet rs) throws SQLException {
		return new BillBuilder()
				.setId(rs.getInt("id"))
				.setCompany(rs.getString("company"))
				.setSumToPay(rs.getDouble("sumToPay"))
				.setClientId(rs.getInt("clientId")).build();
	}

}
