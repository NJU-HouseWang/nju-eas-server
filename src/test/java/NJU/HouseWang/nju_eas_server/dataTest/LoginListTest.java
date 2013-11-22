package NJU.HouseWang.nju_eas_server.dataTest;

import junit.framework.TestCase;
import NJU.HouseWang.nju_eas_server.data.LoginList;
import NJU.HouseWang.nju_eas_server.po.User.GuestPO;
import NJU.HouseWang.nju_eas_server.systemMessage.UserType;

public class LoginListTest extends TestCase {
	private LoginList ll = null;
	private GuestPO test = null;

	protected void setUp() throws Exception {
		ll = new LoginList();
		test = new GuestPO("1", UserType.Admin, "111111");
		ll.init();
	}

	protected void tearDown() throws Exception {
		ll.finish();
	}

	public void testContainsID() {

	}

	public void testGetLoginer() {

	}

	public void testrRemoveLoginer() {

	}

	public void testAddLoginer() {

	}

	public void testUpdateLoginer() {

	}

	public void testGetLoginList() {

	}

}
