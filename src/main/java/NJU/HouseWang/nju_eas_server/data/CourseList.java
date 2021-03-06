package NJU.HouseWang.nju_eas_server.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.dataService.CourseListService;
import NJU.HouseWang.nju_eas_server.po.Edu.CoursePO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;
/**
 * 课程列表类
 * @author 教化场
 * @version 2013-11-13
 */
public class CourseList implements CourseListService {
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
	 * 创建课程列表
	 * @param term 学期
	 * @return 反馈
	 */
	public Feedback createCourseList(String term) {
		sql = "create table " + term + "_course_list(" + "id varchar(12),"
				+ "name varchar(45)," + "module varchar(45),"
				+ "type varchar(45)," + "nature varchar(45)," + "credit int,"
				+ "period int," + "grade varchar(45)," + "term varchar(45),"
				+ "department varchar(45)," + "studentNum int,"
				+ "teacherId varchar(45)," + "teacherName varchar(45),"
				+ "timeAndPlace varchar(45)," + "introduction text,"
				+ "book text," + "syllabus text,"
				+ "primary key(id))engine myisam,charset gbk;";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}
	/**
	 * 弃选课程列表
	 * @param term 学期
	 * @return 反馈
	 */
	public Feedback dropCourseList(String term) {
		sql = "drop table " + term + "_course_list";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}

	/**
	 * 判断是否包含课程
	 * @param term 学期
	 * @param department 院系
	 * @param id 课程号
	 * @return 是否包含课程
	 */
	public boolean containsCourse(String term, String department, String id) {
		Boolean result = false;
		sql = "select id from " + term
				+ "_course_list where department=? and id=?;";
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
	/**
	 * 由院系和课程号获取课程
	 * @param term 学期
	 * @param department 院系
	 * @param id 课程号
	 * @return 课程PO
	 */
	public CoursePO getCourseFromDeptAndCourseId(String term,
			String department, String id) {
		CoursePO result = new CoursePO();
		sql = "select * from " + term
				+ "_course_list where department=? and id=?";
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
	/**
	 * 由教师工号和课程号获取课程
	 * @param term 学期
	 * @param teacherId 教师工号
	 * @param id 课程号
	 * @return 课程PO
	 */
	public CoursePO getCourseFromTeacherIdAndCourseId(String term,
			String teacherId, String id) {
		CoursePO result = new CoursePO();
		sql = "select * from " + term + "_course_list where teacherId=?, id=?";
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
	/**
	 * 删除课程
	 * @param term 学期
	 * @param department 院系
	 * @param id 课程号
	 * @return 反馈
	 */
	public Feedback removeCourse(String term, String department, String id) {
		sql = "delete from " + term
				+ "_course_list where department=? and id=?";
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
	/**
	 * 添加课程
	 * @param term 学期
	 * @param Course 课程PO
	 * @return 反馈
	 */
	public Feedback addCourse(String term, CoursePO Course) {
		System.out.println(term);
		System.out.println(Course);
		sql = "insert into " + term
				+ "_course_list values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
			e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}
	/**
	 * 编辑课程
	 * @param term 学期
	 * @param Course 课程PO
	 * @return 反馈
	 */
	public Feedback updateCourse(String term, CoursePO Course) {
		sql = "update "
				+ term
				+ "_course_list set name=?, module=?,type=?, nature=?,credit=?, period=?, grade=?, term=?, studentNum=?, teacherId=?, teacherName=?, timeAndPlace=?, introduction=?, book=?, syllabus=? where id=? and department=?";
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
			e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}
	/**
	 * 由教师工号获取课程列表
	 * @param term 学期
	 * @param teacherId 教师工号
	 * @return 课程列表
	 */
	public ArrayList<CoursePO> getCourseListFromTeacherId(String term,
			String teacherId) {
		ArrayList<CoursePO> result = new ArrayList<CoursePO>();
		sql = "select * from " + term + "_course_list where teacherId=?";
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
	/**
	 * 由年级和院系获取课程列表
	 * @param term 学期
	 * @param grade 年级
	 * @param department 院系
	 * @return 课程列表
	 */
	public ArrayList<CoursePO> getCourseListFromGradeAndDept(String term,
			String grade, String department) {
		ArrayList<CoursePO> result = new ArrayList<CoursePO>();
		sql = "select * from " + term
				+ "_course_list where grade=? and department=?";
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
	/**
	 * 由院系获取课程列表
	 * @param term 学期
 	 * @param department 院系
	 * @return 课程列表
	 */
	public ArrayList<CoursePO> getCourseListFromDept(String term,
			String department) {
		ArrayList<CoursePO> result = new ArrayList<CoursePO>();
		sql = "select * from " + term + "_course_list where department=?";
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
	/**
	 * 获取课程列表表头
	 * @return 表头
	 */
	public String getCourseListHead() {
		return "课程号；课程名称；课程性质；学分；学时；年级；开设学期；开设院系；任课教师姓名；上课时间及地点";

	}
}
