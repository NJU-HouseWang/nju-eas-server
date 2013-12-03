package NJU.HouseWang.nju_eas_server.logicService;

import NJU.HouseWang.nju_eas_server.po.User.GuestPO;

public interface LoginLogicService extends LogicService {
	// 登录
	public String login(GuestPO logger, String ip);

	// 注销
	public String logout(String ip);
}
