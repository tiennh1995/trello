package edu.ttl.constant;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import edu.ttl.object.CongViec;
import edu.ttl.object.DuAn;
import edu.ttl.object.LoginInfo;
import edu.ttl.object.NewUserInfo;
import edu.ttl.object.NguoiDung;
import edu.ttl.ui.ChiTietCongViecPanel;
import edu.ttl.ui.CongViecPanel;
import edu.ttl.ui.Login;

public class Database {

	private static OutputStream os;
	private static InputStream is;
	private static Socket socket;
	private static Thread readData;
	private static int t = 0;
	private static Thread th;
	private static int updateListCV;
	public static final int updateChiTietCV = 1;

	public static boolean initSocket() {
		try {
			socket = new Socket(R.IP_sever, R.port_sever);
			os = socket.getOutputStream();
			is = socket.getInputStream();
			System.out.println("Database###: Tao socket");
			FileStream.initSocket();
			Search.InitSocket();
			NotificationStream.initSocket();
			// NotificationStream.Listen();
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(R.login, "Địa chỉ IP máy chủ không đúng hoặc có vấn đề về mạng!");
			socket = null;
			return false;
		}
	}

	public static void UpdateListCongViec(Hashtable<Integer, CongViec> list) {
		R.congViecPanel.setListCongViec(list);
		R.congViecPanel.refreshData();
	}

	public static void UpdateUserInfo(NguoiDung user) {
		R.user = user;
		R.main.showUserInfo();
	}

