package NJU.HouseWang.nju_eas_server.logicService;

import java.util.ArrayList;

public interface EduFrameworkLogicService extends LogicService {
	public String addEduFramework(ArrayList<String> list);

	public String delEduFramework();

	public ArrayList<String> showEduFramework();

	public String showModuleNum();
	
	public String showEduFrameworkHead();
}
