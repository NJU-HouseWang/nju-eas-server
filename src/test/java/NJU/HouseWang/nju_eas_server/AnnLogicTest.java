package NJU.HouseWang.nju_eas_server;

import junit.framework.TestCase;
import NJU.HouseWang.nju_eas_server.data.AnnouncementList;
import NJU.HouseWang.nju_eas_server.logic.AnnouncementLogic;

public class AnnLogicTest extends TestCase {
	private AnnouncementLogic alogic;
	
	protected void setUp() throws Exception {
		alogic = new AnnouncementLogic() {
			@Override
			public AnnouncementList setAl() {
				return null;
			}
		};
	}

	protected void tearDown() throws Exception {
		
	}

	public void testOperate() {
		assertTrue(alogic.getAl() == null);
	}

	

}
