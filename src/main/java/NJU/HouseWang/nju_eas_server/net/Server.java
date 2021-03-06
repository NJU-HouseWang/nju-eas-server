package NJU.HouseWang.nju_eas_server.net;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

import NJU.HouseWang.nju_eas_server.SystemFactory.SystemFactory;
import NJU.HouseWang.nju_eas_server.data.AuthorityManager;
import NJU.HouseWang.nju_eas_server.logic.LogLogic;
import NJU.HouseWang.nju_eas_server.logicService.LogicService;
import NJU.HouseWang.nju_eas_server.po.Msg.LogPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public class Server {
	private ServerSocket server = null;
	private ArrayList<Socket> list = new ArrayList<Socket>();

	public void start() throws IOException {
		System.out.println("NJU_EAS_Server is running now...");
		server = new ServerSocket(9100);

		while (true) {
			Socket socket = server.accept();
			list.add(socket);
			System.out.println("#" + socket.getInetAddress() + "\t Connected!");
			invoke(socket);
		}
	}

	public void shutdown() {
		if (server != null) {
			try {
				for (Socket s : list) {
					s.close();
				}
				list.clear();
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void invoke(final Socket socket) throws IOException {
		new Thread(new Runnable() {
			public void run() {
				SocketThread st = new SocketThread(socket);
				String ip = null;
				AuthorityManager am = null;
				try {
					// 读取命令
					String cmd = st.receiveCommand();
					// 创建逻辑
					LogicService ss = SystemFactory.create(cmd);
					if (ss == null) {
						st.sendFeedback(Feedback.COMMAND_ERROR.toString());
						System.err.println("不能识别的客户端命令");
						return;
					}
					// 得到IP
					ip = st.getIp();
					// 得到权限管理器
					am = AuthorityManager.getInstance();
					// 从权限管理器中取得用户，如果无则为null
					String uid = am.getGuest(ip);
					// 逻辑层执行指令，取得反馈
					Object feedback = ss.operate(cmd);
					// 根据反馈的类型向客户端发送反馈结果

					if (feedback instanceof String) {
						if (((String) feedback).contains("ip")) {
							ss = SystemFactory.create(cmd);
							cmd += "；" + ip + "；ok";
							feedback = ss.operate(cmd);
						}
						if (feedback instanceof String) {
							if (((String) feedback).startsWith("file；")) {
								ss = SystemFactory.create(cmd);
								File file = st.receiveFile(((String) feedback)
										.split("；")[1]);
								if (file != null) {
									cmd += "；" + file.getPath() + "；file_ok";
									feedback = ss.operate(cmd);
								} else {
									feedback = Feedback.OPERATION_FAIL
											.toString();
								}
							}
						}
						if (feedback instanceof String) {
							if (((String) feedback).equals("list")) {
								ss = SystemFactory.create(cmd);
								ArrayList<String> list = null;
								list = st.receiveList();
								if (list != null) {
									feedback = ss.operate(cmd, list);
								} else {
									feedback = Feedback.OPERATION_FAIL
											.toString();
								}
							}
						}

						if (feedback instanceof String) {
							st.sendFeedback((String) feedback);
						} else if (feedback instanceof ArrayList<?>) {
							st.sendList((ArrayList<?>) feedback);
						} else if (feedback instanceof File) {
							st.sendFile((File) feedback);
						}
					} else if (feedback instanceof ArrayList<?>) {
						st.sendList((ArrayList<?>) feedback);
					} else if (feedback instanceof File) {
						st.sendFile((File) feedback);
					}
					// 日志系统记录操作日志
					LogLogic ls = new LogLogic();
					ls.addLog(new LogPO(uid, ip, new Date().toString(), cmd));
				} catch (IOException e) { // 如果用户异常离线
					// 得到权限管理器
					am = AuthorityManager.getInstance();
					// 删除用户的IP
					am.removeGuest(ip);
				}
			}
		}).start();
	}
}
