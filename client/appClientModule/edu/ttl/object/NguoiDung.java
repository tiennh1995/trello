package edu.ttl.object;

import java.util.ArrayList;

public class NguoiDung {
	private int userID;
	private String userName = new String();
	private String name = new String();
	private String passWord = new String();
	private ArrayList<DuAn> listCV = new ArrayList<DuAn>();
	private int isAdmin = 0;
	private String avatarUrl = new String("icon/noAvatar.jpg");
	
	public NguoiDung() {
	}
	
	public NguoiDung(int userID, String name) {
		this.userID = userID;
		this.name = name;
	}
	
	public NguoiDung(String userName, String name) {
		this.userName = userName;
		this.name = name;
	}
	
	public NguoiDung(String userName, String passWord, String name) {
		this.userName = userName;
		this.passWord = passWord;
		this.name = name;
	}
	
	public NguoiDung (String userName, String name, ArrayList<DuAn> list, int isadmin, String avaUrl) {
		this.userName = userName;
		this.name = name;
		this.listCV = list;
		this.isAdmin = isadmin;
		this.avatarUrl = avaUrl;
	}

	public String getUserName() {
		return userName;
	}

	public String getName() {
		return name;
	}
	
	public int getAdmin() {
		return isAdmin;
	}

	public ArrayList<DuAn> getListCV() {
		return listCV;
	}

	public boolean isAdmin() {
		return isAdmin == 1;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}
	
	public String getPassWord() {
		return passWord;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public int getUserID() {
		return userID;
	}

	public void setListCV(ArrayList<DuAn> listCV) {
		this.listCV = listCV;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public void clear() {
		userID = 0;
		userName = "";
		name = "";
		passWord = "";
		listCV.clear();
		isAdmin = 0;
		avatarUrl = "icon/noAvatar.jpg";
	}
}
