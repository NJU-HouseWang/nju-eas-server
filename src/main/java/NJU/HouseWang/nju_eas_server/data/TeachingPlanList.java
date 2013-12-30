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
/**
 * 教学计划列表
 * @author 教化场
 * @version 2013-11-17
 */
public class TeachingPlanList implements TeachingPlanListService {
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
	/**
	 * 获取教学计划
	 * @param dept 院系
	 * @return 教学计划PO
	 */
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
				result.setCommitted(Boolean.valueOf(rs.getString(2)));
				result.setStatus(rs.getInt(3));
				if (!(rs.getString(4) == null || rs.getString(4).equals("null"))) {
					result.setTpFile(new File(rs.getString(4)));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 编辑教学计划
	 * @param TeachingPlan 教学计划
	 * @return 反馈
	 */
	public Feedback updateTeachingPlanItem(TeachingPlanPO TeachingPlan) {
		System.out.println("---------------" + TeachingPlan);
		sql = "update " + listName
				+ " set isCommitted=?, status=?, tpFile=? where dept=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, "" + TeachingPlan.isCommitted());
			ps.setInt(2, TeachingPlan.getStatus());
			if (TeachingPlan.getTpFile() != null) {
				ps.setString(3, TeachingPlan.getTpFile().getPath());
			} else {
				ps.setString(3, "null");
			}
			ps.setString(4, TeachingPlan.getDept());
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}
	/**
	 * 获取教学计划列表
	 * @return 教学计划列表
	 */
	public ArrayList<TeachingPlanPO> getTeachingPlanList() {
		ArrayList<TeachingPlanPO> result = new ArrayList<TeachingPlanPO>();
		sql = "select * from " + listName;
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				TeachingPlanPO r = new TeachingPlanPO();
				r.setDept(rs.getString(1));
				r.setCommitted(Boolean.valueOf(rs.getString(2)));
				r.setStatus(rs.getInt(3));
				r.setTpFile(new File(rs.getString(4)));
				result.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 获取列表表头
	 * @return 表头
	 */
	public String getListHead() {
		return "院系；提交状态；审核状态";
	}

}
