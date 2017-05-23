package edu.ttl.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import edu.ttl.constant.Database;
import edu.ttl.constant.R;
import edu.ttl.object.LoginInfo;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected JPanel pnLogin = new JPanel();
	private RegisteredPanel pnRegistered = new RegisteredPanel();
	private JLabel lbLoginBG = new JLabel();
	private static JFormattedTextField ftfIP = new JFormattedTextField();
	private static JFormattedTextField ftfUserName = new JFormattedTextField();
	private static JPasswordField ftfPassWord = new JPasswordField();
	private static JLabel lbHintIP;
	private static JLabel lbHintPassWord;
	private static JLabel lbHintUserName;
	
	protected JButton btLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					R.main.init(); // -------------------------------------------------------------------@@
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		try {
			UIManager.setLookAndFeel(R.windowsClassName);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        SwingUtilities.updateComponentTreeUI(this);
		lbLoginBG.setIcon(R.icon.resize(445, 590, 2, "icon/login/background.png"));
		lbLoginBG.setBounds(0, 0, 445, 590);
		pnLogin.add(lbLoginBG);
		R.login = this;
		pnLogin.setLayout(null);
		setTitle("Qu\u1EA3n l\u00FD d\u1EF1 \u00E1n");
		setIconImage(Toolkit.getDefaultToolkit().getImage("icon/AppIcon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((int) ((R.screenWidth - 450) / 2), (int) ((R.screenHeight - 650) / 2), 450, 618);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ftfIP.setToolTipText("Địa chỉ IP máy chủ");
		ftfIP.setForeground(new Color(192, 192, 192));
		ftfIP.setBounds(36, 232, 198, 25);
		ftfIP.setFont(R.FONT16);
		ftfIP.setBorder(null);
		ftfIP.setForeground(Color.BLACK);
		ftfIP.setBackground(null);
		ftfIP.setOpaque(false);
		pnLogin.add(ftfIP);
		lbHintIP = new JLabel("Địa chỉ IP máy chủ");
		lbHintIP.setBounds(0, 0, 170, 25);
		lbHintIP.setFont(R.FONT14);
		lbHintIP.setBackground(null);
		lbHintIP.setForeground(Color.GRAY);
		ftfIP.add(lbHintIP);
		
		if (!R.IP_sever.isEmpty()) {
			ftfIP.setText(R.IP_sever);
			lbHintIP.setVisible(false);
			ftfUserName.requestFocus();
		}

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

		ftfUserName.setToolTipText("T\u00E0i kho\u1EA3n");
		ftfUserName.setForeground(Color.BLACK);
		ftfUserName.setBounds(36, 281, 198, 25);
		ftfUserName.setFont(R.FONT16);
		ftfUserName.setBorder(null);
		ftfUserName.setBackground(null);
		ftfUserName.setOpaque(false);
		pnLogin.add(ftfUserName);

		lbHintUserName = new JLabel("Tài khoản");
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

		ftfPassWord.setToolTipText("M\u1EADt kh\u1EA9u");
		ftfPassWord.setForeground(Color.BLACK);
		ftfPassWord.setBounds(36, 330, 198, 25);
		ftfPassWord.setFont(R.FONT16);
		ftfPassWord.setBorder(null);
		ftfPassWord.setBackground(null);
		ftfPassWord.setOpaque(false);
		pnLogin.add(ftfPassWord);

		lbHintPassWord = new JLabel("Mật khẩu");
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

		btLogin = new JButton();
		btLogin.setIcon(R.icon.resize(100, 0, 0, "icon/login/btLogin.png"));
		btLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btLogin.setBorder(null);
		// btLogin.setOpaque(false);
		btLogin.setContentAreaFilled(false);
		btLogin.setBounds(77, 400, 100, R.icon.getHeight());
		getRootPane().setDefaultButton(btLogin);
		pnLogin.add(btLogin);
		btLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				R.IP_sever = ftfIP.getText();
				LoginInfo info = new LoginInfo(ftfUserName.getText(), ftfPassWord.getPassword());
				if (R.checkIP_sever() && info.checkData() == 0) {
					Database.sendLoginInfo(info);	
				}
			}
		});

		JLabel lbRegistered = new JLabel("Đăng ký tài khoản");
		lbRegistered.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lbRegistered.setFont(R.FONT16);
		lbRegistered.setForeground(Color.WHITE);
		lbRegistered.setBorder(null);
		lbRegistered.setBackground(null);
		lbRegistered.setBounds(10, 555, 134, 25);
		pnLogin.add(lbRegistered);

		lbRegistered.addMouseListener(new MouseListener() {

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
				pnLogin.setVisible(false);
				pnRegistered.init(ftfIP.getText(), ftfUserName.getText());
				pnRegistered.setVisible(true);
			}
		});
		
		pnLogin.add(lbLoginBG);

		pnRegistered.setBounds(0, 0, 445, 590);
		pnRegistered.setVisible(false);
		contentPane.add(pnRegistered);

		pnLogin.setBounds(0, 0, 445, 590);
		contentPane.add(pnLogin);
	}

	public static void setnull() {
		ftfIP.setText("");
		ftfIP.requestFocus();
		ftfUserName.setText("");
		ftfPassWord.setText("");
		lbHintIP.setVisible(true);
		lbHintPassWord.setVisible(true);
		lbHintUserName.setVisible(true);
		R.login.getRootPane().setDefaultButton(R.login.btLogin);
	}
	
	public static void setnull2() {
		ftfUserName.requestFocus();
		ftfUserName.setText("");
		ftfPassWord.setText("");
		lbHintPassWord.setVisible(true);
		lbHintUserName.setVisible(true);
		R.login.getRootPane().setDefaultButton(R.login.btLogin);
	}
	
	public void setUserName(String username, String IP) {
		ftfUserName.setText(username);
		ftfIP.setText(IP);
		lbHintIP.setVisible(false);
		R.login.getRootPane().setDefaultButton(R.login.btLogin);
		lbHintUserName.setVisible(false);
	}
	
	public RegisteredPanel getPnRegistered() {
		return pnRegistered;
	}
}
