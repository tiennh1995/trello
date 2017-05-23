package edu.ttl.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import org.jdatepicker.ComponentFormatDefaults;
import org.jdatepicker.UtilDateModel;

import edu.ttl.constant.Database;
import edu.ttl.constant.FileStream;
import edu.ttl.constant.R;
import edu.ttl.constant.Search;
import edu.ttl.object.Comment;
import edu.ttl.object.CongViec;
import edu.ttl.object.NguoiDung;

public class ChiTietCongViecPanel extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel slbName = new JLabel();
	private JLabel slbState = new JLabel();
	private JLabel lbCmtText;
	private JLabel lbBgCmtText = new JLabel();
	private JPanel pnCmt = new JPanel();
	private JScrollPane scCmt2 = new JScrollPane(pnCmt);
	private JTextArea taCmt = new JTextArea();
	private JScrollPane scCmt = new JScrollPane(taCmt);
	private JButton btSendCmt = new JButton();
	private ImageButton btReName = new ImageButton("Đổi tên", "icon/chitietCV/rename.png");
	private ImageButton btAddMember = new ImageButton("Phụ trách", "icon/chitietCV/member.png");
	private ImageButton btDeadline = new ImageButton("Deadline", "icon/chitietCV/deadline.png");
	private ImageButton btAddFile = new ImageButton("Ảnh", "icon/chitietCV/file.png");
	private ImageButton btMove = new ImageButton("Đổi trạng thái", "icon/chitietCV/move.png");
	private ImageButton btDelete = new ImageButton("Xóa", "icon/chitietCV/delete.png");
	private ImageButton btRefresh = new ImageButton("Làm mới", "icon/chitietCV/refresh.png");
	private JPanel pnNguoiLam = new JPanel();
	public static CongViec cv = new CongViec();
	private String state = new String();
	private JLabel lbThanhVien;
	private JLabel ftfDeadlineText;
	// private JLabel ftfDeadlineBG = new JLabel();
	private int soNguoi;

	private JPanel pnXX = new JPanel();

	private JPanel pnMoTa = new JPanel();
	private JLabel lbChinhSua = new JLabel();
	private JLabel lbHuy = new JLabel();
	private JTextArea taMoTa = new JTextArea();
	private JScrollPane scMoTa = new JScrollPane(taMoTa);

	public static ArrayList<Comment> listCmt = new ArrayList<Comment>();
	private JLabel lbTrangThai;
	private JLabel lbCmtIcon;
	private JLabel lbNameIcon;
	private JLabel lbAvatar;

	private int hCmt;
	private UtilDateModel model = new UtilDateModel();
	private JFormattedTextField ftfDeadline = new JFormattedTextField();
	private MyDatePicker datePicker;

	protected ChangeNameJobPanel PnChangeName = new ChangeNameJobPanel();
	private ChangeStateDialog changeStateDialog = new ChangeStateDialog();

	protected ThemThanhVien addMember = new ThemThanhVien();
