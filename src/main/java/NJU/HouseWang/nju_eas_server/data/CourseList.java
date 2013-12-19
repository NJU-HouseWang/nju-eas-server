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

	// 是否包含院系课程
	public boolean containsCourse(String listName, String department, String id) {
		Boolean result = false;
		sql = "select id from " + listName + " where department=? and id=?;";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, department);
			ps.setString(2, id);
			rs = ps.executeQuery();
			result = (rs.next() != false);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public CoursePO getCourseFromDeptAndCourseId(String listName,String department, String id) {
		CoursePO result = new CoursePO();
		sql = "select * from " + listName + " where department=? and id=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, department);
			ps.setString(2, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				result.setId(rs.getString(1));
				result.setName(rs.getString(2));
				result.setModule(rs.getString(3));
				result.setType(rs.getString(4));
				result.setNature(rs.getString(5));
				result.setCredit(rs.getInt(6));
				result.setPeriod(rs.getInt(7));
				result.setGrade(rs.getString(8));
				result.setTerm(rs.getString(9));
				result.setDepartment(rs.getString(10));
				result.setStudentNum(rs.getInt(11));
				result.setTeacherId(rs.getString(12));
				result.setTeacherName(rs.getString(13));
				result.setTimeAndPlace(rs.getString(14));
				result.setIntroduction(rs.getString(15));
				result.setBook(rs.getString(16));
				result.setSyllabus(rs.getString(17));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public CoursePO getCourseFromTeacherIdAndCourseId(String listName,String teacherId, String id) {
		CoursePO result = new CoursePO();
		sql = "select * from " + listName + " where teacherId=?, id=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, teacherId);
			ps.setString(2, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				result.setId(rs.getString(1));
				result.setName(rs.getString(2));
				result.setModule(rs.getString(3));
				result.setType(rs.getString(4));
				result.setNature(rs.getString(5));
				result.setCredit(rs.getInt(6));
				result.setPeriod(rs.getInt(7));
				result.setGrade(rs.getString(8));
				result.setTerm(rs.getString(9));
				result.setDepartment(rs.getString(10));
				result.setStudentNum(rs.getInt(11));
				result.setTeacherId(rs.getString(12));
				result.setTeacherName(rs.getString(13));
				result.setTimeAndPlace(rs.getString(14));
				result.setIntroduction(rs.getString(15));
				result.setBook(rs.getString(16));
				result.setSyllabus(rs.getString(17));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Feedback removeCourse(String listName, String department, String id) {
		sql = "delete from " + listName + " where department=? and id=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, department);
			ps.setString(2, id);
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			// e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}

	public Feedback addCourse(String listName, CoursePO Course) {
		sql = "insert into " + listName
				+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
			ps.setString(8, Course.getGrade());
			ps.setString(9, Course.getTerm());
			ps.setString(10, Course.getDepartment());
			ps.setInt(11, Course.getStudentNum());
			ps.setString(12, Course.getTeacherId());
			ps.setString(13, Course.getTeacherName());
			ps.setString(14, Course.getTimeAndPlace());
			ps.setString(15, Course.getIntroduction());
			ps.setString(16, Course.getBook());
			ps.setString(17, Course.getSyllabus());
			
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			// e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}

	public Feedback updateCourse(String listName, CoursePO Course) {
		sql = "update "
				+ listName
				+ " set name=?, module=?,type=?, nature=?,credit=?, period=?, term=?, studentNum=?, teacherId=?, teacherName=?, timeAndPlace=?, introduction=?, book=?, syllabus=?, grade=? where id=? and department=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, Course.getName());
			ps.setString(2, Course.getModule());
			ps.setString(3, Course.getType());
			ps.setString(4, Course.getNature());
			ps.setInt(5, Course.getCredit());
			ps.setInt(6, Course.getPeriod());
			ps.setString(7, Course.getGrade());
			ps.setString(8, Course.getTerm());
			ps.setInt(9, Course.getStudentNum());
			ps.setString(10, Course.getTeacherId());
			ps.setString(11, Course.getTeacherName());
			ps.setString(12, Course.getTimeAndPlace());
			ps.setString(13, Course.getIntroduction());
			ps.setString(14, Course.getBook());
			ps.setString(15, Course.getSyllabus());
			ps.setString(16, Course.getId());
			ps.setString(17, Course.getDepartment());
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			// e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}

	public ArrayList<CoursePO> getCourseListFromTeacherId(String listName, String teacherId) {
		ArrayList<CoursePO> result = new ArrayList<CoursePO>();
		sql = "select * from " + listName+" where teacherId=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, teacherId);
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
				r.setGrade(rs.getString(8));
				r.setTerm(rs.getString(9));
				r.setDepartment(rs.getString(10));
				r.setStudentNum(rs.getInt(11));
				r.setTeacherId(rs.getString(12));
				r.setTeacherName(rs.getString(13));
				r.setTimeAndPlace(rs.getString(14));
				r.setIntroduction(rs.getString(15));
				r.setBook(rs.getString(16));
				r.setSyllabus(rs.getString(17));
				result.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<CoursePO> getCourseListFromGradeAndDept(String listName, String grade, String department) {
		ArrayList<CoursePO> result = new ArrayList<CoursePO>();
		sql = "select * from " + listName+" where grade=? and department=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, grade);
			ps.setString(2, department);
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
				r.setGrade(rs.getString(8));
				r.setTerm(rs.getString(9));
				r.setDepartment(rs.getString(10));
				r.setStudentNum(rs.getInt(11));
				r.setTeacherId(rs.getString(12));
				r.setTeacherName(rs.getString(13));
				r.setTimeAndPlace(rs.getString(14));
				r.setIntroduction(rs.getString(15));
				r.setBook(rs.getString(16));
				r.setSyllabus(rs.getString(17));
				result.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<CoursePO> getCourseListFromDept(String listName, String department) {
		ArrayList<CoursePO> result = new ArrayList<CoursePO>();
		sql = "select * from " + listName+" where department=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, department);
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
				r.setGrade(rs.getString(8));
				r.setTerm(rs.getString(9));
				r.setDepartment(rs.getString(10));
				r.setStudentNum(rs.getInt(11));
				r.setTeacherId(rs.getString(12));
				r.setTeacherName(rs.getString(13));
				r.setTimeAndPlace(rs.getString(14));
				r.setIntroduction(rs.getString(15));
				r.setBook(rs.getString(16));
				r.setSyllabus(rs.getString(17));
				result.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String getCourseListHead() {
		return "课程号；课程名称；课程性质；学分；学时；年级；开设学期；开设院系；任课教师姓名；上课时间及地点";

	}
	
	
}
	
