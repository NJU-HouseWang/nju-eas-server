package NJU.HouseWang.nju_eas_server.logic;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.AuthorityManager;
import NJU.HouseWang.nju_eas_server.data.CourseList;
import NJU.HouseWang.nju_eas_server.logicService.CourseInfoLogicService;
import NJU.HouseWang.nju_eas_server.po.Edu.CoursePO;

public class CourseInfoSystem implements CourseInfoLogicService {
	private CourseList cl;
	private AuthorityManager am;
	
	public CourseInfoSystem(){
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

		return null;
	}

	@Override
	public String editCourse(String time, CoursePO c) {

		return null;
	}

	@Override
	public String addCourse(String time, CoursePO c) {

		return null;
	}

	@Override
	public String delCourse(String time, String department, String id) {

		return null;
	}

	@Override
	public ArrayList<String> showCourseList(String time, String conditions) {

		return null;
	}

	@Override
	public String addCourseList() {

		return null;
	}

	@Override
	public String addCourseListFromTP() {

		return null;
	}

	@Override
	public String showCourseListHead() {

		return null;
	}

	@Override
	public String registerScore(String time) {

		return null;
	}

	@Override
	public ArrayList<String> showStudentCourseList(String studentId) {

		return null;
	}

	@Override
	public ArrayList<String> showStudentScoreList(String time, String studentId) {

		return null;
	}

	@Override
	public ArrayList<String> showStudentListFromTeacherAndCourse(
			String teacherId, String courseId) {

		return null;
	}

	@Override
	public String getTime() {

		return null;
	}

}
