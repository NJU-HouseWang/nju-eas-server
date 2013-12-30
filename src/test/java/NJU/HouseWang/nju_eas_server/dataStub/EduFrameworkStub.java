package NJU.HouseWang.nju_eas_server.dataStub;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.EduFramework;
import NJU.HouseWang.nju_eas_server.po.Edu.EduFrameworkItemPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public class EduFrameworkStub extends EduFramework{

	@Override
	public void init() {

	}

	@Override
	public void finish() {

	}

	@Override
	public String getImportListHead() {
		return "ListHead";
	}

	@Override
	public ArrayList<EduFrameworkItemPO> getEduFramework() {
		ArrayList<EduFrameworkItemPO> al = new ArrayList<EduFrameworkItemPO>();
		al.add(new EduFrameworkItemPO("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"));
		return al;
	}

	@Override
	public Feedback delEduFramework() {
		return Feedback.OPERATION_SUCCEED;
	}

	@Override
	public Feedback addEduFrameworkItem(EduFrameworkItemPO ep) {
		return Feedback.OPERATION_SUCCEED;
	}

	@Override
	public String getListHead() {
		return "ListHead";
	}
	
}
