package NJU.HouseWang.nju_eas_server.logic;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.AuthorityManager;
import NJU.HouseWang.nju_eas_server.data.LoginList;
import NJU.HouseWang.nju_eas_server.logicService.LoginLogicService;
import NJU.HouseWang.nju_eas_server.po.User.GuestPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_server.systemMessage.UserType;
/**
 * 登录逻辑类
 * @author 教化场
 * @version 2013-11-9
 */
public class LoginLogic implements LoginLogicService {
	private LoginList ll;
	private AuthorityManager am;

	public LoginLogic() {
		ll = this.initLoginList();
		am = this.initAuthorityManager();
	}

	public LoginList initLoginList() {
		LoginList l = new LoginList();
		l.init();
		return l;
	}

	public AuthorityManager initAuthorityManager() {
		AuthorityManager a = AuthorityManager.getInstance();
		return a;
	}

	@Override
	// 命令格式：login/logout;(用户类型);(用户名);(密码)
	public Object operate(String cmd) {
		Object feedback = null;
		String[] cmdInfo = cmd.split("；");
		if (cmd.startsWith("login")) {
			if (cmd.endsWith("；ok")) {
				UserType ut = UserType.valueOf(cmdInfo[1]);
				GuestPO newGuest = new GuestPO(cmdInfo[2], ut, cmdInfo[3]);
				feedback = this.login(newGuest, cmdInfo[4]);
			} else {
				feedback = "ip";
			}
		} else {
			if (cmd.endsWith("；ok")) {
				feedback = this.logout(cmdInfo[1]);
			} else {
				feedback = "ip";
			}
		}
		ll.finish();
		return feedback;

	}

	@Override
	public Object operate(String cmd, ArrayList<String> list) {
		return null;
	}

	@Override
	/**
	 * 登录
	 * @param logger 登录用户
	 * @param ip ip地址
	 * @return 反馈
	 */
	public String login(GuestPO logger, String ip) {
		try {
			String id = logger.getId();
			String pw = logger.getPassword();
			UserType ut = logger.getType();
			GuestPO newGuest = new GuestPO(id, ut, pw);
			if (am.containsIP(ip)) {
				return (Feedback.IP_ALREADY_EXISTED.toString());
			} else if (am.containsGuest(id)) {
				return (Feedback.ID_ALREADY_EXISTED.toString());
			} else {
				GuestPO g = (GuestPO) ll.getLoginer(id);
				if (newGuest.equals(g)) {
					am.addGuest(ip, id);
					return (Feedback.OPERATION_SUCCEED.toString());
				} else {
					return (Feedback.ID_PW_NOT_FOUND.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	/**
	 * 注销
	 * @param ip ip地址
	 * @return 反馈
	 */
	public String logout(String ip) {
		try {
			am.removeGuest(ip);
			return (Feedback.OPERATION_SUCCEED.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

}
