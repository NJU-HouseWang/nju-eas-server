package NJU.HouseWang.nju_eas_server.launcher;

import NJU.HouseWang.nju_eas_server.data.Database;

public class ServerLauncher {
	public static Database db = new Database();

	public static void main(String[] args) {
		db.init();
	}
}
