package NJU.HouseWang.nju_eas_server.logic;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.AuthorityManager;
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
	private String course;

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
	
	public StatusList initStatusList(){
		StatusList s = new StatusList();
		s.init();
		return s;
	}
	
	public TermList initTermList(){
		TermList t = new TermList();
		t.init();
		return t;
	}

	@Override
	public Object operate(String cmd) {
		String[] cmdInfo = cmd.split("；");
		String uid = am.getGuest(cmdInfo[cmdInfo.length-1]);
		String cmdType = cmdInfo[0] + cmdInfo[1];
		switch (cmdType) {

		case "showcourse_detail":
			return this.showCourseDetail(cmdInfo[2], cmdInfo[3], cmdInfo[4] );
		case "editcourse":
			course = cmdInfo[3]; 
			for(int i = 4 ; i < cmdInfo.length; i++ ){
				course = course + "；" + cmdInfo[i];
			}
			return this.editCourse(cmdInfo[2],this.stringToCoursePO(course));
			
		case "addcourse":
			course = cmdInfo[2]; 
			for(int i = 3 ; i < cmdInfo.length; i++ ){
				course = course + "；" + cmdInfo[i];
			}
			return this.editCourse(cmdInfo[2],this.stringToCoursePO(course));
			
		case "delcourse":
			return this.delCourse(cmdInfo[2], cmdInfo[3], cmdInfo[4]);
			
		case "showcourse_list":
			return this.showCourseList(cmdInfo[2], cmdInfo[3]);
			
		case "showcommon_course_list":
			return this.showCommonCourseList();
			
		case "addcourse_list":
			return "list";
			
		case "addcourse_list_from_tp":
			return this.addCourseListFromTP();
			
		case "showcourse_list_head":
			return this.showCourseListHead();
			
		case "showcommon_course_list_head":
			return this.showCommonCourseListHead();
		
		case "registerscore":
			return "list";
			
		case "showstudent_course_list":
			return this.showStudentCourseList(cmdInfo[2]);
			
		case "showstudent_score_list":
			return this.showStudentScoreList(cmdInfo[2], cmdInfo[3]);
			
		case "showstudent_list_from_teacher_and_course":
			return this.showStudentListFromTeacherAndCourse(cmdInfo[2], cmdInfo[3]);
			
		case "showterm":
			return this.showTerm();
			
		case "editterm":
			return this.editTerm(cmdInfo[2]);
			
		case "publishcommon_course":
			return "list";
		
		case "showterm_list":
			return this.showTermList();
			
		default:
			return null;
		}
	}

	@Override
	public Object operate(String cmd, ArrayList<String> list) {
		// TODO Auto-generated method stub
		String[] cmdInfo = cmd.split("；");
		String uid = am.getGuest(cmdInfo[cmdInfo.length-1]);
		String cmdType = cmdInfo[0] + cmdInfo[1];
		switch (cmdType) {

		case "addcourse_list":
			return this.addCourseList(list);
			
		case "registerscore":
			return this.registerScore(cmdInfo[2], list, cmdInfo[3]);
			
		case "publishcommon_course":
			return this.publishCommonCourse(list);
		
		default:
			return null;
		}
	}

	@Override
	// 返回课程的介绍、参考书目、教学大纲
	public String showCourseDetail(String term, String department,
			String id) {
		// TODO Auto-generated method stub
		String listName = this.termTransfer(term) +"_course_list";
		CoursePO cp = cl.getCourse(listName, department, id);
		return (cp.getIntroduction() + "；" + cp.getBook() + "；" + cp
				.getSyllabus());
	}

	@Override
	public String editCourse(String term, CoursePO c) {
		// TODO Auto-generated method stub
		String listName = this.termTransfer(term) +"_course_list";
		if (cl.containsCourse(listName, c.getDepartment(), c.getId())) {
			cl.updateCourse(listName, c);
			return Feedback.OPERATION_SUCCEED.toString();
		} else {
			return Feedback.DATA_NOT_FOUND.toString();
		}
	}

	@Override
	public String addCourse(CoursePO c) {
		// TODO Auto-generated method stub
		String listName = this.showTerm() + "_course_list";
		if (!cl.containsCourse(listName, c.getDepartment(), c.getId())) {
			cl.addCourse(listName, c);
			return Feedback.OPERATION_SUCCEED.toString();
		} else {
			return Feedback.DATA_ALREADY_EXISTED.toString();
		}
	}

	@Override
	public String delCourse(String term, String department, String id) {
		// TODO Auto-generated method stub
		String listName = this.termTransfer(term) +"_course_list";
		if (cl.containsCourse(listName, department, id)) {
			cl.removeCourse(listName, department, id);
			return Feedback.OPERATION_SUCCEED.toString();
		} else {
			return Feedback.DATA_NOT_FOUND.toString();
		}
	}

	@Override
	// 筛选条件以中文逗号“，”分隔
	public ArrayList<String> showCourseList(String term, String conditions) {
		// TODO Auto-generated method stub
		String listName = this.termTransfer(term) +"_course_list";
		String[] info = conditions.split("，");
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<CoursePO> courseList = new ArrayList<CoursePO>();
		if (info.length == 1) {
			courseList = cl.getCourseListFromTeacherId(listName, info[0]);
		} else if (info.length == 2) {
			courseList = cl.getCourseListFromGradeAndDept(listName, info[0],
					info[1]);
		}
		for (int i = 0; i < courseList.size(); i++) {
			list.add(courseList.get(i).courseToCommand());
		}
		return list;
	}

	@Override
	public ArrayList<String> showCommonCourseList() {
		// TODO Auto-generated method stub
		String listName = this.showTerm() + "_course_list";
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
	public String addCourseList(ArrayList<String> list) {
		// TODO Auto-generated method stub
		ArrayList<CoursePO> courseList = new ArrayList<CoursePO>();
		String listName = this.showTerm() + "_course_list";
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
		return cl.getCommonCourseListHead();
	}

	@Override
	public String registerScore(String term, ArrayList<String> list,
			String scoreType) {
		// TODO Auto-generated method stub
		String listName = this.termTransfer(term) +"_course_student_list";
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
			String courseId = info[0];
			String studentId = info[1];
			String score = info[2];
			Course_StudentPO csp = csl.getCourse_StudentPO(listName, courseId,
					studentId);
			if (scoreType.equals("originalScore")) {
				csp.setOriginalScore(Integer.parseInt(score));
			} else if (scoreType.equals("secondScore")) {
				csp.setSecondScore(Integer.parseInt(score));
			}
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
		String listName = this.showTerm() + "_course_student_list";
		ArrayList<Course_StudentPO> course_StudentList = csl
				.getListFromStudentId(listName, studentId);
		for (int i = 0; i < course_StudentList.size(); i++) {
			CoursePO cp = cl.getCourse(listName, department, studentId);
			list.add(cp.courseToCommand());
		}
		return list;
	}

	@Override
	public ArrayList<String> showStudentScoreList(String term,
			String studentId) {
		// TODO Auto-generated method stub
		String listName = this.termTransfer(term) +"_course_student_list";
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<Course_StudentPO> scoreList = csl.getListFromStudentId(
				listName, studentId);
		for (int i = 0; i < scoreList.size(); i++) {
			list.add(scoreList.get(i).toCommand());
		}
		return list;
	}

	@Override
	public ArrayList<String> showStudentListFromTeacherAndCourse(
			String courseId, String teacherId) {
		// TODO Auto-generated method stub
		String listName = this.showTerm() + "_course_student_list";
		String department = tl.getTeacher(teacherId).getCompany();
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<Course_StudentPO> course_studentList = csl
				.getListFromCourseId(listName, department, courseId);
		for (int i = 0; i < course_studentList.size(); i++) {
			list.add(course_studentList.get(i).toCommand());
		}
		return list;
	}

	@Override
	public String showTerm() {
		// TODO Auto-generated method stub
		String s = statusList.getStatus("当前学期").getContent();
		String term = this.termTransfer(s);
		return term;
	}
	
	@Override
	public String editTerm(String term) {
		// TODO Auto-generated method stub
		StatusPO sp = new StatusPO("当前学期",term);
		statusList.updateStatus(sp);
		return Feedback.OPERATION_SUCCEED.toString();
	}
	
	@Override
	// 同时初始化courseSelectorNumList
	public String publishCommonCourse(ArrayList<String> list) {
		// TODO Auto-generated method stub
		//清空courSelectorNumList
		csnl.delList();
		String listName = this.showTerm() + "_course_list";
		ArrayList<CoursePO> courseList = new ArrayList<CoursePO>();
		for(int i = 0; i <list.size();i++){
			CoursePO cp = this.stringToCoursePO(list.get(i));
			courseList.add(cp);
			if(cl.containsCourse(listName, cp.getDepartment(), cp.getId())){
				return Feedback.DATA_ALREADY_EXISTED.toString();
			}
		}
		
		for(int j = 0; j < courseList.size(); j++){
			CoursePO cp = courseList.get(j);
			cl.addCourse(listName,cp);
			CourseSelectorNumPO c = new CourseSelectorNumPO(cp.getId(),0,cp.getStudentNum());
			csnl.addCourseSelectorNumPO(c);
		}
		return Feedback.OPERATION_SUCCEED.toString();
	}

	public CoursePO stringToCoursePO(String str) {
		String[] info = str.split("；");
		CoursePO cp = new CoursePO(info[0], info[1], info[2], info[3], info[4],
				Integer.parseInt(info[5]), Integer.parseInt(info[6]), info[7],
				info[8], info[9], Integer.parseInt(info[10]), info[11],
				info[12], info[13], info[14]);
		return cp;
	}

	@Override
	public CoursePO tpPOToCoursePO(String dept, TeachingPlanItemPO tpip) {
		// TODO Auto-generated method stub
		String term = this.showTerm();
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
	
	public String termTransfer(String s){
		String[] list = s.split("学");
		String term = list[0] + "_"+list[1].charAt(list[1].length()-1);
		return term;
	}

}
