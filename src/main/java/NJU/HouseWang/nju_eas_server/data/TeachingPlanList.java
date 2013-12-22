package NJU.HouseWang.nju_eas_server.data;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.dataService.TeachingPlanListService;
import NJU.HouseWang.nju_eas_server.po.Edu.TeachingPlanPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public class TeachingPlanList implements TeachingPlanListService{
	private String listName = "teachingplan_list";
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


	public TeachingPlanPO getTeachingPlan(String dept) {
		TeachingPlanPO result = new TeachingPlanPO();
		sql = "select * from " + listName + " where dept=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, dept);
			rs = ps.executeQuery();
			while (rs.next()) {
				result.setDept(rs.getString(1));
				result.setCommitted(rs.getBoolean(2));
				result.setStatus(rs.getInt(3));
				result.setTpFile(new File(rs.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Feedback updateTeachingPlanItem(TeachingPlanPO TeachingPlan) {
		sql = "update "
				+ listName
				+ " set isCommitted=?, status=?, tpFile=? where dept=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setBoolean(1, TeachingPlan.isCommitted());
			ps.setInt(2, TeachingPlan.getStatus());
			ps.setString(3, TeachingPlan.getTpFile().getName());
			ps.setString(4, TeachingPlan.getDept());
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			// e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}

	public ArrayList<TeachingPlanPO> getTeachingPlanList() {
		ArrayList<TeachingPlanPO> result = new ArrayList<TeachingPlanPO>();
		sql = "select * from " + listName;
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			// ps.setString(1, conditions);
			rs = ps.executeQuery();
			while (rs.next()) {
				TeachingPlanPO r = new TeachingPlanPO();
				r.setDept(rs.getString(1));
				r.setCommitted(rs.getBoolean(2));
				r.setStatus(rs.getInt(3));
				r.setTpFile(new File(rs.getString(4)));
				result.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getListHead() {
		return "院系；提交状态；审核状态";
	}

}
