package NJU.HouseWang.nju_eas_server.dataTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.AnnouncementList;
import NJU.HouseWang.nju_eas_server.data.SQLConnector;
import NJU.HouseWang.nju_eas_server.po.Msg.AnnouncementPO;
import NJU.HouseWang.nju_eas_server.systemMessage.UserType;
import junit.framework.TestCase;

public class AnnouncementListTest extends TestCase {
	private String sql = null;
	private SQLConnector sqlconn = new SQLConnector();
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	private AnnouncementList al;
	private AnnouncementPO ap1;
	private AnnouncementPO ap2;

	protected void setUp() throws Exception {
		al = new AnnouncementList();
		al.init();
		ap1 = new AnnouncementPO("testId", UserType.Admin, "testTitle",
				"testContent", 0);
		ap2 = new AnnouncementPO("testId2", UserType.Admin, "testTitle2",
				"testContent2", 0);
	}

	protected void tearDown() throws Exception {
		sql = "delete from announcement_list where senderId=?";
		try {
			sqlconn.createConnection();
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, "testId");
			ps.execute();
		} catch (SQLException e) {
		}
		sql = "delete from announcement_list where senderId=?";
		try {
			sqlconn.createConnection();
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, "testId2");
			ps.execute();
		} catch (SQLException e) {
		}
		al.finish();
	}

	public void testContainsID() {
		al.addAnnouncement(ap1);
		String id = al.getAnnouncementList(0, UserType.Admin).get(0).getId();
		assertTrue(al.containsID(id));
	}

	public void testGetAnnouncement() {
		al.addAnnouncement(ap1);
		String id = al.getAnnouncementList(0, UserType.Admin).get(0).getId();
		AnnouncementPO ap = al.getAnnouncement(id);
		assertTrue(ap.toCommand().equals(ap1.toCommand()));
	}

	public void testRemoveAnnouncement() {
		al.addAnnouncement(ap1);
		String id = al.getAnnouncementList(0, UserType.Admin).get(0).getId();
		al.removeAnnouncement(id);
		assertTrue(!al.containsID("testId"));
	}

	public void testAddAnnouncement() {
		al.addAnnouncement(ap1);
		String id = al.getAnnouncementList(0, UserType.Admin).get(0).getId();
		assertTrue(al.containsID(id));
	}

	public void testUpdateAnnouncement() {
		al.addAnnouncement(ap1);
		ap1.setStatus(1);
		al.updateAnnouncement(ap1);
		String id = al.getAnnouncementList(0, UserType.Admin).get(0).getId();
		assertTrue(al.getAnnouncement(id).toCommand()
				.equals(ap1.toCommand()));
	}

	public void testGetAnnouncementList() {
		al.addAnnouncement(ap1);
		al.addAnnouncement(ap2);
		ArrayList<AnnouncementPO> list = al.getAnnouncementList(0,
				UserType.Admin);
		ap1.setId(list.get(0).getId());
		ap2.setId(list.get(1).getId());
		assertTrue((ap1.toCommand().equals(list.get(0).toCommand()))
				&& (ap2.toCommand().equals(list.get(1).toCommand())));
	}

	public void testGetListHead() {
		String head = al.getListHead();
		assertTrue(head.equals("公告编号；发信人ID；接收对象；标题；发布状态"));
	}

}
