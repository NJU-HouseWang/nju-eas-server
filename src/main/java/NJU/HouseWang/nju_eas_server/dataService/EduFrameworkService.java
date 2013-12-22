package NJU.HouseWang.nju_eas_server.dataService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.EduFrameworkItemPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public interface EduFrameworkService {
	public ArrayList<EduFrameworkItemPO> getEduFramework();

	public Feedback delEduFramework();

	public Feedback addEduFrameworkItem(EduFrameworkItemPO ep);

	public String getListHead();
}
