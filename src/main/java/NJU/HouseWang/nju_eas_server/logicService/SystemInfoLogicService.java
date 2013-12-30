package NJU.HouseWang.nju_eas_server.logicService;

import java.util.ArrayList;

/**
 * 系统信息逻辑类
 * @author 教化场
 * @version 2013-11-11
 */
public interface SystemInfoLogicService extends LogicService{
	/**
	 * 显示院系列表
	 * @return 院系列表
	 */
	public ArrayList<String> showDeptList();
	
}
