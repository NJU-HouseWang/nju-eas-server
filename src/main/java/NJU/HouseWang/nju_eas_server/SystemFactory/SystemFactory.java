package NJU.HouseWang.nju_eas_server.SystemFactory;

import NJU.HouseWang.nju_eas_server.data.Database;
import NJU.HouseWang.nju_eas_server.systemService.SystemService;

public class SystemFactory {
	private Database db = new Database();
	private AuthorityManager am = new AuthorityManager();
	
	public void init() {
		db.init();
	}
	
	public SystemService create(String s) {
		return null;
	}
}
