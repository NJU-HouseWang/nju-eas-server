package NJU.HouseWang.nju_eas_server.dataService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.Course_StudentPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public interface Course_StudentListService {
	public Feedback createCourseList(String term);

	public Feedback dropCourseList(String term);

	public boolean containsCourse_StudentPO(String term, String courseId,
			String studentId);

	public Course_StudentPO getCourse_StudentPO(String term, String courseId,
			String studentId);

	public Feedback removeCourse_StudentPO(String term, String courseId,
			String studentId);

	public Feedback addCourse_StudentPO(String term, Course_StudentPO po);

	public Feedback updateCourse_StudentPO(String term, Course_StudentPO po);

	public ArrayList<Course_StudentPO> getListFromCourseId(String term,
			String dept, String courseId);

	public ArrayList<Course_StudentPO> getListFromStudentId(String term,
			String studentId);

	public String getListHead();
}