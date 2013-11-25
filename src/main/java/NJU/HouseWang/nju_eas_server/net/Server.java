package NJU.HouseWang.nju_eas_server.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import NJU.HouseWang.nju_eas_server.SystemFactory.AuthorityManager;
import NJU.HouseWang.nju_eas_server.SystemFactory.SystemFactory;
import NJU.HouseWang.nju_eas_server.po.Msg.LogPO;
import NJU.HouseWang.nju_eas_server.system.LogSystem;
import NJU.HouseWang.nju_eas_server.systemService.SystemService;

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
					String cmd = st.receiveCommand();
					SystemService ss = SystemFactory.create(cmd);
					ss.initNetService(st);
					AuthorityManager am = AuthorityManager.getInstance();
					String userName = am.getGuest(st.getIp());
					ss.operate(userName, cmd);
					LogSystem ls = new LogSystem();
					ls.addLog(new LogPO(userName, st.getIp(), new Date()
							.toString(), cmd));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
