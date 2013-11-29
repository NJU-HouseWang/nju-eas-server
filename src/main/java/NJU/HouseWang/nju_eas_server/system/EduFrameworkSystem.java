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

		this.ns = ns;
	}

	@Override
	public void addEduFramework() {

		boolean isValid = true;
		try {
			ArrayList<String> list = ns.receiveList();
			ArrayList<EduFrameworkItemPO> efList = new ArrayList<EduFrameworkItemPO>();
			ArrayList<String> moduleList = new ArrayList<String>();
			ArrayList<String> courseTypeList = new ArrayList<String>();
			int minCreditSum = 0;
			int maxCreditSum = 0;
			int minCredit = 0;
			int maxCredit = 0;
			String moduleName = "";
			String courseType = "";
			for (int i = 0; i < list.size(); i++) {
				EduFrameworkItemPO EduFrameworkItem = this
						.stringToEduFrameworkItemPO(list.get(i));
				efList.add(EduFrameworkItem);
			}
			// 将所有的课程模块添加到模块列表，所有的类型添加到类型列表
			moduleList.add(efList.get(0).getModuleName() + "；"
					+ efList.get(0).getModuleMinCredit() + "；"
					+ efList.get(0).getModuleMaxCredit());
			courseTypeList.add(efList.get(0).getCourseType() + "；"
					+ efList.get(0).getTypeMinCredit() + "；"
					+ efList.get(0).getTypeMaxCredit());
			for (int i = 1; i < efList.size(); i++) {
				if (!moduleList.contains(efList.get(i).getModuleName() + "；"
						+ efList.get(i).getModuleMinCredit() + "；"
						+ efList.get(i).getModuleMaxCredit())) {
					moduleList.add(efList.get(i).getModuleName() + "；"
							+ efList.get(i).getModuleMinCredit() + "；"
							+ efList.get(i).getModuleMaxCredit());
				}
				if (!courseTypeList.contains(efList.get(i).getCourseType()
						+ "；" + efList.get(i).getTypeMinCredit() + "；"
						+ efList.get(i).getTypeMaxCredit())) {
					courseTypeList.add(efList.get(i).getCourseType() + "；"
							+ efList.get(i).getTypeMinCredit() + "；"
							+ efList.get(i).getTypeMaxCredit());
				}
			}
			// 计算所有相同课程模块的课程的最小、最大学分之和，判断是否与该模块的最小最大学分相同，有一个不同则isValid = false
			for (int i = 0; i < moduleList.size(); i++) {
				String[] info = moduleList.get(i).split("；");
				moduleName = info[0];
				minCredit = Integer.parseInt(info[1]);
				maxCredit = Integer.parseInt(info[2]);
				for (int j = 0; j < efList.size(); j++) {
					if (efList.get(j).getModuleName().equals(moduleName)) {
						minCreditSum += efList.get(j).getCourseMinCredit();
						maxCreditSum += efList.get(j).getCourseMaxCredit();
					}
				}
				if ((minCreditSum != minCredit) || (maxCreditSum != maxCredit)) {
					isValid = false;
				}
				minCreditSum = 0;
				maxCreditSum = 0;
			}

			// 计算所有相同课程类型的课程的最小、最大学分之和，判断是否与该类型的最小最大学分相同，有一个不同则isValid = false
			for (int i = 0; i < courseTypeList.size(); i++) {
				String[] info = courseTypeList.get(i).split("；");
				courseType = info[0];
				minCredit = Integer.parseInt(info[1]);
				maxCredit = Integer.parseInt(info[2]);
				for (int j = 0; j < efList.size(); j++) {
					if (efList.get(j).getCourseType().equals(courseType)) {
						minCreditSum += efList.get(j).getCourseMinCredit();
						maxCreditSum += efList.get(j).getCourseMaxCredit();
					}
				}
				if ((minCreditSum != minCredit) || (maxCreditSum != maxCredit)) {
					isValid = false;
				}
				minCreditSum = 0;
				maxCreditSum = 0;
			}
			
			//如果isValid = false，则返回错误信息，否则添加成功
			if(isValid){
				for(int i = 0; i < efList.size();i++){
					ef.addEduFrameworkItem(efList.get(i));
				}
				ns.sendFeedback(Feedback.OPERATION_SUCCEED.toString());
			} else {
				ns.sendFeedback(Feedback.FORMAT_ERROR.toString());
			}

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void delEduFramework() {

		try {
			ns.sendFeedback(ef.delEduFramework().toString());
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void showEduFramework() {

		ArrayList<EduFrameworkItemPO> el = ef.getEduFramework();
		ArrayList<String> feedback = new ArrayList<String>();
		for (int i = 0; i < el.size(); i++) {
			feedback.add(el.get(i).toCommand());
		}
		try {
			ns.sendList(feedback);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public EduFrameworkItemPO stringToEduFrameworkItemPO(String str) {
		String[] info = str.split("；");
		EduFrameworkItemPO ep = new EduFrameworkItemPO(info[0], info[1],
				info[2], info[3], info[4], info[5], info[6], info[7], info[8],
				info[9], info[10], info[11], info[12], info[13]);
		return ep;
	}

}
