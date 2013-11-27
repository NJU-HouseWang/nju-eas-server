package NJU.HouseWang.nju_eas_server.system;

import java.io.IOException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.EduFramework;
import NJU.HouseWang.nju_eas_server.netService.NetService;
import NJU.HouseWang.nju_eas_server.po.Edu.EduFrameworkItemPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_server.systemService.EduFrameworkSystemService;

public class EduFrameworkSystem implements EduFrameworkSystemService {
	EduFramework ef;
	NetService ns;

	public EduFrameworkSystem() {
		ef = new EduFramework();
		ef.init();

	}

	@Override
	public void operate(String uid, String cmd) {
		// TODO Auto-generated method stub
		String[] cmdInfo = cmd.split("；");
		String cmdType = cmdInfo[0] + cmdInfo[1];
		switch (cmdType) {

		case "showeduframework":
			this.showEduFramework();
			break;
		case "addeduframework":
			this.addEduFramework();
			break;
		case "deleduframwwork":
			this.delEduFramework();
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
	public void addEduFramework() {
		// TODO Auto-generated method stub
		try {
			ArrayList<String> list = ns.receiveList();
			for (int i = 0; i < list.size(); i++) {
				EduFrameworkItemPO EduFrameworkItem = this
						.stringToEduFrameworkItemPO(list.get(i));
				ef.addEduFrameworkItem(EduFrameworkItem);
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		try {
			ns.sendFeedback(Feedback.OPERATION_SUCCEED.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void delEduFramework() {
		// TODO Auto-generated method stub
		try {
			ns.sendFeedback(ef.delEduFramework().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void showEduFramework() {
		// TODO Auto-generated method stub
		ArrayList<EduFrameworkItemPO> el = ef.getEduFramework();
		ArrayList<String> feedback = new ArrayList<String>();
		for(int i = 0;i<el.size();i++){
			feedback.add(el.get(i).toCommand());
		}
		try {
			ns.sendList(feedback);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public EduFrameworkItemPO stringToEduFrameworkItemPO(String str) {
		String[] info = str.split("；");
		EduFrameworkItemPO ep = new EduFrameworkItemPO(info[0], info[1],
				info[2], info[3], info[4], info[5], info[6], info[7], info[8],
				info[9], info[10], info[11], info[12], info[13],info[14]);
		return ep;
	}

}
