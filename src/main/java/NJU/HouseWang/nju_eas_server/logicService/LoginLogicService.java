package NJU.HouseWang.nju_eas_server.logicService;

import NJU.HouseWang.nju_eas_server.po.User.GuestPO;

/**
 * 登录逻辑类
 * @author 教化场
 * @version 2013-11-9
 */
public interface LoginLogicService extends LogicService {
	/**
	 * 登录
	 * @param logger 登录用户
	 * @param ip ip地址
	 * @return 反馈
	 */
	public String login(GuestPO logger, String ip);

	/**
	 * 注销
	 * @param ip ip地址
	 * @return 反馈
	 */
	public String logout(String ip);
}
