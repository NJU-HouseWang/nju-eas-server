package NJU.HouseWang.nju_eas_server.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.User.GuestPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_server.systemMessage.UserType;

public class LoginList {
	private String listName = "login_list";
	private String sql = null;
	private SQLConnector sqlconn = new SQLConnector();
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	// 连接数据库
	public void init() {
		try {
			sqlconn.createConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 关闭数据库
	public void finish() {
		try {
			sqlconn.shutdownConnection();
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 是否包含ID
	public boolean containsID(String id) {
		Boolean result = false;
		sql = "select id from " + listName + " where id=?;";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			result = (rs.next() != false);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public GuestPO getLoginer(String id) {
		GuestPO result = new GuestPO();
		sql = "select * from " + listName + " where id=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				result.setId(rs.getString(1));
				result.setType(UserType.valueOf(rs.getString(2)));
				result.setPassword(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Feedback removeLoginer(String id) {
		sql = "delete from " + listName + " where id=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			// e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}

	public Feedback addLoginer(GuestPO user) {
		sql = "insert into " + listName + " values (?,?,?)";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getId());
			ps.setString(2, user.getType().toString());
			ps.setString(3, user.getPassword());
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			// e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}

	public Feedback updateLoginer(GuestPO user) {
		sql = "update " + listName + " set userType=?, password=? where id=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getType().toString());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getId());
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			// e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}

	public ArrayList<GuestPO> getLoginList(String conditions) {
		ArrayList<GuestPO> result = new ArrayList<GuestPO>();
		sql = "select * from " + listName;
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				GuestPO r = new GuestPO();
				r.setId(rs.getString(1));
				r.setType(UserType.valueOf(rs.getString(2)));
				r.setPassword(rs.getString(3));
				result.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String getListHead(){
		return "用户名；用户类型；用户密码";
	}

	public static void main(String[] args) {
		LoginList ll = new LoginList();
		ll.init();
		System.out.println(ll.containsID("admin"));
		System.out.println(ll.containsID("admin1"));
		System.out.println(ll.getLoginer("121250157"));
		System.out.println(ll.removeLoginer("121250157"));
		System.out.println(ll.containsID("121250157"));
		System.out.println(ll.removeLoginer("121250157"));
		System.out.println(ll.containsID("121250157"));
		System.out.println(ll.addLoginer(new GuestPO("121250157",UserType.Student,"bilicrazy123")));
		System.out.println(ll.containsID("121250157"));
		System.out.println(ll.getLoginer("121250157"));
		System.out.println(ll.updateLoginer(new GuestPO("121250157",UserType.Student,"bilicrazy124")));
		System.out.println(ll.getLoginer("121250157"));
		ArrayList<GuestPO> l = ll.getLoginList(null);
		for(GuestPO g:l) {
			System.out.println(g);
		}
		ll.finish();
	}
}
