package edu.ttl.ui;

import java.awt.Cursor;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.ttl.constant.Database;
import edu.ttl.constant.NewDuAn;
import edu.ttl.constant.R;

public class NewProjectPanel extends JDialog {

	private static final long serialVersionUID = 1L;
	protected JTextField tfPojectName = new JTextField();
	protected JButton btAdd = new JButton();
	protected JButton btCancel = new JButton();
	private JPanel pn = new JPanel();

	/**
	 * Launch the application.
	 */

	public NewProjectPanel() {
		setBackground(null);
		//setAlwaysOnTop(true);
		setSize(260, 177);
		setResizable(false);
	    //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Already there
	    //setExtendedState(JFrame.MAXIMIZED_BOTH);
	    setUndecorated(true);
		setContentPane(pn);
		pn.setLayout(null);
		
		btAdd.setBounds(37, 119, 88, 38);
		btAdd.setBackground(null);
		btAdd.setOpaque(false);
		btAdd.setContentAreaFilled(false);
		btAdd.setBorder(null);
		btAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn.add(btAdd);
		
		getRootPane().setDefaultButton(btAdd);
		
		btCancel.setBounds(136, 119, 89, 38);
		btCancel.setBackground(null);
		btCancel.setOpaque(false);
		btCancel.setContentAreaFilled(false);
		btCancel.setBorder(null);
		btCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn.add(btCancel);
		btCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tfPojectName.setText("");
				setVisible(false);
			}
		});
		
		tfPojectName.setBounds(20, 70, 219, 25);
		tfPojectName.setBorder(null);
		tfPojectName.setOpaque(false);
		pn.add(tfPojectName);
		tfPojectName.setColumns(10);
		JLabel lbBG = new JLabel(R.icon.resize(260, 177, 2, "icon/newProject.png"));
		lbBG.setBounds(0, 0, 260, 177);
		pn.add(lbBG);
		
		btAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!tfPojectName.getText().isEmpty()) {
					if (tfPojectName.getText().length() > 100) {
						JOptionPane.showMessageDialog(null,
								"Tên dự án dài hơn 100 ký tự. Vui lòng đặt tên ngắn hơn");
						tfPojectName.requestFocus();
						return;
					}
					NewDuAn duan = new NewDuAn(tfPojectName.getText(), R.user.getUserName());
					Database.sendNewDuAn(duan);
				}
			}
		});

		btCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tfPojectName.setText("");
				dispose();
			}
		});
	}
	
	public void init() {
		Point p = MouseInfo.getPointerInfo().getLocation();
		int x = (int)p.getX();
		int y = (int)p.getY();
		while (x + 260 > R.main.getWidth()) {
			x -= 100;
		}
		setLocation(x , y);
		setVisible(true);
		tfPojectName.requestFocus();		
	}

}
