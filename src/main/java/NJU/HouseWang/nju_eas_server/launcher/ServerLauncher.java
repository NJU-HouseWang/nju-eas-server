/*
 * 文件名：ServerLauncher.java
 * 创建者：王鑫
 * 创建时间：2013-11-20
 * 最后修改：
 * 修改时间：
 * 版本：0.1
 */
package NJU.HouseWang.nju_eas_server.launcher;

import java.io.IOException;

import NJU.HouseWang.nju_eas_server.net.Server;

public class ServerLauncher {

	public static void main(String[] args) {
		Server server = new Server();
		try {
			server.init();
			System.out.println("EAS Server is running now...");
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
