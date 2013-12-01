package NJU.HouseWang.nju_eas_server.system;

import NJU.HouseWang.nju_eas_server.netService.NetService;
import NJU.HouseWang.nju_eas_server.po.Edu.CoursePO;
import NJU.HouseWang.nju_eas_server.po.Edu.Course_StudentPO;
import NJU.HouseWang.nju_eas_server.systemService.CourseInfoSystemService;

public class CourseInfoSystem implements CourseInfoSystemService {

	@Override
	public void operate(String uid, String cmd) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initNetService(NetService ns) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showCourseDetail(String time, String department, String id,
			String item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void editCourse(String time, CoursePO c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addCourse(String time, CoursePO c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delCourse(String time, String department, String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showCourseList(String time, String conditions) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addCourseList() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addCourseListFromTP() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showCourseListHead() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showStudentCourseList(String studentId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showStudentScoreList(String time, String studentId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showStudentListFromTeacherAndCourse(String teacherId,
			String courseId) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerScore(String time) {
		// TODO Auto-generated method stub
		
	}

}
