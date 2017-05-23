package edu.ttl.constant;

import java.io.IOException;
import java.io.InputStream;
//import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
//import java.nio.ByteBuffer;
//import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import edu.ttl.ui.ChiTietCongViecPanel;

public class FileStream {
	private static Thread readData;
	private static Socket socket = null;
	private static OutputStream os;
	private static InputStream is;

	public static void initSocket() {
		try {
				socket = new Socket(R.IP_sever, R.port_sever);
				os = socket.getOutputStream();
				is = socket.getInputStream();
				System.out.println("FileStream####: tạo socket");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			socket = null;
			e.printStackTrace();
		}
	}
	
	public static void upFile(final byte[] size, final byte[] data) {
		try {
			os.write(size);
			os.write(data);
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
				System.out.println("FileStream#: " + format + cmd + extra + data);
				switch (format) {
				case "TXT":
					switch (cmd) {
					case "CMT":
						if (extra.equals("IMGE")) {
							if (dt[0].equals("SUCC")) {
								Search.getComment(ChiTietCongViecPanel.cv);
							} else {
								JOptionPane.showMessageDialog(null, "Up ảnh thất bại");
							}
						}
						break;

					default:
						break;
					}
					break;
				case "IMG":
					switch (cmd) {
					case "AVA":
						if (extra.equals("UPDA")) {
							if (dt[0].equals("SUCC")) {
								Database.getAvatar(String.valueOf(R.user.getUserID()));
							} else {
								JOptionPane.showMessageDialog(null, "Cập nhật avatar thất bại");
							}
						}
						break;

					default:
						break;
					}
					break;
				}

			} catch (IOException ex) {
				System.out.println("Socket filestream has closed");
			} catch (ArrayIndexOutOfBoundsException ex) {
			}
		}
	}

	public static void Listen() {
		readData = new Thread(new ReadData());
		readData.start();
	}
}
