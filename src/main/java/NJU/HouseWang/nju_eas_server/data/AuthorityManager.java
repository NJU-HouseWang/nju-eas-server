package NJU.HouseWang.nju_eas_server.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class AuthorityManager {
	private static AuthorityManager am = null;

	private HashMap<String, LoginInfo> l = new HashMap<String, LoginInfo>();

	private AuthorityManager() {

	}

	public static AuthorityManager getInstance() {
		if (am == null) {
			am = new AuthorityManager();
		}
		return am;
	}
	
	public void shutdown() {
		am = null;
		l.clear();
	}

	public void run() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				ArrayList<LoginInfo> list = new ArrayList<LoginInfo>(l.values());
				for (LoginInfo lInfo : list) {
					long now = new Date().getTime();
					long loginTime = lInfo.d.getTime();
					if (((now - loginTime) / 1000 / 60) > 10) {
						l.remove(lInfo.ip);
					}
				}
			}
		});
		t.start();
	}

	public boolean containsIP(String ip) {
		return l.containsKey(ip);
	}

	public boolean containsGuest(String uid) {
		return l.containsValue(uid);
	}

	public String getGuest(String ip) {
		if (l.get(ip) == null) {
			return null;
		}
		return l.get(ip).uid;
	}

	public void updateGuest(String ip) {
		LoginInfo lInfo = l.get(ip);
		lInfo.d = new Date();
		l.put(ip, lInfo);
	}

	public void addGuest(String ip, String uid) {
		l.put(ip, new LoginInfo(ip, uid, new Date()));
	}

	public void removeGuest(String ip) {
		l.remove(ip);
	}
}

class LoginInfo {
	String ip;
	String uid;
	Date d;

	public LoginInfo(String ip, String uid, Date d) {
		this.ip = ip;
		this.uid = uid;
		this.d = d;
	}
}
