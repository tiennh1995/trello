package edu.ttl.constant;

public class NewDuAn {
	private String tenDuAn = new String();
	private String userName = new String();
	
	public NewDuAn(String tenDA, String user) {
		this.tenDuAn = tenDA;
		this.userName = user;
	}

	public String getTenDuAn() {
		return tenDuAn;
	}

	public String getUserName() {
		return userName;
	}
}
