package NJU.HouseWang.nju_eas_server.dataService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.User.GuestPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public interface LoginListService {
	public boolean containsID(String id);

	public GuestPO getLoginer(String id);

	public Feedback removeLoginer(String id);

	public Feedback addLoginer(GuestPO user);

	public Feedback updateLoginer(GuestPO user);

	public ArrayList<GuestPO> getLoginList();

	public String getListHead();
}
