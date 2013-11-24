package NJU.HouseWang.nju_eas_server.system;

import java.io.IOException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.StatusList;
import NJU.HouseWang.nju_eas_server.netService.NetService;
import NJU.HouseWang.nju_eas_server.po.Edu.StatusPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_server.systemService.CourseSelectionSystemService;

public class CourseSelectionSystem implements CourseSelectionSystemService {
	private NetService ns;
	private StatusList sl;
	public CourseSelectionSystem() {
		// TODO Auto-generated constructor stub
		sl = new StatusList();
		sl.init();
	}
	@Override
	public void operate(String uid, String cmd) {
		// TODO Auto-generated method stub
		String[] cmdInfo = cmd.split("；");
		String cmdType = cmdInfo[0] + cmdInfo[1];
		switch (cmdType) {
		case "showstatus":
			this.showStatus(cmdInfo[2]);
			break;
		case "editstatus":
			StatusPO sp   = new StatusPO();
			sp.setFunction(cmdInfo[2]);
			sp.setIsopen(cmdInfo[3]);
			this.editStatus(sp);
			break;
		case "showstatus_list":
			this.showStatusList();
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
	public void showStatus(String function) {
		// TODO Auto-generated method stub
		StatusPO sp = sl.getStatus(function);
		try {
			ns.sendFeedback(sp.getIsopenToString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void editStatus(StatusPO sp) {
		// TODO Auto-generated method stub
		sl.updateStatus(sp);
		try {
			ns.sendFeedback(Feedback.OPERATION_SUCCEED.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void showStatusList() {
		// TODO Auto-generated method stub
		ArrayList<StatusPO> list = sl.getStatusList();
		ArrayList<String> statusList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			String statusInfo = (list.get(i)).toCommand();
			statusList.add(statusInfo);
		}
		try {
			ns.sendList(statusList);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
