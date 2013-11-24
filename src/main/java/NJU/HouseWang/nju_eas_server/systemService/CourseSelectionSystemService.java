package NJU.HouseWang.nju_eas_server.systemService;

import NJU.HouseWang.nju_eas_server.po.Edu.StatusPO;

public interface CourseSelectionSystemService extends SystemService {
	//显示状态
	public void showStatus(String function);
	//编辑状态
	public void editStatus(StatusPO sp);
	//显示状态列表
	public void showStatusList();
}
