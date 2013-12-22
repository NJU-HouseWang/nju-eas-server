package NJU.HouseWang.nju_eas_server.dataService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Msg.AnnouncementPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_server.systemMessage.UserType;

public interface AnnouncementListService {
	public boolean containsID(String id);
	public AnnouncementPO getAnnouncement(String id);
	public Feedback removeAnnouncement(String id);
	public Feedback addAnnouncement(AnnouncementPO Announcement);
	public Feedback updateAnnouncement(AnnouncementPO Announcement);
	public ArrayList<AnnouncementPO> getAnnouncementList(int status, UserType recipient);
	public String getListHead();
}
