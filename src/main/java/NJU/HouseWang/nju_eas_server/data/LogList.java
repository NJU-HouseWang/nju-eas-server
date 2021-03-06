package NJU.HouseWang.nju_eas_server.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.dataService.LogListService;
import NJU.HouseWang.nju_eas_server.po.Msg.LogPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;
/**
 * 日志列表类
 * @author 教化场
 * @version 2013-11-15
 */
public class LogList implements LogListService {
	private String listName = "log_list";
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
	 * 添加日志
	 * @param log 日志
	 * @return 反馈
	 */
	public Feedback addLog(LogPO log) {
		sql = "insert into " + listName
				+ "(name,ip,time,content) values (?,?,?,?)";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, log.getName());
			ps.setString(2, log.getIp());
			ps.setString(3, log.getTime());
			ps.setString(4, log.getContent());
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			// e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}
	/**
	 * 获取日志列表
	 * @return 日志列表
	 */
	public ArrayList<LogPO> getLogList() {
		ArrayList<LogPO> result = new ArrayList<LogPO>();
		sql = "select * from " + listName;
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				LogPO r = new LogPO();
				r.setName(rs.getString(2));
				r.setIp(rs.getString(3));
				r.setTime(rs.getString(4));
				r.setContent(rs.getString(5));
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
		return "用户名；用户IP；操作时间；操作内容";
	}

	/**
	 * 清空日志列表
	 * @return 反馈
	 */
	public Feedback emptyLogList() {
		sql = "truncate table " + listName;
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
}
