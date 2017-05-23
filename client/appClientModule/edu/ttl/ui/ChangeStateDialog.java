package edu.ttl.ui;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.ttl.constant.Database;
import edu.ttl.constant.R;

public class ChangeStateDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JButton okButton;
	protected JButton cancelButton;
	protected int s;
	JButton btToDo;
	JButton btDoing;
	JButton btDone;

	/**
	 * Launch the application.
	 */
	public ChangeStateDialog() {
		setAlwaysOnTop(true);
		setSize(260, 171);
		setResizable(false);
		setUndecorated(true);
		getContentPane().setLayout(null);
		JPanel pnButton = new JPanel(null);

		okButton = new JButton();
		okButton.setBounds(41, 0, 74, 31);
		okButton.setActionCommand("OK");
		okButton.setOpaque(false);
		okButton.setContentAreaFilled(false);
		okButton.setBorder(null);
		okButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnButton.add(okButton);
		getRootPane().setDefaultButton(okButton);

		cancelButton = new JButton();
		cancelButton.setBounds(140, 0, 74, 31);
		cancelButton.setActionCommand("Cancel");
		cancelButton.setOpaque(false);
		cancelButton.setContentAreaFilled(false);
		cancelButton.setBorder(null);
		cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		pnButton.add(cancelButton);

		JPanel pnState = new  JPanel();
		pnState.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 15, 0, 0);
		btToDo = new JButton();
		btToDo.setIcon(R.icon.resize(55, 55, 2, "icon/chitietCV/toDo-White.png"));
		btToDo.setOpaque(false);
		btToDo.setContentAreaFilled(false);
		btToDo.setBorder(null);
		btToDo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnState.add(btToDo, c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(0, 20, 0, 20);
		btDoing = new JButton();
		btDoing.setIcon(R.icon.resize(55, 55, 2, "icon/chitietCV/doing-White.png"));
		btDoing.setOpaque(false);
		btDoing.setContentAreaFilled(false);
		btDoing.setBorder(null);
		btDoing.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnState.add(btDoing, c);
		
		c.gridx = 2;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 15);
		btDone = new JButton();
		btDone.setIcon(R.icon.resize(55, 55, 2, "icon/chitietCV/done-White.png"));
		btDone.setOpaque(false);
		btDone.setContentAreaFilled(false);
		btDone.setBorder(null);
		btDone.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnState.add(btDone, c);
		pnState.setOpaque(false);
		pnState.setBounds(0, 45, 260, 55);
		
		ActionListener ac = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == btToDo) s = 0;
				else if (e.getSource() == btDoing) s = 1;
				else s = 2;
				changeBtState();
			}
		};
		
		btToDo.addActionListener(ac);
		btDoing.addActionListener(ac);
		btDone.addActionListener(ac);
		
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ChiTietCongViecPanel.cv.setTrangThai(s);
				Database.sendUpdateState(ChiTietCongViecPanel.cv);
				dispose();
			}
		});
		
		getContentPane().add(pnState);
		pnButton.setOpaque(false);
		pnButton.setBounds(0, 125, 260, 31);
		getContentPane().add(pnButton);
		JLabel lbBG = new JLabel(R.icon.resize(260, 171, 2, "icon/chitietcv/changeStateBg.png"));
		lbBG.setBounds(0, 0, 260, 171);
		getContentPane().add(lbBG);
	}
	
	public void init(int state) {
		this.s = state;
		changeBtState();
		setVisible(true);
		setLocation(MouseInfo.getPointerInfo().getLocation());
	}
	
	private void changeBtState() {
		switch (s) {
		case 0:			
			btToDo.setIcon(R.icon.resize(55, 55, 2, "icon/chitietCV/toDo-Blue.png"));
			btDoing.setIcon(R.icon.resize(55, 55, 2, "icon/chitietCV/doing-White.png"));
			btDone.setIcon(R.icon.resize(55, 55, 2, "icon/chitietCV/done-White.png"));
			break;
		case 1:
			btDoing.setIcon(R.icon.resize(55, 55, 2, "icon/chitietCV/doing-Blue.png"));
			btToDo.setIcon(R.icon.resize(55, 55, 2, "icon/chitietCV/toDo-White.png"));
			btDone.setIcon(R.icon.resize(55, 55, 2, "icon/chitietCV/done-White.png"));
			break;
		case 2:
			btDone.setIcon(R.icon.resize(55, 55, 2, "icon/chitietCV/done-Blue.png"));
			btDoing.setIcon(R.icon.resize(55, 55, 2, "icon/chitietCV/doing-White.png"));
			btToDo.setIcon(R.icon.resize(55, 55, 2, "icon/chitietCV/toDo-White.png"));
			break;
		default:
			break;
		}
	}

}
