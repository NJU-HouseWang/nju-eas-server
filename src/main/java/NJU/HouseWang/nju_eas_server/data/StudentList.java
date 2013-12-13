package NJU.HouseWang.nju_eas_server.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.User.StudentPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public class StudentList {
	private String listName = "student_list";
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

	public StudentPO getStudent(String id) {
		StudentPO result = new StudentPO();
		sql = "select * from " + listName + " where id=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				result.setId(rs.getString(1));
				result.setName(rs.getString(2));
				result.setDepartment(rs.getString(3));
				result.setMajor(rs.getString(4));
				result.setGrade(rs.getString(5));
				result.setClassNo(rs.getString(6));
				result.setDuration(rs.getString(7));
				result.setEnrollmentStatus(rs.getString(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Feedback removeStudent(String id) {
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

	public Feedback addStudent(StudentPO user) {
		sql = "insert into " + listName + " values (?,?,?,?,?,?,?,?)";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getId());
			ps.setString(2, user.getName());
			ps.setString(3, user.getDepartment());
			ps.setString(4, user.getMajor());
			ps.setString(5, user.getGrade());
			ps.setString(6, user.getClassNo());
			ps.setString(7, user.getDuration());
			ps.setString(8, user.getEnrollmentStatus());
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			// e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}

	public Feedback updateStudent(StudentPO user) {
		sql = "update "
				+ listName
				+ " set name=?, department=?, major=?, grade=?, classNo=?, duration=?, enrollmentStatus=? where id=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getDepartment());
			ps.setString(3, user.getMajor());
			ps.setString(4, user.getGrade());
			ps.setString(5, user.getClassNo());
			ps.setString(6, user.getDuration());
			ps.setString(7, user.getEnrollmentStatus());
			ps.setString(8, user.getId());
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			// e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}

	public ArrayList<StudentPO> getStudentList(String grade, String department) {
		ArrayList<StudentPO> result = new ArrayList<StudentPO>();
		sql = "select * from " + listName + "where grade=?, department=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, grade);
			ps.setString(2, department);
			rs = ps.executeQuery();
			while (rs.next()) {
				StudentPO r = new StudentPO();
				r.setId(rs.getString(1));
				r.setName(rs.getString(2));
				r.setDepartment(rs.getString(3));
				r.setMajor(rs.getString(4));
				r.setGrade(rs.getString(5));
				r.setClassNo(rs.getString(6));
				r.setDuration(rs.getString(7));
				r.setEnrollmentStatus(rs.getString(8));
				result.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<StudentPO> getStudentList() {
		ArrayList<StudentPO> result = new ArrayList<StudentPO>();
		sql = "select * from " + listName;
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				StudentPO r = new StudentPO();
				r.setId(rs.getString(1));
				r.setName(rs.getString(2));
				r.setDepartment(rs.getString(3));
				r.setMajor(rs.getString(4));
				r.setGrade(rs.getString(5));
				r.setClassNo(rs.getString(6));
				r.setDuration(rs.getString(7));
				r.setEnrollmentStatus(rs.getString(8));
				result.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<String> getGradeList() {
		ArrayList<String> result = new ArrayList<String>();
		sql = "select * from " + listName ;
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String r = (rs.getString(5));
				result.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getListHead() {
		return "学号；姓名；院系；专业；年级；班级编号；学制；学籍状态";
	}

	public ArrayList<String> getAllGrade() {
		ArrayList<String> result = new ArrayList<String>();
		sql = "select grade from " + listName;
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				result.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
