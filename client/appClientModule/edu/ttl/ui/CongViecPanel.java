package edu.ttl.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.ttl.constant.Database;
import edu.ttl.constant.R;
import edu.ttl.object.CongViec;
import edu.ttl.object.DuAn;
import edu.ttl.object.NguoiDung;

public class CongViecPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	protected int infoBar = (int) Math.round(0.07 * R.screenHeight);

	protected DuAn duAn = new DuAn();

	private ArrayList<CongViec> toDoList = new ArrayList<CongViec>();
	private ArrayList<CongViec> doingList = new ArrayList<CongViec>();
	private ArrayList<CongViec> doneList = new ArrayList<CongViec>();
	
	public static ArrayList<NguoiDung> memberList = new ArrayList<NguoiDung>();

	private CongViecTablePanel toDo;
	private CongViecTablePanel doing;
	private CongViecTablePanel done;

	private JTextField tfProjectName = new JTextField();
	private JButton btChangeState = new JButton();
	private JButton btRename = new JButton();
	private JButton btCancelRename = new JButton();

	private JPanel pnAction = new JPanel();
	protected JButton btAddMember = new JButton();
	private JButton btRefresh = new JButton();
	
	protected ThemThanhVien addMember = new ThemThanhVien();
	
	public CongViecPanel() {
		setBackground(new Color(0x0277BD));
		setLayout(null);
		CongViecTablePanel.parent = this;
		toDo = new CongViecTablePanel("To Do", 0, toDoList);
		doing = new CongViecTablePanel("Doing", 1, doingList);
		done = new CongViecTablePanel("Done", 2, doneList);
		init();
	}

	private void init() {
		// INFO BAR
		tfProjectName.setBackground(null);
		tfProjectName.setOpaque(false);
		tfProjectName.setBorder(null);
		tfProjectName.setForeground(Color.WHITE);
		tfProjectName.addKeyListener(new KeyListener() {

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
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					showNormalProjectBar();
					if (!tfProjectName.getText().equals(duAn.getTenDuAn())) {
						duAn.setTenDuAn(tfProjectName.getText());
						Database.sendUpdatedProject(duAn);
					}
				} else {
					tfProjectName.setBounds(R.space, infoBar / 4, tfProjectName.getPreferredSize().width + 20, 30);
					btRename.setFont(R.FONT14);
					btRename.setBounds(tfProjectName.getPreferredSize().width + 2 * R.space + 4, infoBar / 4 + 2,
							btRename.getPreferredSize().width, 30);
					btChangeState.setVisible(false);
					btCancelRename.setVisible(true);
					btCancelRename.setFont(R.FONT14);
					btCancelRename.setBounds(
							tfProjectName.getPreferredSize().width + 3 * R.space + 4 + btRename.getPreferredSize().width,
							infoBar / 4 + 2, btCancelRename.getPreferredSize().width, 30);
				}
			}
		});
		add(tfProjectName);

		btRename.setBackground(null);
		btRename.setBorder(null);
		btRename.setContentAreaFilled(false);
		btRename.setText("Đổi tên");
		btRename.setForeground(Color.WHITE);
		btRename.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btRename.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (tfProjectName.getText().equals(duAn.getTenDuAn())) {
					tfProjectName.selectAll();
					tfProjectName.requestFocus();
					btChangeState.setVisible(false);
					pnAction.setVisible(false);
					btCancelRename.setVisible(true);
					btCancelRename.setFont(R.FONT14);
					btCancelRename.setBounds(
							tfProjectName.getPreferredSize().width + 3 * R.space + 4 + btRename.getPreferredSize().width,
							infoBar /4 + 2, btCancelRename.getPreferredSize().width, 30);
				} else {
					duAn.setTenDuAn(tfProjectName.getText());
					Database.sendUpdatedProject(duAn);
					showNormalProjectBar();
				}
			}

		});
		add(btRename);
		
		btCancelRename.setBackground(null);
		btCancelRename.setBorder(null);
		btCancelRename.setContentAreaFilled(false);
		btCancelRename.setText("Hủy");
		btCancelRename.setForeground(Color.WHITE);
		btCancelRename.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btCancelRename.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				tfProjectName.setText(duAn.getTenDuAn());
				showNormalProjectBar();
			}
		});
		setVisible(false);
		add(btCancelRename);

		btChangeState.setBackground(null);
		btChangeState.setBorder(null);
		btChangeState.setContentAreaFilled(false);
		btChangeState.setForeground(Color.WHITE);
		btChangeState.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btChangeState.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int confirm = JOptionPane.showConfirmDialog(null,
						"Bạn có muốn " + btChangeState.getText() + " dự án " + tfProjectName.getText() + "?",
						"Quản lý dự án", JOptionPane.OK_CANCEL_OPTION);
				if (confirm == JOptionPane.OK_OPTION) {
					if (duAn.getTrangThai() == 1)
						duAn.setTrangThai(0);
					else
						duAn.setTrangThai(1);
					Database.sendUpdatedProject(duAn);
				}
			}
		});
		add(btChangeState);

		btAddMember.setBackground(null);
		btAddMember.setBorder(null);
		btAddMember.setContentAreaFilled(false);
		btAddMember.setText("Thêm thành viên");
		btAddMember.setForeground(Color.WHITE);
		btAddMember.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btAddMember.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addMember.init(1);
			}
		});
		
		btRefresh.setBackground(null);
		btRefresh.setBorder(null);
		btRefresh.setContentAreaFilled(false);
		btRefresh.setText("Làm mới");
		btRefresh.setForeground(Color.WHITE);
		btRefresh.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Database.getListCV(duAn.getMaDuAn(), 1);
			}
		});
		
		pnAction.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
		pnAction.setOpaque(false);
		pnAction.setSize(300, 30);
		pnAction.add(btAddMember);
		pnAction.add(btRefresh);
		add(pnAction);
		
		// Cong viec
		toDo.setBounds(R.space, infoBar, R.BUTTONW, toDo.getHeight());
		doing.setBounds(R.space + R.BUTTONW + R.space, infoBar, R.BUTTONW, doing.getHeight());
		done.setBounds(R.space + R.BUTTONW * 2 + R.space * 2, infoBar, R.BUTTONW, done.getHeight());

		add(toDo);
		add(doing);
		add(done);
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
				exitAddNewCV(3);
				if (R.chitietCV.isVisible()) {
					R.chitietCV.myDispose();
				}
			}
		});
	}

	public void setListCongViec(Hashtable<Integer, CongViec> list) {
		toDoList.clear();
		doingList.clear();
		doneList.clear();

		Set<Map.Entry<Integer, CongViec>> entries = list.entrySet();
		for (Entry<Integer, CongViec> entry : entries) {
			CongViec a = entry.getValue();
			switch (a.getTrangThai()) {
			case 0:
				toDoList.add(a);
				break;
			case 1:
				doingList.add(a);
				break;
			case 2:
				doneList.add(a);
				break;
			}
		}

	}

	public void exitAddNewCV(int a) {
		switch (a) {
		case 0:
			doing.cancelAddCV();
			done.cancelAddCV();
			break;
		case 1:
			toDo.cancelAddCV();
			done.cancelAddCV();
			break;
		case 2:
			toDo.cancelAddCV();
			doing.cancelAddCV();
			break;
		default:
			toDo.cancelAddCV();
			doing.cancelAddCV();
			done.cancelAddCV();
		}
	}

	public void getData() {
		showNormalProjectBar();

		setListCongViec(R.listCV);
		toDo.paintTop();
		doing.paintTop();
		done.paintTop();

		toDo.updateTable();
		doing.updateTable();
		done.updateTable();

		
		toDo.paintBottom();
		doing.paintBottom();
		done.paintBottom();
	}

	public void refreshData() {
		exitAddNewCV(3);
		setListCongViec(R.listCV);
		toDo.updateTable();
		doing.updateTable();
		done.updateTable();
		showNormalProjectBar();
	}
	
	public void rePaint() {
		exitAddNewCV(3);
		toDo.rePaint();
		doing.rePaint();
		done.rePaint();
	}

	public void changebtState() {
		if (duAn.getTrangThai() == 1) {
			btChangeState.setText("Tạm dừng dự án");
		} else {
			btChangeState.setText("Phục hồi dự án");
		}
		btChangeState.setBounds(tfProjectName.getPreferredSize().width + 3 * R.space + btRename.getPreferredSize().width,
				infoBar / 4 + 2, btChangeState.getPreferredSize().width, 30);
	}
	
	public void showNormalProjectBar() {
		String tenDA = duAn.getTenDuAn();
		if (tenDA.length() > 50) {
			tenDA = tenDA.substring(0, 50) + "...";
			tfProjectName.setToolTipText(duAn.getTenDuAn());
		}
		tfProjectName.setText(tenDA);
		tfProjectName.requestFocus(false);
		btChangeState.setVisible(true);
		btCancelRename.setVisible(false);
		changebtState();
		pnAction.setVisible(true);
		tfProjectName.setFont(R.FONT20B);
		tfProjectName.setBounds(R.space, infoBar / 4, tfProjectName.getPreferredSize().width, 30);
		btRename.setFont(R.FONT14);
		btRename.setBounds(tfProjectName.getPreferredSize().width + 2 * R.space, infoBar / 4 + 2,
				btRename.getPreferredSize().width, 30);
		btChangeState.setFont(R.FONT14);
		btChangeState.setBounds(tfProjectName.getPreferredSize().width + 3 * R.space + btRename.getPreferredSize().width,
				infoBar / 4 + 2, btChangeState.getPreferredSize().width, 30);
		
		btAddMember.setFont(R.FONT14);
		btRefresh.setFont(R.FONT14);
		
		if (!R.user.isAdmin()) {
			btChangeState.setVisible(false);
			btRename.setVisible(false);
			tfProjectName.setEditable(false);
			pnAction.setBounds(tfProjectName.getPreferredSize().width + 2 * R.space, infoBar / 4 + 8,
					pnAction.getPreferredSize().width, 30);
		} else {
			btChangeState.setVisible(true);
			btRename.setVisible(true);
			tfProjectName.setEditable(true);
			pnAction.setBounds(tfProjectName.getPreferredSize().width + 4 * R.space + btRename.getPreferredSize().width
					+ btChangeState.getPreferredSize().width, infoBar / 4 + 8, pnAction.getPreferredSize().width, 30);
		}
	}

	public DuAn getDuAn() {
		return duAn;
	}

	public void setDuAn(DuAn duAn) {
		this.duAn = duAn;
	}
}
