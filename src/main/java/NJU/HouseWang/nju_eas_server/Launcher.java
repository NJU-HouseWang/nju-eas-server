/*
 * 文件名：ServerLauncher.java
 * 创建者：王鑫
 * 创建时间：2013-11-20
 * 最后修改：
 * 修改时间：
 * 版本：0.1
 */
package NJU.HouseWang.nju_eas_server;

import java.io.IOException;

import NJU.HouseWang.nju_eas_server.data.AuthorityManager;
import NJU.HouseWang.nju_eas_server.net.Server;

public class Launcher {

	public static void main(String[] args) {
		Server server = new Server();
		AuthorityManager am = AuthorityManager.getInstance();
		try {
			System.out.println("EAS Server is running now...");
			am.run();
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
