package edu.ttl.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import edu.ttl.constant.Database;
import edu.ttl.constant.R;
import edu.ttl.object.DuAn;
import edu.ttl.tool.MyIcon;

public class PButtonStop extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DuAn duAn;
	private JLabel lbText = new JLabel();

	public static MyIcon BIcon;
	public static int width;
	public static int height;

	public PButtonStop(DuAn duAn) {
		this.duAn = duAn;
		this.setLayout(null);
		this.setBorder(null);
		this.setContentAreaFilled(false);
	}

	public void init() {
		int margin;
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.setIcon(BIcon.getIcon());
		margin = (int) (width / 40.0);
		lbText.setText(String.format("<html><div style=\"width:%dpx;\">%s</div><html>", (int) (width * 0.7),
				duAn.getTenDuAn()));
		lbText.setFont(R.FONT16B);
		lbText.setBorder(null);
		lbText.setForeground(Color.WHITE);
		lbText.setBounds(margin, margin, width - 2 * margin, lbText.getPreferredSize().height);
		this.add(lbText);
		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				R.main.disposeAll();
				if (R.user.isAdmin()) {
					R.congViecPanel.setDuAn(duAn);
					Database.getListCV(duAn.getMaDuAn(), 0);
				} else {
					JOptionPane.showMessageDialog(null, "Dự án " + duAn.getTenDuAn() + " đang tạm dừng");
				}
			}
		});
	}
}
