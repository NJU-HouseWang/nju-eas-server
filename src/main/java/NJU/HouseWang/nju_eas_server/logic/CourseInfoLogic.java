package NJU.HouseWang.nju_eas_server.logic;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.AuthorityManager;
import NJU.HouseWang.nju_eas_server.data.CommonCourseList;
import NJU.HouseWang.nju_eas_server.data.CourseList;
import NJU.HouseWang.nju_eas_server.data.CourseSelectorNumList;
import NJU.HouseWang.nju_eas_server.data.Course_StudentList;
import NJU.HouseWang.nju_eas_server.data.DeptList;
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
import NJU.HouseWang.nju_eas_server.po.User.TeacherPO;
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
	private DeptList dl;
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
		dl = initDeptList();

	}

	public DeptList initDeptList() {
		DeptList d = new DeptList();
		d.init();
		return d;
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
			if (!cmd.endsWith("；ok")) {
				feedback = "ip";
			} else {
				uid = am.getGuest(cmdInfo[4]);
				dept = this.getDept(cmdInfo[2], cmdInfo[3], uid);
				feedback = this.showCourseDetail(cmdInfo[2], dept, cmdInfo[3]);
			}
			break;
		case "editcourse":
			feedback = "list";
			break;
		case "editcommon_course":
			course = cmdInfo[2];
			for (int i = 3; i < cmdInfo.length; i++) {
				course = course + "；" + cmdInfo[i];
			}
			feedback = this.editCommonCourse(this.stringToCommonCourse(course));
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
			feedback = this.addCourseListFromTP(cmdInfo[2]);
			break;
		case "showcourse_list_head":
			feedback = this.showCourseListHead();
			break;
		case "showcommon_course_list_head":
			feedback = this.showCommonCourseListHead();
			break;
		case "showselectable_common_course_list_head":
			feedback = this.showSelectableCommonCourseListHead();
			break;
		case "recordscore":
			feedback = "list";
			break;
		case "showstu_course_list":
			if (!cmd.endsWith("；ok")) {
				feedback = "ip";
			} else {
				uid = am.getGuest(cmdInfo[2]);
				feedback = this.showStudentCourseList(uid);
			}
			break;
		case "showstudent_score_list":
			if (!cmd.endsWith("；ok")) {
				feedback = "ip";
			} else {
				uid = am.getGuest(cmdInfo[3]);
				feedback = this.showStudentScoreList(cmdInfo[2], uid);
			}
			break;
		case "showcourse_student_list":
			if (!cmd.endsWith("；ok")) {
				feedback = "ip";
			} else {
				uid = am.getGuest(cmdInfo[3]);
				dept = tl.getTeacher(uid).getCompany();
				if (tl.getTeacher(uid).getType().toString().equals("Teacher")) {
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
			if (!cmd.endsWith("；ok")) {
				feedback = "ip";
			} else {
				uid = am.getGuest(cmdInfo[2]);
				feedback = this.showCourseList(this.showTerm(), uid);
			}
			break;
		case "showstudent_list_from_course":
			if (!cmd.endsWith("；ok")) {
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
		case "showcourse_student_list_head":
			feedback = this.showCourseStudentListHead();
			break;
		case "showcommon_course_list_head_edit":
			feedback = this.showEditCommonCourseListHead();
			break;
		case "showcommon_course_edit":
			feedback = this.showEditCommonCourse(cmdInfo[2]);
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
		dl.finish();
		return feedback;
	}

	public String getDept(String term, String courseId, String userId) {

		try {
			String department = "";
			if (ccl.containsCourse(courseId)) {
				department = "通识课";
			} else if (sl.containsID(userId)) {
				ArrayList<Course_StudentPO> course_StudentList = csl
						.getListFromStudentId(this.termTransfer(term), userId);
				for (int i = 0; i < course_StudentList.size(); i++) {
					if (courseId
							.equals(course_StudentList.get(i).getCourseId())) {
						department = course_StudentList.get(i).getDept();
						break;
					}
				}
			} else if (tl.containsID(userId)) {
				TeacherPO tp = tl.getTeacher(userId);
				department = tp.getCompany();
				if (tp.getType().toString().equals("Teacher")) {
					department = cl.getCourseFromTeacherIdAndCourseId(
							this.termTransfer(term), userId, courseId)
							.getDepartment();
				}
			} else {
				return Feedback.DATA_NOT_FOUND.toString();
			}
			return department;
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public Object operate(String cmd, ArrayList<String> list) {
		cl = initCourseList();
		tpl = initTeachingPlanList();
		tp = initTeachingPlan();
		csnl = initCourseSelectorNumList();
		csl = initCourse_StudentList();
		sl = initStudentList();
		tl = initTeacherList();
		statusList = initStatusList();
		termList = initTermList();
		ccl = initCommonCourseList();
		dl = initDeptList();
		Object feedback = null;
		String[] cmdInfo = cmd.split("；");
		uid = am.getGuest(cmdInfo[cmdInfo.length - 1]);
		String cmdType = cmdInfo[0] + cmdInfo[1];
		switch (cmdType) {

		case "addcourse_list":
			feedback = this.addCourseList(list);
			break;
		case "recordscore":
			feedback = this.recordScore(cmdInfo[2], list);
			break;
		case "publishcommon_course":
			feedback = this.publishCommonCourse(list);
			break;
		case "editcourse":
			if (cmd.endsWith("；ok")) {
				feedback = "ip";
			} else {
				uid = am.getGuest(cmdInfo[4]);
				dept = this.getDept(cmdInfo[2], cmdInfo[3], uid);
				feedback = this.editCourse(cmdInfo[2], cmdInfo[3], dept, list);
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
		dl.finish();
		return feedback;
	}

	@Override
	// 返回课程的介绍、参考书目、教学大纲
	public ArrayList<String> showCourseDetail(String term, String department,
			String courseId) {

		ArrayList<String> feedback = new ArrayList<String>();
		CoursePO cp = cl.getCourseFromDeptAndCourseId(this.termTransfer(term),
				department, courseId);
		feedback.add(cp.getIntroduction() + "");
		feedback.add(cp.getBook() + "");
		feedback.add(cp.getSyllabus() + "");
		return feedback;
	}

	@Override
	public String editCourse(String term, String courseId, String dept,
			ArrayList<String> content) {
		try {
			if (cl.containsCourse(this.termTransfer(term), dept, courseId)) {
				CoursePO c = cl.getCourseFromDeptAndCourseId(
						this.termTransfer(term), dept, courseId);
				if (content.size() == 2) {
					c.setTeacherId(content.get(0));
					c.setTimeAndPlace(content.get(1));
				} else if (content.size() == 3) {
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
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public String editCommonCourse(CoursePO c) {
		try {
			if (ccl.containsCourse(c.getId())) {
				ccl.updateCourse(c);
				return Feedback.OPERATION_SUCCEED.toString();
			} else {
				return Feedback.DATA_NOT_FOUND.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public String addCourse(CoursePO c) {

		try {
			String term = this.getTerm();
			if (!cl.containsCourse(term, c.getDepartment(), c.getId())) {
				cl.addCourse(term, c);
				if (c.getDepartment().equals("通识课")) {
					this.addCommonCourse(c);
				}
				return Feedback.OPERATION_SUCCEED.toString();
			} else {
				return Feedback.DATA_ALREADY_EXISTED.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public String addCommonCourse(CoursePO c) {

		try {
			if (!ccl.containsCourse(c.getId())) {
				ccl.addCourse(c);
				return Feedback.OPERATION_SUCCEED.toString();
			} else {
				return Feedback.DATA_ALREADY_EXISTED.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public String delCourse(String term, String department, String id) {

		try {
			if (cl.containsCourse(this.termTransfer(term), department, id)) {
				cl.removeCourse(this.termTransfer(term), department, id);
				this.delCommonCourse(id);
				return Feedback.OPERATION_SUCCEED.toString();
			} else {
				return Feedback.DATA_NOT_FOUND.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public String delCommonCourse(String id) {

		try {
			if (ccl.containsCourse(id)) {
				ccl.removeCourse(id);
				return Feedback.OPERATION_SUCCEED.toString();
			} else {
				return Feedback.DATA_NOT_FOUND.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	// 筛选条件以中文逗号“，”分隔
	public ArrayList<String> showCourseList(String term, String conditions) {

		String[] info = conditions.split("，");
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<CoursePO> courseList = new ArrayList<CoursePO>();
		if (info.length == 1) {
			courseList = cl.getCourseListFromTeacherId(this.termTransfer(term),
					info[0]);
		} else if (info.length == 2) {
			if (info[0].equals("all")) {
				courseList = cl.getCourseListFromDept(this.termTransfer(term),
						info[1]);
			} else {
				courseList = cl.getCourseListFromGradeAndDept(
						this.termTransfer(term), info[0], info[1]);
			}
		}
		if (!courseList.isEmpty()) {
			for (int i = 0; i < courseList.size(); i++) {
				list.add(courseList.get(i).courseToCommand());
			}
		}
		return list;
	}

	@Override
	public ArrayList<String> showSelectableCommonCourseList() {

		String term = this.getTerm();
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<CoursePO> courseList = cl.getCourseListFromDept(term, "通识课");
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

		try {
			ArrayList<CoursePO> courseList = new ArrayList<CoursePO>();
			String term = this.getTerm();
			for (int i = 0; i < list.size(); i++) {
				courseList.add(stringToCoursePO(list.get(i)));
			}
			for (int i = 0; i < courseList.size(); i++) {
				if (cl.containsCourse(term, courseList.get(i).getDepartment(),
						courseList.get(i).getId())) {
					return Feedback.DATA_ALREADY_EXISTED.toString();
				}
			}
			for (int i = 0; i < list.size(); i++) {
				this.addCourse(courseList.get(i));
			}
			return Feedback.OPERATION_SUCCEED.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public String addCourseListFromTP(String deptName) {

		try {
			String deptId = dl.nametoId(deptName);
			TeachingPlanPO t = tpl.getTeachingPlan(deptId);
			if (t.isCommitted()) {
				ArrayList<TeachingPlanItemPO> teachingPlanItemList = tp
						.getTeachingPlan(deptId);
				for (int j = 0; j < teachingPlanItemList.size(); j++) {
					TeachingPlanItemPO tpip = teachingPlanItemList.get(j);
					// 如果不是选修课，则加入课程列表，并导入学生
					if (!tpip.getCourseNature().equals("选修")) {
						CoursePO cp = this.tpPOToCoursePO(deptId, tpip);
						this.addCourse(cp);
						ArrayList<StudentPO> studentList = sl.getStudentList(
								cp.getGrade(), cp.getDepartment());
						if (!studentList.isEmpty()) {
							for (int p = 0; p < studentList.size(); p++) {
								csl.addCourse_StudentPO(this.getTerm(),
										new Course_StudentPO(
												cp.getDepartment(), cp.getId(),
												studentList.get(p).getId()));
							}
						}
					}
				}

			}
			return Feedback.OPERATION_SUCCEED.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public String showCourseListHead() {

		try {
			return cl.getCourseListHead();
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public String showCommonCourseListHead() {

		return ccl.getCommonCourseListHead();
	}

	@Override
	public String recordScore(String term, ArrayList<String> list) {

		for (int i = 0; i < list.size(); i++) {
			String[] info = list.get(i).split("；");
			String courseId = info[0];
			String studentId = info[1];
			if (!csl.containsCourse_StudentPO(term, courseId, studentId)) {
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
			csl.updateCourse_StudentPO(term, csp);
		}
		return Feedback.OPERATION_SUCCEED.toString();
	}

	@Override
	public ArrayList<String> showStudentCourseList(String studentId) {

		ArrayList<String> list = new ArrayList<String>();
		StudentPO sp = sl.getStudent(studentId);
		String department = sp.getDepartment();
		String term = this.getTerm();
		ArrayList<Course_StudentPO> course_StudentList = csl
				.getListFromStudentId(term, studentId);
		// 通识课的列表
		ArrayList<String> commonCourseList = new ArrayList<String>();

		// 从课程学生列表中将通识课提取出来
		for (int i = 0; i < course_StudentList.size(); i++) {
			if (ccl.containsCourse(course_StudentList.get(i).getCourseId())) {
				commonCourseList.add(course_StudentList.get(i).getCourseId());
				course_StudentList.remove(i);
				i--;
			}
		}

		// 添加非通识课
		for (int j = 0; j < course_StudentList.size(); j++) {
			CoursePO cp = cl.getCourseFromDeptAndCourseId(term, department,
					course_StudentList.get(j).getCourseId());
			list.add(cp.courseToCommand());
		}
		// 添加通识课
		for (int k = 0; k < commonCourseList.size(); k++) {
			CoursePO cp = cl.getCourseFromDeptAndCourseId(term, "通识课",
					commonCourseList.get(k));
			list.add(cp.courseToCommand());
		}
		return list;
	}

	@Override
	public ArrayList<String> showStudentScoreList(String term, String studentId) {

		ArrayList<String> list = new ArrayList<String>();
		ArrayList<Course_StudentPO> scoreList = csl.getListFromStudentId(
				this.termTransfer(term), studentId);
		for (int i = 0; i < scoreList.size(); i++) {
			list.add(scoreList.get(i).toCommand());
		}
		return list;
	}

	@Override
	public ArrayList<String> showCourseStudentList(String courseId,
			String department) {

		String term = this.getTerm();
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<Course_StudentPO> course_studentList = csl
				.getListFromCourseId(term, department, courseId);
		for (int i = 0; i < course_studentList.size(); i++) {
			list.add(course_studentList.get(i).toCommand());
		}
		return list;
	}

	public String getTerm() {

		String term = this.termTransfer(showTerm());
		return term;
	}

	@Override
	public String showTerm() {

		String s = statusList.getStatus("currentTerm").getContent();
		return s;
	}

	@Override
	public String editTerm(String term) {

		StatusPO sp = statusList.getStatus("currentTerm");
		sp.setContent(term);
		statusList.updateStatus(sp);
		termList.addTerm(term);
		cl.createCourseList(this.termTransfer(term));
		csl.createCourseList(this.termTransfer(term));
		return Feedback.OPERATION_SUCCEED.toString();
	}

	@Override
	public String publishCommonCourse(ArrayList<String> list) {
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
		if (!courseList.isEmpty()) {
			for (int j = 0; j < courseList.size(); j++) {
				CoursePO cp = courseList.get(j);
				cp.setDepartment("通识课");
				cp.setNature("指选");
				cp.setTerm(showTerm());
				cl.addCourse(getTerm(), cp);
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
		CoursePO cp = new CoursePO();
		cp.setId(info[0]);
		cp.setName(info[1]);
		cp.setCredit(Integer.parseInt(info[2]));
		cp.setPeriod(Integer.parseInt(info[3]));
		cp.setTeacherId(info[4]);
		cp.setTeacherName(info[5]);
		cp.setTimeAndPlace(info[6]);
		cp.setStudentNum(Integer.parseInt(info[7]));
		return cp;
	}

	@Override
	public CoursePO tpPOToCoursePO(String dept, TeachingPlanItemPO tpip) {

		String term = this.getTerm();
		String grade = ""
				+ (Integer.parseInt(term.substring(1, 5)) - tpip.getStartTerm() / 2);
		String termToDisplay = this.showTerm();
		CoursePO c = new CoursePO(tpip.getCourseId(), tpip.getCourseName(),
				tpip.getModuleName(), tpip.getCourseType(),
				tpip.getCourseNature(), tpip.getCourseCredit(),
				Integer.parseInt(tpip.getPeriod()), grade, termToDisplay, dept);
		return c;
	}

	@Override
	public ArrayList<String> showTermList() {

		ArrayList<String> list = termList.getTermList();
		return list;
	}

	public String termTransfer(String s) {
		String[] list = s.split("学");
		String term = "x" + list[0].split("-")[0] + "_"
				+ list[1].charAt(list[1].length() - 1);
		return term;
	}

	@Override
	public String showSelectableCommonCourseListHead() {
		try {
			return ccl.getSelectedCommonCourseListHead();
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public ArrayList<String> showStudentListFromCourse(String term,
			String courseId, String department) {
		String studentId = "";
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<Course_StudentPO> course_studentList = csl
				.getListFromCourseId(term, department, courseId);
		for (int i = 0; i < course_studentList.size(); i++) {
			studentId = course_studentList.get(i).getStudentId();
			list.add(sl.getStudent(studentId).toCommand());
		}
		return list;
	}

	@Override
	public String showCourseStudentListHead() {

		try {
			return csl.getListHead();
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public String showEditCommonCourseListHead() {
		try {
			return ccl.getEditCommonCourseListHead();
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public String showEditCommonCourse(String courseId) {
		try {
			if (ccl.containsCourse(courseId)) {
				CoursePO c = ccl.getCourse(courseId);
				return (c.getId() + "；" + c.getName() + "；" + c.getCredit()
						+ "；" + c.getTeacherId() + "；" + c.getTeacherName()
						+ "；" + c.getTimeAndPlace() + "；" + c.getStudentNum());
			} else {
				return Feedback.DATA_NOT_FOUND.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}
}
