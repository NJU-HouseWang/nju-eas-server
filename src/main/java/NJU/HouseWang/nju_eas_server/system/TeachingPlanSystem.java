package NJU.HouseWang.nju_eas_server.system;

import java.io.File;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.dataService.DataService;
import NJU.HouseWang.nju_eas_server.netService.NetService;
import NJU.HouseWang.nju_eas_server.po.Edu.CoursePO;
import NJU.HouseWang.nju_eas_server.systemService.TeachingPlanSystemService;

public class TeachingPlanSystem implements TeachingPlanSystemService {
	private NetService ns;
	private DataService ds;

	@Override
	public void showFrameWork() {
		// TODO Auto-generated method stub

	}

	@Override
	public void editFrameWork() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showTeachingPlan(String dept) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addTeachingPlan() {
		// TODO Auto-generated method stub

	}

	@Override
	public void auditTeachingPlan(String dept, boolean audit) {
		// TODO Auto-generated method stub

	}

	@Override
	public void operate(String uid, String cmd) {
		// TODO Auto-generated method stub
		String[] cmdInfo = cmd.split(";");
		String cmdType = cmdInfo[0] + cmdInfo[1];
		switch (cmdType) {
		case "showFramework":
			this.showFrameWork();
			break;
		case "editFramework":
			this.editFrameWork();
			break;
		case "showTeachingPlan":
			this.showTeachingPlan(cmdInfo[2]);
			break;
		case "addTeachingPlan":
			this.addTeachingPlan();
			break;
		case "auditTeachingPlan":
			boolean audit = false;
			if (cmdInfo[3].equals("true")) {
				audit = true;
			}
			this.auditTeachingPlan(cmdInfo[2], audit);
			break;
		default:
			break;

		}
	}

}
