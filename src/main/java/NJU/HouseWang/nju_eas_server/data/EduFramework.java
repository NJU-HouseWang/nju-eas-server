package NJU.HouseWang.nju_eas_server.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.dataService.EduFrameworkService;
import NJU.HouseWang.nju_eas_server.po.Edu.EduFrameworkItemPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;
/**
 * 教学框架类
 * @author 教化场
 * @version 2013-11-14
 */
public class EduFramework implements EduFrameworkService{
	private String listName = "eduframework";
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
	 * 获取教学框架
	 * @return 教学框架列表
	 */
	public ArrayList<EduFrameworkItemPO> getEduFramework() {
		ArrayList<EduFrameworkItemPO> result = new ArrayList<EduFrameworkItemPO>();
		sql = "select * from " + listName;
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				EduFrameworkItemPO r = new EduFrameworkItemPO();
				r.setModuleId(rs.getString(1));
				r.setModuleName(rs.getString(2));
				r.setModuleMinCredit(rs.getInt(3));
				r.setModuleMaxCredit(rs.getInt(4));
				r.setCourseNature(rs.getString(5));
				r.setSerialNum(rs.getString(6));
				r.setCourseType(rs.getString(7));
				r.setTypeMinCredit(rs.getInt(8));
				r.setTypeMaxCredit(rs.getInt(9));
				// r.setCourseId(rs.getString(10));
				r.setCourseName(rs.getString(10));
				r.setCourseMinCredit(rs.getInt(11));
				r.setCourseMaxCredit(rs.getInt(12));
				r.setStartTerm(rs.getInt(13));
				r.setEndTerm(rs.getInt(14));
				result.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 删除教学框架
	 * @return 反馈
	 */
	public Feedback delEduFramework() {
		sql = "truncate " + listName;
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
	 * 添加教学框架
	 * @param ep 教学框架PO
	 * @return 反馈
	 */
	public Feedback addEduFrameworkItem(EduFrameworkItemPO ep) {
		sql = "insert into " + listName
				+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, ep.getModuleId());
			ps.setString(2, ep.getModuleName());
			ps.setInt(3, ep.getModuleMinCredit());
			ps.setInt(4, ep.getModuleMaxCredit());
			ps.setString(5, ep.getCourseNature());
			ps.setString(6, ep.getSerialNum());
			ps.setString(7, ep.getCourseType());
			ps.setInt(8, ep.getTypeMinCredit());
			ps.setInt(9, ep.getTypeMaxCredit());
			// ps.setString(10,ep.getCourseId());
			ps.setString(10, ep.getCourseName());
			ps.setInt(11, ep.getCourseMinCredit());
			ps.setInt(12, ep.getCourseMaxCredit());
			ps.setInt(13, ep.getStartTerm());
			ps.setInt(14, ep.getEndTerm());

			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}
	/**
	 * 获取列表表头
	 * @return 表头
 	 */
	public String getListHead() {
		return "课程模块(学分)；课程性质；序列；课程类别(学分)；课程名称(部分)；建议学分；开设学期";
	}
	/**
	 * 获取导入列表表头
	 * @return 导入列表表头
	 */
	public String getImportListHead() {
		return "模块编号；模块名称；模块最小学分；模块最大学分；课程性质；序列；课程类别；类别最小学分；类别最大学分；课程名称；课程最小学分；课程最大学分；开始学期；结束学期";
	}

}
