package NJU.HouseWang.nju_eas_server.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public void start() throws IOException {
		ServerSocket server = new ServerSocket(9001);

		while (true) {
			Socket socket = server.accept();
			System.out.println("#" + socket.getInetAddress() + "\t Connected!");
			invoke(socket);
		}
	}

	private void invoke(final Socket socket) throws IOException {
		new Thread(new Runnable() {
			public void run() {
				SocketThread st = new SocketThread(socket);
				try {
					String msg = st.receiveCommand();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
