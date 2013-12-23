package NJU.HouseWang.nju_eas_server.dataService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Msg.LogPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public interface LogListService {
	public Feedback addLog(LogPO log);

	public ArrayList<LogPO> getLogList();

	public String getListHead();
	
	public Feedback emptyLogList();
}
