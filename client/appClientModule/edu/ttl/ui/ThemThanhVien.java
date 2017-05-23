package edu.ttl.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import edu.ttl.constant.Database;
import edu.ttl.constant.R;
import edu.ttl.constant.Search;
import edu.ttl.object.NguoiDung;

public class ThemThanhVien extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lbBG = new JLabel();
	protected JTextField tfSearch = new JTextField();
	protected JPanel pnResult = new JPanel();
	private JScrollPane scResult = new JScrollPane(pnResult);
	protected ArrayList<NguoiDung> listMember = new ArrayList<NguoiDung>();
	private int mode = 0;

	/**
	 * Create the dialog.
	 */
	public ThemThanhVien() {
		setAlwaysOnTop(true);
		setSize(300, 250);
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
				Mydispose();
			}
		});
		getContentPane().add(exit);
		tfSearch.setLocation(21, 44);
		tfSearch.setSize(257, 35);
		tfSearch.setBorder(null);
		tfSearch.setFont(R.FONT16);
		tfSearch.setOpaque(false);
		tfSearch.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				String data = tfSearch.getText();
				if (!data.isEmpty()) {
					listMember.clear();
					Set<Map.Entry<String, NguoiDung>> entries = Search.listThanhVien.entrySet();
					for (Map.Entry<String, NguoiDung> entry : entries) {
						String pro = entry.getValue().getName();
						String proj = pro.toUpperCase();
						data = data.toUpperCase();
						if (proj.contains(data)) {
							listMember.add(entry.getValue());
						}
					}
					initPanel();
				} else {
					listMember.clear();
					Set<Map.Entry<String, NguoiDung>> entries = Search.listThanhVien.entrySet();
					for (Map.Entry<String, NguoiDung> entry : entries) {
						listMember.add(entry.getValue());
					}
					initPanel();
				}
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		getContentPane().add(tfSearch);

		JLabel top = new JLabel(R.icon.resize(280, 0, 0, "icon/search/topSearchMember.png"));
		top.setBounds(10, 7, 280, 73);
		getContentPane().add(top);
		pnResult.setLayout(new BoxLayout(pnResult, BoxLayout.Y_AXIS));
		pnResult.setBackground(Color.WHITE);
		pnResult.setAlignmentY(Component.TOP_ALIGNMENT);
		scResult.setBorder(null);
		scResult.getVerticalScrollBar().setUnitIncrement(10);
		getContentPane().add(scResult);

		getContentPane().add(lbBG);
	}

	public void init(int m) {
		this.mode = m;
		setLocation(MouseInfo.getPointerInfo().getLocation());
		if (mode == 0) {
			for (int i = 0; i < ChiTietCongViecPanel.cv.getListNguoiLam().size(); i++) {
				pnNameThemThanhVienCV pn = new pnNameThemThanhVienCV(ChiTietCongViecPanel.cv.getListNguoiLam().get(i));
				pnResult.add(pn);
			}
		} else if (mode == 1) {
			for (int i = 0; i < CongViecPanel.memberList.size(); i++) {
				pnNameThemThanhVienDuAn pn = new pnNameThemThanhVienDuAn(CongViecPanel.memberList.get(i));
				pnResult.add(pn);
			}
		} else if (mode == 2) {
			Set<Map.Entry<String, NguoiDung>> entries = Search.listThanhVien.entrySet();
			for (Map.Entry<String, NguoiDung> entry : entries) {
				pnNameAdminControl pn = new pnNameAdminControl(entry.getValue());
				pnResult.add(pn);
			}
		}
		scResult.setViewportView(pnResult);
		setVisible(true);
		tfSearch.requestFocus();
	}

	public void initPanel() {
		pnResult.removeAll();
		for (int i = 0; i < listMember.size(); i++) {
			if (mode == 0) {
				pnNameThemThanhVienCV pn = new pnNameThemThanhVienCV(listMember.get(i));
				pnResult.add(pn);
			} else if (mode == 1) {
				pnNameThemThanhVienDuAn pn = new pnNameThemThanhVienDuAn(listMember.get(i));
				pnResult.add(pn);
			} else if (mode == 2) {
				pnNameAdminControl pn = new pnNameAdminControl(listMember.get(i));
				pnResult.add(pn);
			}
		}
		scResult.setViewportView(pnResult);
	}

	@Override
	public void setSize(int w, int h) {
		super.setSize(w, h);
		lbBG.setIcon(R.icon.resize(w, h, 2, "icon/search/searchMemberBg.png"));
		lbBG.setBounds(0, 0, w, h);
		scResult.setBounds(10, 90, 280, h - 100);
	}

	public void updateListThanhVien() {
		Search.getData("ThanhVien");
	}

	public void Mydispose() {
		dispose();
		R.listCV.clear();
		if (mode == 1 || mode == 0)
			Database.getListCV(R.congViecPanel.getDuAn().getMaDuAn(), 1);
		tfSearch.setText("");
		pnResult.removeAll();
	}
}
