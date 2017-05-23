package edu.ttl.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import edu.ttl.constant.Database;
import edu.ttl.constant.R;
import edu.ttl.object.CongViec;
import edu.ttl.tool.MyIcon;

public class CongViecTablePanel extends JPanel {

	/**
	 * 
	 */
	final int BUTTONADDNEWCV = (int) (R.BUTTONW / 4);
	public static CongViecPanel parent;

	private static final long serialVersionUID = 1L;
	private String tenPanelCV = new String();
	private final int trangThaiCV; // 0: Todo 1:Doing 2:Done
	protected JPanel pn2 = new JPanel();
	private JScrollPane scPn2 = new JScrollPane();
	private JLabel lbTop = new JLabel();
	private JLabel lbTopText = new JLabel();
	private JButton btBottom = new JButton();
	private JLabel lbTextBottom = new JLabel("Thêm thẻ mới...");
	private JLabel lbAddCV = new JLabel();
	private JTextArea taAddCV = new JTextArea();
	private JScrollPane ScTaAddCV = new JScrollPane();

	private JButton btBottomNew = new JButton();
	private JButton btAdd = new JButton();
	private JButton btCancel = new JButton();

	private ArrayList<CongViec> list;
	private MyIcon topIcon = new MyIcon("icon/cv/top.png");
	private MyIcon bottomIcon = new MyIcon("icon/cv/bottom.png");

	private int height = 0;
	protected int centerH = 0;
	private int scPn2H = 0;
	private int btBottomNewHeight;

	CongViecTablePanel(String ten, int trangthaicv, ArrayList<CongViec> list) {
		this.tenPanelCV = ten;
		this.trangThaiCV = trangthaicv;
		this.list = list;
		init();
	}

