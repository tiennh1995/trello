package edu.ttl.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.ttl.constant.R;
import edu.ttl.object.CongViec;
import edu.ttl.tool.MyIcon;

public class CVButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CongViec cv;
	private MyIcon centerIcon = new MyIcon("icon/cv/center.png");
	private int margin, weight;
	private JLabel lbTen = new JLabel();
	private JLabel lbDateLine = new JLabel();
	private JPanel pnNguoiLam = new JPanel();
	int h = 0;

	public CVButton(CongViec cv, int weight, int margin) {
		//System.out.println("Cong viec " + cv.getTen() + " co" + cv.getListNguoiLam().size() + " nguoi lam");
		
		this.cv = cv;
		this.weight = weight;
		this.margin = margin;

		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.setLayout(null);
		this.setBorder(null);
		//this.setOpaque(false);
		this.setContentAreaFilled(false);
	}
	
	public void init() {
		lbTen.setText(
				String.format("<html><div style=\"width:%dpx;\">%s</div><html>", (int) (weight * 0.7), cv.getTen()));
		lbTen.setFont(R.FONT16);
		lbTen.setForeground(Color.BLACK);
		h = margin;
		lbTen.setBounds(margin, h, weight - 2 * margin, lbTen.getPreferredSize().height);
		this.add(lbTen);
		h += margin + lbTen.getPreferredSize().height;

		if (!cv.getDeadline().equals("")) {
			lbDateLine.setText("Deadline: " + cv.getDeadline());
			lbDateLine.setFont(R.FONT14);
			lbDateLine.setForeground(Color.GRAY);
			lbDateLine.setBounds(margin, h, weight - 2 * margin, lbDateLine.getPreferredSize().height);
			this.add(lbDateLine);
			h+= margin + lbDateLine.getPreferredSize().height;
		}
		
		int soNguoi = cv.getListNguoiLam().size();
		pnNguoiLam.setLayout(new WrapLayout(FlowLayout.LEFT, 0 , 0));
		if (soNguoi != 0) {
			this.add(pnNguoiLam);
			h -= margin;
			JLabel tmp = new JLabel();
			tmp.setText("Người làm: ");
			tmp.setFont(R.FONT14);
			tmp.setForeground(Color.GRAY);
			pnNguoiLam.add(tmp);
			for (int i=0; i<soNguoi; i++) {
				JLabel b = new JLabel();
				String name = cv.getListNguoiLam().get(i).getName();
				if (i != soNguoi -1) {
					name = name + ", ";
				}
				b.setText(name);
				b.setFont(R.FONT14);
				b.setForeground(Color.GRAY);
				//b.getFontMetrics(new Font(FONT, Font.PLAIN, fsize2)).stringWidth("Người làm: ");
				pnNguoiLam.add(b);
			}
			pnNguoiLam.setSize(weight - 2 * margin, 1);
			int tmp2 = pnNguoiLam.getPreferredSize().height;
			pnNguoiLam.setBackground(Color.white);
			pnNguoiLam.setBounds(margin, h, weight - 2 * margin, tmp2);
			h += margin + tmp2;
		}
		
		this.setIcon(centerIcon.resize(weight, h, 2));
		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				R.main.disposeAll();
				R.chitietCV.myDisposeComponent();
				CongViecTablePanel.parent.exitAddNewCV(cv.getTrangThai());
				ChiTietCongViecPanel.cv = cv;
				ChiTietCongViecPanel.listCmt.clear();
				R.chitietCV.init();
				R.congViecPanel.exitAddNewCV(3);
			}
		});
	}
	
	public int getHeight() {
		return h;
	}
}
