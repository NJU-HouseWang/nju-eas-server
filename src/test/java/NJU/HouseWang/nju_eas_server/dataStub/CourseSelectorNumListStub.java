package NJU.HouseWang.nju_eas_server.dataStub;

import NJU.HouseWang.nju_eas_server.data.CourseSelectorNumList;
import NJU.HouseWang.nju_eas_server.po.Edu.CourseSelectorNumPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public class CourseSelectorNumListStub extends CourseSelectorNumList{

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
	public Feedback updateCourseSelectorNumPO(CourseSelectorNumPO p) {
		return Feedback.OPERATION_SUCCEED;
	}

	@Override
	public Feedback addCourseSelectorNumPO(CourseSelectorNumPO p) {
		return Feedback.OPERATION_SUCCEED;
	}

	@Override
	public CourseSelectorNumPO getCourseSelectorNumPO(String courseId) {
		CourseSelectorNumPO csnp = new CourseSelectorNumPO("courseId", 1, 2);
		return csnp;
	}

}
