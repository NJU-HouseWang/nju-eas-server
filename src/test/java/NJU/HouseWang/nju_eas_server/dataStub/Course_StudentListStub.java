package NJU.HouseWang.nju_eas_server.dataStub;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.Course_StudentList;
import NJU.HouseWang.nju_eas_server.po.Edu.Course_StudentPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public class Course_StudentListStub extends Course_StudentList{

	@Override
	public void init() {
		
	}

	@Override
	public void finish() {
		
	}

	
	@Override
	public Feedback createCourseList(String term) {
		return Feedback.OPERATION_SUCCEED;
	}

	@Override
	public Feedback dropCourseList(String term) {
		return Feedback.OPERATION_SUCCEED;
	}

	@Override
	public boolean containsCourse_StudentPO(String listName, String courseId,
			String studentId) {
		return true;
	}

	@Override
	public Course_StudentPO getCourse_StudentPO(String listName,
			String courseId, String studentId) {
		Course_StudentPO csp = new Course_StudentPO("dept", courseId, studentId);
		return csp;
	}

	@Override
	public Feedback removeCourse_StudentPO(String listName, String courseId,
			String studentId) {
		return Feedback.OPERATION_SUCCEED;
	}

	@Override
	public Feedback addCourse_StudentPO(String listName, Course_StudentPO po) {
		return Feedback.OPERATION_SUCCEED;
	}

	@Override
	public Feedback updateCourse_StudentPO(String listName, Course_StudentPO po) {
		return Feedback.OPERATION_SUCCEED;
	}

	@Override
	public ArrayList<Course_StudentPO> getListFromCourseId(String listName,
			String dept, String courseId) {
		ArrayList<Course_StudentPO> al = new ArrayList<Course_StudentPO>();
		Course_StudentPO asp = new Course_StudentPO("dept", "008", "158");
		al.add(asp);
		return al;
	}

	@Override
	public ArrayList<Course_StudentPO> getListFromStudentId(String listName,
			String studentId) {
		ArrayList<Course_StudentPO> al = new ArrayList<Course_StudentPO>();
		Course_StudentPO asp = new Course_StudentPO("dept", "007", "157");
		al.add(asp);
		return al;
	}

	@Override
	public String getListHead() {
		return "ListHead";
	}

}
