package NJU.HouseWang.nju_eas_server.dataService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.DepartmentPO;

/**
 * 院系列表类
 * @author 教化场
 * @version 2013-11-14
 */
public interface DeptListService {
	/**
	 * 获取院系列表
	 * @return 院系列表
	 */
	public ArrayList<DepartmentPO> getDeptList();

}