package NJU.HouseWang.nju_eas_server.dataService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.StatusPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public interface StatusListService {
	public StatusPO getStatus(String status);

	public Feedback updateStatus(StatusPO status);

	public ArrayList<StatusPO> getStatusList();

	public String getListHead();
}
