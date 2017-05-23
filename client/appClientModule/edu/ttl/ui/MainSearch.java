package edu.ttl.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import edu.ttl.constant.R;
import edu.ttl.constant.Search;
import edu.ttl.object.DuAn;

public class MainSearch extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lbBG = new JLabel();
	protected JPanel pnResult = new JPanel();
	private JScrollPane scResult = new JScrollPane(pnResult);
	private ArrayList<DuAn> list = new ArrayList<DuAn>();
	private JTextField tfSearch;
	private JButton exit;

	/**
	 * Create the dialog.
	 */
	public MainSearch(final JTextField tfSearch) {
		this.tfSearch = tfSearch;
		this.setLayout(null);

		exit = new JButton(R.icon.resize(15, 15, 2, "icon/chitietCV/delete.png"));
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
		this.add(exit);
		
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
					list.clear();
					Set<Map.Entry<String, DuAn>> entries = Search.listProject.entrySet();
					for (Map.Entry<String, DuAn> entry : entries) {
						String pro = entry.getValue().getTenDuAn();
						String proj = pro.toUpperCase();
						data = data.toUpperCase();
						if (proj.contains(data)) {
							DuAn mem = new DuAn(Integer.parseInt(entry.getKey()), pro, entry.getValue().getTrangThai());
							list.add(mem);
						}
					}
					initPanel();
					if (!isVisible()) setVisible(true);
				} else {
					setVisible(false);
				}
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		this.add(tfSearch);

		pnResult.setLayout(new BoxLayout(pnResult, BoxLayout.Y_AXIS));
		pnResult.setBackground(Color.WHITE);
		pnResult.setAlignmentY(Component.TOP_ALIGNMENT);
		scResult.setBorder(null);
		scResult.getVerticalScrollBar().setUnitIncrement(10);
		this.add(scResult);

		this.add(lbBG);
		setOpaque(false);
		
	}

	
	public void init(int w, int h, int searchTextFieldHeight) {
		setLocation(w, h + 5);
		setSize(R.searchBarWidth, 250);
	}

	public void initPanel() {
		pnResult.removeAll();
		for (int i=0; i<list.size(); i++) {
			searchPnProject pn = new searchPnProject(list.get(i));
			pn.setAlignmentY(Component.TOP_ALIGNMENT);
			pnResult.add(pn);
		}
		scResult.setViewportView(pnResult);
	}
	
	@Override
	public void setSize(int w, int h) {
		super.setSize(w, h);
		lbBG.setIcon(R.icon.resize(w, h, 2, "icon/search/searchMemberBg.png"));
		lbBG.setBounds(0, 0, w, h);
		scResult.setBounds(10, 20, w-20, h - 30);
		pnResult.setSize(w-20,h);
		exit.setBounds(w-20, 5, 15, 15);
	}
	
	public void Mydispose() {
		setVisible(false);
		tfSearch.setText("");
		pnResult.removeAll();
	}
}
