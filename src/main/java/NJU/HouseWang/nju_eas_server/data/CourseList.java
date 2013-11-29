package NJU.HouseWang.nju_eas_server.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.CoursePO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public class CourseList {
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
	public boolean containsID(String year, String id) {
		String listName = year + "_course_list";
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

	public CoursePO getCourse(String year, String id) {
		String listName = year + "_course_list";
		CoursePO result = new CoursePO();
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
				result.setDepartment(rs.getString(9));
				result.setGrade(rs.getString(10));
				result.setStudentNum(rs.getInt(11));
				result.setTeacherId(rs.getString(12));
				result.setTeacherName(rs.getString(13));
				result.setTime(rs.getString(14));
				result.setPlace(rs.getString(15));
				result.setIntroduction(rs.getString(16));
				result.setBook(rs.getString(17));
				result.setSyllabus(rs.getString(18));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Feedback removeCourse(String year, String id) {
		String listName = year + "_course_list";
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

	public Feedback addCourse(String year, CoursePO Course) {
		String listName = year + "_course_list";
		sql = "insert into " + listName
				+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, Course.getId());
			ps.setString(2, Course.getName());
			ps.setString(3, Course.getModule());
			ps.setString(4, Course.getType());
			ps.setString(5, Course.getNature());
			ps.setInt(6, Course.getCredit());
			ps.setInt(7, Course.getPeriod());
			ps.setString(8, Course.getTerm());
			ps.setString(9, Course.getDepartment());
			ps.setString(10, Course.getGrade());
			ps.setInt(11, Course.getStudentNum());
			ps.setString(12, Course.getTeacherId());
			ps.setString(13, Course.getTeacherName());
			ps.setString(14, Course.getTime());
			ps.setString(15, Course.getPlace());
			ps.setString(16, Course.getIntroduction());
			ps.setString(17, Course.getBook());
			ps.setString(18, Course.getSyllabus());
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			// e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}

	public Feedback updateCourse(String year, CoursePO Course) {
		String listName = year + "_course_list";
		sql = "update "
				+ listName
				+ " set name=?, module=?,type=?, nature=?,credit=?, period=?, term=?, department=?, grade=?, studentNum=?, teacherId=?, teacherName=?, time=?, place=?, introduction=?, book=?, syllabus=? where id=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, Course.getName());
			ps.setString(2, Course.getModule());
			ps.setString(3, Course.getType());
			ps.setString(4, Course.getNature());
			ps.setInt(5, Course.getCredit());
			ps.setInt(6, Course.getPeriod());
			ps.setString(7, Course.getTerm());
			ps.setString(8, Course.getDepartment());
			ps.setString(9, Course.getGrade());
			ps.setInt(10, Course.getStudentNum());
			ps.setString(11, Course.getTeacherId());
			ps.setString(12, Course.getTeacherName());
			ps.setString(13, Course.getTime());
			ps.setString(14, Course.getPlace());
			ps.setString(15, Course.getIntroduction());
			ps.setString(16, Course.getBook());
			ps.setString(17, Course.getSyllabus());
			ps.setString(18, Course.getId());
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			// e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}

	public ArrayList<CoursePO> getCourseList(String year, String conditions) {
		String listName = year + "_course_list";
		ArrayList<CoursePO> result = new ArrayList<CoursePO>();
		sql = "select * from " + listName;
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			// ps.setString(1, conditions);
			rs = ps.executeQuery();
			while (rs.next()) {
				CoursePO r = new CoursePO();
				r.setId(rs.getString(1));
				r.setName(rs.getString(2));
				r.setModule(rs.getString(3));
				r.setType(rs.getString(4));
				r.setNature(rs.getString(5));
				r.setCredit(rs.getInt(6));
				r.setPeriod(rs.getInt(7));
				r.setTerm(rs.getString(8));
				r.setDepartment(rs.getString(9));
				r.setGrade(rs.getString(10));
				r.setStudentNum(rs.getInt(11));
				r.setTeacherId(rs.getString(12));
				r.setTeacherName(rs.getString(13));
				r.setTerm(rs.getString(14));
				r.setTerm(rs.getString(15));
				r.setTerm(rs.getString(16));
				r.setTerm(rs.getString(17));
				r.setTerm(rs.getString(18));
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
