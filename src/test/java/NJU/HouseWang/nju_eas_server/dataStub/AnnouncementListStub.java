package NJU.HouseWang.nju_eas_server.dataStub;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.AnnouncementList;
import NJU.HouseWang.nju_eas_server.po.Msg.AnnouncementPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_server.systemMessage.UserType;

public class AnnouncementListStub extends AnnouncementList{

	@Override
	public void init() {
		// TODO Auto-generated method stub
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean containsID(String id) {
		return false;
	}

	@Override
	public AnnouncementPO getAnnouncement(String id) {
		AnnouncementPO ap = new AnnouncementPO("159", "158", "", "", UserType.SchoolDean, 3);
		return ap;
	}

	@Override
	public Feedback removeAnnouncement(String id) {
		return Feedback.OPERATION_SUCCEED;
	}

	@Override
	public Feedback addAnnouncement(AnnouncementPO Announcement) {
		return Feedback.OPERATION_SUCCEED;
	}

	@Override
	public Feedback updateAnnouncement(AnnouncementPO Announcement) {
		return Feedback.OPERATION_SUCCEED;
	}

	@Override
	public ArrayList<AnnouncementPO> getAnnouncementList(int status,UserType recipient) {
		ArrayList<AnnouncementPO> al = new ArrayList<AnnouncementPO>();
		al.add(new AnnouncementPO("157","158","title","content",UserType.SchoolDean,3));
		return al;
	}

	@Override
	public String getListHead() {
		return "ListHead";
	}
	
}
