package NJU.HouseWang.nju_eas_server.logic;

import java.io.File;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.AuthorityManager;
import NJU.HouseWang.nju_eas_server.data.DeptList;
import NJU.HouseWang.nju_eas_server.data.EduFramework;
import NJU.HouseWang.nju_eas_server.data.TeacherList;
import NJU.HouseWang.nju_eas_server.data.TeachingPlan;
import NJU.HouseWang.nju_eas_server.data.TeachingPlanList;
import NJU.HouseWang.nju_eas_server.logicService.TeachingPlanLogicService;
import NJU.HouseWang.nju_eas_server.po.Edu.EduFrameworkItemPO;
import NJU.HouseWang.nju_eas_server.po.Edu.TeachingPlanItemPO;
import NJU.HouseWang.nju_eas_server.po.Edu.TeachingPlanPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public class TeachingPlanLogic implements TeachingPlanLogicService {
	private TeachingPlan tp;
	private TeachingPlanList tl;
	private EduFramework ef;
	private AuthorityManager am;
	private String uid;
	private TeacherList teacherList;
	private DeptList deptList;

	public TeachingPlanLogic() {
		tp = this.initTeachingPlan();
		tl = this.initTeachingPlanList();
		ef = this.initEduFramework();
		teacherList = this.initTeacherList();
		am = this.initAuthorityManager();
		deptList = this.initDeptList();
	}

	public TeacherList initTeacherList() {
		TeacherList t = new TeacherList();
		t.init();
		return t;
	}

	public TeachingPlan initTeachingPlan() {
		TeachingPlan t = new TeachingPlan();
		t.init();
		return t;
	}

	public TeachingPlanList initTeachingPlanList() {
		TeachingPlanList t = new TeachingPlanList();
		t.init();
		return t;
	}

	public EduFramework initEduFramework() {
		EduFramework e = new EduFramework();
		e.init();
		return e;
	}

	public DeptList initDeptList() {
		DeptList e = new DeptList();
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
		String cmdType = cmdInfo[0] + cmdInfo[1];
		switch (cmdType) {
		case "showteachingplan":
			feedback = this.showTeachingPlan(deptList.nametoId(cmdInfo[2]));
			break;
		case "showteachingplan_list":
			feedback = this.showTeachingPlanList();
			break;
		case "addteachingplan":
			if (cmd.endsWith("；ok")) {
				feedback = "list";
			} else {
				feedback = "ip";
			}
			break;
		case "editteachingplan":
			feedback = "list";
			break;
		case "delteachingplan":
			feedback = this.delTeachingPlan(deptList.nametoId(cmdInfo[2]));
			break;
		case "auditteachingplan":
			feedback = this.auditTeachingPlan(deptList.nametoId(cmdInfo[2]),
					Integer.parseInt(cmdInfo[3]));
			break;
		case "uploadteachingplan_file":
			if (cmd.endsWith("；ok") || cmd.endsWith("；file_ok")) {
				if (cmd.endsWith("；file_ok")) {
					uid = am.getGuest(cmdInfo[2]);
					feedback = this.uploadTeachingPlanFile(cmdInfo[4],
							teacherList.getTeacher(uid).getCompany());
				} else {
					feedback = "file；d:/tpFile/";
				}
			} else {
				feedback = "ip";
			}
			break;
		case "downloadteachingplan_file":
			feedback = this.downloadTeachingPlanFile(deptList
					.nametoId(cmdInfo[2]));
			break;
		case "downloadteachingplan_template":
			feedback = this.downloadTeachingPlanTemplate();
			break;
		case "showteachingplan_head":
			feedback = this.showTeachingPlanHead();
			break;
		case "showteachingplan_list_head":
			feedback = this.showTeachingPlanListHead();
			break;
		case "showfile_name":
			feedback = this.showFlieName(deptList.nametoId(cmdInfo[2]));
			break;
		case "showteachingplan_status":
			feedback = this.showTeachingPlanStatus(deptList
					.nametoId(cmdInfo[2]));
			break;
		case "showteachingplan_head_import":
			feedback = this.showImportTeachingPlanHead();
			break;
		default:
			break;
		}
		tp.finish();
		tl.finish();
		ef.finish();
		teacherList.finish();
		deptList.finish();
		return feedback;
	}

	@Override
	public Object operate(String cmd, ArrayList<String> list) {
		Object feedback = null;
		String[] cmdInfo = cmd.split("；");
		String cmdType = cmdInfo[0] + cmdInfo[1];
		tp = this.initTeachingPlan();
		tl = this.initTeachingPlanList();
		ef = this.initEduFramework();
		teacherList = this.initTeacherList();
		deptList = this.initDeptList();
		switch (cmdType) {
		case "addteachingplan":
			uid = am.getGuest(cmdInfo[2]);
			feedback = this.addTeachingPlan(teacherList.getTeacher(uid)
					.getCompany(), list);
			break;
		case "editteachingplan":
			feedback = this.editTeachingPlan(cmdInfo[2], list);
			break;
		default:
			break;
		}
		tp.finish();
		tl.finish();
		ef.finish();
		teacherList.finish();
		deptList.finish();
		return feedback;
	}

	@Override
	public ArrayList<String> showTeachingPlan(String dept) {
		ArrayList<String> feedback = new ArrayList<String>();
		if (tl.getTeachingPlan(deptList.idtoName(dept)).isCommitted()) {
			ArrayList<TeachingPlanItemPO> tl = tp.getTeachingPlan(dept);
			for (int i = 0; i < tl.size(); i++) {
				feedback.add(tl.get(i).toCommand());
			}
		}
		return feedback;
	}

	@Override
	public ArrayList<String> showTeachingPlanList() {

		ArrayList<TeachingPlanPO> t = tl.getTeachingPlanList();
		ArrayList<String> feedback = new ArrayList<String>();
		for (int i = 0; i < t.size(); i++) {
			feedback.add(deptList.idtoName(t.get(i).getDept()) + "；"
					+ t.get(i).isCommitted() + "；" + t.get(i).getStatus() + "；"
					+ t.get(i).getTpFile().getName());
		}
		return feedback;
	}

	@Override
	public String addTeachingPlan(String dept, ArrayList<String> list) {
		try {
			TeachingPlanPO tpp = tl.getTeachingPlan(dept);
			if (!tpp.isCommitted()) {
				ArrayList<TeachingPlanItemPO> tpl = new ArrayList<TeachingPlanItemPO>();

				for (int i = 0; i < list.size(); i++) {
					tpl.add(stringToTeachingPlanItem(list.get(i)));
				}
				boolean isValid = this.judgeTeachingPlan(tpl);
				if (isValid) {
					tp.createTeachingPlan(deptList.nametoId(dept));
					for (int i = 0; i < tpl.size(); i++) {
						tp.addTeachingPlanItem(deptList.nametoId(dept),
								tpl.get(i));
					}
					tpp.setCommitted(true);
					tpp.setStatus(0);
					tl.updateTeachingPlanItem(tpp);
					return (Feedback.OPERATION_SUCCEED.toString());
				} else {
					return (Feedback.FORMAT_ERROR.toString());
				}
			} else {
				return (Feedback.DATA_ALREADY_EXISTED.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public String editTeachingPlan(String dept, ArrayList<String> list) {
		try {
			this.delTeachingPlan(dept);
			return this.addTeachingPlan(dept, list);
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public String delTeachingPlan(String dept) {
		try {
			tp.dropTeachingPlan(dept);
			TeachingPlanPO tpp = tl.getTeachingPlan(deptList.idtoName(dept));
			tpp.setCommitted(false);
			tpp.setStatus(0);
			tpp.getTpFile().delete();
			tpp.setTpFile(null);
			tl.updateTeachingPlanItem(tpp);
			return (Feedback.OPERATION_SUCCEED.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public String auditTeachingPlan(String dept, int status) {
		try {
			TeachingPlanPO tpp = tl.getTeachingPlan(deptList.idtoName(dept));
			if (tpp.getStatus() == 0) {
				tpp.setStatus(status);
				tl.updateTeachingPlanItem(tpp);
				return (Feedback.OPERATION_SUCCEED.toString());
			} else {
				return (Feedback.AUDIT_REPEATED.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public String uploadTeachingPlanFile(String path, String dept) {
		try {
			String filePath = path;
			TeachingPlanPO tpp = tl.getTeachingPlan(dept);
			tpp.setTpFile(new File(filePath));
			tl.updateTeachingPlanItem(tpp);
			return (Feedback.OPERATION_SUCCEED.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public File downloadTeachingPlanFile(String dept) {
		String filePath = "/tpFile/fileNotExit.png";
		TeachingPlanPO tpp = tl.getTeachingPlan(dept);
		if (tpp.getTpFile() != null) {
			return (tpp.getTpFile());
		} else {
			return (new File(filePath));
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
		// 计算所有相同课程模块的课程的学分之和，判断是否大于等于该模块的学分，有一个小于则isValid = false
		for (int i = 0; i < moduleList.size(); i++) {
			String[] info = moduleList.get(i).split("；");
			moduleName = info[0];
			credit = Integer.parseInt(info[1]);
			for (int j = 0; j < teachingPlan.size(); j++) {
				if (teachingPlan.get(j).getModuleName().equals(moduleName)) {
					if (teachingPlan.get(j).getCourseCredit() > 0) {
						creditSum += teachingPlan.get(j).getCourseCredit();
					}
				}
			}
			if ((creditSum < credit) && (creditSum > 0)) {
				System.out.println("=======================21");
				isValid = false;
			}
			creditSum = 0;
		}

		// 计算所有相同课程类型的课程的学分之和，判断是否大于等于该类型的学分，有一个小于则isValid = false
		for (int i = 0; i < courseTypeList.size(); i++) {
			String[] info = courseTypeList.get(i).split("；");
			courseType = info[0];
			credit = Integer.parseInt(info[1]);
			for (int j = 0; j < teachingPlan.size(); j++) {
				if (teachingPlan.get(j).getCourseType().equals(courseType)) {
					if (teachingPlan.get(j).getCourseCredit() > 0) {
						creditSum += teachingPlan.get(j).getCourseCredit();
					}
				}
			}
			if (creditSum < credit && creditSum > 0) {
				System.out.println("=======================22:" + courseType
						+ ";" + creditSum + ";" + credit);
				isValid = false;
			}
			creditSum = 0;
		}
		// 判断每门课的学分是否在框架规定的范围内
		for (int i = 0; i < el.size(); i++) {
			for (int j = 0; j < teachingPlan.size(); j++) {
				if ((teachingPlan.get(j).getCourseName().equals(el.get(i)
						.getCourseName()))
						&& (!teachingPlan.get(j).getCourseName().equals("null"))) {
					if ((teachingPlan.get(j).getCourseCredit() < el.get(i)
							.getCourseMinCredit())
							|| (teachingPlan.get(j).getCourseCredit() > el.get(
									i).getCourseMaxCredit())) {
						System.out.println("=======================23");
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
	public String showTeachingPlanHead() {
		try {
			return (tp.getListHead());
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public String showTeachingPlanListHead() {
		try {
			return (tl.getListHead());
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public String showFlieName(String dept) {
		try {
			if (tl.getTeachingPlan(dept).getTpFile().exists()) {
				String fileName = tl.getTeachingPlan(dept).getTpFile()
						.getName();
				return fileName;
			} else {
				return Feedback.FILE_NOT_FOUND.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public String showTeachingPlanStatus(String dept) {
		try {
			TeachingPlanPO tpp = tl.getTeachingPlan(dept);
			String feedback = tpp.getDept() + "；";
			if (tpp.isCommitted()) {
				feedback += ("true；" + tpp.getStatus() + "；" + tpp.getTpFile()
						.getName());
			} else {
				feedback += ("false；0；null");
			}
			return feedback;
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public String showImportTeachingPlanHead() {
		try {
			return tp.getImportListHead();
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public File downloadTeachingPlanTemplate() {
		String filePath = "/template/teachingplan_template.xls";
		return new File(filePath);
	}
}
