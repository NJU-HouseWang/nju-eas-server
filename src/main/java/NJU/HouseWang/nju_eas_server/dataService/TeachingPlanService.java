package NJU.HouseWang.nju_eas_server.dataService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.TeachingPlanItemPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public interface TeachingPlanService {
	public ArrayList<TeachingPlanItemPO> getTeachingPlan(String deptId);

	public Feedback delTeachingPlan(String dept);

	public Feedback addTeachingPlanItem(String dept, TeachingPlanItemPO ep);

	public String getListHead();

	public Feedback createTeachingPlan(String deptId);

	public Feedback dropTeachingPlan(String deptId);

}
