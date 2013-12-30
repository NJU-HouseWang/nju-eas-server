package NJU.HouseWang.nju_eas_server.dataStub;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.CommonCourseList;
import NJU.HouseWang.nju_eas_server.po.Edu.CoursePO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public class CommonCourseListStub extends CommonCourseList{

	@Override
	public void init() {
		
	}

	@Override
	public void finish() {
		
	}

	@Override
	public boolean containsCourse(String id) {
		return true;
	}

	@Override
	public CoursePO getCourse(String id) {
		CoursePO cp = new CoursePO();
		return cp;
	}

	@Override
	public Feedback removeCourse(String id) {
		return Feedback.OPERATION_SUCCEED;
	}

	@Override
	public Feedback addCourse(CoursePO Course) {
		return Feedback.OPERATION_SUCCEED;
	}

	@Override
	public Feedback updateCourse(CoursePO Course) {
		return Feedback.OPERATION_SUCCEED;
	}

	@Override
	public ArrayList<CoursePO> getCourseList() {
		ArrayList<CoursePO> list = new ArrayList<CoursePO>();
		CoursePO cp = new CoursePO();
		list.add(cp);
		return list;
	}

	@Override
	public String getSelectedCommonCourseListHead() {
		return "Listhead";
	}

	@Override
	public String getCommonCourseListHead() {
		return "ListHead";
	}

	@Override
	public String getEditCommonCourseListHead() {
		return "ListHead";
	}
	
}
