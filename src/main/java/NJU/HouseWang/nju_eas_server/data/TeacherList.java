package NJU.HouseWang.nju_eas_server.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.dataService.TeacherListService;
import NJU.HouseWang.nju_eas_server.po.User.TeacherPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_server.systemMessage.UserType;
/**
 * 任课老师列表类
 * @author 教化场
 * @version 2013-11-16
 */
public class TeacherList implements TeacherListService{
	private String listName = "teacher_list";
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
	 * 判断是否存在教师工号
	 * @param id 教师工号
	 * @return 是否存在教师工号
	 */
	public boolean containsID(String id) {
		Boolean result = false;
		sql = "select id from " + listName + " where id=?";
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
	 * 获取教师PO
	 * @param id 教师工号
	 * @return 教师PO
	 */
	public TeacherPO getTeacher(String id) {
		TeacherPO result = new TeacherPO();
		sql = "select * from " + listName + " where id=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				result.setId(rs.getString(1));
				result.setType(UserType.valueOf(rs.getString(2)));
				result.setName(rs.getString(3));
				result.setCompany(rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 删除教师
	 * @param id 教师工号
	 * @return 删除教师
	 */
	public Feedback removeTeacher(String id) {
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
	 * 添加教师
	 * @param teacher 教师PO
	 * @return 反馈
	 */
	public Feedback addTeacher(TeacherPO teacher) {
		sql = "insert into " + listName + " values (?,?,?,?)";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, teacher.getId());
			ps.setString(2, teacher.getType().toString());
			ps.setString(3, teacher.getName());
			ps.setString(4, teacher.getCompany());
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			// e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}
	/**
	 * 编辑教师
	 * @param teacher 教师PO
	 * @return 反馈
	 */
	public Feedback updateTeacher(TeacherPO teacher) {
		sql = "update " + listName
				+ " set type=?, name=?, company=? where id=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, teacher.getType().toString());
			ps.setString(2, teacher.getName());
			ps.setString(3, teacher.getCompany());
			ps.setString(4, teacher.getId());
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			// e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}
	/**
	 * 获取教师列表
	 * @return 教师列表
	 */
	public ArrayList<TeacherPO> getTeacherList() {
		ArrayList<TeacherPO> result = new ArrayList<TeacherPO>();
		sql = "select * from " + listName;
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				TeacherPO r = new TeacherPO();
				r.setId(rs.getString(1));
				r.setType(UserType.valueOf(rs.getString(2)));
				r.setName(rs.getString(3));
				r.setCompany(rs.getString(4));
				result.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 获取教师列表
	 * @param type 类型
	 * @return 教师列表
	 */
	public ArrayList<TeacherPO> getTeacherList(String type) {
		ArrayList<TeacherPO> result = new ArrayList<TeacherPO>();
		sql = "select * from " + listName + " where type=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, type);
			rs = ps.executeQuery();
			while (rs.next()) {
				TeacherPO r = new TeacherPO();
				r.setId(rs.getString(1));
				r.setType(UserType.valueOf(rs.getString(2)));
				r.setName(rs.getString(3));
				r.setCompany(rs.getString(4));
				result.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 获取列表表头
	 * @return 表头
	 */
	public String getListHead() {
		return "工号；教师类型；姓名；部门";
	}
	/*
	 * public static void main(String[] args) { TeacherList tl = new
	 * TeacherList(); tl.init(); System.out.println(tl.containsID("admin"));
	 * System.out.println(tl.containsID("admin1"));
	 * System.out.println(tl.getLoginer("121250157"));
	 * System.out.println(tl.removeLoginer("121250157"));
	 * System.out.println(tl.containsID("121250157"));
	 * System.out.println(tl.removeLoginer("121250157"));
	 * System.out.println(tl.containsID("121250157"));
	 * System.out.println(tl.addLoginer(new
	 * GuestPO("121250157",UserType.Student,"bilicrazy123")));
	 * System.out.println(tl.containsID("121250157"));
	 * System.out.println(tl.getLoginer("121250157"));
	 * System.out.println(tl.updateLoginer(new
	 * GuestPO("121250157",UserType.Student,"bilicrazy124")));
	 * System.out.println(tl.getLoginer("121250157")); ArrayList<GuestPO> l =
	 * tl.getLoginList(); for(GuestPO g:l) { System.out.println(g); }
	 * tl.finish(); }
	 */
}
