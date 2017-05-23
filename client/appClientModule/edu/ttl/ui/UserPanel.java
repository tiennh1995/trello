package edu.ttl.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.ttl.constant.R;

public class UserPanel extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton exit;
	private JLabel userName = new JLabel();
	private TextPanel profile = new TextPanel(230, 40, "Thông tin người dùng");
	private TextPanel admin = new TextPanel(230, 40, "Tính năng quản lý");
	private TextPanel about = new TextPanel(230, 40, "Thông tin phần mềm");
	private TextPanel logout = new TextPanel(230, 40, "Đăng xuất");
	
	private JPanel pnCenter = new JPanel();
	private JPanel pnTop  = new JPanel();
	
	private JPanel content = new JPanel();

	/**
	 * Create the dialog.
	 */
	public UserPanel() {
		//setAlwaysOnTop(true);
		setResizable(false);
		setUndecorated(true);
		getContentPane().setLayout(null);
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		content.setBounds(0, 0, 230, 200);
		content.setOpaque(false);

		pnTop.setOpaque(false);
		pnTop.setLayout(null);
		exit = new JButton(R.icon.resize(15, 15, 2, "icon/chitietCV/delete.png"));
		exit.setOpaque(false);
		exit.setContentAreaFilled(false);
		exit.setBorder(null);
		exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		exit.setBounds(210, 5, 15, 15);
		pnTop.add(exit);
		pnTop.add(userName);
		pnTop.setPreferredSize(new Dimension(230, 40));
		
		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
		pnCenter.setSize(new Dimension(230, 120));
		pnCenter.setOpaque(false);
		
		getContentPane().add(content);
		
		JLabel bg = new JLabel(R.icon.resize(230, 200, 2, "icon/user/userPanelBG.png"));
		bg.setBounds(0, 0, 230, 200);
		getContentPane().add(bg);
		
		logout.addMouseListener(new MouseListener() {
			
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
				dispose();
				R.main.reset();
			}
		});
		
		profile.addMouseListener(new MouseListener() {
			
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
				dispose();
				R.main.userControlDialog.changeInfo();
			}
		});
		
		about.addMouseListener(new MouseListener() {
			
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
				new AboutApp();
			}
		});
		
		admin.addMouseListener(new MouseListener() {
			
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
				R.main.adminControlDialog.init();
			}
		});
	}

	public void init() {
		userName.setText(R.user.getName());
		userName.setFont(R.FONT14);
		userName.setForeground(Color.GRAY);
		userName.setBounds((230 - userName.getPreferredSize().width)/2, 12,
				userName.getPreferredSize().width, userName.getPreferredSize().height);
		pnCenter.removeAll();
		pnCenter.add(profile);
		if (R.user.isAdmin()) {
			pnCenter.add(admin);
		}
		pnCenter.add(about);
		if (!R.user.isAdmin()) {
			Dimension d = new Dimension(230, 40);
			pnCenter.add(new Box.Filler(d, d, d));
		}
		setBounds(R.main.getWidth() - 230 - R.margin * 5, (int)(1.5*R.main.TOOLBARHEIGHT), 230, 200);
		content.removeAll();
		content.add(pnTop);
		content.add(pnCenter);	
		Dimension d = new Dimension(10, 10);
		content.add(new Box.Filler(d, d, d));
		content.add(logout);	
		logout.setTextFont(R.FONT16B);
		profile.setTextFont(R.FONT16B);
		about.setTextFont(R.FONT16B);
		admin.setTextFont(R.FONT16B);
		setVisible(true);
	}
	
}
