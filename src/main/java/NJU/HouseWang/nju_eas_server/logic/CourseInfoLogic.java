package NJU.HouseWang.nju_eas_server.logic;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.AuthorityManager;
import NJU.HouseWang.nju_eas_server.data.CourseList;
import NJU.HouseWang.nju_eas_server.logicService.CourseInfoLogicService;
import NJU.HouseWang.nju_eas_server.po.Edu.CoursePO;

public class CourseInfoLogic implements CourseInfoLogicService {
	private CourseList cl;
	private AuthorityManager am;
	
	public CourseInfoLogic(){
		cl = initCourseList();
		am = initAuthorityManager();
	}
	
	public CourseList initCourseList(){
		CourseList cl = new CourseList();
		cl.init();
		return cl;
	}
	
	public AuthorityManager initAuthorityManager(){
		AuthorityManager a = AuthorityManager.getInstance();
		return a;
	}

	@Override
	public Object operate(String cmd) {

		return null;
	}
	
	@Override
	public Object operate(String cmd, ArrayList<String> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String showCourseDetail(String time, String department, String id,
			String item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String editCourse(String time, CoursePO c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addCourse(String time, CoursePO c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delCourse(String time, String department, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> showCourseList(String time, String conditions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addCourseList(ArrayList<String> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addCourseListFromTP() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String showCourseListHead() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String registerScore(String time, ArrayList<String> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> showStudentCourseList(String studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> showStudentScoreList(String time, String studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> showStudentListFromTeacherAndCourse(
			String teacherId, String courseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTime() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	//记得初始化courseSelectorList
	public String publishCommonCourse(ArrayList<String> list) {
		// TODO Auto-generated method stub
		return null;
	}

}