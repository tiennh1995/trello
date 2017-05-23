package edu.ttl.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;
import javax.swing.JLabel;

import edu.ttl.constant.R;

public class AboutApp extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the dialog.
	 */
	public AboutApp() {
		setAlwaysOnTop(true);
		setResizable(false);
		setUndecorated(true);
		getContentPane().setBackground(null);
		setSize(720, 650);
		setLocation((R.screenWidth - 720) / 2, 50);
		getContentPane().setLayout(null);
		JLabel bg = new JLabel(R.icon.resize(720, 650, 2, "icon/About.png"));
		bg.setBounds(0, 0, 720, 650);
		getContentPane().add(bg);
		setVisible(true);
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
	}

}
