package edu.ttl.object;

import javax.swing.JOptionPane;

public class NewUserInfo {
	private String userName = new String();
	private String passWord = new String();
	private String name = new String();

	public NewUserInfo(String username, char[] pass, String name) {
		this.userName = username;
		this.passWord = new String(pass);
		this.name = name;
	}

	public int checkNewUser() {
		if (userName.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Chưa nhập tài khoản");
			return 1;
		}
		if (passWord.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Chưa nhập mật khẩu");
			return 2;
		}
		if (name.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Chưa nhập họ tên");
			return 3;
		}
		return 0;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public String getName() {
		return name;
	}
}
