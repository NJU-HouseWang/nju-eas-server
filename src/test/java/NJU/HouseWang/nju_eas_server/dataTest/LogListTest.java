package NJU.HouseWang.nju_eas_server.dataTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import junit.framework.TestCase;
import NJU.HouseWang.nju_eas_server.data.LogList;
import NJU.HouseWang.nju_eas_server.data.SQLConnector;
import NJU.HouseWang.nju_eas_server.po.Msg.LogPO;

public class LogListTest extends TestCase {
	private LogList ll = null;
	private LogPO newLog1 = null;
	private LogPO newLog2 = null;

	private String sql = null;
	private SQLConnector sqlconn = new SQLConnector();
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	protected void setUp() throws Exception {
		ll = new LogList();
		ll.init();
		newLog1 = new LogPO("test", "testIP", "testTime", "testContent");
		newLog2 = new LogPO("test1", "testIP1", "testTime1", "testContent1");
	}

	protected void tearDown() throws Exception {
		sql = "delete from log_list where name=?";
		try {
			sqlconn.createConnection();
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, "test");
			ps.execute();
		} catch (SQLException e) {
		}
		sql = "delete from log_list where name=?";
		try {
			sqlconn.createConnection();
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, "test1");
			ps.execute();
		} catch (SQLException e) {
		}
		ll.finish();
	}

	public void testAddLog() {
		ll.addLog(newLog1);
		sql = "select * from log_list where name=?";
		try {
			sqlconn.createConnection();
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, "test");
			rs = ps.executeQuery();
			rs.next();
			assertTrue(rs.getString(2).equals("test"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testGetLogList() {
		ll.addLog(newLog1);
		ll.addLog(newLog2);
		ArrayList<LogPO> list = ll.getLogList();
		LogPO l1 = list.get(list.size() - 1);
		LogPO l2 = list.get(list.size() - 2);
		
		boolean r1 = l2.toCommand().equals(newLog1.toCommand());
		boolean r2 = l1.toCommand().equals(newLog2.toCommand());
		
		assertTrue(r1 == r2);
	}

	public void testGetListHead() {
		String s = ll.getListHead();
		assertTrue("用户名；用户IP；操作时间；操作内容".equals(s));
	}

}
