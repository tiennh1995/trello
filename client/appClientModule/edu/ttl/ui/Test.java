package edu.ttl.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.ttl.constant.R;
import edu.ttl.tool.MyIcon;

public class Test extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new FlowLayout());
		setContentPane(contentPane);
		pnName x = new pnName();
		contentPane.add(x);
	}
	
	private class pnName extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		protected JButton state = new JButton();
		protected JLabel name = new JLabel();
		private MyIcon check = new MyIcon(20, 20, 2, "icon/search/check.png");
		private MyIcon uncheck = new MyIcon(20, 20, 2, "icon/search/uncheck.png");
		private int isCheck = 0;
		
		protected pnName() {
			setSize(280, 20);
			name.setText("Nguyen Phuc Long");
			name.setFont(R.FONT16);
			name.setBackground(Color.WHITE);
			setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 0;
			c.ipadx = 230 - name.getPreferredSize().width;
			c.anchor = GridBagConstraints.LINE_START;
			add(name, c);
			
			if(isCheck == 1) {
				state.setIcon(check.getIcon());
			} else {
				state.setIcon(uncheck.getIcon());
			}
			c.gridx = 1;
			c.gridy = 0;
			c.ipadx = 0;
			c.anchor = GridBagConstraints.LINE_END;
			state.setContentAreaFilled(false);
			state.setBorder(null);
			add(state, c);
			
			state.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					if (isCheck == 0) {
						state.setIcon(check.getIcon());
						isCheck = 1;
					} else {
						state.setIcon(uncheck.getIcon());
						isCheck = 0;
					}
				}
			});
		}
	}

}
