package NJU.HouseWang.nju_eas_server.system;

import java.io.IOException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.LogList;
import NJU.HouseWang.nju_eas_server.netService.NetService;
import NJU.HouseWang.nju_eas_server.po.Msg.LogPO;
import NJU.HouseWang.nju_eas_server.systemService.LogSystemService;

public class LogSystem implements LogSystemService {
	NetService ns;
	LogList ll;

	public LogSystem() {
		ll = new LogList();
		ll.init();
	}

	@Override
	public void operate(String uid, String cmd) {

		String[] cmdInfo = cmd.split("；");
		String cmdType = cmdInfo[0] + cmdInfo[1];
		switch (cmdType) {

		case "addlog":
			this.addLog(new LogPO(cmdInfo[2], cmdInfo[3], cmdInfo[4],
					cmdInfo[5]));
			break;
		case "showlog_list":
			this.showLogList(cmdInfo[2]);
			break;
		case "showlog_list_head":
			this.showLogListHead();
			break;
		default:
			break;
		}
	}

	@Override
	public void initNetService(NetService ns) {
		this.ns = ns;
	}

	@Override
	public void addLog(LogPO lp) {
		ll.addLog(lp);
	}

	@Override
	public void showLogList(String conditions) {

		ArrayList<LogPO> list = ll.getLogList(conditions);
		ArrayList<String> logList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			String logInfo = (list.get(i)).toCommand();
			logList.add(logInfo);
		}
		try {
			ns.sendList(logList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void showLogListHead() {

		try {
			ns.sendFeedback(ll.getListHead());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
