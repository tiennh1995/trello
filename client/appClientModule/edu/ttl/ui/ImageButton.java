package edu.ttl.ui;

import java.awt.Cursor;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

import edu.ttl.constant.R;

public class ImageButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ImageButton(String name, String imgUrl) {
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		//this.setLayout(null);
		this.setBorder(null);
		//this.setOpaque(false);
		this.setContentAreaFilled(false);
		
		JLabel bg = new JLabel();
		bg.setIcon(R.icon.resize(133, 34, 2, "icon/chitietCV/btwhite2.png"));
		bg.setLayout(new FlowLayout(FlowLayout.LEFT, 7,7));
		JLabel lbName = new JLabel(name);
		lbName.setFont(R.FONT14B);
		lbName.setBackground(null);
		JLabel lbImg = new JLabel(R.icon.resize(20, 20, 2, imgUrl));
		bg.add(lbImg);
		bg.add(lbName);
		this.add(bg);
	}
}