/*	private 		MouseListener mouseClick = new MouseListener() {

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
			myDisposeComponent(arg0);
		}
	};*/

	// private JPanel bgPn = new JPanel();
	// private JScrollPane scbg = new JScrollPane(bgPn);

	public ChiTietCongViecPanel() {
		//setAlwaysOnTop(true);
		setLayout(new BorderLayout());
		setUndecorated(true);
		setContentPane(pnXX);
		setSize(720, 650);
		setLocation((R.screenWidth - 720) / 2, 50);
		setBackground(Color.WHITE);
		pnXX.setBackground(Color.WHITE);
		pnXX.addMouseListener(new MouseListener() {

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
				if (addMember.isVisible())
					addMember.Mydispose();
				if (changeStateDialog.isVisible())
					changeStateDialog.dispose();
				if (PnChangeName.isVisible())
					PnChangeName.myDispose();
			}
		});
		// add(scbg, BorderLayout.CENTER);
		pnXX.setLayout(null);
		lbThanhVien = new JLabel("Thành viên phụ trách");
		lbThanhVien.setFont(R.FONT14);
		lbThanhVien.setForeground(Color.GRAY);
		lbThanhVien.setBounds(250 - 202, 163 - 90 - lbThanhVien.getPreferredSize().height,
				lbThanhVien.getPreferredSize().width, lbThanhVien.getPreferredSize().height);
		pnXX.add(lbThanhVien);
		// pnNguoiLam.setBackground(null);
		pnNguoiLam.setOpaque(true);
		pnNguoiLam.setBackground(Color.WHITE);
		pnNguoiLam.setLayout(new WrapLayout(FlowLayout.LEFT, 0, 0));

		ftfDeadlineText = new JLabel("Deadline");
		ftfDeadlineText.setFont(R.FONT14);
		ftfDeadlineText.setForeground(Color.GRAY);

		pnMoTa.setLayout(new WrapLayout(FlowLayout.LEFT, 10, 0));
		JLabel lbMoTa = new JLabel("Mô tả");
		lbMoTa.setFont(R.FONT14);
		lbMoTa.setForeground(Color.GRAY);
		pnMoTa.add(lbMoTa);

		pnCmt.setLayout(new BoxLayout(pnCmt, BoxLayout.Y_AXIS));
		pnCmt.setOpaque(true);
		pnCmt.setBackground(Color.WHITE);
		scCmt2.setBorder(null);
		scCmt2.getVerticalScrollBar().setUnitIncrement(10);
		scCmt2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pnXX.add(scCmt2);
		
		lbHuy.setText("Hủy");
		lbHuy.setForeground(Color.GRAY);
		lbHuy.setFont(R.FONT14);
		lbHuy.setVisible(false);
		lbHuy.addMouseListener(new MouseListener() {

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
				taMoTa.setText(cv.getMoTa());
				lbHuy.setVisible(false);
				if (cv.getMoTa().isEmpty())
					lbChinhSua.setText("Thêm mô tả");
				else
					lbChinhSua.setText("Chỉnh sửa");
			}
		});

		lbChinhSua.addMouseListener(new MouseListener() {

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
				lbChinhSua.setText("Lưu");
				if (!taMoTa.getText().equals(cv.getMoTa())) {
					cv.setMoTa(taMoTa.getText());
					Database.sendUpdateMota(cv);
					lbHuy.setVisible(false);
					if (cv.getMoTa().isEmpty())
						lbChinhSua.setText("Thêm mô tả");
					else
						lbChinhSua.setText("Chỉnh sửa");
					taMoTa.requestFocus(false);
				} else {
					taMoTa.requestFocus();
					lbHuy.setVisible(true);
				}
			}
		});
		
		JPanel pnTool = new JPanel();
		pnTool.setLayout(null);
		JLabel lbThem = new JLabel("Thêm");
		lbThem.setFont(R.FONT18B);
		lbThem.setBounds(0, 0, lbThem.getPreferredSize().width, lbThem.getPreferredSize().height);
		pnTool.add(lbThem);
		int h = lbThem.getPreferredSize().height + R.margin;
		btAddMember.setBounds(0, h, 133, 34);
		pnTool.add(btAddMember);
		h += 34 + R.margin;
		btDeadline.setBounds(0, h, 133, 34);
		pnTool.add(btDeadline);
		h += 34 + R.margin;
		btAddFile.setBounds(0, h, 133, 34);
		pnTool.add(btAddFile);
		h += 34 + 3 * R.margin;

		JLabel lbAction = new JLabel("Hành động");
		lbAction.setFont(R.FONT18B);
		lbAction.setBounds(0, h, lbAction.getPreferredSize().width, lbAction.getPreferredSize().height);
		pnTool.add(lbAction);
		h += lbAction.getPreferredSize().height + R.margin;
		btReName.setBounds(0, h, 133, 34);
		pnTool.add(btReName);
		h += 34 + R.margin;
		btMove.setBounds(0, h, 133, 34);
		pnTool.add(btMove);
		h += 34 + R.margin;
		btDelete.setBounds(0, h, 133, 34);
		pnTool.add(btDelete);
		h += 34 + R.margin;
		btRefresh.setBounds(0, h, 133, 34);
		pnTool.add(btRefresh);

		pnTool.setOpaque(false);
		pnTool.setBounds(570, 55, 133, h + 34);
		pnXX.add(pnTool);

		btDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!R.user.isAdmin()) {
					JOptionPane.showMessageDialog(null, "Bạn không có quyền xóa công việc này.\n"
							+ "Hãy liên hệ người quản lý để biết thêm thông tin");
					return;
				}
				int choose = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa " + cv.getTen() + "?",
						"Quản lý dự án", JOptionPane.YES_NO_OPTION);
				if (choose == JOptionPane.YES_OPTION) {
					Database.deleteCV(cv);
				}
			}
		});

		btRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//Database.updateChiTietCV = 1;
				Database.getListCV(R.congViecPanel.duAn.getMaDuAn(), 1);
			}
		});
		
		btReName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PnChangeName.setVisible(true);
				PnChangeName.setLocation(MouseInfo.getPointerInfo().getLocation());
				PnChangeName.taJobName.setText(cv.getTen());
				PnChangeName.taJobName.selectAll();
				PnChangeName.taJobName.requestFocus();
			}
		});

		PnChangeName.setVisible(false);
		PnChangeName.setLocation((R.screenWidth - 720) / 2 + 20, 50 + 40);
		PnChangeName.btOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = PnChangeName.taJobName.getText();
				if (!name.isEmpty() && !name.equals(cv.getTen())) {
					if (name.length() >= 100) {
						JOptionPane.showMessageDialog(null, "Tên công việc dài hơn 100 ký tự, hãy đặt tên ngắn hơn.");
						PnChangeName.taJobName.selectAll();
						PnChangeName.taJobName.requestFocus();
					} else {
						cv.setTen(name);
						Database.sendUpdateNameJob(cv);
						PnChangeName.setVisible(false);
					}
				}
			}
		});

		btAddMember.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				addMember.init(0);
			}
		});

		btMove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				changeStateDialog.init(cv.getTrangThai());
			}
		});

		btAddFile.addActionListener(new ActionListener() {

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
						String str = "IMGCMTNULL" + cv.getKey() + "|" + R.user.getUserID() + "|" + name + "|";
						byte[] cmt = str.getBytes(Charset.forName("UTF-8"));
						BufferedImage image = ImageIO.read(myFile);
						Image img;
						int x = R.imgSize;
						int y = R.imgSize;
						int ix = image.getWidth();
						int iy = image.getHeight();
						int dx, dy;

						if (ix > x || iy > x) {
							if ((float) x / y >= (float) ix / iy) {
								dy = y;
								float dxf = y * (float) ix / iy;
								dx = Math.round(dxf);
							} else {
								dx = x;
								float dyf = x * (float) iy / ix;
								dy = Math.round(dyf);
							}
						} else {
							dx = ix;
							dy = iy;
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

		JLabel exit = new JLabel(R.icon.resize(20, 20, 2, "icon/chitietCV/delete.png"));
		exit.setBounds(690, 10, 20, 20);
		exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		exit.setBackground(null);
		pnXX.add(exit);
		exit.addMouseListener(new MouseListener() {

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
				myDispose();
			}
		});

		btSendCmt.setIcon(R.icon.resize(133, 34, 2, "icon/chitietCV/btGreen.png"));
		btSendCmt.setBorder(null);
		JLabel lbGuiBinhLuan = new JLabel("Gửi bình luận");
		lbGuiBinhLuan.setFont(R.FONT14B);
		btSendCmt.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 7));
		btSendCmt.add(lbGuiBinhLuan);
		// taCmt.setOpaque(false);
		taCmt.requestFocus(false);
		taCmt.setBackground(Color.WHITE);
		taCmt.setBorder(null);
		taCmt.setLineWrap(true);
		taCmt.setWrapStyleWord(true);
		scCmt.setBorder(null);
		scCmt.setBackground(Color.white);

		lbAvatar = new JLabel();

		taMoTa.setBackground(Color.WHITE);
		taMoTa.setBorder(null);
		taMoTa.setLineWrap(true);
		taMoTa.setSize(493, 50);
		taMoTa.setLineWrap(true);
		taMoTa.setWrapStyleWord(true);
		scMoTa.setBorder(null);

		lbTrangThai = new JLabel("trạng thái");
		lbTrangThai.setFont(R.FONT14);
		slbState.setFont(R.FONT16B);
		slbName.setFont(R.FONT20B);

		lbChinhSua.setForeground(Color.GRAY);
		lbChinhSua.setFont(R.FONT14);

		lbCmtIcon = new JLabel(R.icon.resize(30, 30, 2, "icon/chitietCV/cmt.png"));
		lbNameIcon = new JLabel(R.icon.resize(30, 30, 2, "icon/chitietCV/name.png"));

		lbCmtText = new JLabel("Bình luận");
		pnXX.add(lbCmtText);

		ftfDeadline.setFont(R.FONT16B);
		ftfDeadline.setBorder(null);

		ComponentFormatDefaults defaults = ComponentFormatDefaults.getInstance();

		defaults.setFormat(ComponentFormatDefaults.Key.SELECTED_DATE_FIELD, new SimpleDateFormat("dd/MM/yyyy"));

		pnXX.add(ftfDeadline);
	}

	public void init() {
		/*Set<Component> components = R.getAllComponents(R.chitietCV);
		for (Component component : components) {
				component.addMouseListener(mouseClick);
		}*/
		taMoTa.setText(cv.getMoTa());
		taMoTa.setFont(R.FONT14);
		soNguoi = cv.getListNguoiLam().size();
		switch (cv.getTrangThai()) {
		case 0:
			state = "To Do";
			break;
		case 1:
			state = "Doing";
			break;
		case 2:
			state = "Done";
			break;
		}
		String tenViec = new String(cv.getTen());
		if (tenViec.length() > 50) {
			tenViec = tenViec.substring(0, 40) + "...";
		}
		slbName.setToolTipText(cv.getTen());
		slbName.setText(tenViec);

		slbName.setBounds(250 - 202, 131 - 89 - slbName.getPreferredSize().height, slbName.getPreferredSize().width,
				slbName.getPreferredSize().height);
		pnXX.add(slbName);

		lbNameIcon.setBounds(10, 15, 30, 30);
		pnXX.add(lbNameIcon);

		lbTrangThai.setBounds(250 - 202 + 6 + slbName.getPreferredSize().width,
				131 - 90 - lbTrangThai.getPreferredSize().height, lbTrangThai.getPreferredSize().width,
				lbTrangThai.getPreferredSize().height);
		pnXX.add(lbTrangThai);
		slbState.setText(state);
		slbState.setBounds(250 - 202 + 6 + slbName.getPreferredSize().width + 6 + lbTrangThai.getPreferredSize().width,
				131 - 90 - slbState.getPreferredSize().height, slbState.getPreferredSize().width,
				slbState.getPreferredSize().height);

		pnXX.add(slbState);

		int hNguoiLam = 163 - 90, wNguoiLam = 0;
		lbThanhVien.setVisible(true);
		setMember(cv.getListNguoiLam());
		if (soNguoi > 0) {
			pnXX.add(pnNguoiLam);
			pnNguoiLam.setSize(308, 50);
			pnNguoiLam.setBounds(250 - 202, 163 - 90, 308, pnNguoiLam.getPreferredSize().height);
		}
		wNguoiLam = 308;
		hNguoiLam += pnNguoiLam.getPreferredSize().height;
		ftfDeadlineText.setBounds(250 + wNguoiLam - 202, 163 - 90 - ftfDeadlineText.getPreferredSize().height,
				ftfDeadlineText.getPreferredSize().width, ftfDeadlineText.getPreferredSize().height);
		pnXX.add(ftfDeadlineText);

		if (!cv.getDeadline().isEmpty()) {
			String[] dt = cv.getDeadline().split("/");
			model.setDate(Integer.parseInt(dt[2]), Integer.parseInt(dt[1]) - 1, Integer.parseInt(dt[0]));
			model.setSelected(true);
			ftfDeadline.setText(cv.getDeadline());
			ftfDeadline.setVisible(true);
		} else {
			LocalDate da = LocalDate.now();
			model.setDate(da.getYear(), da.getMonthValue() - 1, da.getDayOfMonth());
			model.setSelected(true);
			ftfDeadline.setVisible(false);
		}

		ftfDeadline.setBackground(Color.WHITE);
		ftfDeadline.setBounds(250 + wNguoiLam - 202, 163 - 90, ftfDeadline.getPreferredSize().width,
				ftfDeadline.getPreferredSize().height);
		// datePicker.xy = MouseInfo.getPointerInfo().getLocation();
		datePicker = new MyDatePicker(model);
		datePicker.setComponent(ftfDeadline, btDeadline);
		datePicker.Init();
		datePicker.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String text = ftfDeadline.getText();
				if (!text.isEmpty()) {
					cv.setDeadline(text);
					Database.sendNewDeadLine(cv);
				}
			}
		});

		if (cv.getMoTa().isEmpty())
			lbChinhSua.setText("Thêm mô tả");
		else
			lbChinhSua.setText("Chỉnh sửa");

		pnMoTa.add(lbChinhSua);
		pnMoTa.add(lbHuy);

		pnMoTa.setSize(493, 1);
		pnMoTa.setBounds(38, hNguoiLam + 2 * R.margin, 493, pnMoTa.getPreferredSize().height);
		pnMoTa.setOpaque(false);
		pnXX.add(pnMoTa);

		scMoTa.setPreferredSize(new Dimension(493, 52));
		scMoTa.setBounds(48, hNguoiLam + 2 * R.margin + R.margin / 2 + pnMoTa.getPreferredSize().height, 493, 50);
		pnXX.add(scMoTa);

		int hMoTa = hNguoiLam + 2 * R.margin + R.margin / 2 + pnMoTa.getPreferredSize().height + scMoTa.getHeight();

		lbCmtText.setFont(R.FONT20B);
		lbCmtText.setBounds(48, hMoTa + 20, lbCmtText.getPreferredSize().width, lbCmtText.getPreferredSize().height);

		lbCmtIcon.setBounds(10, hMoTa + 20, 30, 30);
		pnXX.add(lbCmtIcon);

		lbAvatar.setIcon(R.icon.resize(30, 30, 2, R.user.getAvatarUrl()));
		lbAvatar.setBounds(12, hMoTa + 20 + lbCmtText.getPreferredSize().height + R.margin / 2, 30, 30);
		pnXX.add(lbAvatar);

		taCmt.setSize(493 - 2 * (R.margin / 2), 72 - 2 * (R.margin / 2));
		taCmt.setFont(R.FONT16);
		taCmt.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (!taCmt.getText().isEmpty()) {
						Comment cmt = new Comment(cv.getKey(), R.user.getUserID(), taCmt.getText());
						System.out.println(cv.getKey() + " " + R.user.getUserID() + " " + taCmt.getText());
						Search.sendNewCmt(cmt);
						taCmt.setText("");
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		scCmt.setPreferredSize(new Dimension(493 - 2 * (R.margin / 2), 72 - 2 * (R.margin / 2)));
		scCmt.setBounds(R.margin / 2, R.margin / 2, 493 - 2 * (R.margin / 2), 72 - 2 * (R.margin / 2));

		lbBgCmtText.setIcon(R.icon.resize(493, 72, 2, "icon/chitietCV/bgCommentTextArea.png"));
		lbBgCmtText.add(scCmt);
		lbBgCmtText.setBounds(48, hMoTa + 20 + lbCmtText.getPreferredSize().height + R.margin / 2, 493, 72);
		pnXX.add(lbBgCmtText);

		btSendCmt.setBounds(493 - 133 + 48, hMoTa + 20 + lbCmtText.getPreferredSize().height + 3 * R.margin / 2 + 72,
				133, 34);
		btSendCmt.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btSendCmt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!taCmt.getText().isEmpty()) {
					Comment cmt = new Comment(cv.getKey(), R.user.getUserID(), taCmt.getText());
					taCmt.setText("");
					System.out.println(cv.getKey() + " " + R.user.getUserID() + " " + taCmt.getText());
					Search.sendNewCmt(cmt);
				}
			}
		});
		pnXX.add(btSendCmt);

		hCmt = hMoTa + 20 + lbCmtText.getPreferredSize().height + 3 * R.margin / 2 + 72 + 34;
		
		pnCmt.removeAll();
		pnCmt.setPreferredSize(new Dimension(493, 260));
		
		Search.getComment(cv);
		

		scCmt2.setViewportView(pnCmt);
		scCmt2.setBounds(48, hCmt + R.margin, 493, 260);
		// lbBackground.setIcon(R.icon.resize(720, h, 2,
		// "icon/chitietcv/Background.png"));
		// lbBackground.setBounds(0, 0, 720, h);
		// pnXX.add(lbBackground);
		pnXX.setBounds(0, 0, 720, 650);
		setVisible(true);
	}

	public void setMember(ArrayList<NguoiDung> list) {
		pnNguoiLam.removeAll();
		if (!pnNguoiLam.isVisible())
			pnNguoiLam.setVisible(true);
		for (int i = 0; i < soNguoi; i++) {
			JLabel b = new JLabel();
			String name = list.get(i).getName();
			if (i != soNguoi - 1) {
				name = name + ", ";
			}
			b.setText(name);
			b.setFont(R.FONT14);
			pnNguoiLam.add(b);
		}
	}

	public void getCmtSuccess() {
		String text;
		int h = 0;
		pnCmt.removeAll();
		for (int i = listCmt.size() - 1; i >= 0; i--) {
			Comment cmt = listCmt.get(i);
			if (cmt.getType().equals("TEXT")) {
				JLabel c = new JLabel();
				text = "<b>" + cmt.getNguoiBinh() + ": </b>" + cmt.getLoiBinh();
				c.setText(String.format("<html><div style=\"width:%dpx;\">%s</div><html>", 480, text));
				c.setFont(R.FONT14);
				h += c.getPreferredSize().height;
				pnCmt.add(c);
			} else if (cmt.getType().equals("IMGE")) {
				JLabel lbMsg = new JLabel("<html><b>" + cmt.getNguoiBinh() + ": </b></html>");
				lbMsg.setFont(R.FONT14);
				JLabel pic = new JLabel();
				pic.setIcon(cmt.getPic().getIcon());
				pnCmt.add(lbMsg);
				pnCmt.add(pic);
				h += lbMsg.getPreferredSize().height + pic.getPreferredSize().height;
			}
		}
		pnCmt.setPreferredSize(new Dimension(493, h + 10));
		pnCmt.setSize(493, 260);
		scCmt2.setViewportView(pnCmt);
		JScrollBar vertical = scCmt2.getVerticalScrollBar();
        vertical.setValue(vertical.getMinimum());
	}

	public void myDisposeComponent() {
		if (PnChangeName.isVisible())
			PnChangeName.setVisible(false);
		if (addMember.isVisible())
			addMember.Mydispose();
		if (changeStateDialog.isVisible())
			changeStateDialog.dispose();
		if (PnChangeName.isVisible())
			PnChangeName.myDispose();
	}
	
	public void myDispose() {
		setVisible(false);
		if (PnChangeName.isVisible())
			PnChangeName.setVisible(false);
		if (addMember.isVisible())
			addMember.Mydispose();
		if (changeStateDialog.isVisible())
			changeStateDialog.dispose();
		if (PnChangeName.isVisible())
			PnChangeName.myDispose();
	}
	
	public void myDisposeComponent(MouseEvent me) {
		//System.out.println(me.getSource().toString());
		if (!R.isComponent((Component)me.getSource(), btAddMember) && me.getSource() != addMember && addMember.isVisible())
			addMember.Mydispose();
		if (!R.isComponent((Component)me.getSource(), btMove) && me.getSource() != changeStateDialog && changeStateDialog.isVisible())
			changeStateDialog.dispose();
		if (!R.isComponent((Component)me.getSource(), btReName) && me.getSource() != PnChangeName && PnChangeName.isVisible())
			PnChangeName.myDispose();
	}
}
