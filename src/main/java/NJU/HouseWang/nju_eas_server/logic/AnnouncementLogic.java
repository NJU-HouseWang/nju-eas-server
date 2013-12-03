package NJU.HouseWang.nju_eas_server.logic;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.AnnouncementList;
import NJU.HouseWang.nju_eas_server.data.AuthorityManager;
import NJU.HouseWang.nju_eas_server.data.LoginList;
import NJU.HouseWang.nju_eas_server.logicService.AnnouncementLogicService;
import NJU.HouseWang.nju_eas_server.po.Msg.AnnouncementPO;
import NJU.HouseWang.nju_eas_server.po.User.GuestPO;
import NJU.HouseWang.nju_eas_server.systemMessage.UserType;

public class AnnouncementLogic implements AnnouncementLogicService {
	private AnnouncementList al;
	private AuthorityManager am;

	public AnnouncementLogic() {
		al = initAnnouncementList();
		am = initAuthorityManager();
	}

	public AnnouncementList initAnnouncementList() {
		AnnouncementList al = new AnnouncementList();
		al.init();
		return al;
	}
	public AuthorityManager initAuthorityManager(){
		AuthorityManager a = AuthorityManager.getInstance();
		return a;
	}
	@Override
	public Object operate(String cmd) {
		String[] cmdInfo = cmd.split("；");
		String uid = am.getGuest(cmdInfo[cmdInfo.length-1]);
		String cmdType = cmdInfo[0] + cmdInfo[1];
		switch (cmdType) {
		case "showannouncement":
			return showAnnouncement(cmdInfo[2]);
		case "editannouncement":
			AnnouncementPO a1 = new AnnouncementPO(cmd.substring(18,
					cmd.length() - 1));
			return editAnnouncement(a1);
		case "addannouncement":
			AnnouncementPO a2 = new AnnouncementPO(cmd.substring(17,
					cmd.length() - 1));
			return addAnnouncement(a2);
		case "delannouncement":
			return delAnnouncement(cmdInfo[2]);
		case "showannouncement_list":
			return showAnnouncementList(Integer.parseInt(cmdInfo[2]),
					cmdInfo[3]);
		case "showannouncement_list_head":
			return showAnnouncementListHead();
		default:
			return null;
		}
	}

	@Override
	public String showAnnouncement(String id) {
		return al.getAnnouncement(id).toCommand();
	}

	@Override
	public String addAnnouncement(AnnouncementPO ap) {
		return al.addAnnouncement(ap).toString();
	}

	@Override
	public String editAnnouncement(AnnouncementPO ap) {
		return al.updateAnnouncement(ap).toString();
	}

	@Override
	public String delAnnouncement(String id) {
		return al.removeAnnouncement(id).toString();
	}

	@Override
	public ArrayList<String> showAnnouncementList(int status, String uid) {
		LoginList ll = new LoginList();
		ll.init();
		GuestPO g = ll.getLoginer(uid);
		UserType ut = g.getType();
		ArrayList<AnnouncementPO> list = al.getAnnouncementList(status, ut);
		ArrayList<String> announcementList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			String announcementInfo = (list.get(i)).toCommand();
			announcementList.add(announcementInfo);
		}
		return announcementList;
	}

	@Override
	public String showAnnouncementListHead() {
		return al.getListHead();
	}

	@Override
	public Object operate(String cmd, ArrayList<String> list) {
		// TODO Auto-generated method stub
		return null;
	}

}
