package NJU.HouseWang.nju_eas_server.system;

import java.io.IOException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.AnnouncementList;
import NJU.HouseWang.nju_eas_server.data.LoginList;
import NJU.HouseWang.nju_eas_server.netService.NetService;
import NJU.HouseWang.nju_eas_server.po.Msg.AnnouncementPO;
import NJU.HouseWang.nju_eas_server.po.User.GuestPO;
import NJU.HouseWang.nju_eas_server.systemMessage.UserType;
import NJU.HouseWang.nju_eas_server.systemService.AnnouncementSystemService;

public class AnnouncementSystem implements AnnouncementSystemService {
	NetService ns;
	AnnouncementList al;

	public AnnouncementSystem() {
		al = new AnnouncementList();
		al.init();

	}

	@Override
	public void operate(String uid, String cmd) {
		// TODO Auto-generated method stub
		String[] cmdInfo = cmd.split("；");
		String cmdType = cmdInfo[0] + cmdInfo[1];
		switch (cmdType) {
		case "showannouncement":
			this.showAnnouncement(cmdInfo[2]);
			break;
		case "editannouncement":
			AnnouncementPO a1 = this.stringToAnnouncementPO(cmd.substring(18,
					cmd.length() - 1));
			this.editAnnouncement(a1);
			break;
		case "addannouncement":
			AnnouncementPO a2 = this.stringToAnnouncementPO(cmd.substring(17,
					cmd.length() - 1));
			this.addAnnouncement(a2);
			break;
		case "delannouncement":
			this.delAnnouncement(cmdInfo[2]);
			break;
		case "showannouncement_list":
			this.showAnnouncementList(Integer.parseInt(cmdInfo[2]),cmdInfo[3]);
			break;
		case "showannouncement_list_head":
			this.showAnnouncementListHead();
			break;
		default:
			break;
		}
	}

	@Override
	public void initNetService(NetService ns) {
		// TODO Auto-generated method stub
		this.ns = ns;
	}

	@Override
	public void showAnnouncement(String id) {
		// TODO Auto-generated method stub
		;
		try {
			ns.sendFeedback(al.getAnnouncement(id).toCommand());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void addAnnouncement(AnnouncementPO ap) {
		// TODO Auto-generated method stub

		try {
			ns.sendFeedback(al.addAnnouncement(ap).toString());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void editAnnouncement(AnnouncementPO ap) {
		// TODO Auto-generated method stub
		try {
			ns.sendFeedback(al.updateAnnouncement(ap).toString());
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

	@Override
	public void delAnnouncement(String id) {
		// TODO Auto-generated method stub
		try {
			ns.sendFeedback(al.removeAnnouncement(id).toString());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void showAnnouncementList(int status, String uid) {
		// TODO Auto-generated method stub
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
		try {
			ns.sendList(announcementList);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public AnnouncementPO stringToAnnouncementPO(String str) {
		String[] info = str.split("；");
		AnnouncementPO ap = new AnnouncementPO(info[0],
				UserType.valueOf(info[1]), info[2], info[3],
				Integer.parseInt(info[4]));
		return ap;
	}

	@Override
	public void showAnnouncementListHead() {
		// TODO Auto-generated method stub
		try {
			ns.sendFeedback(al.getListHead());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
