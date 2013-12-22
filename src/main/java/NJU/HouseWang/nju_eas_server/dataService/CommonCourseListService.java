package NJU.HouseWang.nju_eas_server.dataService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.CoursePO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public interface CommonCourseListService {
	public boolean containsCourse(String id);

	public CoursePO getCourse(String id);

	public Feedback removeCourse(String id);

	public Feedback addCourse(CoursePO Course);

	public Feedback updateCourse(CoursePO Course);

	public ArrayList<CoursePO> getCourseList();

	public String getSelectedCommonCourseListHead();

	public String getCommonCourseListHead();
}
