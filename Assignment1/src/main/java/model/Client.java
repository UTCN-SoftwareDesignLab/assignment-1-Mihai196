package model;

public class Client {
	
	private int id;
	private String name;
	private Long idCardNr;
	private Long persNrCode;
	private String address;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getIdCardNr() {
		return idCardNr;
	}
	public void setIdCardNr(Long idCardNr) {
		this.idCardNr = idCardNr;
	}
	public Long getPersNrCode() {
		return persNrCode;
	}
	public void setPersNrCode(Long persNrCode) {
		this.persNrCode = persNrCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
