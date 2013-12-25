package NJU.HouseWang.nju_eas_server.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.dataService.MessageListService;
import NJU.HouseWang.nju_eas_server.po.Msg.MessagePO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public class MessageList implements MessageListService {
	private static String[] listName = { "msg_in_list", "msg_out_list",
			"msg_draft_list", "msg_trash_list" };
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
	public boolean containsID(int listType, String id) {
		Boolean result = false;
		sql = "select id from " + listName[listType] + " where id=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(id));
			rs = ps.executeQuery();
			result = (rs.next() != false);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public MessagePO getMessage(int listType, String id) {
		MessagePO result = new MessagePO();
		sql = "select * from " + listName[listType] + " where id=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(id));
			rs = ps.executeQuery();
			while (rs.next()) {
				result.setId("" + rs.getInt(1));
				result.setSenderId(rs.getString(2));
				result.setRecipientId(rs.getString(3));
				result.setTitle(rs.getString(4));
				result.setContent(rs.getString(5));
				result.setStatus(rs.getInt(5));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public MessagePO getMessageWithoutId(int listType, String id) {
		MessagePO result = new MessagePO();
		sql = "select * from " + listName[listType] + " where id=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(id));
			rs = ps.executeQuery();
			while (rs.next()) {
				result.setSenderId(rs.getString(2));
				result.setRecipientId(rs.getString(3));
				result.setTitle(rs.getString(4));
				result.setContent(rs.getString(5));
				result.setStatus(rs.getInt(5));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Feedback removeMessage(int listType, String id) {
		sql = "delete from " + listName[listType] + " where id=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(id));
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}

	public Feedback addMessage(int listType, MessagePO Message) {
		sql = "insert into " + listName[listType] + " values (?,?,?,?,?,?)";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(Message.getId()));
			ps.setString(2, Message.getSenderId());
			ps.setString(3, Message.getRecipientId());
			ps.setString(4, Message.getTitle());
			ps.setString(5, Message.getContent());
			ps.setInt(6, Message.getStatus());
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}

	public Feedback updateMessage(int listType, MessagePO Message) {
		sql = "update "
				+ listName[listType]
				+ " set senderId=?, recipientId=?, title=?, content=?, status=? where id=?";
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, Message.getSenderId());
			ps.setString(2, Message.getRecipientId());
			ps.setString(3, Message.getTitle());
			ps.setString(4, Message.getContent());
			ps.setInt(5, Message.getStatus());
			ps.setInt(6, Integer.parseInt(Message.getId()));
			ps.execute();
			return Feedback.OPERATION_SUCCEED;
		} catch (SQLException e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL;
		}
	}

	public ArrayList<MessagePO> getMessageList(int listType, String operatorId) {
		ArrayList<MessagePO> result = new ArrayList<MessagePO>();
		if (listType == 0 || listType == 3) {
			sql = "select * from " + listName[listType]
					+ " where recipientId=?";

		} else {
			sql = "select * from " + listName[listType] + " where senderId=?";
		}
		try {
			conn = sqlconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, operatorId);
			rs = ps.executeQuery();
			while (rs.next()) {
				MessagePO r = new MessagePO();
				r.setId("" + rs.getInt(1));
				r.setSenderId(rs.getString(2));
				r.setRecipientId(rs.getString(3));
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

	public String getSenderListHead() {
		return "私信编号；发信人ID；标题；是否已读";
	}

	public String getRecipientListHead() {
		return "私信编号；收件人ID；标题";
	}
}
