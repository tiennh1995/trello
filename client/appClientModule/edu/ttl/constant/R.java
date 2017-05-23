package edu.ttl.constant;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import javax.swing.JOptionPane;

import edu.ttl.object.CongViec;
import edu.ttl.object.DuAn;
import edu.ttl.object.NguoiDung;
import edu.ttl.object.Notification;
import edu.ttl.tool.MyIcon;
import edu.ttl.ui.ChiTietCongViecPanel;
import edu.ttl.ui.CongViecPanel;
import edu.ttl.ui.CongViecTablePanel;
import edu.ttl.ui.Login;
import edu.ttl.ui.Main;

public class R {
	public static NguoiDung user = new NguoiDung();
	public static String IP_sever = new String();
	public static int port_sever = 51195;

	// public static final String Login = "TXT";
	public static int imgSize = 360;
	public static int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
	public static int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
	public static int BUTTONW = (int) Math.round(screenWidth / 4.5);
	public static int margin = (int) Math.round(0.01 * R.screenHeight);
	public static int space = (int) Math.round(R.screenWidth * 0.01);
	public static int searchBarWidth;
	public static MyIcon icon = new MyIcon();

	public static final String APPTITLE = "Quản lý dự án";

	public static Login login;
	public static Main main = new Main();
	public static CongViecTablePanel congViecTable; // Ho tro viec lay du lieu
													// tu database
	public static ChiTietCongViecPanel chitietCV;
	public static CongViecPanel congViecPanel;

	public static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public static final String FONT = "Roboto-Light";
	public static final Font FONT16 = new Font(FONT, Font.PLAIN, 16);
	public static final Font FONT16B = new Font(FONT, Font.BOLD, 16);
	public static final Font FONT18B = new Font(FONT, Font.BOLD, 16);
	public static final Font FONT14 = new Font(FONT, Font.PLAIN, 14);
	public static final Font FONT14B = new Font(FONT, Font.BOLD, 14);
	public static final Font FONT20B = new Font(FONT, Font.BOLD, 20);

	public static String windowsClassName = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

	public static ArrayList<DuAn> listProject = new ArrayList<DuAn>();
	public static ArrayList<DuAn> listProjectStop = new ArrayList<DuAn>();
	public static Hashtable<Integer, CongViec> listCV = new Hashtable<Integer, CongViec>();
	public static ArrayList<Notification> listNotification = new ArrayList<>();

	public static boolean checkIP_sever() {
		if (IP_sever.equals("localhost"))
			return true;
		int tmp = 0;
		for (int i = 0; i < IP_sever.length(); i++) {
			if (IP_sever.charAt(i) == '.')
				tmp++;
		}
		if (tmp != 3) {
			JOptionPane.showMessageDialog(null, "Định dạng địa chỉ IP máy chủ không đúng.\nVí dụ: 192.168.1.1");
			return false;
		}
		return true;
	}

	public static BufferedImage toBufferedImage(Image img) {
		if (img instanceof BufferedImage) {
			return (BufferedImage) img;
		}

		// Create a buffered image with transparency
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		// Draw the image on to the buffered image
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();

		// Return the buffered image
		return bimage;
	}
	
    public static Set<Component> getAllComponents(Component component) {
        Set<Component> children = new HashSet<Component>();
        children.add(component);
        if (component instanceof Container) {
            Container container = (Container) component;
            Component[] components = container.getComponents();
            for (int i = 0; i < components.length; i++) {
                children.addAll(getAllComponents(components[i]));
            }
        }
        return children;
    }
    
    public static boolean isComponent(Component componient, Component container) {
    	Set<Component> children = getAllComponents(container);
    	for (Component com : children) {
    		if (com.toString().equalsIgnoreCase(componient.toString())) return true;
    	}
    	return false;
    }
    
    public static boolean checkPassWord(String name, char[] pass) {
    	if (pass.length < 6) {
    		JOptionPane.showMessageDialog(null, name + " phải có tối thiểu 6 ký tự");
    		return false;
    	}
    	if (pass.length > 100) {
    		JOptionPane.showMessageDialog(null, name + " tối đa 100 ký tự");
    		return false;
    	}
    	return true;
    }
    
    public static boolean checkUserName(String name, String username) {
    	String kytuthuong = "abcdefghijklmnopqrstuvwxyz";
    	String kytuhoa = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    	String chuso = "0123456789";
    	
    	if (username.length() < 6) {
    		JOptionPane.showMessageDialog(null, name + " phải có tối thiểu 6 ký tự");
    		return false;
    	}
    	
    	if (username.length() > 100) {
    		JOptionPane.showMessageDialog(null, name + " tối đa 100 ký tự");
    		return false;
    	}
    	
    	char[] arr = username.toCharArray();
    	
    	if (chuso.indexOf(arr[0]) != -1) {
    		JOptionPane.showMessageDialog(null, name + " phải bắt đầu bằng một chữ cái");
    		return false;
    	}
    	
    	for (char c : arr) {
    		if (kytuthuong.indexOf(c) != -1) continue;
    		if (kytuhoa.indexOf(c) != -1) continue;
    		if (chuso.indexOf(c) != -1) continue;
    		JOptionPane.showMessageDialog(null, name + " chỉ bao gồm chữ cái thường, chữ cái hoa và chữ số");
    		return false;
    	}
    	return true;
    }
}
