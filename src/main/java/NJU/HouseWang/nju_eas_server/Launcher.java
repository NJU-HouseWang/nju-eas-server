package NJU.HouseWang.nju_eas_server;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;

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
		Server server = new Server();
		AuthorityManager am = AuthorityManager.getInstance();
		try {
			JFrame frame = new JFrame();
			JLabel lb = new JLabel("EAS Server is running now...");
			frame.add(lb, BorderLayout.CENTER);
			frame.pack();
			frame.setLocation(400, 300);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			am.run();
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
