package edu.ttl.object;

import javax.swing.JOptionPane;

public class LoginInfo {
	
	private String userName = new String();
	private String passWord = new String();
	
	public LoginInfo(String name, char[] pass) {
		this.userName = name;
		this.passWord = new String(pass);
	}
	
	public int checkData() {
		if (userName.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Chưa nhập tài khoản");
			return 1;
		}
		if (passWord.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Chưa nhập mật khẩu");
			return 2;
		}
		return 0;
	}
	
	public String getUserName() {
		return userName;
	}
	public String getPassWord() {
		return passWord;
	}
}
