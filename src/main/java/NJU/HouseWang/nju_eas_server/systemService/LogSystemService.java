package NJU.HouseWang.nju_eas_server.systemService;

import NJU.HouseWang.nju_eas_server.po.Msg.LogPO;

public interface LogSystemService extends SystemService {
	//新增一条日志
	public void addLog(LogPO lp);
	//显示日志列表
	public void showLogList(String conditions);
}
