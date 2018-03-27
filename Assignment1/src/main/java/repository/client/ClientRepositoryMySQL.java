package repository.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Client;
import model.builder.ClientBuilder;

public class ClientRepositoryMySQL implements ClientRepository{
	
	private Connection connection;
	

	public ClientRepositoryMySQL(Connection connection) {
		super();
		this.connection = connection;
	}


	@Override
	public boolean addClient(Client client) {
		// TODO Auto-generated method stub
		try
		{
			PreparedStatement insertStatement=connection.prepareStatement("INSERT INTO client values (null, ?, ?, ?,?)");
			insertStatement.setString(1, client.getName());
			insertStatement.setLong(2, client.getIdCardNr());
			insertStatement.setLong(3, client.getPersNrCode());
			insertStatement.setString(4,client.getAddress());
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
	public boolean updateClient(Client client) {
		// TODO Auto-generated method stub
		try 
		{
			PreparedStatement updateStatement=connection.prepareStatement("UPDATE client SET name=?,idCardNr=?,persNrCode=?,address=? WHERE id=?");
			updateStatement.setString(1, client.getName());
			updateStatement.setLong(2, client.getIdCardNr());
			updateStatement.setLong(3, client.getPersNrCode());
			updateStatement.setString(4, client.getAddress());
			updateStatement.setInt(5, client.getId());
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
	public boolean deleteClient(Client client) {
		// TODO Auto-generated method stub
		try
		{
			PreparedStatement deleteStatement=connection.prepareStatement("DELETE FROM client where id=?");
			deleteStatement.setInt(1, client.getId());
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
	public Client findById(int id) {
		// TODO Auto-generated method stub
		try
		{
			PreparedStatement findStatement=connection.prepareStatement("SELECT * FROM client where id=?");
			findStatement.setInt(1, id);
			ResultSet rs=findStatement.executeQuery();
			if (rs.next())
			{
				return getClientFromResultSet(rs);
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


	@Override
	public List<Client> findAllClients() {
		// TODO Auto-generated method stub
		List<Client> clients=new ArrayList<>();
		try 
		{
			PreparedStatement findStatement=connection.prepareStatement("SELECT * FROM client");
			ResultSet rs=findStatement.executeQuery();
			while(rs.next())
			{
				clients.add(getClientFromResultSet(rs));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return clients;
	}
	
	private Client getClientFromResultSet(ResultSet rs) throws SQLException
	{
		return new ClientBuilder()
				.setId(rs.getInt("id"))
				.setName(rs.getString("name"))
				.setIdCardNr(rs.getLong("idCardNr"))
				.setPersNrCode(rs.getLong("persNrCode"))
				.setAddress(rs.getString("address")).build();
	}

	
	
	

}
