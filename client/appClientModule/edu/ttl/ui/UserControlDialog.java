package edu.ttl.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import edu.ttl.constant.Database;
import edu.ttl.constant.FileStream;
import edu.ttl.constant.R;

public class UserControlDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton exit;
	protected JButton btAvatar = new JButton();
	private JLabel lbUserName = new JLabel();
	private JTextField tfFullName = new JTextField();
	private JPasswordField pfOldPW = new JPasswordField();
	private JPasswordField pfNewPW = new JPasswordField();
	private JPasswordField pfRePW = new JPasswordField();
	private JButton btOKProfile = new JButton();
	private JButton btCancelProfile = new JButton();
	private JButton btOKChangePW = new JButton();
	private JButton btCancelPW = new JButton();

	/**
	 * Create the dialog.
	 */
	public UserControlDialog() {
		//setAlwaysOnTop(true);
		setResizable(false);
		setUndecorated(true);
		getContentPane().setBackground(null);
		setSize(720, 650);
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
		exit.setBounds(700, 5, 15, 15);
		getContentPane().add(exit);

		int x = 270;
		int w = 212, h = 31;

		btAvatar.setBounds(133, 64, 99, 99);
		btAvatar.setBorder(null);
		btAvatar.setContentAreaFilled(false);
		btAvatar.setToolTipText("Đổi avatar");
		btAvatar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(btAvatar);

		lbUserName.setBounds(x, 86, w, h);
		lbUserName.setBackground(null);
		getContentPane().add(lbUserName);

		tfFullName.setBounds(x, 171, w, h);
		tfFullName.setOpaque(false);
		tfFullName.setBorder(null);
		getContentPane().add(tfFullName);

		pfOldPW.setBounds(x, 357, w, h);
		pfOldPW.setOpaque(false);
		pfOldPW.setBorder(null);
		getContentPane().add(pfOldPW);

		pfNewPW.setBounds(x, 431, w, h);
		pfNewPW.setOpaque(false);
		pfNewPW.setBorder(null);
		getContentPane().add(pfNewPW);

		pfRePW.setBounds(x, 504, w, h);
		pfRePW.setOpaque(false);
		pfRePW.setBorder(null);
		getContentPane().add(pfRePW);

		btOKProfile.setBounds(273, 222, 95, 41);
		btOKProfile.setOpaque(false);
		btOKProfile.setContentAreaFilled(false);
		btOKProfile.setBorder(null);
		btOKProfile.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(btOKProfile);

		btCancelProfile.setBounds(386, 222, 95, 41);
		btCancelProfile.setOpaque(false);
		btCancelProfile.setContentAreaFilled(false);
		btCancelProfile.setBorder(null);
		btCancelProfile.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(btCancelProfile);

		btOKChangePW.setBounds(273, 556, 95, 41);
		btOKChangePW.setOpaque(false);
		btOKChangePW.setContentAreaFilled(false);
		btOKChangePW.setBorder(null);
		btOKChangePW.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(btOKChangePW);

		btCancelPW.setBounds(386, 556, 95, 41);
		btCancelPW.setOpaque(false);
		btCancelPW.setContentAreaFilled(false);
		btCancelPW.setBorder(null);
		btCancelPW.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(btCancelPW);

		JLabel bg = new JLabel(R.icon.resize(720, 650, 2, "icon/user/userControlPanelBG.png"));
		bg.setBounds(0, 0, 720, 650);
		getContentPane().add(bg);

		btCancelProfile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tfFullName.setText(R.user.getName());
			}
		});

		btCancelPW.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pfOldPW.setText("");
				pfNewPW.setText("");
				pfRePW.setText("");
			}
		});

		btOKProfile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = tfFullName.getText();
				if (!name.equals("") && !name.equals(R.user.getName())) {
					if (name.length() >= 100) {
						JOptionPane.showMessageDialog(R.main, "Tên bạn đặt quá dài.");
						tfFullName.selectAll();
						tfFullName.requestFocus();
						return;
					}
					R.user.setName(name);
					tfFullName.setText(name);
					Database.sendUpdateUser();
				}
			}
		});

		btOKChangePW.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				char[] Pold = pfOldPW.getPassword();
				if (!R.checkPassWord("Mật khẩu cũ", Pold)) {
					pfOldPW.requestFocus();
					pfOldPW.setText("");
					pfNewPW.setText("");
					pfRePW.setText("");
					return;
				}
				char[] Pnew = pfNewPW.getPassword();
				if (!R.checkPassWord("Mật khẩu mới", Pnew)) {
					pfNewPW.requestFocus();
					pfNewPW.setText("");
					pfRePW.setText("");
					return;
				}
				char[] Pre = pfRePW.getPassword();
				if (!new String(Pre).equals(new String(Pnew))) {
					pfNewPW.requestFocus();
					pfNewPW.setText("");
					pfRePW.setText("");
					JOptionPane.showMessageDialog(null, "Mật khẩu nhập lại không trùng khớp");
					return;
				}
				R.user.setPassWord(new String(Pnew));
				Database.sendUpdateUser();
				pfOldPW.setText("");
				pfNewPW.setText("");
				pfRePW.setText("");
			}
		});

		btAvatar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FileChooser fc = new FileChooser("Open file", FileChooser.FILE_OPEN, "png, jpg, jpeg, gif",
						"Select a photo (*png, *jpg)");
				if (fc.isSuccess()) {
					BufferedInputStream bis = null;
					try {
						File myFile = fc.getFile();
						String name = fc.getFileName();
						String str = "IMGAVAUPDA" + R.user.getUserID() + "|" + name + "|";
						byte[] cmt = str.getBytes(Charset.forName("UTF-8"));
						BufferedImage image = ImageIO.read(myFile);
						Image img;
						int avatarw = 150;
						int x = avatarw;
						int y = avatarw;
						int ix = image.getWidth();
						int iy = image.getHeight();
						int dx, dy;

						if (ix > avatarw || iy > avatarw) {
							if ((float) x / y >= (float) ix / iy) {
								dy = y;
								dx = y;
							} else {
								dx = x;
								dy = x;
							}
						} else {
							if (ix > iy) {
								dx = iy;
								dy = iy;
							} else {
								dx = ix;
								dy = ix;
							}
						}

						img = image.getScaledInstance(dx, dy, java.awt.Image.SCALE_SMOOTH);
						ByteArrayOutputStream os = new ByteArrayOutputStream();
						ImageIO.write(R.toBufferedImage(img), "jpg", os);
						bis = new BufferedInputStream(new ByteArrayInputStream(os.toByteArray()));
						byte[] byteImage = new byte[(int) os.size() + cmt.length];
						for (int i = 0; i < cmt.length; i++) {
							byteImage[i] = cmt[i];
						}
						bis.read(byteImage, cmt.length, byteImage.length - cmt.length);
						byte[] size = ByteBuffer.allocate(9).putInt(byteImage.length).array();
						FileStream.upFile(size, byteImage);
					} catch (FileNotFoundException ex) {
						Logger.getLogger(R.main.getName()).log(Level.SEVERE, null, ex);
					} catch (IOException ex) {
						Logger.getLogger(R.main.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
		});
	}

	public void changeInfo() {
		btAvatar.setIcon(R.icon.resize(99, 99, 2, R.user.getAvatarUrl()));
		lbUserName.setText(R.user.getUserName());
		tfFullName.setText(R.user.getName());
		setLocation((R.screenWidth - 720) / 2, 50);
		lbUserName.setFont(R.FONT16);
		tfFullName.setFont(R.FONT16);
		lbUserName.setForeground(Color.GRAY);
		pfOldPW.setFont(R.FONT14);
		pfNewPW.setFont(R.FONT14);
		pfRePW.setFont(R.FONT14);
		setVisible(true);
	}

	public void myDispose() {
		pfOldPW.setText("");
		pfNewPW.setText("");
		pfRePW.setText("");
		dispose();
	}
}
