package NJU.HouseWang.nju_eas_server.dataStub;

import java.io.File;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.TeachingPlanList;
import NJU.HouseWang.nju_eas_server.po.Edu.TeachingPlanPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public class TeachingPlanListStub extends TeachingPlanList{

	@Override
	public void init() {
	}

	@Override
	public void finish() {
		
	}

	@Override
	public TeachingPlanPO getTeachingPlan(String dept) {
		TeachingPlanPO tp = new TeachingPlanPO();
		tp.setTpFile(new File("d:/tianhaiyi"));
		tp.setCommitted(true);
		return tp;
	}

	@Override
	public Feedback updateTeachingPlanItem(TeachingPlanPO TeachingPlan) {
		return Feedback.OPERATION_SUCCEED;
	}

	@Override
	public ArrayList<TeachingPlanPO> getTeachingPlanList() {
		ArrayList<TeachingPlanPO> al = new ArrayList<TeachingPlanPO>();
		TeachingPlanPO tp = new TeachingPlanPO();
		tp.setCommitted(true);
		tp.setDept("");
		File f = new File("d");
		tp.setTpFile(f);
		al.add(tp);
		return al;
	}

	@Override
	public String getListHead() {
		return "ListHead";
	}
	
}
