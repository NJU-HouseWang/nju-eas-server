package NJU.HouseWang.nju_eas_server.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.dataService.CourseSelectionListService;
import NJU.HouseWang.nju_eas_server.po.Edu.CourseSelectionPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;
/**
 * 选课列表类
 * @author 教化场
 * @version 2013-11-13
 */
public class CourseSelectionList implements CourseSelectionListService{
	private String listName = "course_selection_list";
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
	 * 删除列表
	 * @return 反馈
 	 */
	public Feedback delList() {
		sql = "truncate " + listName;
		return Feedback.OPERATION_SUCCEED;
	}

	/**
	 * 判断是否包含选课记录
	 * @param courseId 课程号
	 * @param studentId 学号
	 * @return 是否包含选课记录
	 */
	public boolean containsCourseSelection(String courseId, String studentId) {
		Boolean result = false;
		sql = "select * from " + listName
				+ " where courseId=? and studentId=?;";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, courseId);
			ps.setString(2, studentId);
			rs = ps.executeQuery();
			result = (rs.next() != false);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 删除选课记录
	 * @param courseId 课程号
	 * @param studentId 学号
	 * @return 反馈
	 */
	public Feedback removeCourseSelection(String courseId, String studentId) {
		sql = "delete from " + listName + " where courseId=? and studentId=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, courseId);
			ps.setString(2, studentId);
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}
	/**
	 * 添加选课记录
	 * @param CourseSelection 选课记录
	 * @return 反馈
	 */
	public Feedback addCourseSelection(CourseSelectionPO CourseSelection) {
		sql = "insert into " + listName + " values (?,?,?)";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, CourseSelection.getCourseId());
			ps.setString(2, CourseSelection.getStudentId());
			ps.setInt(3, CourseSelection.getPriority());
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}
	/**
	 * 由课程号获取选课记录
	 * @param courseId 课程号
	 * @return 选课记录列表
	 */
	public ArrayList<CourseSelectionPO> getCourseSelectionListFromCourseId(
			String courseId) {
		ArrayList<CourseSelectionPO> result = new ArrayList<CourseSelectionPO>();
		sql = "select * from " + listName + " where courseId=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, courseId);
			rs = ps.executeQuery();
			while (rs.next()) {
				CourseSelectionPO r = new CourseSelectionPO();
				r.setCourseId(rs.getString(1));
				r.setStudentId(rs.getString(2));
				r.setPriority(rs.getInt(3));
				result.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 由学号获取选课记录列表
	 * @param studentId 学号
	 * @return 选课记录列表
	 */
	public ArrayList<CourseSelectionPO> getCourseSelectionListFromStudentId(
			String studentId) {
		ArrayList<CourseSelectionPO> result = new ArrayList<CourseSelectionPO>();
		sql = "select * from " + listName + " where studentId=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, studentId);
			rs = ps.executeQuery();
			while (rs.next()) {
				CourseSelectionPO r = new CourseSelectionPO();
				r.setCourseId(rs.getString(1));
				r.setStudentId(rs.getString(2));
				r.setPriority(rs.getInt(3));
				result.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 获取选课记录列表
	 * @return 选课记录列表
	 */
	public ArrayList<CourseSelectionPO> getCourseSelectionList() {
		ArrayList<CourseSelectionPO> result = new ArrayList<CourseSelectionPO>();
		sql = "select * from " + listName;
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				CourseSelectionPO r = new CourseSelectionPO();
				r.setCourseId(rs.getString(1));
				r.setStudentId(rs.getString(2));
				r.setPriority(rs.getInt(3));
				result.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
