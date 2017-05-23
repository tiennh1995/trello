package edu.ttl.ui;

import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.ttl.constant.R;

public class TextPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lb = new JLabel();
	
	public TextPanel(int w, int h, String text) {
		setSize(w, h);
		lb.setText(text);
		lb.setFont(R.FONT18B);
		setLayout(new FlowLayout(FlowLayout.LEFT, 15, 0));
		setOpaque(false);
		add(lb);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	public void setTextFont(Font f) {
		lb.setFont(f);
	}
}
