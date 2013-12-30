package NJU.HouseWang.nju_eas_server.dataStub;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.CourseList;
import NJU.HouseWang.nju_eas_server.po.Edu.CoursePO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public class CourseListStub extends CourseList {

	@Override
	public void init() {

	}

	@Override
	public void finish() {

	}

	@Override
	public boolean containsCourse(String listName, String department, String id) {
		return true;
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
	public CoursePO getCourseFromDeptAndCourseId(String term,
			String department, String id) {
		CoursePO cp = new CoursePO(id, "name", "module", "type", "nature", 1,
				2, "grade", term, department, 3, "teacherId",
				"teacherName", "timeAndPlace", "place");
		return cp;
	}

	@Override
	public CoursePO getCourseFromTeacherIdAndCourseId(String term,
			String teacherId, String id) {
		CoursePO cp = new CoursePO(id, "name", "module", "type", "nature", 1,
				2, "grade", term, "department", 3, teacherId,
				"teacherName", "timeAndPlace", "place");
		return cp;
	}

	@Override
	public Feedback removeCourse(String listName, String department, String id) {
		return Feedback.OPERATION_SUCCEED;
	}

	@Override
	public Feedback addCourse(String listName, CoursePO Course) {
		return Feedback.OPERATION_SUCCEED;
	}

	@Override
	public Feedback updateCourse(String listName, CoursePO Course) {
		return Feedback.OPERATION_SUCCEED;
	}

	@Override
	public ArrayList<CoursePO> getCourseListFromTeacherId(String listName,
			String teacherId) {
		ArrayList<CoursePO> al = new ArrayList<CoursePO>();
		CoursePO cp = new CoursePO();
		al.add(cp);
		return al;
	}

	@Override
	public ArrayList<CoursePO> getCourseListFromGradeAndDept(String listName,
			String grade, String department) {
		ArrayList<CoursePO> al = new ArrayList<CoursePO>();
		CoursePO cp = new CoursePO();
		al.add(cp);
		return al;
	}

	@Override
	public ArrayList<CoursePO> getCourseListFromDept(String listName,
			String department) {
		ArrayList<CoursePO> al = new ArrayList<CoursePO>();
		CoursePO cp = new CoursePO();
		al.add(cp);
		return al;
	}

	@Override
	public String getCourseListHead() {
		return "CourseListHead";
	}

}
