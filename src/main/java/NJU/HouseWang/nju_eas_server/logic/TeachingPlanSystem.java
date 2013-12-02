package NJU.HouseWang.nju_eas_server.logic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.EduFramework;
import NJU.HouseWang.nju_eas_server.data.TeachingPlan;
import NJU.HouseWang.nju_eas_server.data.TeachingPlanList;
import NJU.HouseWang.nju_eas_server.logicService.TeachingPlanLogicService;
import NJU.HouseWang.nju_eas_server.netService.NetService;
import NJU.HouseWang.nju_eas_server.po.Edu.EduFrameworkItemPO;
import NJU.HouseWang.nju_eas_server.po.Edu.TeachingPlanItemPO;
import NJU.HouseWang.nju_eas_server.po.Edu.TeachingPlanPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public class TeachingPlanSystem implements TeachingPlanLogicService {
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

		this.ns = ns;
	}

	@Override
	public void showTeachingPlan(String dept) {

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

		TeachingPlanPO tpp = tl.getTeachingPlan(dept);
		if (!tpp.isCommitted()) {
			try {
				ArrayList<String> list = ns.receiveList();
				ArrayList<TeachingPlanItemPO> tpl = new ArrayList<TeachingPlanItemPO>();

				for(int i = 0; i<list.size();i++){
					tpl.add(stringToTeachingPlanItem(list.get(i)));
				}
				
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
	
				e.printStackTrace();
			}
		} else {
			try {
				ns.sendFeedback(Feedback.DATA_ALREADY_EXISTED.toString());
			} catch (IOException e) {
	
				e.printStackTrace();
			}
		}

	}

	@Override
	public void editTeachingPlan(String dept) {

		this.delTeachingPlan(dept);
		this.addTeachingPlan(dept);
	}

	@Override
	public void delTeachingPlan(String dept) {

		tp.delTeachingPlan(dept);
		TeachingPlanPO tpp = tl.getTeachingPlan(dept);
		tpp.setCommitted(false);
		tpp.setStatus(0);
		tpp.getTpFile().delete();
		tpp.setTpFile(null);
		try {
			ns.sendFeedback(Feedback.OPERATION_SUCCEED.toString());
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void auditTeachingPlan(String dept, int status) {

		TeachingPlanPO tpp = tl.getTeachingPlan(dept);
		if (tpp.getStatus() == 0) {
			tpp.setStatus(status);
			try {
				ns.sendFeedback(Feedback.OPERATION_SUCCEED.toString());
			} catch (IOException e) {
	
				e.printStackTrace();
			}
		} else {
			try {
				ns.sendFeedback(Feedback.AUDIT_REPEATED.toString());
			} catch (IOException e) {
	
				e.printStackTrace();
			}
		}
	}

	@Override
	public void uploadTeachingPlanFile(String dept, String filePath) {

		TeachingPlanPO tpp = tl.getTeachingPlan(dept);
		if (tpp.getTpFile() == null) {
			try {
				ns.receiveFile(filePath);
				tpp.setTpFile(new File(filePath));
				ns.sendFeedback(Feedback.OPERATION_SUCCEED.toString());
			} catch (IOException e) {
	
				e.printStackTrace();
			}
		} else {
			try {
				ns.sendFeedback(Feedback.DATA_ALREADY_EXISTED.toString());
			} catch (IOException e) {
	
				e.printStackTrace();
			}
		}
	}

	@Override
	public void downloadTeachingPlanFile(String dept, String filePath) {

		TeachingPlanPO tpp = tl.getTeachingPlan(dept);
		if (tpp.getTpFile() != null) {
			try {
				ns.sendFile(new File(filePath));
			} catch (IOException e) {
	
				e.printStackTrace();
			}
		} else {
			try {
				ns.sendFeedback(Feedback.DATA_NOT_FOUND.toString());
			} catch (IOException e) {
	
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean judgeTeachingPlan(ArrayList<TeachingPlanItemPO> teachingPlan) {

		boolean isValid = true;
		ArrayList<EduFrameworkItemPO> el = ef.getEduFramework();
		ArrayList<String> moduleList = new ArrayList<String>();
		ArrayList<String> courseTypeList = new ArrayList<String>();
		int creditSum = 0;
		int credit = 0;
		String moduleName = "";
		String courseType = "";
		// 将所有的课程模块添加到模块列表，所有的类型添加到类型列表
		moduleList.add(teachingPlan.get(0).getModuleName() + "；"
				+ teachingPlan.get(0).getModuleCredit());
		courseTypeList.add(teachingPlan.get(0).getCourseType() + "；"
				+ teachingPlan.get(0).getTypeCredit());
		for (int i = 1; i < teachingPlan.size(); i++) {
			if (!moduleList.contains(teachingPlan.get(i).getModuleName() + "；"
					+ teachingPlan.get(i).getModuleCredit())) {
				moduleList.add(teachingPlan.get(i).getModuleName() + "；"
						+ teachingPlan.get(i).getModuleCredit());
			}
			if (!courseTypeList.contains(teachingPlan.get(i).getCourseType()
					+ "；" + teachingPlan.get(i).getTypeCredit())) {
				courseTypeList.add(teachingPlan.get(i).getCourseType() + "；"
						+ teachingPlan.get(i).getTypeCredit());
			}
		}
		// 计算所有相同课程模块的课程的学分之和，判断是否与该模块的学分相同，有一个不同则isValid = false
		for (int i = 0; i < moduleList.size(); i++) {
			String[] info = moduleList.get(i).split("；");
			moduleName = info[0];
			credit = Integer.parseInt(info[1]);
			for (int j = 0; j < teachingPlan.size(); j++) {
				if (teachingPlan.get(j).getModuleName().equals(moduleName)) {
					creditSum += teachingPlan.get(j).getCourseCredit();
				}
			}
			if (creditSum != credit) {
				isValid = false;
			}
			creditSum = 0;
		}

		// 计算所有相同课程类型的课程的学分之和，判断是否与该类型的学分相同，有一个不同则isValid = false
		for (int i = 0; i < courseTypeList.size(); i++) {
			String[] info = courseTypeList.get(i).split("；");
			courseType = info[0];
			credit = Integer.parseInt(info[1]);
			for (int j = 0; j < teachingPlan.size(); j++) {
				if (teachingPlan.get(j).getCourseType().equals(courseType)) {
					creditSum += teachingPlan.get(j).getCourseCredit();
				}
			}
			if (creditSum != credit) {
				isValid = false;
			}
			creditSum = 0;
		}
		// 判断每门课的学分是否在框架规定的范围内
		for (int i = 0; i < el.size(); i++) {
			for (int j = 0; j < teachingPlan.size(); j++) {
				if (teachingPlan.get(j).getCourseName()
						.equals(el.get(i).getCourseName())) {
					if ((teachingPlan.get(j).getCourseCredit() < el.get(i)
							.getCourseMinCredit())
							|| (teachingPlan.get(j).getCourseCredit() > el.get(
									i).getCourseMaxCredit())) {
						isValid = false;
					}
				}
			}
		}
		return isValid;
	}

	public TeachingPlanItemPO stringToTeachingPlanItem(String str) {
		String[] info = str.split("；");
		TeachingPlanItemPO tp = new TeachingPlanItemPO(info[0], info[1],
				info[2], info[3], info[4], info[5], info[6], info[7], info[8],
				info[9], info[10]);
		return tp;
	}

	@Override
	public void showTeachingPlanHead() {

		try {
			ns.sendFeedback(tp.getListHead());
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void showTeachingPlanListHead() {

		try {
			ns.sendFeedback(tl.getListHead());
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
