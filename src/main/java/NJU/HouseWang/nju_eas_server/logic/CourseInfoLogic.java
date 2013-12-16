package NJU.HouseWang.nju_eas_server.logic;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.AuthorityManager;
import NJU.HouseWang.nju_eas_server.data.CommonCourseList;
import NJU.HouseWang.nju_eas_server.data.CourseList;
import NJU.HouseWang.nju_eas_server.data.CourseSelectorNumList;
import NJU.HouseWang.nju_eas_server.data.Course_StudentList;
import NJU.HouseWang.nju_eas_server.data.StatusList;
import NJU.HouseWang.nju_eas_server.data.StudentList;
import NJU.HouseWang.nju_eas_server.data.TeacherList;
import NJU.HouseWang.nju_eas_server.data.TeachingPlan;
import NJU.HouseWang.nju_eas_server.data.TeachingPlanList;
import NJU.HouseWang.nju_eas_server.data.TermList;
import NJU.HouseWang.nju_eas_server.logicService.CourseInfoLogicService;
import NJU.HouseWang.nju_eas_server.po.Edu.CoursePO;
import NJU.HouseWang.nju_eas_server.po.Edu.CourseSelectorNumPO;
import NJU.HouseWang.nju_eas_server.po.Edu.Course_StudentPO;
import NJU.HouseWang.nju_eas_server.po.Edu.StatusPO;
import NJU.HouseWang.nju_eas_server.po.Edu.TeachingPlanItemPO;
import NJU.HouseWang.nju_eas_server.po.Edu.TeachingPlanPO;
import NJU.HouseWang.nju_eas_server.po.User.StudentPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public class CourseInfoLogic implements CourseInfoLogicService {
	private CourseList cl;
	private AuthorityManager am;
	private TeachingPlanList tpl;
	private TeachingPlan tp;
	private CourseSelectorNumList csnl;
	private Course_StudentList csl;
	private StudentList sl;
	private TeacherList tl;
	private StatusList statusList;
	private TermList termList;
	private CommonCourseList ccl;
	private String course;
	private String uid;
	private String dept;

	public CourseInfoLogic() {
		cl = initCourseList();
		am = initAuthorityManager();
		tpl = initTeachingPlanList();
		tp = initTeachingPlan();
		csnl = initCourseSelectorNumList();
		csl = initCourse_StudentList();
		sl = initStudentList();
		tl = initTeacherList();
		statusList = initStatusList();
		termList = initTermList();
		ccl = initCommonCourseList();

	}

	public CommonCourseList initCommonCourseList() {
		CommonCourseList c = new CommonCourseList();
		c.init();
		return c;
	}

	public CourseList initCourseList() {
		CourseList cl = new CourseList();
		cl.init();
		return cl;
	}

	public AuthorityManager initAuthorityManager() {
		AuthorityManager a = AuthorityManager.getInstance();
		return a;
	}

	public TeachingPlanList initTeachingPlanList() {
		TeachingPlanList t = new TeachingPlanList();
		t.init();
		return t;
	}

	public TeachingPlan initTeachingPlan() {
		TeachingPlan t = new TeachingPlan();
		t.init();
		return t;
	}

	public CourseSelectorNumList initCourseSelectorNumList() {
		CourseSelectorNumList c = new CourseSelectorNumList();
		c.init();
		return c;
	}

	public Course_StudentList initCourse_StudentList() {
		Course_StudentList c = new Course_StudentList();
		c.init();
		return c;
	}

	public StudentList initStudentList() {
		StudentList s = new StudentList();
		s.init();
		return s;
	}

	public TeacherList initTeacherList() {
		TeacherList t = new TeacherList();
		t.init();
		return t;
	}

	public StatusList initStatusList() {
		StatusList s = new StatusList();
		s.init();
		return s;
	}

	public TermList initTermList() {
		TermList t = new TermList();
		t.init();
		return t;
	}

	@Override
	public Object operate(String cmd) {
		Object feedback = null;
		String[] cmdInfo = cmd.split("；");
		uid = am.getGuest(cmdInfo[cmdInfo.length - 1]);
		String cmdType = cmdInfo[0] + cmdInfo[1];
		switch (cmdType) {
		case "showcourse_detail":
			feedback = this
					.showCourseDetail(cmdInfo[2], cmdInfo[3], cmdInfo[4]);
			break;
		case "editcourse":
			feedback = "list";
			break;
		case "editcommon_course":
			course = cmdInfo[2];
			for (int i = 3; i < cmdInfo.length; i++) {
				course = course + "；" + cmdInfo[i];
			}
			feedback = this.editCommonCourse(this.stringToCoursePO(course));
			break;
		case "addcourse":
			course = cmdInfo[2];
			for (int i = 3; i < cmdInfo.length; i++) {
				course = course + "；" + cmdInfo[i];
			}
			feedback = this.addCourse(this.stringToCoursePO(course));
			break;
		case "delcourse":
			feedback = this.delCourse(cmdInfo[2], cmdInfo[3], cmdInfo[4]);
			break;
		case "delcommon_course":
			feedback = this.delCommonCourse(cmdInfo[2]);
			break;
		case "showcourse_list":
			feedback = this.showCourseList(cmdInfo[2], cmdInfo[3]);
			break;
		case "showcommon_course_list":
			feedback = this.showCommonCourseList();
			break;
		case "showselectable_common_course_list":
			feedback = this.showSelectableCommonCourseList();
			break;
		case "addcourse_list":
			feedback = "list";
			break;
		case "addcourse_list_from_tp":
			feedback = this.addCourseListFromTP();
			break;
		case "showcourse_list_head":
			feedback = this.showCourseListHead();
			break;
		case "showcommon_course_list_head":
			feedback = this.showCommonCourseListHead();
			break;
		case "showselectable_common_course_list_head":
			feedback = this.showSelectableCommonCourseListHead();
		case "registerscore":
			feedback = "list";
			break;
		case "showstu_course_list":
			if (cmd.endsWith("；ok")) {
				feedback = "ip";
			} else {
				uid = am.getGuest(cmdInfo[2]);
				feedback = this.showStudentCourseList(uid);
			}
			break;
		case "showstudent_score_list":
			if (cmd.endsWith("；ok")) {
				feedback = "ip";
			} else {
				uid = am.getGuest(cmdInfo[3]);
				feedback = this.showStudentScoreList(cmdInfo[2], uid);
			}
			break;
		case "showcourse_student_list":
			if (cmd.endsWith("；ok")) {
				feedback = "ip";
			} else {
				uid = am.getGuest(cmdInfo[3]);
				dept = tl.getTeacher(uid).getCompany();
				if (dept.equals("Teacher")) {
					dept = cl.getCourseFromTeacherIdAndCourseId(
							this.getTerm() + "_course_student_list", uid,
							cmdInfo[2]).getDepartment();
				}
				feedback = this.showCourseStudentList(cmdInfo[2], dept);
			}
			break;
		case "showterm":
			feedback = this.showTerm();
			break;
		case "editterm":
			feedback = this.editTerm(cmdInfo[2]);
			break;
		case "publishcommon_course":
			feedback = "list";
			break;
		case "showterm_list":
			feedback = this.showTermList();
			break;
		case "addcommon_course":
			course = cmdInfo[2];
			for (int i = 3; i < cmdInfo.length; i++) {
				course = course + "；" + cmdInfo[i];
			}
			feedback = this.addCommonCourse(this.stringToCommonCourse(course));
			break;
		case "showtea_course_list":
			if (cmd.endsWith("；ok")) {
				feedback = "ip";
			} else {
				uid = am.getGuest(cmdInfo[2]);
				feedback = this.showCourseList(this.getTerm(), uid);
			}
			break;
		case "showstudent_list_from_course":
			if (cmd.endsWith("；ok")) {
				feedback = "ip";
			} else {
				uid = am.getGuest(cmdInfo[4]);
				dept = tl.getTeacher(uid).getCompany();
				if (dept.equals("Teacher")) {
					dept = cl.getCourseFromTeacherIdAndCourseId(
							this.getTerm() + "_course_student_list", uid,
							cmdInfo[2]).getDepartment();
				}
				feedback = this.showStudentListFromCourse(cmdInfo[2],
						cmdInfo[3], dept);
			}
			break;
		default:
			break;
		}

		cl.finish();
		tpl.finish();
		tp.finish();
		csnl.finish();
		csl.finish();
		sl.finish();
		tl.finish();
		statusList.finish();
		termList.finish();
		ccl.finish();
		return feedback;
	}

	@Override
	public Object operate(String cmd, ArrayList<String> list) {
		// TODO Auto-generated method stub
		Object feedback = null;
		String[] cmdInfo = cmd.split("；");
		uid = am.getGuest(cmdInfo[cmdInfo.length - 1]);
		String cmdType = cmdInfo[0] + cmdInfo[1];
		switch (cmdType) {

		case "addcourse_list":
			feedback = this.addCourseList(list);
			break;
		case "registerscore":
			feedback = this.registerScore(cmdInfo[2], list);
			break;
		case "publishcommon_course":
			feedback = this.publishCommonCourse(list);
			break;
		case "editcourse":
			if (cmd.endsWith("；ok")) {
				feedback = "ip";
			} else {
				uid = am.getGuest(cmdInfo[4]);
				dept = tl.getTeacher(uid).getCompany();
				if (dept.equals("Teacher")) {
					dept = cl.getCourseFromTeacherIdAndCourseId(
							this.getTerm() + "_course_student_list", uid,
							cmdInfo[2]).getDepartment();
				}
				feedback = this.editCourse(cmdInfo[2], cmdInfo[3], dept,
						list);
			}
			break;
		default:
			break;
		}
		cl.finish();
		tpl.finish();
		tp.finish();
		csnl.finish();
		csl.finish();
		sl.finish();
		tl.finish();
		statusList.finish();
		termList.finish();
		ccl.finish();
		return feedback;
	}

	@Override
	// 返回课程的介绍、参考书目、教学大纲
	public ArrayList<String> showCourseDetail(String term, String department,
			String courseId) {
		// TODO Auto-generated method stub
		ArrayList<String> feedback = new ArrayList<String>();
		String listName = this.termTransfer(term) + "_course_list";
		CoursePO cp = cl.getCourseFromDeptAndCourseId(listName, department,
				courseId);
		feedback.add(cp.getIntroduction());
		feedback.add(cp.getBook());
		feedback.add(cp.getSyllabus());
		return feedback;
	}

	@Override
	public String editCourse(String term, String courseId, String dept,
			ArrayList<String> content) {
		// TODO Auto-generated method stub
		String listName = this.termTransfer(term) + "_course_list";
		if (cl.containsCourse(listName, dept, courseId)) {
			CoursePO c = cl.getCourseFromDeptAndCourseId(listName, dept, courseId);
			if(content.size() == 2){
				c.setTeacherId(content.get(0));
				c.setTimeAndPlace(content.get(1));
			} else if(content.size()==3){
				c.setIntroduction(content.get(0));
				c.setBook(content.get(1));
				c.setSyllabus(content.get(2));
			} else {
				return Feedback.OPERATION_FAIL.toString();
			}
			this.editCommonCourse(c);
			return Feedback.OPERATION_SUCCEED.toString();
		} else {
			return Feedback.DATA_NOT_FOUND.toString();
		}
	}

	@Override
	public String editCommonCourse(CoursePO c) {
		// TODO Auto-generated method stub
		if (ccl.containsCourse(c.getId())) {
			ccl.updateCourse(c);
			return Feedback.OPERATION_SUCCEED.toString();
		} else {
			return Feedback.DATA_NOT_FOUND.toString();
		}
	}

	@Override
	public String addCourse(CoursePO c) {
		// TODO Auto-generated method stub
		String listName = this.getTerm() + "_course_list";
		if (!cl.containsCourse(listName, c.getDepartment(), c.getId())) {
			cl.addCourse(listName, c);
			this.addCommonCourse(c);
			return Feedback.OPERATION_SUCCEED.toString();
		} else {
			return Feedback.DATA_ALREADY_EXISTED.toString();
		}
	}

	@Override
	public String addCommonCourse(CoursePO c) {
		// TODO Auto-generated method stub
		if (!ccl.containsCourse(c.getId())) {
			ccl.addCourse(c);
			return Feedback.OPERATION_SUCCEED.toString();
		} else {
			return Feedback.DATA_ALREADY_EXISTED.toString();
		}
	}

	@Override
	public String delCourse(String term, String department, String id) {
		// TODO Auto-generated method stub
		String listName = this.termTransfer(term) + "_course_list";
		if (cl.containsCourse(listName, department, id)) {
			cl.removeCourse(listName, department, id);
			this.delCommonCourse(id);
			return Feedback.OPERATION_SUCCEED.toString();
		} else {
			return Feedback.DATA_NOT_FOUND.toString();
		}
	}

	@Override
	public String delCommonCourse(String id) {
		// TODO Auto-generated method stub
		if (ccl.containsCourse(id)) {
			ccl.removeCourse(id);
			return Feedback.OPERATION_SUCCEED.toString();
		} else {
			return Feedback.DATA_NOT_FOUND.toString();
		}
	}

	@Override
	// 筛选条件以中文逗号“，”分隔
	public ArrayList<String> showCourseList(String term, String conditions) {
		// TODO Auto-generated method stub
		String listName = this.termTransfer(term) + "_course_list";
		String[] info = conditions.split("，");
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<CoursePO> courseList = new ArrayList<CoursePO>();
		if (info[0].equals("all")) {
			courseList = cl.getCourseListFromDept(listName, info[1]);
		} else {
			courseList = cl.getCourseListFromGradeAndDept(listName, info[0],
					info[1]);
		}
		for (int i = 0; i < courseList.size(); i++) {
			list.add(courseList.get(i).courseToCommand());
		}
		return list;
	}

	@Override
	public ArrayList<String> showSelectableCommonCourseList() {
		// TODO Auto-generated method stub
		String listName = this.getTerm() + "_course_list";
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<CoursePO> courseList = cl.getCourseListFromDept(listName,
				"通识课");
		for (int i = 0; i < courseList.size(); i++) {
			CoursePO cp = courseList.get(i);
			String courseId = cp.getId();
			CourseSelectorNumPO csnp = csnl.getCourseSelectorNumPO(courseId);
			list.add(cp.commonCourseToCommand() + "；" + csnp.getSelectorNum()
					+ "；" + csnp.getTotalNum());
		}
		return list;
	}

	@Override
	public ArrayList<String> showCommonCourseList() {
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<CoursePO> courseList = ccl.getCourseList();
		for (int i = 0; i < courseList.size(); i++) {
			CoursePO cp = courseList.get(i);
			list.add(cp.commonCourseToCommand() + "；" + cp.getStudentNum()
					+ "人");
		}
		return list;
	}

	@Override
	public String addCourseList(ArrayList<String> list) {
		// TODO Auto-generated method stub
		ArrayList<CoursePO> courseList = new ArrayList<CoursePO>();
		String listName = this.getTerm() + "_course_list";
		for (int i = 0; i < list.size(); i++) {
			courseList.add(stringToCoursePO(list.get(i)));
		}
		for (int i = 0; i < courseList.size(); i++) {
			if (cl.containsCourse(listName, courseList.get(i).getDepartment(),
					courseList.get(i).getId())) {
				return Feedback.DATA_ALREADY_EXISTED.toString();
			}
		}
		for (int i = 0; i < list.size(); i++) {
			this.addCourse(courseList.get(i));
		}
		return Feedback.OPERATION_SUCCEED.toString();
	}

	@Override
	public String addCourseListFromTP() {
		// TODO Auto-generated method stub
		ArrayList<TeachingPlanPO> teachingPlanList = tpl.getTeachingPlanList();
		for (int i = 0; i < teachingPlanList.size(); i++) {
			if (teachingPlanList.get(i).isCommitted()) {
				String dept = teachingPlanList.get(i).getDept();
				ArrayList<TeachingPlanItemPO> teachingPlanItemList = tp
						.getTeachingPlan(dept);
				for (int j = 0; j < teachingPlanItemList.size(); j++) {
					TeachingPlanItemPO tpip = teachingPlanItemList.get(j);
					CoursePO cp = this.tpPOToCoursePO(dept, tpip);
					this.addCourse(cp);
				}

			}
		}
		return Feedback.OPERATION_SUCCEED.toString();
	}

	@Override
	public String showCourseListHead() {
		// TODO Auto-generated method stub
		return cl.getCourseListHead();
	}

	@Override
	public String showCommonCourseListHead() {
		// TODO Auto-generated method stub
		return ccl.getCommonCourseListHead();
	}

	@Override
	public String registerScore(String term, ArrayList<String> list) {
		// TODO Auto-generated method stub
		String listName = this.termTransfer(term) + "_course_student_list";
		for (int i = 0; i < list.size(); i++) {
			String[] info = list.get(i).split("；");
			String courseId = info[0];
			String studentId = info[1];
			if (!csl.containsCourse_StudentPO(listName, courseId, studentId)) {
				return ("Error: " + studentId + ":" + Feedback.COURSE_STUDENT_NOT_FOUND
						.toString());
			}
		}

		for (int i = 0; i < list.size(); i++) {
			String[] info = list.get(i).split("；");
			Course_StudentPO csp = new Course_StudentPO(info[0], info[1],
					info[2]);
			csp.setOriginalScore(Integer.parseInt(info[3]));
			csp.setSecondScore(Integer.parseInt(info[4]));
			csl.updateCourse_StudentPO(listName, csp);
		}
		return Feedback.OPERATION_SUCCEED.toString();
	}

	@Override
	public ArrayList<String> showStudentCourseList(String studentId) {
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<String>();
		StudentPO sp = sl.getStudent(studentId);
		String department = sp.getDepartment();
		String listName = this.getTerm() + "_course_student_list";
		ArrayList<Course_StudentPO> course_StudentList = csl
				.getListFromStudentId(listName, studentId);
		for (int i = 0; i < course_StudentList.size(); i++) {
			CoursePO cp = cl.getCourseFromDeptAndCourseId(listName, department,
					course_StudentList.get(i).getCourseId());
			list.add(cp.courseToCommand());
		}
		return list;
	}

	@Override
	public ArrayList<String> showStudentScoreList(String term, String studentId) {
		// TODO Auto-generated method stub
		String listName = this.termTransfer(term) + "_course_student_list";
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<Course_StudentPO> scoreList = csl.getListFromStudentId(
				listName, studentId);
		for (int i = 0; i < scoreList.size(); i++) {
			list.add(scoreList.get(i).toCommand());
		}
		return list;
	}

	@Override
	public ArrayList<String> showCourseStudentList(String courseId,
			String department) {
		// TODO Auto-generated method stub
		String listName = this.getTerm() + "_course_student_list";
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<Course_StudentPO> course_studentList = csl
				.getListFromCourseId(listName, department, courseId);
		for (int i = 0; i < course_studentList.size(); i++) {
			list.add(course_studentList.get(i).toCommand());
		}
		return list;
	}

	public String getTerm() {
		// TODO Auto-generated method stub
		String term = this.termTransfer(showTerm());
		return term;
	}

	@Override
	public String showTerm() {
		// TODO Auto-generated method stub
		String s = statusList.getStatus("currentTerm").getContent();
		return s;
	}

	@Override
	public String editTerm(String term) {
		// TODO Auto-generated method stub
		StatusPO sp = statusList.getStatus("currentTerm");
		sp.setContent(term);
		statusList.updateStatus(sp);
		termList.addTerm(term);
		// 还差一个新建数据库不会写。。。。。
		this.addCommonCourseToCourseList();
		return Feedback.OPERATION_SUCCEED.toString();
	}

	@Override
	public String publishCommonCourse(ArrayList<String> list) {
		// TODO Auto-generated method stub
		ArrayList<CoursePO> courseList = new ArrayList<CoursePO>();
		for (int i = 0; i < list.size(); i++) {
			CoursePO cp = this.stringToCommonCourse(list.get(i));
			courseList.add(cp);
			if (ccl.containsCourse(cp.getId())) {
				return Feedback.DATA_ALREADY_EXISTED.toString();
			}
		}

		for (int j = 0; j < courseList.size(); j++) {
			CoursePO cp = courseList.get(j);
			ccl.addCourse(cp);
		}
		return Feedback.OPERATION_SUCCEED.toString();
	}

	// 同时初始化courseSelectorNumList
	public void addCommonCourseToCourseList() {
		// 清空courSelectorNumList
		csnl.delList();
		ArrayList<CoursePO> courseList = ccl.getCourseList();
		String listName = this.getTerm() + "_course_list";
		if (!courseList.isEmpty()) {
			for (int j = 0; j < courseList.size(); j++) {
				CoursePO cp = courseList.get(j);
				cp.setDepartment("通识课");
				cp.setTerm(this.showTerm());
				cl.addCourse(listName, cp);
				CourseSelectorNumPO c = new CourseSelectorNumPO(cp.getId(), 0,
						cp.getStudentNum());
				csnl.addCourseSelectorNumPO(c);
			}
		}
	}

	public CoursePO stringToCoursePO(String str) {
		String[] info = str.split("；");
		CoursePO cp = new CoursePO(info[0], info[1], info[2], info[3], info[4],
				Integer.parseInt(info[5]), Integer.parseInt(info[6]), info[7],
				info[8], info[9], Integer.parseInt(info[10]), info[11],
				info[12], info[13], info[14]);
		return cp;
	}

	public CoursePO stringToCommonCourse(String str) {
		String[] info = str.split("；");
		CoursePO cp = new CoursePO(info[0], info[1], info[2], info[3], info[4],
				Integer.parseInt(info[5]), Integer.parseInt(info[6]), info[7],
				Integer.parseInt(info[8]), info[9], info[10], info[11],
				info[12]);
		return cp;
	}

	@Override
	public CoursePO tpPOToCoursePO(String dept, TeachingPlanItemPO tpip) {
		// TODO Auto-generated method stub
		String term = this.getTerm();
		String[] yearInfo = term.split("-");
		String[] termInfo = term.split("_");
		String grade = ""
				+ (Integer.parseInt(yearInfo[0]) - tpip.getStartTerm() / 2);
		String termToDisplay = termInfo[0] + "学年   第" + termInfo[1] + "学期";
		CoursePO c = new CoursePO(tpip.getCourseId(), tpip.getCourseName(),
				tpip.getModuleName(), tpip.getCourseType(),
				tpip.getCourseNature(), tpip.getCourseCredit(),
				Integer.parseInt(tpip.getPeriod()), grade, termToDisplay, dept);
		return c;
	}

	@Override
	public ArrayList<String> showTermList() {
		// TODO Auto-generated method stub
		ArrayList<String> list = termList.getTermList();
		return list;
	}

	public String termTransfer(String s) {
		String[] list = s.split("学");
		String term = list[0] + "_" + list[1].charAt(list[1].length() - 1);
		return term;
	}

	@Override
	public String showSelectableCommonCourseListHead() {
		// TODO Auto-generated method stub
		return ccl.getSelectedCommonCourseListHead();
	}

	@Override
	public ArrayList<String> showStudentListFromCourse(String term,
			String courseId, String department) {
		String listName = this.termTransfer(term) + "_course_student_list";
		String studentId = "";
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<Course_StudentPO> course_studentList = csl
				.getListFromCourseId(listName, department, courseId);
		for (int i = 0; i < course_studentList.size(); i++) {
			studentId = course_studentList.get(i).getStudentId();
			list.add(sl.getStudent(studentId).toCommand());
		}
		return list;
	}
}
