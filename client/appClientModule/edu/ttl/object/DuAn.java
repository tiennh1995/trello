package edu.ttl.object;

public class DuAn {
	private int maDuAn;
	private String tenDuAn = new String();
	private int trangThai;
	
	public DuAn(int key, String ten, int trangthai) {
		this.maDuAn = key;
		this.tenDuAn = ten;
		this.trangThai = trangthai;
	}
	
	public DuAn() {
	}

	public void setTenDuAn(String tenDuAn) {
		this.tenDuAn = tenDuAn;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	public int getMaDuAn() {
		return maDuAn;
	}

	public String getTenDuAn() {
		return tenDuAn;
	}

	public int getTrangThai() {
		return trangThai;
	}
}
