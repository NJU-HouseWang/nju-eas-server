package NJU.HouseWang.nju_eas_server.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.Course_StudentPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public class Course_StudentList {
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
	// 是否包含课程对应学生的记录
		public boolean containsCourse_StudentPO(String year, String courseId, String studentId) {
			String listName = year + "_course_student_list";
			Boolean result = false;
			sql = "select id from " + listName + " where courseId=?, studentId=?;";
			try {
				conn = sqlconn.getConnection();
				ps = conn.prepareStatement(sql);
				ps.setString(1, courseId);
				ps.setString(2,studentId);
				rs = ps.executeQuery();
				result = (rs.next() != false);

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
		}

	
	public Course_StudentPO getCourse_StudentPO(String year, String courseId,String studentId) {
		String listName = year + "_course_student_list";
		Course_StudentPO result = new Course_StudentPO();
		sql = "select * from " + listName + " where courseId=?, studentId=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, courseId);
			ps.setString(2,studentId);
			rs = ps.executeQuery();
			while (rs.next()) {
				result.setDept(rs.getString(1));
				result.setCourseId(rs.getString(2));
				result.setStudentId(rs.getString(3));
				result.setOriginalScore(rs.getInt(4));
				result.setSecondScore(rs.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Feedback removeCourse_StudentPO(String year, String courseId, String studentId) {
		String listName = year + "_course_student_list";
		sql = "delete from " + listName + " where courseId=?, studentId=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, courseId);
			ps.setString(2, studentId);
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			// e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}

	public Feedback addCourse_StudentPO(String year, Course_StudentPO po) {
		String listName = year + "_course_student_list";
		sql = "insert into " + listName
				+ " values (?,?,?,?,?)";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, po.getDept());
			ps.setString(2, po.getCourseId());
			ps.setString(3, po.getStudentId());
			ps.setInt(4, po.getOriginalScore());
			ps.setInt(5, po.getSecondScore());
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			// e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}

	public Feedback updateCourse_StudentPO(String year, Course_StudentPO po) {
		String listName = year + "_course_student_list";
		sql = "update "
				+ listName
				+ " set originalScore=?, secondScore=? where dept=?, courseId=?, studentId=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, po.getOriginalScore());
			ps.setInt(2, po.getSecondScore());
			ps.setString(3, po.getDept());
			ps.setString(4, po.getCourseId());
			ps.setString(5, po.getStudentId());
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			// e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}

	public ArrayList<Course_StudentPO> getListFromCourseId(String year, String dept, String courseId) {
		String listName = year + "_course_student_list";
		ArrayList<Course_StudentPO> result = new ArrayList<Course_StudentPO>();
		sql = "select * from " + listName + "where dept=?,courseId=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Course_StudentPO r = new Course_StudentPO();
				r.setDept(rs.getString(1));
				r.setCourseId(rs.getString(2));
				r.setStudentId(rs.getString(3));
				r.setOriginalScore(rs.getInt(4));
				r.setSecondScore(rs.getInt(5));
				result.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<Course_StudentPO> getListFromStudentId(String year, String dept, String studentId) {
		String listName = year + "_course_student_list";
		ArrayList<Course_StudentPO> result = new ArrayList<Course_StudentPO>();
		sql = "select * from " + listName + "where dept=?,studentId=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Course_StudentPO r = new Course_StudentPO();
				r.setDept(rs.getString(1));
				r.setCourseId(rs.getString(2));
				r.setStudentId(rs.getString(3));
				r.setOriginalScore(rs.getInt(4));
				r.setSecondScore(rs.getInt(5));
				result.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getListHead() {
		return "院系；课程号；学生学号；原始成绩；补考成绩";
	}
}
