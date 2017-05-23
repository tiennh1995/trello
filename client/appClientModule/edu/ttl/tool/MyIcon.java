package edu.ttl.tool;

import java.awt.Image;
import javax.swing.ImageIcon;

public class MyIcon {
	
	private int width, height;
	private ImageIcon icon = new ImageIcon();
	
	public MyIcon() {
		this.width = this.height= 0;
	}
	
	public MyIcon(ImageIcon icon) {
		this.width = this.height= 0;
		this.icon = icon;
	}
	
	public MyIcon(String url) {
		try {
		icon = new ImageIcon(url);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		this.width = this.height= 0;
	}
	
	public MyIcon(int w, int h, int s, String url) {
		this.height = h;
		this.width = w;
		icon = new ImageIcon(url);
		if (s==0) {
			this.height = (int) Math.round((float)(icon.getIconHeight()*w)/icon.getIconWidth());
		} else if (s == 1) {
			this.width = (int) Math.round((float)(icon.getIconWidth()*h)/icon.getIconHeight());
		}
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(this.width, this.height, java.awt.Image.SCALE_SMOOTH);
		this.icon = new ImageIcon(newimg);
	}
	
	public ImageIcon resize(int w, int h, int s, String url) {
		this.height = h;
		this.width = w;
		icon = new ImageIcon(url);
		if (s==0) {
			this.height = (int) Math.round((float)(icon.getIconHeight()*w)/icon.getIconWidth());
		} else if (s == 1) {
			this.width = (int) Math.round((float)(icon.getIconWidth()*h)/icon.getIconHeight());
		}
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(this.width, this.height, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		return icon;
	}
	
	public ImageIcon resize(int w, int h, int s) {
		this.height = h;
		this.width = w;
		if (s==0) {
			this.height = (int) Math.round((float)(icon.getIconHeight()*w)/icon.getIconWidth());
		} else if (s == 1) {
			this.width = (int) Math.round((float)(icon.getIconWidth()*h)/icon.getIconHeight());
		}
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(this.width, this.height, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		return icon;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public ImageIcon getIcon() {
		return icon;
	}
}
