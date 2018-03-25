package model.builder;

import model.Client;

public class ClientBuilder {
	
	private Client client;
	
	public ClientBuilder()
	{
		client=new Client();
	}
	public ClientBuilder setId(int id)
	{
		client.setId(id);
		return this;
	}
	public ClientBuilder setName(String name)
	{
		client.setName(name);
		return this;
	}
	public ClientBuilder setIdCardNr(Long idCardNr)
	{
		client.setIdCardNr(idCardNr);
		return this;
	}
	public ClientBuilder setPersNrCode(Long persNrCode)
	{
		client.setPersNrCode(persNrCode);
		return this;
	}
	public ClientBuilder setAddress(String address)
	{
		client.setAddress(address);
		return this;
	}
	public Client build ()
	{
		return client;
	}
}
