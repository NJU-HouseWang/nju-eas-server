package NJU.HouseWang.nju_eas_server.system;

import java.io.IOException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.LogList;
import NJU.HouseWang.nju_eas_server.dataStub.LogListStub;
import NJU.HouseWang.nju_eas_server.net.ServerStub;
import NJU.HouseWang.nju_eas_server.netService.NetService;
import NJU.HouseWang.nju_eas_server.po.Msg.LogPO;
import NJU.HouseWang.nju_eas_server.systemService.LogSystemService;



public class LogSystem implements LogSystemService {
	NetService ns;
	LogList ll;
	
	public LogSystem() {
		// TODO Auto-generated constructor stub
		ll = new LogList();
		ll.init();
	}
	@Override
	public void operate(String uid, String cmd) {
		// TODO Auto-generated method stub
		String[] cmdInfo = cmd.split("；");
		String cmdType = cmdInfo[0] + cmdInfo[1];
		switch (cmdType) {
		
		case "addlog":
			this.addLog(new LogPO(cmdInfo[2],cmdInfo[3],cmdInfo[4],cmdInfo[5]));
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
		// TODO Auto-generated method stub
		this.ns = ns;
	}

	@Override
	public void addLog(LogPO lp) {
		// TODO Auto-generated method stub
		try {
			ns.sendFeedback(ll.addLog(lp).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void showLogList(String conditions) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		try {
			ns.sendFeedback(ll.getListHead());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
