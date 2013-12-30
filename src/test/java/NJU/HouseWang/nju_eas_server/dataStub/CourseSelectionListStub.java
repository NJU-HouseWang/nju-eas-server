package NJU.HouseWang.nju_eas_server.dataStub;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.CourseSelectionList;
import NJU.HouseWang.nju_eas_server.po.Edu.CourseSelectionPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public class CourseSelectionListStub extends CourseSelectionList {

	@Override
	public void init() {
		
	}

	@Override
	public void finish() {
		
	}

	@Override
	public Feedback delList() {
		return Feedback.OPERATION_SUCCEED;
	}

	@Override
	public boolean containsCourseSelection(String courseId, String studentId) {
		return true;
	}

	@Override
	public Feedback removeCourseSelection(String courseId, String studentId) {
		return Feedback.OPERATION_SUCCEED;
	}

	@Override
	public Feedback addCourseSelection(CourseSelectionPO CourseSelection) {
		return Feedback.OPERATION_SUCCEED;
	}

	@Override
	public ArrayList<CourseSelectionPO> getCourseSelectionListFromCourseId(
			String courseId) {
		ArrayList<CourseSelectionPO> list = new ArrayList<CourseSelectionPO>();
		CourseSelectionPO csp = new CourseSelectionPO("courseId", "studentId", 1);
		list.add(csp);
		return list;
	}

	@Override
	public ArrayList<CourseSelectionPO> getCourseSelectionListFromStudentId(
			String studentId) {
		ArrayList<CourseSelectionPO> list = new ArrayList<CourseSelectionPO>();
		CourseSelectionPO csp = new CourseSelectionPO("courseId", "studentId", 1);
		list.add(csp);
		return list;
	}

	@Override
	public ArrayList<CourseSelectionPO> getCourseSelectionList() {
		ArrayList<CourseSelectionPO> list = new ArrayList<CourseSelectionPO>();
		CourseSelectionPO csp = new CourseSelectionPO("courseId", "studentId", 1);
		list.add(csp);
		return list;
	}

}
