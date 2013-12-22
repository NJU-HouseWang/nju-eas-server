package NJU.HouseWang.nju_eas_server.dataService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.TeachingPlanPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public interface TeachingPlanListService {
	public TeachingPlanPO getTeachingPlan(String dept);

	public Feedback updateTeachingPlanItem(TeachingPlanPO TeachingPlan);

	public ArrayList<TeachingPlanPO> getTeachingPlanList();

	public String getListHead();

}
