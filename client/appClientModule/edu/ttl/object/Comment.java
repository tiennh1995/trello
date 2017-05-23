package edu.ttl.object;

import edu.ttl.tool.MyIcon;

public class Comment {
	private String type = new String();
	private int BL;
	private int CV;
	private int TV;
	private String loiBinh = new String();
	private String nguoiBinh = new String();
	private MyIcon pic;
	
	public String getType() {
		return type;
	}

	public MyIcon getPic() {
		return pic;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPic(MyIcon pic) {
		this.pic = pic;
	}

	public String getNguoiBinh() {
		return nguoiBinh;
	}

	public void setNguoiBinh(String nguoiBinh) {
		this.nguoiBinh = nguoiBinh;
	}

	public Comment(int CV, int TV, String loibinh) {
		this.CV = CV;
		this.TV = TV;
		this.loiBinh = loibinh;
	}
	
	public Comment(int BL, int CV, int TV, String loibinh) {
		this.BL = BL;
		this.CV = CV;
		this.TV = TV;
		this.loiBinh = loibinh;
	}
	
	public Comment(String loibinh, String nguoi) {
		this.loiBinh = loibinh;
		this.nguoiBinh = nguoi;
	}
	
	public Comment() {
		
	}

	public void setBL(int bL) {
		BL = bL;
	}

	public void setCV(int cV) {
		CV = cV;
	}

	public void setTV(int tV) {
		TV = tV;
	}

	public void setLoiBinh(String loiBinh) {
		this.loiBinh = loiBinh;
	}

	public int getBL() {
		return BL;
	}

	public int getCV() {
		return CV;
	}

	public int getTV() {
		return TV;
	}

	public String getLoiBinh() {
		return loiBinh;
	}
	
	
}
