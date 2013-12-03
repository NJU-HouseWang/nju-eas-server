package NJU.HouseWang.nju_eas_server.logicService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Msg.LogPO;

public interface LogLogicService extends LogicService {
	// 新增一条日志
	public void addLog(LogPO lp);

	// 显示日志列表
	public ArrayList<String> showLogList(String conditions);

	// 显示日志列表的表头
	public String showLogListHead();
}
