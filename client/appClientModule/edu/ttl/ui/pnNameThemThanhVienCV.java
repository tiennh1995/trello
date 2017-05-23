package edu.ttl.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.ttl.constant.R;
import edu.ttl.constant.Search;
import edu.ttl.object.NguoiDung;
import edu.ttl.tool.MyIcon;

public class pnNameThemThanhVienCV extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JButton state = new JButton();
	protected JLabel name = new JLabel();
	protected NguoiDung mem;
	private static final MyIcon check = new MyIcon(30, 30, 2, "icon/search/check.png");
	private static final MyIcon uncheck = new MyIcon(30, 30, 2, "icon/search/uncheck.png");
	private int isCheck = 0;

	protected pnNameThemThanhVienCV(final NguoiDung mem2) {
		this.mem = mem2;
		setSize(280, 30);
		name.setToolTipText(mem.getName() + " (" + mem.getUserName() + ")");
		name.setText(mem.getName() + " (" + mem.getUserName() + ")");
		name.setFont(R.FONT16);
		name.setBackground(Color.WHITE);
		name.setCursor(new Cursor(Cursor.HAND_CURSOR));
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 230 - name.getPreferredSize().width;
		// c.insets = new Insets(5, 0, 5, 0);
		c.anchor = GridBagConstraints.LINE_START;
		add(name, c);
		boolean isContain = false;
		for (NguoiDung nd : ChiTietCongViecPanel.cv.getListNguoiLam()) {
			if (nd.getUserID() == mem.getUserID()) {
				isContain = true;
				break;
			}
		}
		if (isContain) {
			state.setIcon(check.getIcon());
			isCheck = 1;
		} else {
			state.setIcon(uncheck.getIcon());
			isCheck = 0;
		}
		c.gridx = 1;
		c.gridy = 0;
		c.ipadx = 0;
		//c.insets = new Insets(5, 0, 5, 0);
		c.anchor = GridBagConstraints.LINE_END;
		state.setContentAreaFilled(false);
		state.setBorder(null);
		state.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(state, c);
		
		setOpaque(false);

		state.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (isCheck == 0) {
					state.setIcon(check.getIcon());
					isCheck = 1;
					Search.sendAddMemberToJob(ChiTietCongViecPanel.cv.getKey(), mem.getUserID());
				} else {
					state.setIcon(uncheck.getIcon());
					isCheck = 0;
					Search.sendDelMemberFromJob(ChiTietCongViecPanel.cv.getKey(), mem.getUserID());
				}
			}
		});
		
		MouseListener mousel = new MouseListener() {
			
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
				if (isCheck == 0) {
					state.setIcon(check.getIcon());
					isCheck = 1;
					Search.sendAddMemberToJob(ChiTietCongViecPanel.cv.getKey(), mem.getUserID());
				} else {
					state.setIcon(uncheck.getIcon());
					isCheck = 0;
					Search.sendDelMemberFromJob(ChiTietCongViecPanel.cv.getKey(), mem.getUserID());
				}
			}
		};
		
		name.addMouseListener(mousel);
		addMouseListener(mousel);	
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setAlignmentY(Container.TOP_ALIGNMENT);
	}
}
