package NJU.HouseWang.nju_eas_server.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.StatusPO;

import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;


public class StatusList {
	private String listName = "status_list";
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


	public StatusPO getStatus(String function) {
		StatusPO result = new StatusPO();
		sql = "select * from " + listName + " where function=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, function);
			rs = ps.executeQuery();
			while (rs.next()) {
				result.setFunction(rs.getString(1));
				result.setIsopen(rs.getBoolean(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	

	public Feedback updateStatus(StatusPO status) {
		sql = "update " + listName + " set isopen = ? where function =?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, status.getFunction());
			ps.setBoolean(2, status.getIsopen());
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			// e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}

	public ArrayList<StatusPO> getStatusList() {
		ArrayList<StatusPO> result = new ArrayList<StatusPO>();
	//	sql = "select * from " + listName;
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				StatusPO r = new StatusPO();
				r.setFunction(rs.getString(1));
				r.setIsopen(rs.getBoolean(2));
				result.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
