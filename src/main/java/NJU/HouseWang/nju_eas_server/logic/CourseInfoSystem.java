package NJU.HouseWang.nju_eas_server.logic;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.logicService.CourseInfoLogicService;
import NJU.HouseWang.nju_eas_server.po.Edu.CoursePO;

public class CourseInfoSystem implements CourseInfoLogicService {

	@Override
	public void setUid(String uid) {

	}

	@Override
	public void setCmd(String cmd) {

	}

	@Override
	public Object operate() {

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
