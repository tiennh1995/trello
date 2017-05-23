package edu.ttl.ui;

import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.ttl.constant.Database;
import edu.ttl.constant.R;
import edu.ttl.object.DuAn;

public class searchPnProject extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ten = new String();
	private JLabel lb = new JLabel();
	private MouseListener mouseClick;

	public searchPnProject(final DuAn duan) {
		setOpaque(false);
		this.ten = duan.getTenDuAn();
		setSize(R.searchBarWidth - 20, 30);
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		lb.setText(ten);
		lb.setFont(R.FONT16);
		this.add(lb);
		mouseClick = new MouseListener() {

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
				DuAn da = searchDuAn(duan.getMaDuAn());
				if (!R.user.isAdmin()) {
					if (da != null) {
						if (da.getTrangThai() == 0) {
							JOptionPane.showMessageDialog(null,
									"Dự án " + da.getTenDuAn() + " đang tạm dừng, bạn không có quyền xem."
											+ " Liên hệ người quản lý để biết thêm thông tin.");
						} else {
							R.congViecPanel.setDuAn(da);
							Database.getListCV(da.getMaDuAn(), 0);
						}
					} else {

							JOptionPane.showMessageDialog(null, "Bạn không tham gia dự án này nên không có quyền xem."
									+ " Liên hệ người quản lý để biết thêm thông tin.");

					}

				}else {
					R.congViecPanel.setDuAn(duan);
					Database.getListCV(duan.getMaDuAn(), 0);
				}
				R.main.mainSearch.Mydispose();
			}
		};
		lb.addMouseListener(mouseClick);
		this.addMouseListener(mouseClick);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	private static DuAn searchDuAn(int key) {
		for (DuAn da : R.listProject) {
			if (da.getMaDuAn() == key)
				return da;
		}
		for (DuAn da : R.listProjectStop) {
			if (da.getMaDuAn() == key)
				return da;
		}
		return null;
	}
}
