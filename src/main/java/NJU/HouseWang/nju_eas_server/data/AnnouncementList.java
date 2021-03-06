package NJU.HouseWang.nju_eas_server.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.dataService.AnnouncementListService;
import NJU.HouseWang.nju_eas_server.po.Msg.AnnouncementPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_server.systemMessage.UserType;
/**
 * 公告列表类
 * @author 教化场
 * @version 2013-11-12
 */
public class AnnouncementList implements AnnouncementListService{
	private String listName = "announcement_list";
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
	 * 判断是否存在id
	 * @param id id
	 * @return 是否存在id
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
	 * 获取公告
	 * @param id 公告号
	 * @return 公告
	 */
	public AnnouncementPO getAnnouncement(String id) {
		AnnouncementPO result = new AnnouncementPO();
		sql = "select * from " + listName + " where id=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				result.setId(rs.getString(1));
				result.setSenderId(rs.getString(2));
				result.setRecipient(UserType.valueOf(rs.getString(3)));
				result.setTitle(rs.getString(4));
				result.setContent(rs.getString(5));
				result.setStatus(Integer.parseInt(rs.getString(6)));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 删除公告
	 * @param id 公告号
	 * @return 反馈
	 */
	public Feedback removeAnnouncement(String id) {
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
	 * 添加公告
	 * @param Announcement 公告
	 * @return 反馈
	 */
	public Feedback addAnnouncement(AnnouncementPO Announcement) {
		sql = "insert into " + listName
				+ " values (?,?,?,?,?,?)";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(2, Announcement.getSenderId());
			ps.setString(3, Announcement.getRecipient().toString());
			ps.setString(4, Announcement.getTitle());
			ps.setString(5, Announcement.getContent());
			ps.setInt(6, Announcement.getStatus());
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			// e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}
	/**
	 * 更新公告
	 * @param Announcement 公告
	 * @return 反馈
	 */
	public Feedback updateAnnouncement(AnnouncementPO Announcement) {
		sql = "update "
				+ listName
				+ " set senderId=?, recipient=?,title=?, content=?,status=? where id=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, Announcement.getSenderId());
			ps.setString(2, Announcement.getRecipient().toString());
			ps.setString(3, Announcement.getTitle());
			ps.setString(4, Announcement.getContent());
			ps.setInt(5, Announcement.getStatus());
			ps.setString(6, Announcement.getId());
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			// e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}
	/**
	 * 获取公告列表
	 * @param status 状态
	 * @param recipient 接收者类型
	 * @return 公告列表
	 */
	public ArrayList<AnnouncementPO> getAnnouncementList(int status, UserType recipient) {
		ArrayList<AnnouncementPO> result = new ArrayList<AnnouncementPO>();
		sql = "select * from " + listName + " where status=?,recipient=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, status);
			ps.setString(2, recipient.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				AnnouncementPO r = new AnnouncementPO();
				r.setId(rs.getString(1));
				r.setSenderId(rs.getString(2));
				r.setRecipient(UserType.valueOf(rs.getString(3)));
				r.setTitle(rs.getString(4));
				r.setContent(rs.getString(5));
				r.setStatus(rs.getInt(6));
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
	public String getListHead(){
		return "公告编号；发信人ID；接收对象；标题；发布状态";
	}
}
