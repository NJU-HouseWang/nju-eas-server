package NJU.HouseWang.nju_eas_server.SystemFactory;

import NJU.HouseWang.nju_eas_server.system.LoginSystem;
import NJU.HouseWang.nju_eas_server.systemService.SystemService;

public class SystemFactory {

	public static SystemService create(String cmd) {
		String[] cmdtmp = cmd.split("；");
		String action = cmdtmp[0];
		String aim = cmdtmp[1];
		switch (action) {
		case "login":
		case "logout":
			return (new LoginSystem());
		case "show":
		case "add":
		case "edit":
		case "del":
		case "import":
		case "export":
		case "publish":
		case "select":
		case "byElect":
		case "set":
		case "audit":
		case "":
		}
		return null;
	}
	
	public static void main(String[] args){
		create("login；Student；121250157；bilicrazy123").operate("121250157","login；Student；121250157；bilicrazy123");
	}
}
