package NJU.HouseWang.nju_eas_server.systemService;

import NJU.HouseWang.nju_eas_server.po.User.GuestPO;

public interface LoginSystemService extends SystemService {
	// 登录
	public void login(GuestPO logger, String ip);

	// 注销
	public void logout(String ip);
}
