package NJU.HouseWang.nju_eas_server.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.PlannedCoursePO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public class PlannedCourseList {
	private String listName = "planned_course_list";
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

	public PlannedCoursePO getPlannedCourse(String id) {
		PlannedCoursePO result = new PlannedCoursePO();
		sql = "select * from " + listName + " where id=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				result.setId(rs.getString(1));
				result.setName(rs.getString(2));
				result.setModule(rs.getString(3));
				result.setType(rs.getString(4));
				result.setNature(rs.getString(5));
				result.setCredit(rs.getInt(6));
				result.setPeriod(rs.getInt(7));
				result.setTerm(rs.getString(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Feedback removePlannedCourse(String id) {
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

	public Feedback addPlannedCourse(PlannedCoursePO PlannedCourse) {
		sql = "insert into " + listName
				+ " values (?,?,?,?,?,?,?,?)";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, PlannedCourse.getId());
			ps.setString(2, PlannedCourse.getName());
			ps.setString(3, PlannedCourse.getModule());
			ps.setString(4, PlannedCourse.getType());
			ps.setString(5, PlannedCourse.getNature());
			ps.setInt(6, PlannedCourse.getCredit());
			ps.setInt(7, PlannedCourse.getPeriod());
			ps.setString(8, PlannedCourse.getTerm());
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			// e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}

	public Feedback updatePlannedCourse(PlannedCoursePO PlannedCourse) {
		sql = "update "
				+ listName
				+ " set name=?, module=?,type=?, nature=?,credit=?, period=?, term=? where id=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, PlannedCourse.getName());
			ps.setString(2, PlannedCourse.getModule());
			ps.setString(3, PlannedCourse.getType());
			ps.setString(4, PlannedCourse.getNature());
			ps.setInt(5, PlannedCourse.getCredit());
			ps.setInt(6, PlannedCourse.getPeriod());
			ps.setString(7, PlannedCourse.getTerm());
			ps.setString(8, PlannedCourse.getId());
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			// e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}

	public ArrayList<PlannedCoursePO> getPlannedCourseList(String conditions) {
		ArrayList<PlannedCoursePO> result = new ArrayList<PlannedCoursePO>();
		sql = "select * from " + listName;
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			// ps.setString(1, conditions);
			rs = ps.executeQuery();
			while (rs.next()) {
				PlannedCoursePO r = new PlannedCoursePO();
				r.setId(rs.getString(1));
				r.setName(rs.getString(2));
				r.setModule(rs.getString(3));
				r.setType(rs.getString(4));
				r.setNature(rs.getString(5));
				r.setCredit(rs.getInt(6));
				r.setPeriod(rs.getInt(7));
				r.setTerm(rs.getString(8));
				result.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getListHead() {
		return "课程号；课程名称；所属模块；课程类别；课程性质；学分；学时；开设学期";
	}
}
