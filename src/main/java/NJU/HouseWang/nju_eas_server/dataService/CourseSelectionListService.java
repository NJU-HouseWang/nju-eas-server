package NJU.HouseWang.nju_eas_server.dataService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.CourseSelectionPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public interface CourseSelectionListService {
	public Feedback delList();

	public boolean containsCourseSelection(String courseId, String studentId);

	public Feedback removeCourseSelection(String courseId, String studentId);

	public Feedback addCourseSelection(CourseSelectionPO CourseSelection);

	public ArrayList<CourseSelectionPO> getCourseSelectionListFromCourseId(
			String courseId);

	public ArrayList<CourseSelectionPO> getCourseSelectionListFromStudentId(
			String studentId);

	public ArrayList<CourseSelectionPO> getCourseSelectionList();

}