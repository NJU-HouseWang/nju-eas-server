package NJU.HouseWang.nju_eas_server.net;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.netService.NetService;

public class SocketThread implements NetService {
	private Socket socket = null;
	private DataInputStream fis = null;
	private DataOutputStream fos = null;
	private DataInputStream in = null;
	private DataOutputStream out = null;

	public SocketThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void sendFeedback(String feedback) throws IOException {
		try {
			out = new DataOutputStream(socket.getOutputStream());
			System.out.println("Send Feedback:" + feedback);
			out.writeUTF(feedback);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			if (out != null) {
				out.close();
			}
			throw e;
		}
	}

	@Override
	public String receiveCommand() throws IOException {
		in = new DataInputStream(new BufferedInputStream(
				socket.getInputStream()));
		String netCmd = null;
		while (true) {
			if ((netCmd = in.readUTF()) != null) {
				System.out.println("Receive Connamd: " + netCmd);
				break;
			}
		}
		return netCmd;
	}

	public void sendList(ArrayList<String> list) throws IOException {
		try {
			out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF("listStart");
			for (String str : list) {
				out.writeUTF(str);
				System.out.println("Send List Item :" + str);
			}
			out.writeUTF("listEnd");
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			if (out != null) {
				out.close();
			}
			throw e;
		}
	}

	public ArrayList<String> receiveList() throws IOException {
		in = new DataInputStream(new BufferedInputStream(
				socket.getInputStream()));
		ArrayList<String> list = new ArrayList<String>();
		String line;
		while (!in.readUTF().equals("listStart")) {
			System.out.println("Waiting for Client......");
		}
		while (!(line = in.readUTF()).trim().equals("listEnd")) {
			System.out.println("Receive List Item :" + line);
			list.add(line);
		}
		return list;
	}

	@Override
	public void sendFile(File file) throws IOException {
		try {
			fis = new DataInputStream(new BufferedInputStream(
					new FileInputStream(file)));
			out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(file.getName());
			out.flush();
			out.writeLong(file.length());
			out.flush();

			int bufferSize = 8192;
			byte[] buf = new byte[bufferSize];

			while (true) {
				int readState = 0;
				if (fis != null) {
					readState = fis.read(buf);
				}

				if (readState == -1) {
					break;
				}
				out.write(buf, 0, readState);
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			if (out != null) {
				fis.close();
				out.close();
			}
			throw e;
		}
	}

	@Override
	public void receiveFile(String fileName) throws IOException {
		try {
			// 本地保存路径，文件名会自动从服务器端继承而来。
			String savePath = "E:\\";
			int bufferSize = 8192;
			byte[] buf = new byte[bufferSize];
			int passedlen = 0;
			long len = 0;

			savePath += in.readUTF();
			fos = new DataOutputStream(new BufferedOutputStream(
					new BufferedOutputStream(new FileOutputStream(savePath))));
			len = in.readLong();

			System.out.println("文件的长度为:" + len + "\n");
			System.out.println("开始接收文件!" + "\n");

			while (true) {
				int read = 0;
				if (in != null) {
					read = in.read(buf);
				}
				passedlen += read;
				if (read == -1) {
					break;
				}
				// 下面进度条本为图形界面的prograssBar做的，这里如果是打文件，可能会重复打印出一些相同的百分比
				System.out.println("文件接收了" + (passedlen * 100 / len) + "%\n");
				fos.write(buf, 0, read);
			}
			System.out.println("接收完成，文件存为" + savePath + "\n");

			fos.close();
		} catch (Exception e) {
			System.out.println("接收消息错误" + "\n");
			if (fos != null) {
				fos.close();
			}
			return;
		}
	}

	@Override
	public String getIp() throws IOException {
		return socket.getInetAddress().toString();
	}

}
