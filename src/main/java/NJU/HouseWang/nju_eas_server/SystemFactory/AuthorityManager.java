package NJU.HouseWang.nju_eas_server.SystemFactory;

import NJU.HouseWang.nju_eas_server.po.User.GuestPO;
import NJU.HouseWang.nju_eas_server.po.User.UserPO;

public class AuthorityManager {
	
	public void run() {
	}

	public int checkAuthority(String ip, GuestPO user) {
		return 0;
	}

	public UserPO getUser(String ip) {
		return null;
	}

	public void updateUser(String ip) {
	}

	public void addIp_User(String ip, GuestPO user) {
	}

	public void removeIp_User(String ip) {
	}
}
