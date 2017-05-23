package edu.ttl.object;

import java.util.ArrayList;

public class CongViec {
	private int key;
	private int fkey;
	private String ten = new String();
	private String moTa = new String();
	private String deadline = new String();
	private ArrayList<NguoiDung> listNguoiLam = new ArrayList<NguoiDung>();
	private int trangThai; //0: Todo 1:Doing 2:Done
	
	public CongViec(int key, int fkey, String ten, int trangThai) {
		this.key = key;
		this.fkey = fkey;
		this.ten = ten;
		this.trangThai = trangThai;
	}
	
	public CongViec() {

	}
	
	public CongViec(int fkey, String ten, int trangThai) {
		this.fkey = fkey;
		this.ten = ten;
		this.trangThai = trangThai;
	}
	
	public CongViec(int key, int fkey, String ten, String moTa, String deadline, int trangThai, ArrayList<NguoiDung> nguoiLam) {
		this.key = key;
		this.fkey = fkey;
		this.ten = ten;
		this.moTa = moTa;
		this.deadline = deadline;
		this.trangThai = trangThai;
		this.listNguoiLam = nguoiLam;
	}
	
	public CongViec(int key, String ten, String deadline, int trangThai, ArrayList<NguoiDung> nguoiLam) {
		this.key = key;
		this.ten = ten;
		this.deadline = deadline;
		this.trangThai = trangThai;
		this.listNguoiLam = nguoiLam;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public void setFkey(int fkey) {
		this.fkey = fkey;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	public int getKey() {
		return key;
	}

	public int getFkey() {
		return fkey;
	}

	public String getTen() {
		return ten;
	}

	public String getMoTa() {
		return moTa;
	}

	public String getDeadline() {
		return deadline;
	}

	public int getTrangThai() {
		return trangThai;
	}
	
	public ArrayList<NguoiDung> getListNguoiLam() {
		return listNguoiLam;
	}
}
