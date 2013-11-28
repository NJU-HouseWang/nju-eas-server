package NJU.HouseWang.nju_eas_server.system;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.EduFramework;
import NJU.HouseWang.nju_eas_server.data.TeachingPlan;
import NJU.HouseWang.nju_eas_server.data.TeachingPlanList;
import NJU.HouseWang.nju_eas_server.netService.NetService;
import NJU.HouseWang.nju_eas_server.po.Edu.EduFrameworkItemPO;
import NJU.HouseWang.nju_eas_server.po.Edu.StatusPO;
import NJU.HouseWang.nju_eas_server.po.Edu.TeachingPlanItemPO;
import NJU.HouseWang.nju_eas_server.po.Edu.TeachingPlanPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_server.systemService.TeachingPlanSystemService;

public class TeachingPlanSystem implements TeachingPlanSystemService {
	NetService ns;
	TeachingPlan tp;
	TeachingPlanList tl;
	EduFramework ef;

	public TeachingPlanSystem() {
		tp = new TeachingPlan();
		tl = new TeachingPlanList();
		ef = new EduFramework();
		tp.init();
		tl.init();
		ef.init();
	}

	@Override
	public void operate(String uid, String cmd) {
		// TODO Auto-generated method stub
		String[] cmdInfo = cmd.split("；");
		String cmdType = cmdInfo[0] + cmdInfo[1];
		switch (cmdType) {
		case "showteachingplan":
			this.showTeachingPlan(cmdInfo[2]);
			break;
		case "showteachingplanlist":
			this.showTeachingPlanList();
			break;
		case "addteachingplan":
			this.addTeachingPlan(cmdInfo[2]);
			break;
		case "editteachingplan":
			this.editTeachingPlan(cmdInfo[2]);
			break;
		case "delteachingplan":
			this.delTeachingPlan(cmdInfo[2]);
			break;
		case "auditteachingplan":
			this.auditTeachingPlan(cmdInfo[2], Integer.parseInt(cmdInfo[3]));
			break;
		case "uploadteachingplanfile":
			this.uploadTeachingPlanFile(cmdInfo[2], cmdInfo[3]);
			break;
		case "downloadteachingplanfile":
			this.downloadTeachingPlanFile(cmdInfo[2], cmdInfo[3]);
			break;
		case "showteachingplan_head":
			this.showTeachingPlanHead();
			break;
		case "showteachingplanlist_head":
			this.showTeachingPlanListHead();
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
	public void showTeachingPlan(String dept) {
		// TODO Auto-generated method stub
		ArrayList<TeachingPlanItemPO> tl = tp.getTeachingPlan(dept);
		ArrayList<String> feedback = new ArrayList<String>();
		for (int i = 0; i < tl.size(); i++) {
			feedback.add(tl.get(i).toCommand());
		}
		try {
			ns.sendList(feedback);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void showTeachingPlanList() {
		// TODO Auto-generated method stub
		ArrayList<TeachingPlanPO> t = tl.getTeachingPlanList();
		ArrayList<String> feedback = new ArrayList<String>();
		for (int i = 0; i < t.size(); i++) {
			feedback.add(t.get(i).toCommand());
		}
		try {
			ns.sendList(feedback);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void addTeachingPlan(String dept) {
		// TODO Auto-generated method stub
		TeachingPlanPO tpp = tl.getTeachingPlan(dept);
		if (!tpp.isCommitted()) {
			try {
				ArrayList<String> list = ns.receiveList();
				ArrayList<TeachingPlanItemPO> tpl = new ArrayList<TeachingPlanItemPO>();
				boolean isValid = this.judgeTeachingPlan(tpl);
				if (isValid) {
					for (int i = 0; i < tpl.size(); i++) {
						tp.addTeachingPlanItem(dept, tpl.get(i));
					}
					ns.sendFeedback(Feedback.OPERATION_SUCCEED.toString());
				} else {
					ns.sendFeedback(Feedback.FORMAT_ERROR.toString());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				ns.sendFeedback(Feedback.DATA_ALREADY_EXISTED.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void editTeachingPlan(String dept) {
		// TODO Auto-generated method stub
		this.delTeachingPlan(dept);
		this.addTeachingPlan(dept);
	}

	@Override
	public void delTeachingPlan(String dept) {
		// TODO Auto-generated method stub
		tp.delTeachingPlan(dept);
		TeachingPlanPO tpp = tl.getTeachingPlan(dept);
		tpp.setCommitted(false);
		tpp.setStatus(0);
		tpp.getTpFile().delete();
		tpp.setTpFile(null);
		try {
			ns.sendFeedback(Feedback.OPERATION_SUCCEED.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void auditTeachingPlan(String dept, int status) {
		// TODO Auto-generated method stub
		TeachingPlanPO tpp = tl.getTeachingPlan(dept);
		if(tpp.getStatus()==0){
			tpp.setStatus(status);
			try {
				ns.sendFeedback(Feedback.OPERATION_SUCCEED.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}  else{
			try {
				ns.sendFeedback(Feedback.AUDIT_REPEATED.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void uploadTeachingPlanFile(String dept, String filePath) {
		// TODO Auto-generated method stub
		TeachingPlanPO tpp = tl.getTeachingPlan(dept);
		if(tpp.getTpFile()==null){
			try {
				ns.receiveFile(filePath);
				tpp.setTpFile(new File(filePath));
				ns.sendFeedback(Feedback.OPERATION_SUCCEED.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else{
			try {
				ns.sendFeedback(Feedback.DATA_ALREADY_EXISTED.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void downloadTeachingPlanFile(String dept, String filePath) {
		// TODO Auto-generated method stub
		TeachingPlanPO tpp = tl.getTeachingPlan(dept);
		if(tpp.getTpFile()!=null){
			try {
				ns.sendFile(new File(filePath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else{
			try {
				ns.sendFeedback(Feedback.DATA_NOT_FOUND.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean judgeTeachingPlan(ArrayList<TeachingPlanItemPO> teachingPlan) {
		// TODO Auto-generated method stub
		boolean isValid = true;
		ArrayList<EduFrameworkItemPO> ep = ef.getEduFramework();
		return isValid;
	}

	public TeachingPlanItemPO stringToTeachingPlanItemPO(String str) {
		String[] info = str.split("；");
		TeachingPlanItemPO tp = new TeachingPlanItemPO(info[0], info[1],
				info[2], info[3], info[4], info[5], info[6], info[7], info[8],
				info[9], info[10]);
		return tp;
	}

	@Override
	public void showTeachingPlanHead() {
		// TODO Auto-generated method stub
		try {
			ns.sendFeedback(tp.getListHead());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void showTeachingPlanListHead() {
		// TODO Auto-generated method stub
		try {
			ns.sendFeedback(tl.getListHead());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
