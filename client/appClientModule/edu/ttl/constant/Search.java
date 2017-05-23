package edu.ttl.constant;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
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
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import edu.ttl.object.Comment;
import edu.ttl.object.CongViec;
import edu.ttl.object.DuAn;
import edu.ttl.object.NguoiDung;
import edu.ttl.tool.MyIcon;
import edu.ttl.ui.ChiTietCongViecPanel;

public class Search extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;

	private static Thread readData;
	private static int t = 0;
	private static Thread th;
	private static Socket socket = null;
	private static OutputStream os;
	private static InputStream is;

	public static Hashtable<String, DuAn> listProject = new Hashtable<>();
	public static Hashtable<String, NguoiDung> listThanhVien = new Hashtable<>();
	public static Hashtable<String, String> listCongViec = new Hashtable<>();

	public static String title;

	public static void InitSocket() {
		try {
			socket = new Socket(R.IP_sever, R.port_sever);
			os = socket.getOutputStream();
			is = socket.getInputStream();
			System.out.println("SEARCH#####: Tạo socket");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			socket = null;
			e.printStackTrace();
		}
	}

	public static void getData(final String title) {
		Search.title = title;
		try {
			String str = "TXTPROSEAR" + title;
			byte[] search = str.getBytes(Charset.forName("UTF-8"));
			byte[] size = ByteBuffer.allocate(9).putInt(search.length).array();
			os.write(size);
			os.write(search);
			os.flush();
			Listen();
		} catch (IOException ex) {
			Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
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
				String data = new String(mybytearray, 10, mybytearray.length - 10, "UTF-8");
				String dt[] = data.split("[|]");
				System.out.println("SEARCH#####: " + format + cmd + extra + data);
				switch (format) {
				case "TXT":
					if (cmd.equals("PRO")) {
						if (extra.equals("SEAR")) {
							if (data != null && data.equals("") == false) {
								for (int i = 0; i < dt.length;) {
									switch (title) {
									case "DuAn":
										listProject.put(dt[i], new DuAn(Integer.parseInt(dt[i]), dt[i + 1],
												Integer.parseInt(dt[i + 2])));
										i += 3;
										break;
									case "ThanhVien":
										NguoiDung u = new NguoiDung();
										u.setUserID(Integer.parseInt(dt[i]));
										u.setName(dt[i + 1]);
										u.setUserName(dt[i + 2]);
										u.setIsAdmin(Integer.parseInt(dt[i + 3]));
										listThanhVien.put(dt[i], u);
										i += 4;
										break;
									case "CongViec":
										listCongViec.put(dt[i], dt[i + 1]);
										i += 2;
										break;
									default:
										break;
									}
								}
							}
							switch (title) {
							case "DuAn":
								getData("ThanhVien");
								break;
							case "ThanhVien":

								break;
							default:
								break;
							}
						}
					} else if (cmd.equals("MEM")) {
						t = 1;
						if (extra.equals("ADDJ")) {
							if (dt[0].equals("SUCC")) {

							} else {
								JOptionPane.showMessageDialog(R.chitietCV, "Thêm thành viên vào công việc thất bại!");

							}
						} else if (extra.equals("DELJ")) {
							if (dt[0].equals("SUCC")) {

							} else {
								JOptionPane.showMessageDialog(R.chitietCV, "Xóa thành viên từ công việc thất bại!");

							}
						}
						if (extra.equals("ADDP")) {
							if (dt[0].equals("SUCC")) {

							} else {
								JOptionPane.showMessageDialog(R.main, "Thêm thành viên vào dự án thất bại!");

							}
						} else if (extra.equals("DELP")) {
							if (dt[0].equals("SUCC")) {

							} else {
								JOptionPane.showMessageDialog(R.main, "Xóa thành viên từ dự án thất bại!");

							}
						}
					} else if (cmd.equals("CMT")) {
						t = 1;
						if (extra.equals("END1")) {
							System.out.println("XXXXXXXXXXXX");
							R.chitietCV.getCmtSuccess();
						}
						if (extra.equals("TEXT")) {
							Comment cmt = new Comment(dt[1], dt[0]);
							cmt.setType("TEXT");
							ChiTietCongViecPanel.listCmt.add(cmt);
							Listen();
						} else if (extra.equals("IMGE")) {
							byte[] command = dt[0].getBytes(Charset.forName("UTF-8"));
							int sizeCMD = command.length + 11;
							ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(mybytearray, sizeCMD,
									mybytearray.length - sizeCMD);
							BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
							Comment cmt = new Comment();
							cmt.setType("IMGE");
							cmt.setNguoiBinh(dt[0]);
							cmt.setPic(new MyIcon(new ImageIcon(bufferedImage)));
							ChiTietCongViecPanel.listCmt.add(cmt);
							Listen();
						} else if (extra.equals("SUCC")) {
							getComment(ChiTietCongViecPanel.cv);
						}

					}
					break;
				}

			} catch (IOException ex) {
				System.out.println("Socket search has closed");
			} catch (ArrayIndexOutOfBoundsException ex) {
			}
		}
	}

	public static void sendAddMemberToJob(final int cvID, final int memID) {
		// Database.updateChiTietCV = 1;
		th = new Thread(new Runnable() {
			int listen = 0;
			String str = "TXTMEMADDJ" + cvID + "|" + memID;
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

	public static void sendDelMemberFromJob(final int cvID, final int memID) {
		// Database.updateChiTietCV = 1;
		th = new Thread(new Runnable() {
			int listen = 0;
			String str = "TXTMEMDELJ" + cvID + "|" + memID;
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

	public static void sendAddMemberToProject(final int projectID, final int memID) {
		th = new Thread(new Runnable() {
			int listen = 0;
			String str = "TXTMEMADDP" + projectID + "|" + memID;
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

	public static void sendDelMemberFromProject(final int projectID, final int memID) {
		th = new Thread(new Runnable() {
			int listen = 0;
			String str = "TXTMEMDELP" + projectID + "|" + memID;
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

	public static void getComment(final CongViec cv) {
		ChiTietCongViecPanel.listCmt.clear();
		th = new Thread(new Runnable() {
			int listen = 0;
			String str = "TXTJOBGETC" + cv.getKey();
			byte[] getCmt = str.getBytes(Charset.forName("UTF-8"));
			byte[] sizeGetCmt = ByteBuffer.allocate(9).putInt(getCmt.length).array();

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				t = 0;
				while (t == 0) {
					try {
						os.write(sizeGetCmt);
						os.write(getCmt);
						os.flush();
						if (listen++ == 0) {
							Listen();
						}
						if (listen == 2) {
							readData.stop();
							JOptionPane.showMessageDialog(R.chitietCV, "Có vấn đề về mạng. Kiểm tra lại");
							th.stop();
						}
						Thread.sleep(3000);
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

	public static void sendNewCmt(final Comment cmt) {
		th = new Thread(new Runnable() {
			int listen = 0;
			String str = "TXTCMTADDC" + cmt.getCV() + "|" + cmt.getTV() + "|" + cmt.getLoiBinh();
			byte[] cmtSend = str.getBytes(Charset.forName("UTF-8"));
			byte[] sizeCmtSend = ByteBuffer.allocate(9).putInt(cmtSend.length).array();

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				t = 0;
				while (t == 0) {
					try {
						os.write(sizeCmtSend);
						os.write(cmtSend);
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

	public static void Listen() {
		readData = new Thread(new ReadData());
		readData.start();
	}
}
