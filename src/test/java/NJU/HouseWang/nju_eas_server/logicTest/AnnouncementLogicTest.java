package NJU.HouseWang.nju_eas_server.logicTest;

import junit.framework.TestCase;
import NJU.HouseWang.nju_eas_server.data.AnnouncementList;
import NJU.HouseWang.nju_eas_server.data.AuthorityManager;
import NJU.HouseWang.nju_eas_server.dataStub.AnnouncementListStub;
import NJU.HouseWang.nju_eas_server.logic.AnnouncementLogic;
import NJU.HouseWang.nju_eas_server.po.Msg.AnnouncementPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_server.systemMessage.UserType;

public class AnnouncementLogicTest extends TestCase {
	public AnnouncementListStub al;
	public AuthorityManager am;
	public AnnouncementLogic alo;

	public void setUp() throws Exception {
		am = AuthorityManager.getInstance();
		al = new AnnouncementListStub();

		alo = new AnnouncementLogic() {
			@Override
			public AnnouncementList initAnnouncementList() {
				return al;
			}

			@Override
			public AuthorityManager initAuthorityManager() {
				return am;
			}

		};
	}

	protected void tearDown() throws Exception {
	}

	public void testShowAnnouncement() {
		String result = alo.showAnnouncement("121250159");
		AnnouncementPO ap = new AnnouncementPO("159", "158", "", "",
				UserType.SchoolDean, 3);
		assertTrue(result.equals(ap.toCommand()));
	}

	public void testAddAnnouncement() {
		AnnouncementPO ap = new AnnouncementPO("159", "158", "", "",
				UserType.SchoolDean, 3);
		String result = alo.addAnnouncement(ap);
		assertTrue(result.equals(Feedback.OPERATION_SUCCEED.toString()));
	}

	public void testEditAnnouncement() {
		AnnouncementPO ap = new AnnouncementPO("159", "158", "", "",
				UserType.SchoolDean, 3);
		String result = alo.editAnnouncement(ap);
		assertTrue(result.equals(Feedback.OPERATION_SUCCEED.toString()));
	}

	public void testDelAnnouncement() {
		String id = "157";
		String result = alo.delAnnouncement(id);
		assertTrue(result.equals(Feedback.OPERATION_SUCCEED.toString()));
	}

	public void testShowAnnouncementList() {
	}

	// ?????

	public void testShowAnnouncementListHead() {
		String result = alo.showAnnouncementListHead();
		assertTrue(result.equals("ListHead"));
	}

}
