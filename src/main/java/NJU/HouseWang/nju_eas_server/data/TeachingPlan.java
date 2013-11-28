package NJU.HouseWang.nju_eas_server.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.TeachingPlanItemPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public class TeachingPlan {
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


	public ArrayList<TeachingPlanItemPO> getTeachingPlan(String dept) {
		ArrayList<TeachingPlanItemPO> result = new ArrayList<TeachingPlanItemPO>();
		String listName = dept + "_teachingplan_list";
		sql = "select * from " + listName;
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				TeachingPlanItemPO r = new TeachingPlanItemPO();
				r.setModuleId(rs.getString(1));
				r.setModuleName(rs.getString(2));
				r.setModuleCredit(rs.getInt(3));
				r.setCourseNature(rs.getString(4));
				r.setCourseType(rs.getString(5));
				r.setTypeCredit(rs.getInt(6));
				r.setCourseId(rs.getString(7));
				r.setCourseName(rs.getString(8));
				r.setCourseCredit(rs.getInt(9));
				r.setStartTerm(rs.getInt(10));
				r.setEndTerm(rs.getInt(11));
				result.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Feedback delTeachingPlan(String dept){
		String listName = dept + "_teachingplan_list";
		sql ="truncate " +listName;
		return Feedback.OPERATION_SUCCEED;
	}
	
	public Feedback addTeachingPlanItem(String dept, TeachingPlanItemPO ep){	
		String listName = dept + "_teachingplan_list";
		sql = "insert into " + listName + " values (?,?,?,?,?,?,?,?,?,?,?)";
			try {
				conn = sqlconn.getConnection();
				ps = conn.prepareStatement(sql);
				ps.setString(1, ep.getModuleId());
				ps.setString(2, ep.getModuleName());
				ps.setInt(3, ep.getModuleCredit());
				ps.setString(4,ep.getCourseNature());
				ps.setString(5,ep.getCourseType());
				ps.setInt(6,ep.getTypeCredit());
				ps.setString(7,ep.getCourseId());
				ps.setString(8,ep.getCourseName());
				ps.setInt(9,ep.getCourseCredit());
				ps.setInt(10,ep.getStartTerm());
				ps.setInt(11,ep.getEndTerm());
				
				ps.execute();
				return Feedback.OPERATION_SUCCEED;
			} catch (SQLException e) {
				// e.printStackTrace();
				return Feedback.OPERATION_FAIL;
			}
	}
	
	public String getListHead() {
		return "课程模块(学分)；课程性质；课程类别(学分)；课程号；课程名称；课程学分；开设学期";
	}
}