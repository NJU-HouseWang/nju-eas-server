package NJU.HouseWang.nju_eas_server.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import NJU.HouseWang.nju_eas_server.data.AuthorityManager;
import NJU.HouseWang.nju_eas_server.data.CourseList;
import NJU.HouseWang.nju_eas_server.data.CourseSelectionList;
import NJU.HouseWang.nju_eas_server.data.CourseSelectorNumList;
import NJU.HouseWang.nju_eas_server.data.Course_StudentList;
import NJU.HouseWang.nju_eas_server.data.StatusList;
import NJU.HouseWang.nju_eas_server.logicService.CourseSelectionLogicService;
import NJU.HouseWang.nju_eas_server.po.Edu.CourseSelectionPO;
import NJU.HouseWang.nju_eas_server.po.Edu.CourseSelectorNumPO;
import NJU.HouseWang.nju_eas_server.po.Edu.Course_StudentPO;
import NJU.HouseWang.nju_eas_server.po.Edu.StatusPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public class CourseSelectionLogic implements CourseSelectionLogicService {
	private StatusList sl;
	private Course_StudentList c_sl;
	private CourseSelectionList csl;
	private CourseSelectorNumList csnl;
	private CourseList cl;
	private AuthorityManager am;
	private CourseInfoLogic courseInfoSystem = new CourseInfoLogic();
	private static final int MAXSELECTIONNUM = 4; // 最大的选课数量

	public CourseSelectionLogic() {
		sl = initStatusList();
		c_sl = initCourse_StudentList();
		csl = initCourseSelectionList();
		csnl = initCourseSelectorNumList();
		cl = initCourseList();
		am = this.initAuthorityManager();

	}

	public StatusList initStatusList() {
		StatusList l = new StatusList();
		l.init();
		return l;

	}

	public Course_StudentList initCourse_StudentList() {
		Course_StudentList l = new Course_StudentList();
		l.init();
		return l;
	}

	public CourseSelectionList initCourseSelectionList() {
		CourseSelectionList l = new CourseSelectionList();
		l.init();
		return l;
	}

	public CourseSelectorNumList initCourseSelectorNumList() {
		CourseSelectorNumList l = new CourseSelectorNumList();
		l.init();
		return l;
	}

	public CourseList initCourseList() {
		CourseList l = new CourseList();
		l.init();
		return l;
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
		case "showstatus":
			feedback = showStatus(cmdInfo[2]);
			break;
		case "editstatus":
			StatusPO sp = new StatusPO();
			sp.setStatus(cmdInfo[2]);
			sp.setContent(cmdInfo[3]);
			feedback = editStatus(sp);
			break;
		case "showstatus_list":
			feedback = showStatusList();
			break;
		case "showstatus_list_head":
			feedback = showStatusListHead();
			break;
		case "selectcommon_course":
			feedback = this.selectCommonCourse(cmdInfo[2], cmdInfo[3]);
			break;
		case "selectcourse":
			feedback = this.selectCourse(cmdInfo[2], cmdInfo[3], cmdInfo[4]);
			break;
		case "byelectcourse":
			feedback = this.byElectCourse(cmdInfo[2], cmdInfo[3], cmdInfo[4]);
			break;
		case "quitcourse":
			feedback = this.quitCourse(cmdInfo[2], cmdInfo[3], cmdInfo[4]);
			break;
		case "addcourse_student_list":
			feedback = "list";
			break;
		case "delcourse_student_list":
			feedback = "list";
			break;
		case "processcommon_course_selection":
			feedback = this.processCommonCourseSelection(cmdInfo[2]);
			break;
		case "showmax_selection_num":
			feedback = this.showMaxSelectionNum();
			break;
		case "showselected_course":
			feedback = this.showSelectedCouse(cmdInfo[2], cmdInfo[3]);
			break;
		case "delcourse_student_po":
			feedback = this.delCourse_StudentPO(cmdInfo[2], cmdInfo[3], cmdInfo[4]);
			break;
		default:
			break;
		}
		sl.finish();
		c_sl.finish();
		csl.finish();
		csnl.finish();
		cl.finish();
		return feedback;
	}

	@Override
	public Object operate(String cmd, ArrayList<String> list) {
		Object feedback = null;
		String[] cmdInfo = cmd.split("；");
		String uid = am.getGuest(cmdInfo[cmdInfo.length - 1]);
		String cmdType = cmdInfo[0] + cmdInfo[1];
		switch (cmdType) {
		case "addcourse_student_list":
			feedback = this.addCourse_StudentList(cmdInfo[2], list);
			break;
		case "delcourse_student_list":
			feedback = this.delCourse_StudentList(cmdInfo[2], list);
			break;
		default:
			break;
		}
		sl.finish();
		c_sl.finish();
		csl.finish();
		csnl.finish();
		cl.finish();
		return feedback;
	}

	@Override
	public String showStatus(String function) {

		StatusPO sp = sl.getStatus(function);
		return (sp.getStatus()+"；"+sp.getContent());
	}

	@Override
	public String editStatus(StatusPO sp) {

		sl.updateStatus(sp);
		return (Feedback.OPERATION_SUCCEED.toString());
	}

	@Override
	public ArrayList<String> showStatusList() {

		ArrayList<StatusPO> list = sl.getStatusList();
		ArrayList<String> statusList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			String statusInfo = (list.get(i)).toCommand();
			statusList.add(statusInfo);
		}
		return statusList;
	}

	@Override
	public String showStatusListHead() {

		return (sl.getListHead());
	}

	@Override
	public String selectCommonCourse(String courseId, String studentId) {

		if (!isMax(studentId)) {
			if (!csl.containsCourseSelection(courseId, studentId)) {
				// 添加选课记录
				csl.addCourseSelection(new CourseSelectionPO(courseId,
						studentId, getPriority(studentId)));
				// 课程选择人数po的选择人数加一
				CourseSelectorNumPO cp = csnl.getCourseSelectorNumPO(courseId);
				int selectorNum = cp.getSelectorNum() + 1;
				cp.setSelectorNum(selectorNum);
				csnl.updateCourseSelectorNumPO(cp);
				return (Feedback.OPERATION_SUCCEED.toString());
			} else {
				return (Feedback.SELECTION_REPEATED.toString());
			}
		} else {
			return (Feedback.MAX_SELECTION.toString());
		}
	}

	@Override
	public String selectCourse(String term, String courseId,
			String studentId) {
		String listName =courseInfoSystem.termTransfer(term) +"_course_student_list";
		if (!c_sl.containsCourse_StudentPO(listName, courseId, studentId)) {

			c_sl.addCourse_StudentPO(listName, new Course_StudentPO(listName,
					courseId, studentId));
			return (Feedback.OPERATION_SUCCEED.toString());
		} else {
			return (Feedback.SELECTION_REPEATED.toString());
		}
	}

	@Override
	public String byElectCourse(String term, String courseId,
			String studentId) {
		String listName =courseInfoSystem.termTransfer(term) +"_course_student_list";
		CourseSelectorNumPO csnp = csnl.getCourseSelectorNumPO(courseId);
		if (csnp.getSelectorNum() < csnp.getTotalNum()) {
			csnp.setSelectorNum(csnp.getSelectorNum() + 1);
			c_sl.addCourse_StudentPO(listName, new Course_StudentPO("通识课",
					courseId, studentId));
			csnl.updateCourseSelectorNumPO(csnp);
			return (Feedback.OPERATION_SUCCEED.toString());
		} else {
			return (Feedback.MAX_SELECTION.toString());
		}
	}

	@Override
	public String quitCourse(String term, String courseId, String studentId) {
		String listName =courseInfoSystem.termTransfer(term) +"_course_student_list";
		if (c_sl.containsCourse_StudentPO(listName, courseId, studentId)) {
			c_sl.removeCourse_StudentPO(listName, courseId, studentId);
			CourseSelectorNumPO csnp = csnl.getCourseSelectorNumPO(courseId);
			csnp.setSelectorNum(csnp.getSelectorNum() - 1);
			csnl.updateCourseSelectorNumPO(csnp);
			return (Feedback.OPERATION_SUCCEED.toString());
		} else {
			return (Feedback.OPERATION_FAIL.toString());
		}
	}

	@Override
	public String addCourse_StudentList(String term, ArrayList<String> list) {
		String listName =courseInfoSystem.termTransfer(term) +"_course_student_list";
		// 先检查所添加的列表中是否有重复添加的课程，若重复，则返回错误信息，否则，则添加所有项
		for (int i = 0; i < list.size(); i++) {
			String[] info = list.get(i).split("；");
			if (c_sl.containsCourse_StudentPO(listName, info[0], info[1])) {
				return Feedback.SELECTION_REPEATED.toString();
			}
		}
		for (int i = 0; i < list.size(); i++) {
			String[] info = list.get(i).split("；");
			c_sl.addCourse_StudentPO(listName, new Course_StudentPO(listName,
					info[0], info[1]));
		}
		return (Feedback.OPERATION_SUCCEED.toString());
	}

	@Override
	public String delCourse_StudentList(String term, ArrayList<String> list) {
		String listName =courseInfoSystem.termTransfer(term) +"_course_student_list";
		for (int i = 0; i < list.size(); i++) {
			String[] info = list.get(i).split("；");
			if (!c_sl.containsCourse_StudentPO(listName, info[0], info[1])) {
				return Feedback.DATA_NOT_FOUND.toString();
			}
		}
		for (int i = 0; i < list.size(); i++) {
			String[] info = list.get(i).split("；");
			c_sl.removeCourse_StudentPO(listName, info[0], info[1]);
		}
		return Feedback.OPERATION_SUCCEED.toString();
	}

	@Override
	public String processCommonCourseSelection(String term) {
		String courseListName =courseInfoSystem.termTransfer(term) +"_course_list";
		String course_StudentListName =courseInfoSystem.termTransfer(term) +"_course_student_list";
		ArrayList<CourseSelectionPO> courseSelectionList = csl
				.getCourseSelectionList();
		ArrayList<String> courseList = new ArrayList<String>();
		courseList.add(courseSelectionList.get(0).getCourseId());
		for (int i = 0; i < courseSelectionList.size(); i++) {
			if (!courseList.contains(courseSelectionList.get(i).getCourseId())) {
				courseList.add(courseSelectionList.get(i).getCourseId());
			}
		}

		for (int j = 0; j < courseList.size(); j++) {
			// 选择某一课程的学生选课列表
			ArrayList<CourseSelectionPO> csl1 = csl
					.getCourseSelectionListFromCourseId(courseList.get(j));
			int num = cl.getCourse(courseListName, "通识课", courseList.get(j))
					.getStudentNum();
			// 如果选课人数大于可选总人数，
			if (csl1.size() > num) {
				csl1 = lot(csl1, num);
			}
			for (int m = 0; m < csl1.size(); m++) {
				Course_StudentPO p = new Course_StudentPO("通识课", csl1.get(m)
						.getCourseId(), csl1.get(m).getStudentId());
				c_sl.addCourse_StudentPO(course_StudentListName, p);
			}
			CourseSelectorNumPO cp = csnl.getCourseSelectorNumPO(csl1.get(j)
					.getCourseId());
			cp.setSelectorNum(csl1.size());
			csnl.updateCourseSelectorNumPO(cp);

		}
		// 通识课抽签完成，清空选课列表
		csl.delList();
		return Feedback.OPERATION_SUCCEED.toString();
	}

	// 抽签逻辑
	public ArrayList<CourseSelectionPO> lot(ArrayList<CourseSelectionPO> list,
			int totalNum) {
		// 把所有人的优先级加起来，用作随机数
		int totalPriority = 0;
		ArrayList<LotStatus> lotList = new ArrayList<LotStatus>();
		for (int i = 0; i < list.size(); i++) {
			lotList.add(new LotStatus(list.get(i).getStudentId(),
					totalPriority + 1, totalPriority
							+ list.get(i).getPriority()));
			totalPriority+=list.get(i).getPriority();
		}
		while (list.size() > totalNum) {
			int selectedNum = (int)Math.random()*totalPriority+1;
			for(int j = 0; j< lotList.size();j++){
				if((selectedNum>=lotList.get(j).startNum)&&(selectedNum<=lotList.get(j).endNum)){
					for(int i = 0 ; i <list.size();i++){
						if(list.get(i).getStudentId().equals(lotList.get(j).studentId)){
							list.remove(i);
						}
					}
					lotList.remove(j);
				}
			}
		}
		return list;
	}

	@Override
	public String showMaxSelectionNum() {
		return ("" + MAXSELECTIONNUM);
	}

	@Override
	public boolean isMax(String studentId) {

		boolean isMax = false;
		ArrayList<CourseSelectionPO> list = csl
				.getCourseSelectionListFromStudentId(studentId);
		if (list.size() >= MAXSELECTIONNUM) {
			isMax = true;
		}
		return isMax;
	}

	@Override
	public ArrayList<String> showSelectedCouse(String term, String studentId) {
		String listName =courseInfoSystem.termTransfer(term) +"_course_list";
		ArrayList<CourseSelectionPO> list = csl
				.getCourseSelectionListFromStudentId(studentId);
		ArrayList<String> courseList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			courseList.add(cl.getCourse(listName, "通识课",
					list.get(i).getCourseId()).toString());
		}
		return courseList;
	}

	// 优先级最高为1，最低为4
	public int getPriority(String studentId) {
		int priority = 5 - (Calendar.getInstance().get(Calendar.YEAR) - 2000 - Integer
				.parseInt(studentId.substring(0, 1)));
		return priority;
	}

	@Override
	public String delCourse_StudentPO(String term, String courseId,
			String studentId) {
		String listName =courseInfoSystem.termTransfer(term) +"_course_student_list";
		if (c_sl.containsCourse_StudentPO(listName, courseId, studentId)) {
			c_sl.removeCourse_StudentPO(listName, courseId, studentId);
			return (Feedback.OPERATION_SUCCEED.toString());
		} else {
			return (Feedback.DATA_NOT_FOUND.toString());
		}
	}

	class LotStatus {
		String studentId;
		int startNum;
		int endNum;

		LotStatus(String studentId, int startNum, int endNum) {
			this.studentId = studentId;
			this.startNum = startNum;
			this.endNum = endNum;
		}
	}

}