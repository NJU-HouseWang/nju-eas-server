package NJU.HouseWang.nju_eas_server.logic;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.AnnouncementList;
import NJU.HouseWang.nju_eas_server.data.AuthorityManager;
import NJU.HouseWang.nju_eas_server.data.LoginList;
import NJU.HouseWang.nju_eas_server.logicService.AnnouncementLogicService;
import NJU.HouseWang.nju_eas_server.po.Msg.AnnouncementPO;
import NJU.HouseWang.nju_eas_server.po.User.GuestPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;
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

	public AuthorityManager initAuthorityManager() {
		AuthorityManager a = AuthorityManager.getInstance();
		return a;
	}

	@Override
	public Object operate(String cmd) {
		Object feedback = null;
		String[] cmdInfo = cmd.split("；");
		String uid = am.getGuest(cmdInfo[cmdInfo.length - 1]);
		String cmdType = cmdInfo[0] + cmdInfo[1];
		switch (cmdType) {
		case "showannouncement":
			feedback = showAnnouncement(cmdInfo[2]);
			break;
		case "editannouncement":
			AnnouncementPO a1 = new AnnouncementPO(cmd.substring(18,
					cmd.length() - 1));
			feedback = editAnnouncement(a1);
			break;
		case "addannouncement":
			AnnouncementPO a2 = new AnnouncementPO(cmd.substring(17,
					cmd.length() - 1));
			feedback = addAnnouncement(a2);
			break;
		case "delannouncement":
			feedback = delAnnouncement(cmdInfo[2]);
			break;
		case "showannouncement_list":
			feedback = showAnnouncementList(Integer.parseInt(cmdInfo[2]),
					cmdInfo[3]);
			break;
		case "showannouncement_list_head":
			feedback = showAnnouncementListHead();
			break;
		default:
			break;
		}
		al.finish();
		return feedback;
	}

	@Override
	public String showAnnouncement(String id) {
		try {
			return al.getAnnouncement(id).toCommand();
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public String addAnnouncement(AnnouncementPO ap) {
		try {
			return al.addAnnouncement(ap).toString();
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public String editAnnouncement(AnnouncementPO ap) {
		try {
			return al.updateAnnouncement(ap).toString();
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public String delAnnouncement(String id) {
		try {
			return al.removeAnnouncement(id).toString();
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
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
		try {
			return al.getListHead();
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public Object operate(String cmd, ArrayList<String> list) {
		// TODO Auto-generated method stub
		return null;
	}

}
