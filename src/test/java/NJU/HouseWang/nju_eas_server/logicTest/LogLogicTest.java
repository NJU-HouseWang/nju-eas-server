package NJU.HouseWang.nju_eas_server.logicTest;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.AnnouncementList;
import NJU.HouseWang.nju_eas_server.data.AuthorityManager;
import NJU.HouseWang.nju_eas_server.data.LogList;
import NJU.HouseWang.nju_eas_server.dataStub.LogListStub;
import NJU.HouseWang.nju_eas_server.logic.AnnouncementLogic;
import NJU.HouseWang.nju_eas_server.logic.LogLogic;
import NJU.HouseWang.nju_eas_server.po.Msg.LogPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;
import junit.framework.TestCase;

public class LogLogicTest extends TestCase{
	private LogListStub ll;
	private AuthorityManager am;
	public LogLogic llo;
	
	public void setUp() throws Exception {
		am = AuthorityManager.getInstance();
		ll = new LogListStub();
		
		llo = new LogLogic(){
			@Override
			public LogList initLogList() {
				return ll;
			}
			@Override
			public AuthorityManager initAuthorityManager() {
				return am;
			}
			
		};
	}
	
	protected void tearDown() throws Exception {}
	
	public void testShowLogList(){
		String conditions = "conditions";
		ArrayList<LogPO> list = ll.getLogList(conditions);
		ArrayList<String> logList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			String logInfo = (list.get(i)).toCommand();
			logList.add(logInfo);
		}
		assertTrue(llo.showLogList().get(0).equals("name；ip；time；content"));
	}
	
	public void testShowLogListHead(){
		String result = llo.showLogListHead();
		assertTrue(result.equals("ListHead"));
	}
}
