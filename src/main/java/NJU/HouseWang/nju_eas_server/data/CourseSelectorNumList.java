package NJU.HouseWang.nju_eas_server.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import NJU.HouseWang.nju_eas_server.dataService.CourseSelectorNumListService;
import NJU.HouseWang.nju_eas_server.po.Edu.CourseSelectorNumPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public class CourseSelectorNumList implements CourseSelectorNumListService{
	private String listName = "course_selector_num_list";
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

	public Feedback delList(){
		sql ="truncate " +listName;
		return Feedback.OPERATION_SUCCEED;
	}

	public Feedback updateCourseSelectorNumPO(CourseSelectorNumPO p){
		sql = "update "
				+ listName
				+ " set selectorNum=?, totalNum=? where courseId=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, p.getSelectorNum());
			ps.setInt(2, p.getTotalNum());
			ps.setString(3, p.getCourseId());
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			// e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}
	public Feedback addCourseSelectorNumPO(CourseSelectorNumPO p) {
		sql = "insert into " + listName
				+ " values (?,?,?)";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, p.getCourseId());
			ps.setInt(2, p.getSelectorNum());
			ps.setInt(3, p.getTotalNum());
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			// e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}
	
	public CourseSelectorNumPO getCourseSelectorNumPO(String courseId) {
		CourseSelectorNumPO result = new CourseSelectorNumPO();
		sql = "select * from " + listName + " where courseId=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, courseId);
			rs = ps.executeQuery();
			while (rs.next()) {
				result.setCourseId(rs.getString(1));
				result.setSelectorNum(rs.getInt(2));
				result.setTotalNum(rs.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	/*
	public ArrayList<CourseSelectorNumPO> getCourseSelectorNumList() {
		ArrayList<CourseSelectorNumPO> result = new ArrayList<CourseSelectorNumPO>();
		sql = "select * from " + listName;
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				CourseSelectorNumPO r = new CourseSelectorNumPO();
				r.setCourseId(rs.getString(1));
				r.setSelectorNum(rs.getInt(2));
				r.setTotalNum(rs.getInt(3));
				result.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}*/
}
