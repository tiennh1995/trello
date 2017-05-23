package edu.ttl.object;

public class Notification {
	private String noiDung = new String();
	private int trangThai;
	
	public Notification(String n, int s) {
		noiDung = n;
		trangThai = s;
	}
	
	public String getNoiDung() {
		return noiDung;
	}
	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}
	public int getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}
	public boolean isSeen() {
		return trangThai == 1;
	}
}
