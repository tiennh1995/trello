package edu.ttl.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import edu.ttl.constant.Database;
import edu.ttl.constant.R;
import edu.ttl.object.NewUserInfo;
import edu.ttl.tool.MyIcon;

public class RegisteredPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private static final long serialVersionUID = 1L;

	private JLabel lbRegisteredBG = new JLabel();
	private MyIcon icon = new MyIcon();
	private static JFormattedTextField ftfIP = new JFormattedTextField();
	private static JFormattedTextField ftfName = new JFormattedTextField();
	private static JPasswordField ftfPassWord = new JPasswordField();
	private static JFormattedTextField ftfUserName = new JFormattedTextField();
	private static JLabel lbHintIP = new JLabel("Địa chỉ IP máy chủ");
	private static JLabel lbHintUserName = new JLabel("Tài khoản");
	private static JLabel lbHintPassWord = new JLabel("Mật khẩu");
	private static JLabel lbHintName = new JLabel("Họ tên");
	private JButton btRegistered = new JButton();
	private JButton btCancel = new JButton();

	public RegisteredPanel() {
		setLayout(null);
	}

	public void init(String IP, String userName) {
		ftfIP.setText(IP);
		ftfIP.setToolTipText("\u0110\u1ECBa ch\u1EC9 IP m\u00E1y ch\u1EE7");
		ftfIP.setForeground(new Color(192, 192, 192));
		ftfIP.setBounds(36, 232, 198, 25);
		ftfIP.setFont(R.FONT16);
		ftfIP.setForeground(Color.BLACK);
		ftfIP.setBorder(null);
		ftfIP.setBackground(null);
		ftfIP.setOpaque(false);
		this.add(ftfIP);

		if (IP.isEmpty()) {
			lbHintIP.setBounds(0, 0, 170, 25);
			lbHintIP.setFont(R.FONT14);
			lbHintIP.setBackground(null);
			lbHintIP.setForeground(Color.GRAY);
			ftfIP.add(lbHintIP);
			ftfIP.requestFocus();
			ftfIP.addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
				}

				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
				}

				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					lbHintIP.setVisible(false);
				}
			});
		}

		ftfUserName.setText(userName);
		ftfUserName.setToolTipText("T\u00E0i kho\u1EA3n");
		ftfUserName.setForeground(Color.BLACK);
		ftfUserName.setBounds(36, 281, 198, 25);
		ftfUserName.setFont(R.FONT16);
		ftfUserName.setBorder(null);
		ftfUserName.setBackground(null);
		ftfUserName.setOpaque(false);
		this.add(ftfUserName);

		if (userName.isEmpty()) {
			if (!IP.isEmpty()) ftfUserName.requestFocus();
			ftfUserName.add(lbHintUserName);
			lbHintUserName.setBounds(0, 0, 170, 25);
			lbHintUserName.setFont(R.FONT14);
			lbHintUserName.setBackground(null);
			lbHintUserName.setForeground(Color.GRAY);
			ftfUserName.addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
				}

				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
				}

				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					lbHintUserName.setVisible(false);
				}
			});
		}

		ftfPassWord.setToolTipText("M\u1EADt kh\u1EA9u");
		ftfPassWord.setForeground(Color.BLACK);
		ftfPassWord.setBounds(36, 330, 198, 25);
		ftfPassWord.setFont(R.FONT14);
		ftfPassWord.setBorder(null);
		ftfPassWord.setBackground(null);
		ftfPassWord.setOpaque(false);
		this.add(ftfPassWord);

		lbHintPassWord.setBounds(0, 0, 170, 25);
		lbHintPassWord.setFont(R.FONT14);
		lbHintPassWord.setBackground(null);
		lbHintPassWord.setForeground(Color.GRAY);
		ftfPassWord.add(lbHintPassWord);
		ftfPassWord.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				lbHintPassWord.setVisible(false);
			}
		});

		ftfName.setToolTipText("Họ tên");
		ftfName.setForeground(Color.BLACK);
		ftfName.setBounds(36, 379, 198, 25);
		ftfName.setFont(R.FONT16);
		ftfName.setBorder(null);
		ftfName.setBackground(null);
		ftfName.setOpaque(false);
		this.add(ftfName);

		ftfName.add(lbHintName);
		lbHintName.setBounds(0, 0, 170, 25);
		lbHintName.setFont(R.FONT14);
		lbHintName.setBackground(null);
		lbHintName.setForeground(Color.GRAY);
		ftfName.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				lbHintName.setVisible(false);
			}
		});

		btRegistered.setIcon(icon.resize(90, 0, 0, "icon/login/btRegistered.png"));
		btRegistered.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btRegistered.setBorder(null);
		// btRegistered.setOpaque(false);
		btRegistered.setContentAreaFilled(false);
		btRegistered.setBounds(34, 450, 90, icon.getHeight());
		R.login.getRootPane().setDefaultButton(btRegistered);
		this.add(btRegistered);
		btRegistered.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				R.IP_sever = ftfIP.getText();
				if (!R.checkUserName("Tài khoản", ftfUserName.getText())) {
					ftfUserName.requestFocus();
					ftfUserName.selectAll();
					ftfPassWord.setText("");
					return;
				}
				if (!R.checkPassWord("Mật khẩu", ftfPassWord.getPassword())) {
					ftfPassWord.setText("");
					ftfPassWord.requestFocus();
					return;
				}
				if (ftfName.getText().length() >= 100) {
					JOptionPane.showMessageDialog(null, "Tên bạn đặt dài hơn số ký tự tối đa (100 ký tự).");
					ftfName.requestFocus();
					ftfName.selectAll();
				}
				NewUserInfo newUser = new NewUserInfo(ftfUserName.getText(), ftfPassWord.getPassword(),
						ftfName.getText());
				if (R.checkIP_sever() && newUser.checkNewUser() == 0) {
					Database.sendNewUserInfo(newUser);
				}
			}
		});

		btCancel.setIcon(icon.resize(90, 0, 0, "icon/login/btCancel.png"));
		btCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btCancel.setBorder(null);
		// btRegistered.setOpaque(false);
		btCancel.setContentAreaFilled(false);
		btCancel.setBounds(133, 450, 90, icon.getHeight());
		this.add(btCancel);

		btCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setnull();
				setVisible(false);
				Login.setnull2();
				R.login.pnLogin.setVisible(true);
			}
		});

		lbRegisteredBG.setIcon(icon.resize(445, 590, 2, "icon/login/registeredBG.png"));
		lbRegisteredBG.setBounds(0, 0, 445, 590);
		this.add(lbRegisteredBG);

	}

	public static void setnull() {
		ftfIP.setText("");
		ftfIP.requestFocus();
		ftfUserName.setText("");
		ftfPassWord.setText("");
		ftfName.setText("");
		lbHintIP.setVisible(true);
		lbHintUserName.setVisible(true);
		lbHintPassWord.setVisible(true);
		lbHintName.setVisible(true);
	}

	public static void setnull2() {
		ftfName.requestFocus();
		ftfUserName.setText("");
		ftfPassWord.setText("");
		ftfName.setText("");
		lbHintUserName.setVisible(true);
		lbHintPassWord.setVisible(true);
		lbHintName.setVisible(true);
	}

	public void showResultRegedited(int result) {
		if (result == 0) {
			JOptionPane.showMessageDialog(null, "Tạo tài khoản " + ftfUserName.getText() + " thành công.");
			setVisible(false);
			R.login.setUserName(ftfUserName.getText(), ftfIP.getText());
			R.login.pnLogin.setVisible(true);
			setnull2();
		} else if (result == 1) {
			JOptionPane.showMessageDialog(null,
					"Tài khoản " + ftfUserName.getText() + " không khả dụng. Vui lòng chọn tên đăng nhập khác.");
			ftfUserName.requestFocus();
			ftfUserName.selectAll();
			ftfPassWord.setText("");
		}
	}
}
