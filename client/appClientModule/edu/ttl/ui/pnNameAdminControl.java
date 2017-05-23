package edu.ttl.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.ttl.constant.R;
import edu.ttl.object.NguoiDung;

public class pnNameAdminControl extends JPanel{
	private static final long serialVersionUID = 1L;
	protected JLabel name = new JLabel();
	protected NguoiDung mem;

	protected pnNameAdminControl(final NguoiDung mem2) {
		this.mem = mem2;
		setSize(280, 30);
		name.setToolTipText(mem.getName() + " (" + mem.getUserName() + ")");
		name.setText(mem.getName() + " (" + mem.getUserName() + ")");
		name.setFont(R.FONT16);
		name.setBackground(Color.WHITE);
		name.setCursor(new Cursor(Cursor.HAND_CURSOR));
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LINE_START;
		c.ipadx = 0;
		c.ipady = 0;
		
		add(name, c);
		
		setOpaque(false);
		
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
				R.main.adminControlDialog.curUser = mem2;
				R.main.adminControlDialog.setUserInfo();
				R.main.adminControlDialog.searchMem.Mydispose();
			}
		};
		
		name.addMouseListener(mousel);
		addMouseListener(mousel);	
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setAlignmentY(Container.TOP_ALIGNMENT);
	}
}
