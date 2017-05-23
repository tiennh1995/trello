package edu.ttl.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import edu.ttl.constant.Database;
import edu.ttl.constant.NotificationStream;
import edu.ttl.constant.R;
import edu.ttl.constant.Search;
import edu.ttl.object.CongViec;
import edu.ttl.object.DuAn;
import edu.ttl.tool.MyIcon;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int TOOLBARHEIGHT = (int) Math.round(0.065 * R.screenHeight);

	private JPanel pnDuAn = new JPanel();
	private CongViecPanel pnCongViec = new CongViecPanel();
	private JScrollPane snDuAn = new JScrollPane();

	private JPanel pnToolBar = new JPanel();
	private JPanel RightButton = new JPanel();

	private JLabel lbAvatarB = new JLabel();
	private JLabel lbNameRight = new JLabel();
	private JButton btName = new JButton();
	private JButton btNotification = new JButton();
	protected JTextField tfSearch = new JTextField();
	private JButton btSearch = new JButton();
	private JButton newProject = new JButton();
	private NewProjectPanel newProPn = new NewProjectPanel();

	private JButton btAvatar = new JButton();
	private JLabel lbName = new JLabel();

	private ArrayList<PButton> pBList = new ArrayList<PButton>();
	private ArrayList<PButtonStop> pBListStop = new ArrayList<PButtonStop>();

	private int iconHeight = (int) Math.round((4 * TOOLBARHEIGHT / 5.0));
	protected int marginSize = 0;

	private int buttonW = (int) Math.round(R.screenWidth / 4.5);
	private int buttonH = (int) Math.round(R.BUTTONW / 3);
	private int BLine = 0, BRow = 0, BRowMax = 0, space = (int) Math.round(R.screenWidth * 0.0185185);
	private int nrW;
	private int rightButtonPanel;
	private int nbW = 75;

	private int curLayer = 0;

	private JPanel contentPane;
	private JLayeredPane lyp = new JLayeredPane();
	protected MainSearch mainSearch = new MainSearch(tfSearch);

	private UserPanel userPn = new UserPanel();
	public UserControlDialog userControlDialog = new UserControlDialog();
	protected AdminControlDialog adminControlDialog = new AdminControlDialog();
	protected JLabel lbCountNoti = new JLabel();
	private NotificationDialog notificationDialog = new NotificationDialog();

	/**
	 * Launch the application.
	 * 
	 * @return
	 */
	public Main() {

	}

	public void init() {
		try {
			UIManager.setLookAndFeel(R.windowsClassName);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(this);

		PButton.BIcon = new MyIcon("icon/buttonB.png");
		PButtonStop.BIcon = new MyIcon("icon/ButtonB-stop.png");

		R.congViecPanel = pnCongViec;
		int w = 0;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation((int) ((R.screenWidth - 1051) / 2), 50);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(1051, 649));
		setTitle(R.APPTITLE);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.createImage("icon/AppIcon.png");
		setIconImage(img);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());

		pnToolBar.setBackground(new Color(0x009688));
		contentPane.add(pnToolBar, BorderLayout.NORTH);
		pnToolBar.setLayout(null);

		if (TOOLBARHEIGHT < 50)
			TOOLBARHEIGHT = 50;
		pnToolBar.setPreferredSize(new Dimension(R.screenWidth, TOOLBARHEIGHT));

		JButton btHome = new JButton();
		if (iconHeight != (int) Math.round((4 * TOOLBARHEIGHT / 5.0)))
			iconHeight = (int) Math.round((4 * TOOLBARHEIGHT / 5.0));
		marginSize = (int) Math.round(TOOLBARHEIGHT / 10.0);
		btHome.setIcon(R.icon.resize(0, iconHeight, 1, "icon/Home.png"));
		w = R.icon.getWidth();
		btHome.setBounds(marginSize, marginSize, w, iconHeight);
		w += marginSize;
		btHome.setLayout(null);
		btHome.setContentAreaFilled(false);
		btHome.setBorder(null);
		btHome.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnToolBar.add(btHome);

		btHome.addActionListener(new ActionListener() { // ~~~~~~~~~~~~~~~~~~~

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				backHome();
			}
		});
		// new Search("CongViec");
		add(mainSearch);
		mainSearch.setVisible(false);
		btSearch.setIcon(R.icon.resize(0, iconHeight, 1, "icon/Search.png"));
		R.searchBarWidth = R.icon.getWidth();
		btSearch.setBounds(w + marginSize, marginSize, R.icon.getWidth(), iconHeight);
		mainSearch.init(w + marginSize, TOOLBARHEIGHT, iconHeight);

		btSearch.setLayout(null);
		btSearch.setBorder(null);
		btSearch.setContentAreaFilled(false);
		btSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				tfSearch.requestFocus();
			}
		});
		tfSearch.setOpaque(false);
		tfSearch.setBorder(null);
		tfSearch.setBounds(marginSize, 0, R.icon.getWidth() - 7 * marginSize, iconHeight);
		tfSearch.setForeground(Color.white);
		tfSearch.setFont(R.FONT16);
		// tfSearch.addMouseListener(MainSearch.mouseClick);
		btSearch.add(tfSearch);
		btSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnToolBar.add(btSearch);
		w += R.icon.getWidth();

		lbAvatarB.setIcon(R.icon.resize(iconHeight, iconHeight, 0, "icon/avatarB.png"));

		btAvatar.setBounds(marginSize / 2, marginSize / 2, iconHeight - 2 * (marginSize / 2),
				iconHeight - 2 * (marginSize / 2));

		btAvatar.setContentAreaFilled(false);
		btAvatar.setBorder(null);
		btAvatar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lbAvatarB.add(btAvatar);

		btAvatar.addActionListener(new ActionListener() { // ~~~~~~~~~~~~~~~~~~~

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				userPn.init();
			}
		});
		btName.addActionListener(new ActionListener() { // ~~~~~~~~~~~~~~~~~~~

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				userPn.init();
			}
		});

		lbName.addMouseListener(new MouseListener() {

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
				userPn.init();
			}
		});

		lbNameRight.setIcon(R.icon.resize(0, iconHeight, 1, "icon/nameRight.png"));
		nrW = R.icon.getWidth();
		lbNameRight.setBounds(getWidth() - 62, marginSize, nrW, iconHeight);
		pnToolBar.add(lbNameRight);

		lbName.setFont(R.FONT16B);
		lbName.setForeground(Color.WHITE);
		lbName.setBorder(null);

		btName.setIcon(R.icon.resize(nbW, iconHeight, -1, "icon/vuong.png"));
		lbName.setBounds(0, 0, nbW, iconHeight);
		btName.add(lbName);
		btName.setBorder(null);
		btName.setContentAreaFilled(false);
		btName.setLayout(null);
		btName.setCursor(new Cursor(Cursor.HAND_CURSOR));

		lbCountNoti.setBackground(null);
		lbCountNoti.setFont(R.FONT14B);
		lbCountNoti.setAlignmentX(Container.RIGHT_ALIGNMENT);
		lbCountNoti.setAlignmentY(Container.TOP_ALIGNMENT);
		btNotification.setLayout(new BoxLayout(btNotification, BoxLayout.Y_AXIS));
		btNotification.add(lbCountNoti);
		btNotification.setBorder(null);
		btNotification.setContentAreaFilled(false);
		btNotification.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btNotification.setIcon(R.icon.resize(iconHeight, iconHeight, 1, "icon/notification.png"));
		btNotification.addActionListener(new ActionListener() { // ~~~~~~~~~~~~~~~~~~~

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Database.sendUpdateNotification();
				notificationDialog.init();
			}
		});

		// icon phia ben phai Toolbar
		rightButtonPanel = iconHeight + nrW + marginSize + nbW + iconHeight;
		RightButton.setLayout(null);
		RightButton.setBackground(null);
		// RightButton.setSize(rightButtonPanel, TOOLBARHEIGHT);
		RightButton.add(btNotification);
		RightButton.add(lbNameRight);
		RightButton.add(btName);
		RightButton.add(lbAvatarB);
		pnToolBar.add(RightButton);

		// setup panel
		contentPane.add(lyp, BorderLayout.CENTER);
		pnDuAn.setBackground(Color.WHITE);
		pnDuAn.setLayout(null);
		snDuAn.setViewportView(pnDuAn);
		snDuAn.getVerticalScrollBar().setUnitIncrement(10);
		snDuAn.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		pnDuAn.add(newProject);

		lyp.add(snDuAn, 0);
		lyp.add(pnCongViec, 1);

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				disposeAll();
				RightButton.setBounds(getWidth() - rightButtonPanel - marginSize * 4, 0, rightButtonPanel,
						TOOLBARHEIGHT);
				if (curLayer == 0) {
					updatePnDuAn();
				} else if (curLayer == 1) {
					pnCongViec.setBounds(0, 0, getWidth() - marginSize * 3, getHeight() - TOOLBARHEIGHT);
					pnCongViec.rePaint();
				}
			}
		});

		addWindowStateListener(new WindowStateListener() {

			@Override
			public void windowStateChanged(WindowEvent e) {
				// TODO Auto-generated method stub
				disposeAll();
				R.chitietCV.myDisposeComponent();
			}
		});

		R.chitietCV = new ChiTietCongViecPanel();
		MouseListener mouseClick = new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				disposeAll(arg0);
			}
		};

		Set<Component> components = R.getAllComponents(this);
		for (Component component : components) {
			component.addMouseListener(mouseClick);
		}

	}

	private void updatePnDuAn() {
		int gw = getWidth();
		int gh = getHeight() - TOOLBARHEIGHT;
		int tmp = gw / buttonW;
		if (gw - tmp * buttonW - (tmp - 1 + 3) * space < 0) {
			int tmp2 = (int) ((gw - (tmp - 1 + 3) * space) / (float) tmp);
			if (tmp2 >= (int) (buttonW * 0.75)) {
				buttonW = tmp2;
			} else
				tmp--;
		}
		if (tmp != BRowMax || buttonW != R.BUTTONW) {
			BLine = 0;
			BRow = 0;
			BRowMax = tmp;
			rePaintProjectButton();
			buttonW = R.BUTTONW;
		}
		pnDuAn.setPreferredSize(new Dimension(gw, (int) ((3.5 + BLine) * space) + BLine * buttonH));
		pnDuAn.setSize(gw - marginSize * 3, gh - marginSize * 2);
		snDuAn.setBounds(0, 0, gw - marginSize * 3, gh - marginSize * 2);
	}

	private void createProjectButton() {
		PButton.BIcon.resize(buttonW, buttonH, 2);
		PButton.width = buttonW;
		PButton.height = buttonH;
		pBList.clear();

		PButtonStop.BIcon.resize(buttonW, buttonH, 2);
		PButtonStop.width = buttonW;
		PButtonStop.height = buttonH;
		pBListStop.clear();

		for (int i = 0; i < R.listProject.size(); i++) {
			PButton b = new PButton(R.listProject.get(i));
			b.init();
			pBList.add(b);
			pnDuAn.add(b);
		}

		for (int i = 0; i < R.listProjectStop.size(); i++) {
			PButtonStop b = new PButtonStop(R.listProjectStop.get(i));
			b.init();
			pBListStop.add(b);
			pnDuAn.add(b);
		}

		if (R.user.isAdmin()) {
			newProject.setIcon(new MyIcon().resize(buttonW, buttonH, 2, "icon/ButtonNew.png"));
			newProject.setContentAreaFilled(false);
			newProject.setBorder(null);
			newProject.setCursor(new Cursor(Cursor.HAND_CURSOR));

			newProject.addActionListener(new ActionListener() { // ~~~~~~~~~~~~~~~~~~~~~~~~~~

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					newProPn.init();
				}
			});

			newProject.setVisible(true);
			pnDuAn.add(newProject);
		}
	}

	private void rePaintProjectButton() {
		for (int i = 0; i < pBList.size(); i++) {
			PButton b = pBList.get(i);
			b.setBounds((int) ((1.5 + BRow) * space + BRow * buttonW), (int) ((1.5 + BLine) * space + BLine * buttonH),
					buttonW, buttonH);
			if (++BRow >= BRowMax) { // BRow tinh tu 0
				BRow = 0;
				BLine++;
			}
		}
		if (R.user.isAdmin()) {
			newProject.setBounds((int) ((1.5 + BRow) * space + BRow * buttonW),
					(int) ((1.5 + BLine) * space + BLine * buttonH), buttonW, buttonH);
			if (++BRow >= BRowMax) { // BRow tinh tu 0
				BRow = 0;
				BLine++;
			}
		}
		if (BRow != 0) {
			BRow = 0;
			BLine++;
		}
		for (int i = 0; i < pBListStop.size(); i++) {
			PButtonStop b = pBListStop.get(i);
			b.setBounds((int) ((1.5 + BRow) * space + BRow * buttonW), (int) ((1.5 + BLine) * space + BLine * buttonH),
					buttonW, buttonH);
			if (++BRow >= BRowMax) { // BRow tinh tu 0
				BRow = 0;
				BLine++;
			}
		}
		snDuAn.setViewportView(pnDuAn);
	}

	public void showUserInfo() {
		lbName.setText(R.user.getName());
		nbW = lbName.getPreferredSize().width + marginSize;
		int margin2 = marginSize;
		if (nbW < 75) {
			margin2 = (int) ((75 - nbW) / 2);
			nbW = 75;
		}
		btName.setIcon(R.icon.resize(nbW, iconHeight, -1, "icon/vuong.png"));
		lbName.setBounds(margin2, 0, nbW, iconHeight);

		rightButtonPanel = iconHeight + nrW + marginSize + nbW + iconHeight;
		int wtmp = rightButtonPanel - iconHeight;
		btNotification.setBounds(wtmp, marginSize, iconHeight, iconHeight);
		wtmp = wtmp - nrW - marginSize;
		lbNameRight.setBounds(wtmp, marginSize, nrW, iconHeight);
		wtmp = wtmp - nbW;
		btName.setBounds(wtmp, marginSize, nbW, iconHeight);
		wtmp = wtmp - iconHeight;
		lbAvatarB.setBounds(wtmp, marginSize, iconHeight, iconHeight);
		// btAvatar.setIcon(R.icon.resize(iconHeight - 2*(marginSize/2),
		// iconHeight - 2*(marginSize/2), 2, R.user.getAvatarUrl()));
		RightButton.setBounds(getWidth() - rightButtonPanel - marginSize * 4, 0, rightButtonPanel, TOOLBARHEIGHT);
	}

	public void updateAvatar() {
		btAvatar.setIcon(R.icon.resize(iconHeight - 2 * (marginSize / 2), iconHeight - 2 * (marginSize / 2), 2,
				R.user.getAvatarUrl()));
		if (userControlDialog.isVisible())
			userControlDialog.btAvatar.setIcon(R.icon.resize(100, 100, 2, R.user.getAvatarUrl()));
	}
	
	public void updateName() {
		lbName.setText(R.user.getName());
	}

	public void updateListProject() {
		Database.getListProject();
	}

	public void updateListProjectSuccess() {
		reCreateProjectButton();
		pnCongViec.setVisible(false);
		curLayer = 0;
		lyp.setLayer(snDuAn, 0);
		snDuAn.setVisible(true);
	}

	public void reCreateProjectButton() {
		pnDuAn.removeAll();
		int gw = getWidth();
		int gh = getHeight() - TOOLBARHEIGHT;
		int tmp = gw / buttonW;
		if (gw - tmp * buttonW - (tmp - 1 + 3) * space < 0) {
			int tmp2 = (int) ((gw - (tmp - 1 + 3) * space) / (float) tmp);
			if (tmp2 >= (int) (buttonW * 0.75)) {
				buttonW = tmp2;
			} else
				tmp--;
		}
		if (tmp != BRowMax || buttonW != R.BUTTONW) {
			BRowMax = tmp;
			buttonW = R.BUTTONW;
		}
		BLine = 0;
		BRow = 0;

		PButton.BIcon.resize(buttonW, buttonH, 2);
		PButton.width = buttonW;
		PButton.height = buttonH;
		pBList.clear();

		PButtonStop.BIcon.resize(buttonW, buttonH, 2);
		PButtonStop.width = buttonW;
		PButtonStop.height = buttonH;
		pBListStop.clear();

		for (int i = 0; i < R.listProject.size(); i++) {
			PButton b = new PButton(R.listProject.get(i));
			b.init();
			pBList.add(b);
			pnDuAn.add(b);
		}

		for (int i = 0; i < R.listProjectStop.size(); i++) {
			PButtonStop b = new PButtonStop(R.listProjectStop.get(i));
			b.init();
			pBListStop.add(b);
			pnDuAn.add(b);
		}
		if (R.user.isAdmin()) {
			pnDuAn.add(newProject);
		}
		rePaintProjectButton();
		pnDuAn.setPreferredSize(new Dimension(gw, (int) ((3.5 + BLine) * space) + BLine * buttonH));
		pnDuAn.setSize(gw - marginSize * 3, gh - marginSize * 2);
		snDuAn.setBounds(0, 0, gw - marginSize * 3, gh - marginSize * 2);
	}

	public void showInfo() {
		createProjectButton();
		rePaintProjectButton();
		showUserInfo();
	}

	public void backHome() {
		pnCongViec.exitAddNewCV(3);
		R.listProject.clear();
		R.listProjectStop.clear();
		pBList.clear();
		pBListStop.clear();
		pnDuAn.removeAll();
		BLine = 0;
		BRow = 0;
		// R.listCV.clear();
		updateListProject();

		// pnCongViec.refreshData();
	}

	public void createNewProjectSuccess(DuAn duanmoi) {
		R.listCV.clear();
		newProPn.tfPojectName.setText("");
		newProPn.setVisible(false);
		pnCongViec.setDuAn(duanmoi);
		showListCongViec();
	}

	public void showListCongViec() {
		pnCongViec.setBounds(0, 0, getWidth() - marginSize * 3, getHeight() - TOOLBARHEIGHT - marginSize * 2);
		curLayer = 1;
		pnCongViec.getData();
		pnCongViec.setVisible(true);
		lyp.setLayer(pnCongViec, 1);
		snDuAn.setVisible(false);
	}

	public void disposeAll(MouseEvent mv) {
		if (mv.getSource() != btName && mv.getSource() != lbName && mv.getSource() != btAvatar && userPn.isVisible())
			userPn.dispose();
		if (mv.getSource() != btSearch && mv.getSource() != tfSearch && mainSearch.isVisible())
			mainSearch.Mydispose();
		if (mv.getSource() != newProject && mv.getSource() != newProPn && newProPn.isVisible())
			newProPn.dispose();
		if (mv.getSource() != btNotification && mv.getSource() != lbCountNoti 
				&& notificationDialog.isVisible()) notificationDialog.myDispose();
		if (mv.getSource() != pnCongViec.btAddMember && pnCongViec.addMember.isVisible()) pnCongViec.addMember.Mydispose();
		if (userControlDialog.isVisible())
			userControlDialog.setVisible(false);
		if (adminControlDialog.isVisible())
			adminControlDialog.setVisible(false);
	}

	public void disposeAll() {
		if (userPn.isVisible())
			userPn.dispose();
		if (mainSearch.isVisible())
			mainSearch.Mydispose();
		if (newProPn.isVisible())
			newProPn.dispose();
		if (userControlDialog.isVisible())
			userControlDialog.setVisible(false);
		if (adminControlDialog.isVisible())
			adminControlDialog.setVisible(false);
		if (notificationDialog.isVisible()) 
			notificationDialog.dispose();
		if (pnCongViec.addMember.isVisible()) pnCongViec.addMember.Mydispose();
	}

	@SuppressWarnings("deprecation")
	public void reset() {
		setVisible(false);
		R.login.setVisible(true);
		Login.setnull2();
		R.listCV.clear();
		R.listProject.clear();
		R.listProjectStop.clear();
		R.listNotification.clear();
		setNotificationButton(0);
		pBList.clear();
		pBListStop.clear();
		pnCongViec.setVisible(false);
		curLayer = 0;
		pnDuAn.removeAll();
		BLine = 0;
		BRow = 0;
		lyp.setLayer(snDuAn, 0);
		snDuAn.setVisible(true);
		R.user.clear();
		ChiTietCongViecPanel.cv = new CongViec();
		NotificationStream.th.stop();
	}
	
	public void setNotificationButton(int c) {
		if (c != 0) {
			lbCountNoti.setText(String.valueOf(c) + " ");
			lbCountNoti.setForeground(Color.WHITE);
			lbCountNoti.setFont(R.FONT16B);
			btNotification.setIcon(R.icon.resize(iconHeight, iconHeight, 1, "icon/notification-action.png"));
		} else {
			btNotification.setIcon(R.icon.resize(iconHeight, iconHeight, 1, "icon/notification.png"));
			lbCountNoti.setText("");
		}
	}
}
