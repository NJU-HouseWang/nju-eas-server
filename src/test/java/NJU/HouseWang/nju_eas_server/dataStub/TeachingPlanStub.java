package NJU.HouseWang.nju_eas_server.dataStub;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.TeachingPlan;
import NJU.HouseWang.nju_eas_server.po.Edu.TeachingPlanItemPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public class TeachingPlanStub extends TeachingPlan {

	@Override
	public void init() {
		
	}

	@Override
	public void finish() {
		
	}

	@Override
	public Feedback createTeachingPlan(String deptId) {
		return Feedback.OPERATION_SUCCEED;
	}

	@Override
	public Feedback dropTeachingPlan(String deptId) {
		return Feedback.OPERATION_SUCCEED;
	}

	@Override
	public String getImportListHead() {
		return "ListHead";
	}

	@Override
	public ArrayList<TeachingPlanItemPO> getTeachingPlan(String dept) {
		ArrayList<TeachingPlanItemPO> al = new ArrayList<TeachingPlanItemPO>();
		TeachingPlanItemPO tpip = new TeachingPlanItemPO("moduleId", "moduleName", 1, "courseNature", "courseType", 2, "courseId", "courseName", 3, "4", 5);
		al.add(tpip);
		return al;
		
	}

	@Override
	public Feedback delTeachingPlan(String dept) {
		return Feedback.OPERATION_SUCCEED;
	}

	@Override
	public Feedback addTeachingPlanItem(String dept, TeachingPlanItemPO ep) {
		return Feedback.OPERATION_SUCCEED;
	}

	@Override
	public String getListHead() {
		return "ListHead";
	}
	
}