	public static int getListCV(final int maDuAn, int updatelist) {
		updateListCV = updatelist;
		R.listCV.clear();
		th = new Thread(new Runnable() {
			int listen = 0;
			String str = "TXTPROGETJ" + String.valueOf(maDuAn);
			byte[] job = str.getBytes(Charset.forName("UTF-8"));
			byte[] sizeJob = ByteBuffer.allocate(9).putInt(job.length).array();

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				t = 0;
				while (t == 0) {
					try {
						os.write(sizeJob);
						os.write(job);
						os.flush();
					} catch (IOException ex) {
						Logger.getLogger(R.main.toString()).log(Level.SEVERE, null, ex);
					}
					if (listen++ == 0) {
						Listen();
					}
					if (listen == 3) {
						readData.stop();
						JOptionPane.showMessageDialog(R.main, "Có vấn đề về mạng. Kiểm tra lại");
						th.stop();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		th.start();
		return 1;
	}

	public static void sendLoginInfo(final LoginInfo user) {
		Database.initSocket();
		R.user.setUserName(user.getUserName());
		R.user.setPassWord(user.getPassWord());
		th = new Thread(new Runnable() {
			int listen = 0;
			String str = "TXTLOGNULL" + R.user.getUserName() + "|" + user.getPassWord();
			byte[] login = str.getBytes(Charset.forName("UTF-8"));
			byte[] size = ByteBuffer.allocate(9).putInt(login.length).array();

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				t = 0;
				while (t == 0) {
					try {
						os.write(size);
						os.write(login);
						os.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (listen++ == 0) {
						Listen();
					}
					if (listen == 3) {
						readData.stop();
						JOptionPane.showMessageDialog(R.login, "Có vấn đề về mạng. Kiểm tra lại");
						th.stop();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		th.start();
	}

	public static void getListProject() {
		th = new Thread(new Runnable() {
			int listen = 0;

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				t = 0;
				while (t == 0) {
					String str = "TXTPROGETL" + R.user.getUserID();
					byte[] createPro = str.getBytes(Charset.forName("UTF-8"));
					byte[] sizePro = ByteBuffer.allocate(9).putInt(createPro.length).array();
					try {
						os.write(sizePro);
						os.write(createPro);
						os.flush();
						if (listen++ == 0) {
							Listen();
							R.listProject.clear();
							R.listProjectStop.clear();
						}
						if (listen == 3) {
							readData.stop();
							JOptionPane.showMessageDialog(R.main, "Có vấn đề về mạng. Kiểm tra lại");
							th.stop();
						}
						Thread.sleep(1000);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});
		th.start();
	}

	public static void getAvatar(String TV) {
		try {
			String str = "IMGAVAGETA" + TV;
			byte[] ava = str.getBytes(Charset.forName("UTF-8"));
			byte[] sizeAva = ByteBuffer.allocate(9).putInt(ava.length).array();
			os.write(sizeAva);
			os.write(ava);
			os.flush();
			Listen();
		} catch (IOException ex) {
			Logger.getLogger(R.main.toString()).log(Level.SEVERE, null, ex);
		}
	}

	public static void sendNewCongViec(final CongViec cv) {
		th = new Thread(new Runnable() {
			int listen = 0;
			String str = "TXTJOBCREA" + cv.getTen() + "|" + cv.getTrangThai() + "|" + cv.getFkey();
			byte[] sendCV = str.getBytes(Charset.forName("UTF-8"));
			byte[] sizeSendCV = ByteBuffer.allocate(9).putInt(sendCV.length).array();

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				t = 0;
				while (t == 0) {
					try {
						os.write(sizeSendCV);
						os.write(sendCV);
						os.flush();
					} catch (IOException ex) {
						Logger.getLogger(R.main.getName()).log(Level.SEVERE, null, ex);
					}
					if (listen++ == 0) {
						Listen();
					}
					if (listen == 3) {
						readData.stop();
						JOptionPane.showMessageDialog(R.main, "Có vấn đề về mạng. Kiểm tra lại");
						th.stop();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		th.start();
	}

	public static void sendNewDuAn(final NewDuAn duan) {
		th = new Thread(new Runnable() {
			int listen = 0;
			String str = "TXTPROCREA" + duan.getTenDuAn() + "|" + R.user.getUserID();
			byte[] createPro = str.getBytes(Charset.forName("UTF-8"));
			byte[] sizePro = ByteBuffer.allocate(9).putInt(createPro.length).array();

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				t = 0;
				while (t == 0) {
					try {
						os.write(sizePro);
						os.write(createPro);
						os.flush();
					} catch (IOException ex) {
						Logger.getLogger(R.main.getName()).log(Level.SEVERE, null, ex);
					}
					if (listen++ == 0) {
						Listen();
					}
					if (listen == 3) {
						readData.stop();
						JOptionPane.showMessageDialog(R.main, "Có vấn đề về mạng. Kiểm tra lại");
						th.stop();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		th.start();
	}

	public static int sendNewUserInfo(final NewUserInfo user) {
		Database.initSocket();
		th = new Thread(new Runnable() {
			int listen = 0;
			String str = "TXTACCCREA" + user.getUserName() + "|" + user.getPassWord() + "|" + user.getName();
			byte[] createAcc = str.getBytes(Charset.forName("UTF-8"));
			byte[] sizeAcc = ByteBuffer.allocate(9).putInt(createAcc.length).array();

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				t = 0;
				while (t == 0) {
					try {
						os.write(sizeAcc);
						os.write(createAcc);
						os.flush();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (listen++ == 0) {
						Listen();
					}
					if (listen == 3) {
						readData.stop();
						JOptionPane.showMessageDialog(R.login, "Có vấn đề về mạng. Kiểm tra lại");
						th.stop();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		th.start();
		return 1;
	}

	public static void sendUpdatedProject(final DuAn duAn) {
		th = new Thread(new Runnable() {
			int listen = 0;
			String str = "TXTPROUPDA" + duAn.getMaDuAn() + "|" + duAn.getTenDuAn() + "|" + duAn.getTrangThai();
			byte[] upPro = str.getBytes(Charset.forName("UTF-8"));
			byte[] sizePro = ByteBuffer.allocate(9).putInt(upPro.length).array();

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				t = 0;
				while (t == 0) {
					try {
						os.write(sizePro);
						os.write(upPro);
						os.flush();
					} catch (IOException ex) {
						Logger.getLogger(R.main.getName()).log(Level.SEVERE, null, ex);
					}
					if (listen++ == 0) {
						Listen();
					}
					if (listen == 3) {
						readData.stop();
						JOptionPane.showMessageDialog(R.main, "Có vấn đề về mạng. Kiểm tra lại");
						th.stop();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		th.start();
	}

	public static void sendNewDeadLine(final CongViec cv) {
		th = new Thread(new Runnable() {
			int listen = 0;
			String str = "TXTDEAUPDA" + cv.getKey() + "|" + cv.getDeadline();
			byte[] deaUpda = str.getBytes(Charset.forName("UTF-8"));
			byte[] sizedeaUpda = ByteBuffer.allocate(9).putInt(deaUpda.length).array();

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				t = 0;
				while (t == 0) {
					try {
						os.write(sizedeaUpda);
						os.write(deaUpda);
						os.flush();
					} catch (IOException ex) {
						Logger.getLogger(R.main.getName()).log(Level.SEVERE, null, ex);
					}
					if (listen++ == 0) {
						Listen();
					}
					if (listen == 3) {
						readData.stop();
						JOptionPane.showMessageDialog(R.chitietCV, "Có vấn đề về mạng. Kiểm tra lại");
						th.stop();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		th.start();
	}

	public static void sendUpdateMota(final CongViec cv) {
		th = new Thread(new Runnable() {
			int listen = 0;
			String str = "TXTDESNULL" + cv.getKey() + "|" + cv.getMoTa();
			byte[] desUpda = str.getBytes(Charset.forName("UTF-8"));
			byte[] sizedesUpda = ByteBuffer.allocate(9).putInt(desUpda.length).array();

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				t = 0;
				while (t == 0) {
					try {
						os.write(sizedesUpda);
						os.write(desUpda);
						os.flush();
					} catch (IOException ex) {
						Logger.getLogger(R.main.getName()).log(Level.SEVERE, null, ex);
					}
					if (listen++ == 0) {
						Listen();
					}
					if (listen == 3) {
						readData.stop();
						JOptionPane.showMessageDialog(R.chitietCV, "Có vấn đề về mạng. Kiểm tra lại");
						th.stop();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		th.start();
	}

	public static void deleteCV(final CongViec cv) {
		th = new Thread(new Runnable() {
			int listen = 0;
			String str = "TXTJOBDELE" + cv.getKey();
			byte[] delCV = str.getBytes(Charset.forName("UTF-8"));
			byte[] sizeDEL = ByteBuffer.allocate(9).putInt(delCV.length).array();

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				t = 0;
				while (t == 0) {
					try {
						os.write(sizeDEL);
						os.write(delCV);
						os.flush();
					} catch (IOException ex) {
						Logger.getLogger(R.main.getName()).log(Level.SEVERE, null, ex);
					}
					if (listen++ == 0) {
						Listen();
					}
					if (listen == 3) {
						readData.stop();
						JOptionPane.showMessageDialog(R.chitietCV, "Có vấn đề về mạng. Kiểm tra lại");
						th.stop();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		th.start();
	}

	public static void sendUpdateNameJob(final CongViec cv) {
		// updateChiTietCV = 1;
		th = new Thread(new Runnable() {
			int listen = 0;
			String str = "TXTJOBUPNA" + cv.getKey() + "|" + cv.getTen();
			byte[] updaName = str.getBytes(Charset.forName("UTF-8"));
			byte[] sizeupdaName = ByteBuffer.allocate(9).putInt(updaName.length).array();

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				t = 0;
				while (t == 0) {
					try {
						os.write(sizeupdaName);
						os.write(updaName);
						os.flush();
					} catch (IOException ex) {
						Logger.getLogger(R.main.getName()).log(Level.SEVERE, null, ex);
					}
					if (listen++ == 0) {
						Listen();
					}
					if (listen == 3) {
						readData.stop();
						JOptionPane.showMessageDialog(R.chitietCV, "Có vấn đề về mạng. Kiểm tra lại");
						th.stop();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		th.start();
	}

	public static void sendUpdateState(final CongViec cv) {
		// updateChiTietCV = 1;
		th = new Thread(new Runnable() {
			int listen = 0;
			String str = "TXTJOBREMO" + cv.getKey() + "|" + cv.getTrangThai();
			byte[] updaState = str.getBytes(Charset.forName("UTF-8"));
			byte[] sizeupdaState = ByteBuffer.allocate(9).putInt(updaState.length).array();

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				t = 0;
				while (t == 0) {
					try {
						os.write(sizeupdaState);
						os.write(updaState);
						os.flush();
					} catch (IOException ex) {
						Logger.getLogger(R.main.getName()).log(Level.SEVERE, null, ex);
					}
					if (listen++ == 0) {
						Listen();
					}
					if (listen == 3) {
						readData.stop();
						JOptionPane.showMessageDialog(R.chitietCV, "Có vấn đề về mạng. Kiểm tra lại");
						th.stop();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		th.start();
	}

	public static void sendUpdateUser() {
		th = new Thread(new Runnable() {
			int listen = 0;
			String str = "TXTMEMUPDA" + R.user.getUserID() + "|" + R.user.getPassWord() + "|" + R.user.getName();
			byte[] updaState = str.getBytes(Charset.forName("UTF-8"));
			byte[] sizeupdaState = ByteBuffer.allocate(9).putInt(updaState.length).array();

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				t = 0;
				while (t == 0) {
					try {
						os.write(sizeupdaState);
						os.write(updaState);
						os.flush();
					} catch (IOException ex) {
						Logger.getLogger(R.main.getName()).log(Level.SEVERE, null, ex);
					}
					if (listen++ == 0) {
						Listen();
					}
					if (listen == 3) {
						readData.stop();
						JOptionPane.showMessageDialog(null, "Có vấn đề về mạng. Kiểm tra lại");
						th.stop();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		th.start();
	}

	public static void sendUpdateUser(final NguoiDung user) {
		th = new Thread(new Runnable() {
			int listen = 0;
			String str = "TXTMEMUPD2" + user.getUserID() + "|" + user.getUserName() + "|" + user.getName() + "|"
					+ user.getAdmin();
			byte[] updaState = str.getBytes(Charset.forName("UTF-8"));
			byte[] sizeupdaState = ByteBuffer.allocate(9).putInt(updaState.length).array();

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				t = 0;
				while (t == 0) {
					try {
						os.write(sizeupdaState);
						os.write(updaState);
						os.flush();
					} catch (IOException ex) {
						Logger.getLogger(R.main.getName()).log(Level.SEVERE, null, ex);
					}
					if (listen++ == 0) {
						Listen();
					}
					if (listen == 3) {
						readData.stop();
						JOptionPane.showMessageDialog(null, "Có vấn đề về mạng. Kiểm tra lại");
						th.stop();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		th.start();
	}

	public static void getMemberOfProject(final int proID) {
		th = new Thread(new Runnable() {
			int listen = 0;
			String str = "TXTMEMGETP" + proID;
			byte[] updaState = str.getBytes(Charset.forName("UTF-8"));
			byte[] sizeupdaState = ByteBuffer.allocate(9).putInt(updaState.length).array();

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				t = 0;
				while (t == 0) {
					try {
						os.write(sizeupdaState);
						os.write(updaState);
						os.flush();
					} catch (IOException ex) {
						Logger.getLogger(R.main.getName()).log(Level.SEVERE, null, ex);
					}
					if (listen++ == 0) {
						Listen();
					}
					if (listen == 3) {
						readData.stop();
						JOptionPane.showMessageDialog(null, "Có vấn đề về mạng. Kiểm tra lại");
						th.stop();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		th.start();
	}

	public static void sendUpdateNotification() {

		String str = "TXTMEMUPNO" + R.user.getUserID();
		byte[] updaState = str.getBytes(Charset.forName("UTF-8"));
		byte[] sizeupdaState = ByteBuffer.allocate(9).putInt(updaState.length).array();
		try {
			os.write(sizeupdaState);
			os.write(updaState);
			os.flush();
		} catch (IOException ex) {
			Logger.getLogger(R.main.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public static class ReadData implements Runnable {

		@Override
		public void run() {
			try {
				int bytesRead = 0;
				int bytesToRead = 0;
				byte[] size = new byte[9];
				is.read(size);
				bytesToRead = ByteBuffer.wrap(size).asIntBuffer().get();
				byte[] mybytearray = new byte[bytesToRead];
				while (bytesRead < bytesToRead) {
					bytesRead += is.read(mybytearray, bytesRead, bytesToRead - bytesRead);
				}
				String format = new String(mybytearray, 0, 3, "UTF-8");
				String cmd = new String(mybytearray, 3, 3, "UTF-8");
				String extra = new String(mybytearray, 6, 4, "UTF-8");
				switch (format) {
				case "TXT":
					String data = new String(mybytearray, 10, mybytearray.length - 10, "UTF-8");
					String dt[] = data.split("[|]");
					System.out.println("Database###: " + format + cmd + extra + data);
					if (cmd.equals("LOG")) {
						if (extra.equals("SUCC")) {
							t = 1;
							R.user.setUserID(Integer.parseInt(dt[0]));
							R.user.setName(dt[1]);
							R.user.setIsAdmin(Integer.parseInt(dt[2]));
							for (int i = 3; i < dt.length; i += 3) {
								DuAn da = new DuAn(Integer.parseInt(dt[i]), dt[i + 1], Integer.parseInt(dt[i + 2]));
								if (Integer.parseInt(dt[i + 2]) == 1) {
									R.listProject.add(da);
								} else {
									R.listProjectStop.add(da);
								}
							}
							getAvatar(dt[0]);
							NotificationStream.getNotification(dt[0]);
							// NotificationStrem.getNotificationNow();
							R.main.showInfo(); // Hien thi bang cong viec
							R.login.dispose();
							R.main.setVisible(true);
							Search.getData("DuAn");
						} else {
							t = 1;
							JOptionPane.showMessageDialog(null, "Tên đăng nhập hoặc mật khẩu không đúng!");
							Login.setnull2();
						}
					}

					if (cmd.equals("ACC")) {
						t = 1;
						if (dt[0].equals("SUCC")) {
							R.user.setUserID(Integer.parseInt(dt[1]));
							R.login.getPnRegistered().showResultRegedited(0);
						} else if (dt[0].equals("EXIS")) {
							R.login.getPnRegistered().showResultRegedited(1);
						} else
							JOptionPane.showMessageDialog(null, "Xảy ra lỗi khi tạo tài khoản. Vui lòng thử lại.");
					}

					if (cmd.equals("PRO")) {
						// LAY THONG TIN CAC CONG VIEC TRONG DU AN
						if (extra.equals("GETJ")) {
							t = 1;
							String listMaCV = new String();
							for (int i = 1; i < dt.length; i += 5) {
								CongViec congViec = new CongViec();
								congViec.setKey(Integer.parseInt(dt[i]));
								if (/* updateChiTietCV == 1 && */ChiTietCongViecPanel.cv.getKey() == congViec.getKey())
									ChiTietCongViecPanel.cv = congViec;
								congViec.setTen(dt[i + 1]);
								if (dt[i + 2].equals("null")) {
									dt[i + 2] = "";
								}
								congViec.setDeadline(dt[i + 2]);
								if (dt[i + 3].equals("null")) {
									dt[i + 3] = "";
								}
								congViec.setMoTa(dt[i + 3]);
								congViec.setTrangThai(Integer.parseInt(dt[i + 4]));
								R.listCV.put(congViec.getKey(), congViec);
								listMaCV = listMaCV + dt[i] + "|";
							}
							try {
								String str = "TXTMEMGETJ" + listMaCV;
								byte[] login = str.getBytes(Charset.forName("UTF-8"));
								byte[] sizeMaCV = ByteBuffer.allocate(9).putInt(login.length).array();
								os.write(sizeMaCV);
								os.write(login);
								os.flush();
								Listen();
							} catch (IOException ex) {
								Logger.getLogger(R.main.toString()).log(Level.SEVERE, null, ex);
							}
						}

						if (extra.equals("CREA")) {
							if (dt[0].equals("SUCC")) {
								t = 1;
								DuAn da = new DuAn(Integer.parseInt(dt[1]), dt[2], 1);
								R.congViecPanel.setDuAn(da);
								R.main.createNewProjectSuccess(da);
							} else {
								JOptionPane.showMessageDialog(null, "Tạo dự án thất bại!");
							}
						}

						if (extra.equals("GETL")) {
							t = 1;
							if (!dt[0].isEmpty()) {
								for (int i = 0; i < dt.length; i += 3) {
									DuAn da = new DuAn(Integer.parseInt(dt[i]), dt[i + 1], Integer.parseInt(dt[i + 2]));
									if (Integer.parseInt(dt[i + 2]) == 1) {
										R.listProject.add(da);
									} else {
										R.listProjectStop.add(da);
									}
								}
							}
							R.main.updateListProjectSuccess();
							Search.getData("DuAn");
						}
						if (extra.equals("UPDA")) {
							if (dt[0].equals("SUCC")) {
								t = 1;
								R.congViecPanel.changebtState();
								Search.getData("DuAn");
							} else {
								JOptionPane.showMessageDialog(null, "Cập nhật trạng thái dự án thất bại!");

							}
						}
					}

					if (cmd.equals("MEM")) {
						if (extra.equals("GETJ")) {
							t = 1;
							if (dt[0].equals("SUCC")) {
								for (int i = 1; i < dt.length; i += 2 + Integer.parseInt(dt[i + 1]) * 3) {
									if (dt[i].equals("null"))
										break;
									CongViec congViec = R.listCV.get(Integer.parseInt(dt[i]));
									int size2 = 0;
									size2 = Integer.parseInt(dt[i + 1]);
									for (int j = i + 2; j < size2 * 3 + i + 2; j += 3) {
										NguoiDung nd = new NguoiDung(Integer.parseInt(dt[j]), dt[j + 2]);
										nd.setUserName(dt[j + 1]);
										congViec.getListNguoiLam().add(nd);
									}
									// R.listCV.put(Integer.parseInt(dt[i]),
									// congViec); con tro
								}
								if (updateListCV == 1) {
									R.congViecPanel.refreshData();
									CongViecPanel.memberList.clear();
									getMemberOfProject(R.congViecPanel.getDuAn().getMaDuAn());
									// if (updateChiTietCV == 1) {
									// updateChiTietCV = 0;
									if (R.chitietCV.isVisible()) {
										R.chitietCV.init();
									}
									// }
								} else {
									R.main.showListCongViec();
									CongViecPanel.memberList.clear();
									getMemberOfProject(R.congViecPanel.getDuAn().getMaDuAn());
								}
							}
						} else if (extra.equals("GETP")) {
							t = 1;
							if (dt[0].equals("SUCC")) {
								for (int i = 1; i < dt.length; i += 2 + Integer.parseInt(dt[i + 1]) * 3) {
									if (dt[i].equals("null"))
										break;
									int size2 = 0;
									size2 = Integer.parseInt(dt[i + 1]);
									for (int j = i + 2; j < size2 * 3 + i + 2; j += 3) {
										NguoiDung nd = new NguoiDung(Integer.parseInt(dt[j]), dt[j + 2]);
										nd.setUserName(dt[j + 1]);
										CongViecPanel.memberList.add(nd);
									}
									// R.listCV.put(Integer.parseInt(dt[i]),
									// congViec); con tro
								}
							}
						} else if (extra.equals("UPDA")) {
							t = 1;
							if (dt[0].equals("SUCC")) {
								JOptionPane.showMessageDialog(R.main.userControlDialog,
										"Cập nhật thông tin người dùng thành công");
								R.main.updateName();
							}
						} else if (extra.equals("UPD2")) {
							t = 1;
							if (dt[0].equals("SUCC")) {
								JOptionPane.showMessageDialog(R.main.userControlDialog,
										"Cập nhật thông tin người dùng thành công");
							} else {
								JOptionPane.showMessageDialog(R.main.userControlDialog,
										"Cập nhật thông tin người dùng không thành công");
							}
						}
					}
					if (cmd.equals("JOB")) {
						// TAO MOT CONG VIEC
						if (extra.equals("CREA")) {
							if (dt[0].equals("SUCC")) {
								t = 1;
								CongViec congViec = new CongViec();
								congViec.setKey(Integer.parseInt(dt[1]));
								congViec.setTen(dt[2]);
								congViec.setTrangThai(Integer.parseInt(dt[3]));
								getListCV(R.congViecPanel.getDuAn().getMaDuAn(), 1);
							} else {
								JOptionPane.showMessageDialog(null, "Tạo công việc thất bại!");
							}
						}
						if (extra.equals("UPNA")) {
							t = 1;
							if (dt[0].equals("SUCC")) {
								R.listCV.clear();
								getListCV(R.congViecPanel.getDuAn().getMaDuAn(), 1);
							} else {
								JOptionPane.showMessageDialog(null, "Đổi tên công việc thất bại!");
							}
						}
						if (extra.equals("REMO")) {
							t = 1;
							if (dt[0].equals("SUCC")) {
								R.listCV.clear();
								getListCV(R.congViecPanel.getDuAn().getMaDuAn(), 1);
							} else {
								JOptionPane.showMessageDialog(null, "Đổi trạng thái công việc thất bại!");
							}
						}
						if (extra.equals("DELE")) {
							t = 1;
							if (dt[0].equals("SUCC")) {
								R.listCV.clear();
								getListCV(R.congViecPanel.getDuAn().getMaDuAn(), 1);
								R.chitietCV.setVisible(false);
							} else {
								JOptionPane.showMessageDialog(null, "Xóa công việc thất bại!");
							}
						}
					}

					if (cmd.equals("DEA")) {
						t = 1;
						if (extra.equals("UPDA")) {
							if (dt[0].equals("SUCC")) {
								R.listCV.clear();
								getListCV(R.congViecPanel.getDuAn().getMaDuAn(), 1);
							} else
								JOptionPane.showMessageDialog(null, "Cập nhật mô tả thất bại");
						}
					}
					if (cmd.equals("DES")) {
						t = 1;
						if (extra.equals("UPDA")) {
							if (dt[0].equals("SUCC")) {

							} else
								JOptionPane.showMessageDialog(null, "Cập nhật Deadline thất bại");
						}
					}
					break;

				case "IMG":
					if (cmd.equals("AVA")) {
						if (extra.equals("SUCC")) {
							t = 1;
							int sizeCMD = 10;
							ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(mybytearray, sizeCMD,
									mybytearray.length - sizeCMD);
							BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
							File f = new File("data/user");
							if (!f.exists())
								f.mkdirs();
							File outputFile = new File("data/user/" + String.valueOf(R.user.getUserID()) + ".jpg");
							ImageIO.write(bufferedImage, "jpg", outputFile);
							R.user.setAvatarUrl("data/user/" + String.valueOf(R.user.getUserID()) + ".jpg");
						}
						if (extra.equals("NULL")) {
							R.user.setAvatarUrl("icon/noAvatar.jpg");
						}
						R.main.updateAvatar();
					}
					break;
				}

			} catch (IOException ex) {

				System.out.println("Socket database has closed");

			} catch (ArrayIndexOutOfBoundsException ex) {
			}
		}
	}

	public static void Listen() {
		readData = new Thread(new ReadData());
		readData.start();
	}
}
