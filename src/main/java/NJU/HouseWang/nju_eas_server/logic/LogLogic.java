package NJU.HouseWang.nju_eas_server.logic;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.AuthorityManager;
import NJU.HouseWang.nju_eas_server.data.LogList;
import NJU.HouseWang.nju_eas_server.logicService.LogLogicService;
import NJU.HouseWang.nju_eas_server.po.Msg.LogPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;
/**
 * 日志逻辑类
 * @author 教化场
 * @version 2013-11-10
 */
public class LogLogic implements LogLogicService {
	private LogList ll;
	private AuthorityManager am;

	public LogLogic() {
		ll = this.initLogList();
		am = this.initAuthorityManager();
	}

	public LogList initLogList() {
		LogList l = new LogList();
		l.init();
		return l;
	}

	public AuthorityManager initAuthorityManager() {
		AuthorityManager a = AuthorityManager.getInstance();
		return a;
	}

	@Override
	public Object operate(String cmd) {
		Object feedback = null;
		String[] cmdInfo = cmd.split("；");
		String uid = am.getGuest(cmdInfo[cmdInfo.length - 1]);
		String cmdType = cmdInfo[0] + cmdInfo[1];
		switch (cmdType) {

		case "addlog":
			this.addLog(new LogPO(cmdInfo[2], cmdInfo[3], cmdInfo[4],
					cmdInfo[5]));
			break;
		case "showlog_list":
			feedback = this.showLogList();
			break;
		case "showlog_list_head":
			feedback = this.showLogListHead();
			break;
		case "emptylog_list":
			feedback = this.emptyLogList();
			break;
		default:
			break;
		}
		ll.finish();
		return feedback;
	}

	@Override
	public Object operate(String cmd, ArrayList<String> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * 新增一条日志
	 * @param lp 登录PO
	 */
	public void addLog(LogPO lp) {
		String cmd = lp.getContent();
		if (!cmd.startsWith("show")) {
			ll.addLog(lp);
		}

	}

	@Override
	/**
	 * 显示日志列表
	 * @return 日志列表
	 */
	public ArrayList<String> showLogList() {
		ArrayList<LogPO> list = ll.getLogList();
		ArrayList<String> logList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			String logInfo = (list.get(i)).toCommand();
			logList.add(logInfo);
		}
		return logList;
	}

	@Override
	/**
	 * 显示日志列表的表头
	 * @return 表头
	 */
	public String showLogListHead() {
		try {
			return (ll.getListHead());
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	/**
	 * 清空日志列表
	 * @return 反馈
	 */
	public String emptyLogList() {
		try{
			ll.emptyLogList();
			return Feedback.OPERATION_SUCCEED.toString();
		}catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

}
