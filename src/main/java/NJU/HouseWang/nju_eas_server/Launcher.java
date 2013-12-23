package NJU.HouseWang.nju_eas_server;

import java.io.IOException;

import NJU.HouseWang.nju_eas_server.data.AuthorityManager;
import NJU.HouseWang.nju_eas_server.net.Server;

/**
 * 服务器程序入口
 * 
 * @author Xin
 * @version 2013-11-20
 */
public class Launcher {

	/**
	 * 主方法
	 * 
	 * @param args
	 *            运行时参数
	 */
	public static void main(String[] args) {
//		ServerUI serverUI = new ServerUI();
		Server server = new Server();
		AuthorityManager am = AuthorityManager.getInstance();
		try {
			am.run();
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
