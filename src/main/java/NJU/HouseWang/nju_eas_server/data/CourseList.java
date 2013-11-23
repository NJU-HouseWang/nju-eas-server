package NJU.HouseWang.nju_eas_server.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.CoursePO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public class CourseList {
	private String listName = "course_list";
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

	public CoursePO getCourse(String id) {
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
				result.setCredit(Integer.parseInt(rs.getString(6)));
				result.setPeriod(Integer.parseInt(rs.getString(7)));
				result.setDepartment(rs.getString(8));
				result.setTeacher(rs.getString(9));
				result.setTime(rs.getString(10));
				result.setPlace(rs.getString(11));
				result.setIntroduction(rs.getString(12));
				result.setBook(rs.getString(13));
				result.setSyllabus(rs.getString(14));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Feedback removeCourse(String id) {
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

	public Feedback addCourse(CoursePO course) {
		sql = "insert into " + listName
				+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, course.getId());
			ps.setString(2, course.getName());
			ps.setString(3, course.getModule());
			ps.setString(4, course.getType());
			ps.setString(5, course.getNature());
			ps.setInt(6, course.getCredit());
			ps.setInt(7, course.getPeriod());
			ps.setString(8, course.getDepartment());
			ps.setString(9, course.getTeacher());
			ps.setString(10, course.getTime());
			ps.setString(11, course.getPlace());
			ps.setString(12, course.getIntroduction());
			ps.setString(13, course.getBook());
			ps.setString(14, course.getSyllabus());
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			// e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}

	public Feedback updateCourse(CoursePO course) {
		sql = "update "
				+ listName
				+ " set name=?, module=?,type=?, nature=?,credit=?, period=?,department=?, teacher=?,time=?, place=?, introduction=?, book=?, syllabus=? where id=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, course.getName());
			ps.setString(2, course.getModule());
			ps.setString(3, course.getType());
			ps.setString(4, course.getNature());
			ps.setInt(5, course.getCredit());
			ps.setInt(6, course.getPeriod());
			ps.setString(7, course.getDepartment());
			ps.setString(8, course.getTeacher());
			ps.setString(9, course.getTime());
			ps.setString(10, course.getPlace());
			ps.setString(11, course.getIntroduction());
			ps.setString(12, course.getBook());
			ps.setString(13, course.getSyllabus());
			ps.setString(14, course.getId());
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			// e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}

	public ArrayList<CoursePO> getCourseList(String conditions) {
		ArrayList<CoursePO> result = new ArrayList<CoursePO>();
		sql = "select * from " + listName;
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				CoursePO r = new CoursePO();
				r.setId(rs.getString(1));
				r.setName(rs.getString(2));
				r.setModule(rs.getString(3));
				r.setType(rs.getString(4));
				r.setNature(rs.getString(5));
				r.setCredit(Integer.parseInt(rs.getString(6)));
				r.setPeriod(Integer.parseInt(rs.getString(7)));
				r.setDepartment(rs.getString(8));
				r.setTeacher(rs.getString(9));
				r.setTime(rs.getString(10));
				r.setPlace(rs.getString(11));
				r.setIntroduction(rs.getString(12));
				r.setBook(rs.getString(13));
				r.setSyllabus(rs.getString(14));
				result.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
