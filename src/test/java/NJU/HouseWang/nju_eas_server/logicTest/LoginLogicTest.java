package NJU.HouseWang.nju_eas_server.logicTest;

import NJU.HouseWang.nju_eas_server.data.AnnouncementList;
import NJU.HouseWang.nju_eas_server.data.AuthorityManager;
import NJU.HouseWang.nju_eas_server.data.LoginList;
import NJU.HouseWang.nju_eas_server.dataStub.AnnouncementListStub;
import NJU.HouseWang.nju_eas_server.dataStub.LoginListStub;
import NJU.HouseWang.nju_eas_server.logic.AnnouncementLogic;
import NJU.HouseWang.nju_eas_server.logic.LoginLogic;
import NJU.HouseWang.nju_eas_server.po.User.GuestPO;
import NJU.HouseWang.nju_eas_server.systemMessage.UserType;
import junit.framework.TestCase;

public class LoginLogicTest extends TestCase{
	public LoginListStub ll;
	public AuthorityManager am;
	public LoginLogic llo;
	
	public void setUp() throws Exception {
		am = AuthorityManager.getInstance();
		ll = new LoginListStub();
		
		llo = new LoginLogic(){
			@Override
			public LoginList initLoginList() {
				return ll;
			}
			@Override
			public AuthorityManager initAuthorityManager() {
				return am;
			}
		};
	}
	
	protected void tearDown() throws Exception {}
	
	public void testLogin(){
		GuestPO logger = new GuestPO("121250157",UserType.Student,"bilicrazy123");
		String ip = "ip";
		llo.login(logger, ip);
		assertTrue(am.containsIP(ip) && am.containsGuest(logger.getId()));
	}
	
	public void testLogout(){
		String ip = "ip";
		llo.logout(ip);
		assertTrue(!am.containsIP(ip));
	}
}
