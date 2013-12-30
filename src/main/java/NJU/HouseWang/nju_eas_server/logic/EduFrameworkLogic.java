package NJU.HouseWang.nju_eas_server.logic;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.AuthorityManager;
import NJU.HouseWang.nju_eas_server.data.EduFramework;
import NJU.HouseWang.nju_eas_server.logicService.EduFrameworkLogicService;
import NJU.HouseWang.nju_eas_server.po.Edu.EduFrameworkItemPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;
/**
 * 教学框架逻辑类
 * @author 教化场
 * @version 2013-11-8
 */
public class EduFrameworkLogic implements EduFrameworkLogicService {
	private EduFramework ef;
	private AuthorityManager am;

	public EduFrameworkLogic() {
		ef = this.intEduFramework();
		am = this.initAuthorityManager();
	}

	public EduFramework intEduFramework() {
		EduFramework e = new EduFramework();
		e.init();
		return e;
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
		case "showeduframework":
			feedback = this.showEduFramework();
			break;
		case "addeduframework":
			feedback = "list";
			break;
		case "deleduframework":
			feedback = this.delEduFramework();
			break;
		case "showmodulenum":
			feedback = this.showModuleNum();
			break;
		case "showeduframework_head":
			feedback = this.showEduFrameworkHead();
			break;
		case "showeduframework_head_import":
			feedback = this.showImportEduFrameworkHead();
			break;
		default:
			break;
		}
		ef.finish();
		return feedback;
	}

	@Override
	public Object operate(String cmd, ArrayList<String> list) {
		Object feedback = null;
		ef = this.intEduFramework();
		String[] cmdInfo = cmd.split("；");
		String uid = am.getGuest(cmdInfo[cmdInfo.length - 1]);
		String cmdType = cmdInfo[0] + cmdInfo[1];
		switch (cmdType) {
		case "addeduframework":
			feedback = this.addEduFramework(list);
			break;
		default:
			break;
		}
		ef.finish();
		return feedback;
	}

	@Override
	/**
	 * 添加教学框架
	 * @param list
	 * @return 反馈
	 */
	public String addEduFramework(ArrayList<String> list) {
		try {
			boolean isValid = true;
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
				if (((minCreditSum < minCredit) || (maxCreditSum > maxCredit))
						&& (minCreditSum >= 0)) {
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
				if (((minCreditSum < minCredit) || (maxCreditSum > maxCredit))
						&& (minCreditSum >= 0)) {
					isValid = false;
				}
				minCreditSum = 0;
				maxCreditSum = 0;
			}

			// 如果isValid = false，则返回错误信息，否则添加成功
			if (isValid) {
				for (int i = 0; i < efList.size(); i++) {
					System.out.println("++" + efList.get(i));
					Feedback fb = ef.addEduFrameworkItem(efList.get(i));
					if (fb == Feedback.OPERATION_FAIL) {
						return Feedback.OPERATION_FAIL.toString();
					}
				}
				return (Feedback.OPERATION_SUCCEED.toString());
			} else {
				return (Feedback.FORMAT_ERROR.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	/**
	 * 删除教学框架
	 * @return 反馈
	 */
	public String delEduFramework() {
		try {
			return (ef.delEduFramework().toString());
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	/**
	 * 显示教学框架
	 * @return 教学框架列表
	 */
	public ArrayList<String> showEduFramework() {

		ArrayList<EduFrameworkItemPO> el = ef.getEduFramework();
		ArrayList<String> feedback = new ArrayList<String>();
		for (int i = 0; i < el.size(); i++) {
			feedback.add(el.get(i).toCommand());
		}
		return feedback;
	}
	/**
	 * string添加到教学框架项
	 * @param str
	 * @return 教学框架项
	 */
	public EduFrameworkItemPO stringToEduFrameworkItemPO(String str) {
		String[] info = str.split("；");
		if (info[2].equals("null")) {
			info[2] = "-1";
		}
		if (info[3].equals("null")) {
			info[3] = "-1";
		}
		if (info[7].equals("null")) {
			info[7] = "-1";
		}
		if (info[8].equals("null")) {
			info[8] = "-1";
		}
		if (info[10].equals("null")) {
			info[10] = "-1";
		}
		if (info[11].equals("null")) {
			info[11] = "-1";
		}
		EduFrameworkItemPO ep = new EduFrameworkItemPO(info[0], info[1],
				info[2], info[3], info[4], info[5], info[6], info[7], info[8],
				info[9], info[10], info[11], info[12], info[13]);
		return ep;
	}

	@Override
	/**
	 * 显示模块数量
	 * @return 模块数量
	 */
	public String showModuleNum() {
		try {
			ArrayList<EduFrameworkItemPO> el = ef.getEduFramework();
			ArrayList<String> moduleList = new ArrayList<String>();
			moduleList.add(el.get(0).getModuleName());
			for (int i = 1; i < el.size(); i++) {
				String moduleName = el.get(i).getModuleName();
				if (!moduleList.contains(moduleName)) {
					moduleList.add(moduleName);
				}
			}
			return ("" + (moduleList.size() + 1));
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	/**
	 * 显示教学框架列表表头
	 * @return 表头
	 */
	public String showEduFrameworkHead() {
		// TODO Auto-generated method stub
		try {
			return ef.getListHead();
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	/**
	 * 显示导入教学框架表头
	 * @return 表头
	 */
	public String showImportEduFrameworkHead() {
		// TODO Auto-generated method stub
		try {
			return ef.getImportListHead();
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

}
