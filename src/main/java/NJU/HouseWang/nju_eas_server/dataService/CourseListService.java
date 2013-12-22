package NJU.HouseWang.nju_eas_server.dataService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.CoursePO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public interface CourseListService {
	public Feedback createCourseList(String term);

	public Feedback dropCourseList(String term);

	public boolean containsCourse(String term, String department, String id);

	public CoursePO getCourseFromDeptAndCourseId(String term,
			String department, String id);

	public CoursePO getCourseFromTeacherIdAndCourseId(String term,
			String teacherId, String id);

	public Feedback removeCourse(String term, String department, String id);

	public Feedback addCourse(String term, CoursePO Course);

	public Feedback updateCourse(String term, CoursePO Course);

	public ArrayList<CoursePO> getCourseListFromTeacherId(String term,
			String teacherId);

	public ArrayList<CoursePO> getCourseListFromGradeAndDept(String term,
			String grade, String department);

	public ArrayList<CoursePO> getCourseListFromDept(String term,
			String department);

	public String getCourseListHead();
}
