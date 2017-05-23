package edu.ttl.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import edu.ttl.constant.R;

public class NotificationDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pn = new JPanel();
	private JLabel bg = new JLabel();
	private JScrollPane sc = new JScrollPane();

	/**
	 * Create the dialog.
	 */
	public NotificationDialog() {
		//setAlwaysOnTop(true);
		setResizable(false);
		setUndecorated(true);
		getContentPane().setLayout(null);

		JButton exit = new JButton(R.icon.resize(15, 15, 2, "icon/chitietCV/delete.png"));
		exit.setBounds(280, 5, 15, 15);
		exit.setOpaque(false);
		exit.setContentAreaFilled(false);
		exit.setBorder(null);
		exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				myDispose();
			}
		});
		getContentPane().add(exit);

		pn.setLayout(new BoxLayout(pn, BoxLayout.Y_AXIS));
		pn.setBackground(Color.WHITE);
		sc.setBounds(2, 20, 290, 408);
		sc.setOpaque(false);
		sc.setBorder(null);
		add(sc);
		bg.setIcon(R.icon.resize(300, 430, 2, "icon/user/notification.png"));
		bg.setBounds(0, 0, 300, 430);
		getContentPane().add(bg);
		
		sc.getVerticalScrollBar().setUnitIncrement(10);
		sc.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	}

	public void init() {
		setBounds(R.main.getWidth() - 300 - R.main.marginSize * 4, (int)(1.5*R.main.TOOLBARHEIGHT), 300, 430);
		R.main.setNotificationButton(0);
		pn.removeAll();
		pn.setPreferredSize(new Dimension(296, 0));
		int h = 0;
		for (int i = R.listNotification.size() - 1; i >= 0; i--) {
			pnNoti pnno = new pnNoti(R.listNotification.get(i));
			pn.add(pnno);
			h += pnno.getHeight();
		}
		pn.setPreferredSize(new Dimension(296, h));
		sc.setViewportView(pn);
		if (h < 398) {
			sc.setSize(296, h);
		} else
			sc.setSize(296, 398);
		JScrollBar vertical = sc.getVerticalScrollBar();
        vertical.setValue(vertical.getMinimum());
		setVisible(true);
	}

	public void myDispose() {
		dispose();
	}
}
