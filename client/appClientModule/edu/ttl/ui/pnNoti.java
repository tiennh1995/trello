package edu.ttl.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.ttl.constant.R;
import edu.ttl.object.Notification;

public class pnNoti extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Notification noti;
	private JLabel lb = new JLabel();

	public pnNoti(Notification no) {
			this.noti = no;
			setSize(290, 40);
			setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(0, 5, 0, 5);
			//c.ipady = 10;
			c.anchor = GridBagConstraints.CENTER;
			lb.setFont(R.FONT16);
			lb.setText(String.format("<html><div style=\"width:%dpx;\">%s</div><html>", 220, noti.getNoiDung()));
			lb.setForeground(Color.BLACK);
			setSize(290, lb.getPreferredSize().height);
			this.add(lb, c);
			if (noti.isSeen()) {
				setBackground(Color.WHITE);
			} else {
				setBackground(new Color(0xBBDEFB));
			}
			setCursor(new Cursor(Cursor.HAND_CURSOR));
			addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub
					setBackground(Color.WHITE);
					noti.setTrangThai(1);
				}
			});
		}
}