	public void init() {
		setBackground(null);
		setLayout(null);
		topIcon.resize(R.BUTTONW, 0, 0);
		bottomIcon.resize(R.BUTTONW, 0, 0);
		scPn2H = parent.getHeight() - parent.infoBar - topIcon.getHeight() - bottomIcon.getHeight() - 5 * R.space;

		pn2.setBackground(new Color(0xe1e1e1));
		pn2.setLayout(null);

		// pn.setBounds(2 * R.space + R.space * p + R.BUTTONW * p, infoBar,
		// R.BUTTONW,
		// topIcon.getHeight() + h + bottomIcon.getHeight() + 2 * R.margin);

		lbAddCV.setLayout(null);
		lbAddCV.setIcon(R.icon.resize(R.BUTTONW - 2 * R.margin, BUTTONADDNEWCV, 2, "icon/cv/center.png"));
		taAddCV.setBackground(Color.WHITE);
		taAddCV.setBorder(null);
		taAddCV.setLineWrap(true);
		taAddCV.setSize(R.BUTTONW - 4 * R.margin, BUTTONADDNEWCV - 2 * R.margin);
		ScTaAddCV.setBorder(null);
		ScTaAddCV.setViewportView(taAddCV);
		ScTaAddCV.setBounds(R.margin, R.margin, R.BUTTONW - 3 * R.margin, BUTTONADDNEWCV - 2*R.margin);
		lbAddCV.add(ScTaAddCV);
		lbAddCV.setVisible(false);

		lbTop.setLayout(null);
		lbTop.setIcon(topIcon.getIcon());
		lbTop.setBounds(0, 0, R.BUTTONW, topIcon.getHeight());
		lbTopText.setText(tenPanelCV);
		lbTop.add(lbTopText);
		add(lbTop);
		
		btBottom.setContentAreaFilled(false);
		btBottom.setBorder(null);
		btBottom.setLayout(null);
		btBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btBottom.setIcon(bottomIcon.getIcon());
		add(btBottom);
		
		btBottomNew.setLayout(null);
		btBottomNew.setBorder(null);
		btBottomNew.setContentAreaFilled(false);
		btBottomNew.setIcon(R.icon.resize(R.BUTTONW, 0, 0, "icon/cv/bottomNewCV.png"));
		btBottomNew.setVisible(false);

		btBottomNewHeight = R.icon.getHeight();

		btAdd.setContentAreaFilled(false);
		btAdd.setBorder(null);
		btAdd.setLayout(null);
		btAdd.setIcon(R.icon.resize(0, btBottomNewHeight - 2 * R.margin, 1, "icon/cv/add.png"));
		int tmp = R.icon.getWidth();
		btAdd.setBounds(R.margin, R.margin, tmp, btBottomNewHeight - 2 * R.margin);
		btBottomNew.add(btAdd);

		btCancel.setContentAreaFilled(false);
		btCancel.setBorder(null);
		btCancel.setLayout(null);
		btCancel.setIcon(R.icon.resize(0, btBottomNewHeight - 2 * R.margin, 1, "icon/cv/cancel.png"));
		btCancel.setBounds(R.margin * 2 + tmp, R.margin, tmp, btBottomNewHeight - 2 * R.margin);
		btBottomNew.add(btCancel);

		height = centerH + topIcon.getHeight() + bottomIcon.getHeight();

		btBottom.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				parent.exitAddNewCV(trangThaiCV);
				btBottomNew.setVisible(true);
				taAddCV.setFont(R.FONT16);
				lbAddCV.setVisible(true);
				lbAddCV.setBounds(R.margin, centerH, R.BUTTONW - 2 * R.margin, BUTTONADDNEWCV);
				centerH += BUTTONADDNEWCV + R.margin;
				btBottom.setVisible(false);
				scPn2H = parent.getHeight() - parent.infoBar - topIcon.getHeight() - btBottomNewHeight - 5 * R.space;
				if (centerH > scPn2H) {
					btBottomNew.setBounds(0, scPn2H + topIcon.getHeight(), R.BUTTONW, btBottomNewHeight);
				} else {
					btBottomNew.setBounds(0, centerH + topIcon.getHeight(), R.BUTTONW, btBottomNewHeight);
					scPn2H = centerH;
				}
				taAddCV.requestFocus();
				pn2.setPreferredSize(new Dimension(R.BUTTONW, centerH));
				pn2.setSize(R.BUTTONW, scPn2H);
				scPn2.setViewportView(pn2);
				scPn2.setBounds(0, topIcon.getHeight(), R.BUTTONW, scPn2H);
				height = centerH + topIcon.getHeight() + btBottomNewHeight;
				setSize(R.BUTTONW, height);
				getRootPane().setDefaultButton(btAdd);
			}
		});

		btAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String cvname = taAddCV.getText();
				if (!cvname.equals("")) {
					if (cvname.length() > 100) {
						JOptionPane.showMessageDialog(null, "Tên công việc dài hơn 100 ký tự. Vui lòng đặt tên ngắn hơn");
						taAddCV.requestFocus();
						return;
					}
					CongViec cv = new CongViec(parent.getDuAn().getMaDuAn(), cvname, trangThaiCV);
					R.congViecTable = getCongViecTablePanel();
					Database.sendNewCongViec(cv);
				}
			}
		});

		btCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub				
				cancelAddCV();
			}
		});

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
				parent.exitAddNewCV(trangThaiCV);
			}
		});

		scPn2.setBorder(new EmptyBorder(0, 0, 0, 0));
		scPn2.setViewportView(pn2);
		scPn2.getVerticalScrollBar().setUnitIncrement(10);
		scPn2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		add(btBottomNew);
		add(scPn2);
	}

	public void cancelAddCV() {
		if (btBottom.isVisible()) {
			return;
		}
		taAddCV.setText("");
		centerH -= BUTTONADDNEWCV + R.margin;
		btBottom.setVisible(true);
		btBottomNew.setVisible(false);
		lbAddCV.setVisible(false);
		scPn2H = parent.getHeight() - parent.infoBar - topIcon.getHeight() - bottomIcon.getHeight() - 5 * R.space;
		if (centerH > scPn2H) {
			btBottom.setBounds(0, scPn2H + topIcon.getHeight(), R.BUTTONW, bottomIcon.getHeight());
		} else {
			btBottom.setBounds(0, centerH + topIcon.getHeight(), R.BUTTONW, bottomIcon.getHeight());
			scPn2H = centerH;
		}
		lbTopText.setBounds(R.margin, R.margin, lbTopText.getPreferredSize().width,
				lbTopText.getPreferredSize().height);
		pn2.setPreferredSize(new Dimension(R.BUTTONW, centerH));
		pn2.setSize(R.BUTTONW, scPn2H);
		scPn2.setViewportView(pn2);
		scPn2.setBounds(0, topIcon.getHeight(), R.BUTTONW, scPn2H);
		height = centerH + topIcon.getHeight() + bottomIcon.getHeight();
		setSize(R.BUTTONW, height);
	}

	public void updateTable() {
		centerH = R.margin;
		btBottom.setVisible(true);
		pn2.removeAll();
		if (lbAddCV.isVisible()) lbAddCV.setVisible(false);
		for (int i = 0; i < list.size(); i++) {
			CVButton b = new CVButton(list.get(i), R.BUTTONW - 2 * R.margin, R.margin);
			b.init();
			b.setBounds(R.margin, centerH, R.BUTTONW - 2 * R.margin, b.getHeight());
			pn2.add(b);
			centerH += R.margin + b.getHeight();
		}
		pn2.add(lbAddCV);
		scPn2H = parent.getHeight() - parent.infoBar - topIcon.getHeight() - bottomIcon.getHeight() - 5 * R.space;
		if (centerH > scPn2H) {
			btBottom.setBounds(0, scPn2H + topIcon.getHeight(), R.BUTTONW, bottomIcon.getHeight());
		} else {
			btBottom.setBounds(0, centerH + topIcon.getHeight(), R.BUTTONW, bottomIcon.getHeight());
			scPn2H = centerH;
		}
		pn2.setPreferredSize(new Dimension(R.BUTTONW, centerH));
		pn2.setSize(R.BUTTONW, scPn2H);
		scPn2.setViewportView(pn2);
		scPn2.setBounds(0, topIcon.getHeight(), R.BUTTONW, scPn2H);
		height = centerH + topIcon.getHeight() + bottomIcon.getHeight();
		setSize(R.BUTTONW, height);
	}

	public void addList(CongViec cv) {
		this.list.add(cv);
	}

	@Override
	public int getHeight() {
		return height;
	}

	public void rePaint() {
		scPn2H = parent.getHeight() - parent.infoBar - topIcon.getHeight() - bottomIcon.getHeight() - 5 * R.space;
		if (centerH > scPn2H) {
			btBottom.setBounds(0, scPn2H + topIcon.getHeight(), R.BUTTONW, bottomIcon.getHeight());
		} else {
			btBottom.setBounds(0, centerH + topIcon.getHeight(), R.BUTTONW, bottomIcon.getHeight());
			scPn2H = centerH;
		}
		pn2.setPreferredSize(new Dimension(R.BUTTONW, centerH));
		pn2.setSize(R.BUTTONW, scPn2H);
		scPn2.setViewportView(pn2);
		scPn2.setBounds(0, topIcon.getHeight(), R.BUTTONW, scPn2H);
	}

	public void paintTop() {
		lbTopText.setForeground(Color.BLACK);
		lbTopText.setFont(R.FONT18B);
		lbTopText.setBounds(R.margin, R.margin, lbTopText.getPreferredSize().width,
				lbTopText.getPreferredSize().height);
	}

	public void paintBottom() {
		lbTextBottom.setForeground(Color.GRAY);
		lbTextBottom.setFont(R.FONT14);
		lbTextBottom.setBounds(R.margin, R.margin, lbTextBottom.getPreferredSize().width,
				lbTextBottom.getPreferredSize().height);
		btBottom.add(lbTextBottom);
		add(btBottom);
	}
	
	public void addCongViecSuccess(CongViec cv) {
		list.add(cv);
		CVButton b = new CVButton(list.get(list.size()-1), R.BUTTONW - 2 * R.margin, R.margin);
		b.init();
		centerH -= BUTTONADDNEWCV + R.margin;
		b.setBounds(R.margin, centerH, R.BUTTONW - 2 * R.margin, centerH);
		pn2.add(b);
		centerH += R.margin + b.getHeight();
		lbAddCV.setLocation(R.margin, centerH);
		taAddCV.requestFocus();
		centerH += BUTTONADDNEWCV + R.margin;
		scPn2H = parent.getHeight() - parent.infoBar - topIcon.getHeight() - btBottomNewHeight
				- 5 * R.space;
		if (centerH > scPn2H) {
			btBottomNew.setBounds(0, scPn2H + topIcon.getHeight(), R.BUTTONW, btBottomNewHeight);
		} else {
			btBottomNew.setBounds(0, centerH + topIcon.getHeight(), R.BUTTONW, btBottomNewHeight);
			scPn2H = centerH;
		}
		pn2.setPreferredSize(new Dimension(R.BUTTONW, centerH));
		pn2.setSize(R.BUTTONW, scPn2H);
		scPn2.setBounds(0, topIcon.getHeight(), R.BUTTONW, scPn2H);
		height = centerH + topIcon.getHeight() + btBottomNewHeight;
		setSize(R.BUTTONW, height);
		taAddCV.setText("");
	}
	
	private CongViecTablePanel getCongViecTablePanel() {
		return this;
	}
}
