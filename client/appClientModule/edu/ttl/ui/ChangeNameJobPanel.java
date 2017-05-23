package edu.ttl.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import edu.ttl.constant.R;

public class ChangeNameJobPanel extends JDialog {

	private static final long serialVersionUID = 1L;
	protected JTextArea taJobName = new JTextArea();
	protected JButton btOk = new JButton();
	protected JButton btCancel = new JButton();
	private JPanel pn = new JPanel();
	private JScrollPane scTa = new JScrollPane();

	/**
	 * Launch the application.
	 */

	public ChangeNameJobPanel() {
		setBackground(null);
		setAlwaysOnTop(true);
		setSize(260, 171);
		setResizable(false);
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Already there
		// setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);

		setContentPane(pn);
		pn.setLayout(null);

		btOk.setBounds(41, 125, 74, 30);
		btOk.setBackground(null);
		btOk.setOpaque(false);
		btOk.setContentAreaFilled(false);
		btOk.setBorder(null);
		btOk.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn.add(btOk);

		getRootPane().setDefaultButton(btOk);

		btCancel.setBounds(140, 125, 74, 30);
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
				myDispose();
			}
		});
		int margin = R.margin/2;
		taJobName.setSize(242 - 2*margin, 54 - 2*margin);
		taJobName.setBorder(null);
		taJobName.setBackground(Color.WHITE);
		taJobName.setFont(R.FONT16);
		taJobName.setWrapStyleWord(true);
		taJobName.setLineWrap(true);
		taJobName.setBorder(null);
		scTa.setBorder(null);
		scTa.setViewportView(taJobName);
		scTa.setBounds(10 + margin, 55 + margin, 242 - 2*margin, 54 - 2*margin);
		pn.add(scTa);		
		JLabel lbBG = new JLabel(R.icon.resize(260, 171, 2, "icon/chitietcv/doiTenCV.png"));
		lbBG.setBounds(0, 0, 260, 171);
		pn.add(lbBG);
	}
	
	public void myDispose() {
		taJobName.setText("");
		dispose();
	}
}
