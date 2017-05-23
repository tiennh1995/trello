package edu.ttl.ui;

import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.ttl.constant.Database;
import edu.ttl.constant.R;
import edu.ttl.object.NguoiDung;

public class AdminControlDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton exit;
	private JTextField tfUserName = new JTextField();
	private JTextField tfName = new JTextField();
	private JButton btIsAdmin = new JButton();
	private JButton btUser = new JButton();
	private JButton btOK = new JButton();
	private JButton btCancel = new JButton();
	private final int dw = 342;
	private final int dh = 407;
	private int isAdmin = 0;
	public NguoiDung curUser = new NguoiDung();
	public ThemThanhVien searchMem = new ThemThanhVien();
	private JButton btSearch = new JButton();
	private JLabel lbCurUser = new JLabel();
	private JPanel pnLb = new JPanel();

	/**
	 * Create the dialog.
	 */
	public AdminControlDialog() {
		setResizable(false);
		setUndecorated(true);
		getContentPane().setBackground(null);
		setSize(dw, dh);
		setLocation(100, 50);

		getContentPane().setLayout(null);
		exit = new JButton(R.icon.resize(15, 15, 2, "icon/chitietCV/delete.png"));
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
		exit.setBounds(dw - 20, 5, 15, 15);
		getContentPane().add(exit);

		pnLb.setLayout(new WrapLayout(FlowLayout.CENTER, 0, 0));
		pnLb.setOpaque(false);
		pnLb.add(lbCurUser);

		getContentPane().add(pnLb);
		
		int x = 66;
		int tfw = 212;
		int tfh = 31;

		tfUserName.setBounds(x, 105 + 3, tfw, tfh);
		tfUserName.setOpaque(false);
		tfUserName.setBorder(null);
		getContentPane().add(tfUserName);

		tfName.setBounds(x, 189 + 3, tfw, tfh);
		tfName.setOpaque(false);
		tfName.setBorder(null);
		getContentPane().add(tfName);
		
		btSearch.setIcon(R.icon.resize(tfh, tfh, 2, "icon/user/search.png"));
		btSearch.setBorder(null);
		btSearch.setBackground(null);
		btSearch.setContentAreaFilled(false);
		btSearch.setBounds(x + tfw + 10, 189 + 3, tfh, tfh);
		btSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(btSearch);

		btIsAdmin.setBounds(58, 259, 101, 37);
		btIsAdmin.setContentAreaFilled(false);
		btIsAdmin.setBorder(null);
		btIsAdmin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(btIsAdmin);

		btUser.setBounds(183, 259, 101, 37);
		btUser.setContentAreaFilled(false);
		btUser.setBorder(null);
		btUser.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(btUser);

		btOK.setBounds(67, 335, 95, 41);
		btOK.setOpaque(false);
		btOK.setContentAreaFilled(false);
		btOK.setBorder(null);
		btOK.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(btOK);

		btCancel.setBounds(180, 335, 95, 41);
		btCancel.setOpaque(false);
		btCancel.setContentAreaFilled(false);
		btCancel.setBorder(null);
		btCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(btCancel);

		JLabel bg = new JLabel(R.icon.resize(dw, dh, 2, "icon/user/AdminControlPanelBG.png"));
		bg.setBounds(0, 0, dw, dh);
		getContentPane().add(bg);

		setBackground(null);
		
		btSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				searchMem.init(2);
			}
		});
		
		btIsAdmin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (isAdmin == 0) {
					isAdmin = 1;
					setIsAdmin();
				}
			}
		});

		btUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (isAdmin == 1) {
					isAdmin = 0;
					setIsAdmin();
				}
			}
		});
		
		btCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setUserInfo();
				setIsAdmin();
			}
		});
		
		btOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				R.checkUserName("Tài khoản", tfUserName.getText());
				curUser.setUserName(tfUserName.getText());
				curUser.setName(tfName.getText());
				curUser.setIsAdmin(isAdmin);
				Database.sendUpdateUser(curUser);
			}
		});
	}

	public void init() {
		isAdmin = 1;
		setLocation((R.screenWidth - dw) / 2, (R.screenHeight - dh) / 2);
		tfUserName.setFont(R.FONT16);
		tfName.setFont(R.FONT16);
		setIsAdmin();
		lbCurUser.setText("Bấm nút search cạnh ô nhập tên để chọn");
		lbCurUser.setFont(R.FONT14);
		pnLb.setSize(dw, dh);
		pnLb.setBounds(10, 55, dw - 14, pnLb.getPreferredSize().height);
		setVisible(true);
	}

	public void myDispose() {
		dispose();
		curUser.clear();
		tfName.setText("");
		tfUserName.setText("");
	}

	public void setIsAdmin() {
		if (isAdmin == 1) {
			btIsAdmin.setIcon(R.icon.resize(101, 37, 2, "icon/user/Admin-blue.png"));
			btUser.setIcon(R.icon.resize(101, 37, 2, "icon/user/User-gray.png"));
		} else {
			btIsAdmin.setIcon(R.icon.resize(101, 37, 2, "icon/user/Admin-gray.png"));
			btUser.setIcon(R.icon.resize(101, 37, 2, "icon/user/User-blue.png"));
		}
	}
	
	public void setUserInfo() {
		tfUserName.setText(curUser.getUserName());
		tfName.setText(curUser.getName());
		isAdmin = curUser.isAdmin() ? 1 : 0;
		setIsAdmin();
		lbCurUser.setText("Người dùng: " + curUser.getName() + "(" + curUser.getUserName() + ")" );
		lbCurUser.setFont(R.FONT14);
		pnLb.setSize(dw, dh);
		pnLb.setBounds(10, 55, dw - 14, pnLb.getPreferredSize().height);
	}
}
