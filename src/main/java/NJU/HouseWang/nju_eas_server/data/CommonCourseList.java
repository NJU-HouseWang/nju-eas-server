package NJU.HouseWang.nju_eas_server.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.dataService.CommonCourseListService;
import NJU.HouseWang.nju_eas_server.po.Edu.CoursePO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;
/**
 * 通识课列表
 * @author 教化场
 * @version 2013-11-12
 */
public class CommonCourseList implements CommonCourseListService{
	private String listName = "common_course_list";
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
	 * 判断是否包含课程
	 * @param id 课程号
	 * @return 是否包含课程
	 */
	public boolean containsCourse(String id) {
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
	/**
	 * 获取课程
	 * @param id 课程号
	 * @return 课程PO
	 */
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
				result.setCredit(rs.getInt(6));
				result.setPeriod(rs.getInt(7));
				result.setStudentNum(rs.getInt(8));
				result.setTeacherId(rs.getString(9));
				result.setTeacherName(rs.getString(10));
				result.setTimeAndPlace(rs.getString(11));
				result.setIntroduction(rs.getString(12));
				result.setBook(rs.getString(13));
				result.setSyllabus(rs.getString(14));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 删除课程
	 * @param id 课程号
	 * @return 反馈
 	 */
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
	/**
	 * 添加课程
	 * @param Course 课程PO
	 * @return 反馈
	 */
	public Feedback addCourse(CoursePO Course) {
		sql = "insert into " + listName
				+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
			ps.setInt(8, Course.getStudentNum());
			ps.setString(9, Course.getTeacherId());
			ps.setString(10, Course.getTeacherName());
			ps.setString(11, Course.getTimeAndPlace());
			ps.setString(12, Course.getIntroduction());
			ps.setString(13, Course.getBook());
			ps.setString(14, Course.getSyllabus());
			
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			 e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}
	/**
	 * 编辑课程
	 * @param Course 课程PO
	 * @return 反馈
	 */
	public Feedback updateCourse(CoursePO Course) {
		sql = "update "
				+ listName
				+ " set name=?, module=?,type=?, nature=?,credit=?, period=?, studentNum=?, teacherId=?, teacherName=?, timeAndPlace=?, introduction=?, book=?, syllabus=? where id=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, Course.getName());
			ps.setString(2, Course.getModule());
			ps.setString(3, Course.getType());
			ps.setString(4, Course.getNature());
			ps.setInt(5, Course.getCredit());
			ps.setInt(6, Course.getPeriod());
			ps.setInt(7, Course.getStudentNum());
			ps.setString(8, Course.getTeacherId());
			ps.setString(9, Course.getTeacherName());
			ps.setString(10, Course.getTimeAndPlace());
			ps.setString(11, Course.getIntroduction());
			ps.setString(12, Course.getBook());
			ps.setString(13, Course.getSyllabus());
			ps.setString(14, Course.getId());
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			 e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}
	/**
	 * 获取课程列表
	 * @return 课程列表
	 */
	public ArrayList<CoursePO> getCourseList() {
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
				r.setCredit(rs.getInt(6));
				r.setPeriod(rs.getInt(7));
				r.setStudentNum(rs.getInt(8));
				r.setTeacherId(rs.getString(9));
				r.setTeacherName(rs.getString(10));
				r.setTimeAndPlace(rs.getString(11));
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
	/**
	 * 获取已选通识课列表表头
	 * @return 表头
	 */
	public String getSelectedCommonCourseListHead(){
		return "课程号；课程名称；学分；任课教师姓名；上课时间及地点；已选人数；课程总人数";
	}
	/**
	 * 获取通识课列表表头
	 * @return 表头
	 */
	public String getCommonCourseListHead() {
		return "课程号；课程名称；学分；任课教师姓名；上课时间及地点；课程总人数";

	}

	/**
	 * 获取编辑通识课列表表头
	 * @return 表头
	 */
	public String getEditCommonCourseListHead() {
		return "课程号；课程名称；学分；学时；任课老师工号；任课教师姓名；上课时间及地点；课程总人数";
	}
}
