package edu.ttl.constant;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.ttl.object.Notification;

public class NotificationStream {

	private static Thread readData;
	private static Socket socket = null;
	private static OutputStream os;
	private static InputStream is;
	private static int count = 0;
	private static int t;
	public static Thread th;

	public static void initSocket() {
		try {
			socket = new Socket(R.IP_sever, R.port_sever);
			os = socket.getOutputStream();
			is = socket.getInputStream();
			System.out.println("NotiStream####: tạo socket");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			socket = null;
			e.printStackTrace();
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
				System.out.println("NotiStream#: " + format + cmd + extra + data);
				ArrayList<Notification> list = new ArrayList<>();
				switch (format) {
				case "TXT":
					if (cmd.equals("MEM")) {
						if (extra.equals("GETN")) {
							count = 0;
							int tmp = 0;
							for (int i = 0; i < dt.length; i += 2) {
								if (!dt[i + 1].equals("null")) {
									tmp = Integer.parseInt(dt[i + 1]);
								}
								if (tmp == 0)
									count++;
								Notification no = new Notification(dt[i], tmp);
								list.add(no);
							}
							R.listNotification = list;
							R.main.setNotificationButton(count);
						}
					}
					break;
				}
			} catch (IOException ex) {
				System.out.println("Socket filestream has closed");
			} catch (ArrayIndexOutOfBoundsException ex) {
			}

		}
	}

	public static void getNotification(final String userID) {
		th = new Thread(new Runnable() {
			String str = "TXTMEMGETN" + userID;
			byte[] updaState = str.getBytes(Charset.forName("UTF-8"));
			byte[] sizeupdaState = ByteBuffer.allocate(9).putInt(updaState.length).array();

			@Override
			public void run() {
				t = 0;
				while (t == 0) {
					try {
						os.write(sizeupdaState);
						os.write(updaState);
						os.flush();
						Listen();
						Thread.sleep(15 * 1000);
						System.out.println("ĐÃ GỬI LẤY THÔNG BÁO SAU 30S");
						t = 0;
					} catch (IOException ex) {
						Logger.getLogger(R.main.getName()).log(Level.SEVERE, null, ex);
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
