package repository.account;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.Account;
import model.Bill;
import model.builder.AccountBuilder;
import model.builder.BillBuilder;

public class AccountRepositoryMySQL implements AccountRepository {
	
	private final Connection connection;

	public AccountRepositoryMySQL(Connection connection) {
		this.connection = connection;
	}

	@Override
	public boolean addAccount(Account account) {
		// TODO Auto-generated method stub
		try 
		{
			PreparedStatement insertStatement=connection.prepareStatement("INSERT INTO account values (null, ?, ?, ?,?)");
			insertStatement.setString(1, account.getType());
			insertStatement.setDouble(2, account.getBalance());
			insertStatement.setDate(3, new java.sql.Date(System.currentTimeMillis()));
			insertStatement.setInt(4, account.getClientId());
			insertStatement.executeUpdate();
			
			return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean updateAccount(Account account) {
		// TODO Auto-generated method stub
		try 
		{
			PreparedStatement updateStatement=connection.prepareStatement("UPDATE account SET type=?,balance=?,clientId=? WHERE id=?");
			updateStatement.setString(1, account.getType());
			updateStatement.setDouble(2, account.getBalance());
			//updateStatement.setDate(3, new java.sql.Date(System.currentTimeMillis()));
			updateStatement.setInt(3, account.getClientId());
			updateStatement.setInt(4, account.getId());
			updateStatement.executeUpdate();
			
			return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean deleteAccount(Account account) {
		// TODO Auto-generated method stub
		try
		{
			PreparedStatement deleteStatement=connection.prepareStatement("DELETE FROM account where id=?");
			deleteStatement.setInt(1, account.getId());
			deleteStatement.executeUpdate();
			return true;
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public Account findById(int id) {
		// TODO Auto-generated method stub
		try
		{
			PreparedStatement findStatement=connection.prepareStatement("SELECT * FROM account where id=?");
			findStatement.setInt(1, id);
			ResultSet rs=findStatement.executeQuery();
			if (rs.next())
			{
				return getAccountFromResultSet(rs);
			}
			else
			{
				return null;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	private Account getAccountFromResultSet(ResultSet rs) throws SQLException  {
		// TODO Auto-generated method stub
		return new AccountBuilder()
				.setId(rs.getInt("id"))
				.setType(rs.getString("type"))
				.setBalance(rs.getDouble("balance"))
				.setDateOfCreation(new Date(rs.getDate("dateOfCreation").getTime()))
				.setClientId(rs.getInt("clientId")).build();
	}

	@Override
	public List<Account> findAllAccounts() {
		// TODO Auto-generated method stub
		List<Account> accounts=new ArrayList<>();
		try 
		{
			PreparedStatement findStatement=connection.prepareStatement("SELECT * FROM account");
			ResultSet rs=findStatement.executeQuery();
			while(rs.next())
			{
				accounts.add(getAccountFromResultSet(rs));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return accounts;
	}

	@Override
	public Bill findBillById(int id) {
		// TODO Auto-generated method stub
		try
		{
			PreparedStatement findStatement=connection.prepareStatement("SELECT * FROM bill where id=?");
			findStatement.setInt(1, id);
			ResultSet rs=findStatement.executeQuery();
			if (rs.next())
			{
				return getBillFromResultSet(rs);
			}
			else
			{
				return null;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	private Bill getBillFromResultSet(ResultSet rs) throws SQLException {
		return new BillBuilder()
				.setId(rs.getInt("id"))
				.setCompany(rs.getString("company"))
				.setSumToPay(rs.getDouble("sumToPay"))
				.setClientId(rs.getInt("clientId")).build();
	}

	@Override
	public boolean deleteBill(Bill bill) {
		// TODO Auto-generated method stub
		try
		{
			PreparedStatement deleteStatement=connection.prepareStatement("DELETE FROM bill where id=?");
			deleteStatement.setInt(1, bill.getId());
			deleteStatement.executeUpdate();
			return true;
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
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
}
