package NJU.HouseWang.nju_eas_server.po.Msg;

import NJU.HouseWang.nju_eas_server.po.DataPOService;

public class MessagePO implements DataPOService {
	// 消息编号
	private int id;
	// 发信人ID
	private String senderId;
	// 收信人ID
	private String recipientId;
	// 标题
	private String title;
	// 正文内容
	private String content;

	// 0表示未读 1表示已读
	private int status;

	public MessagePO(String id, String senderId, String recipientId,
			String title, String content, String status) {
		super();
		this.id = Integer.parseInt(id);
		this.senderId = senderId;
		this.title = title;
		this.content = content;
		this.recipientId = recipientId;
		this.setStatus(Integer.parseInt(status));
	}

	public MessagePO(String senderId, String recipientId, String title,
			String content, String status) {
		super();
		this.senderId = senderId;
		this.title = title;
		this.content = content;
		this.recipientId = recipientId;
		this.setStatus(Integer.parseInt(status));
	}
	
	public MessagePO(int id, String senderId, String recipientId,
			String title, String content, int status) {
		super();
		this.id = id;
		this.senderId = senderId;
		this.title = title;
		this.content = content;
		this.recipientId = recipientId;
		this.status = status;
	}

	public MessagePO(String senderId, String recipientId, String title,
			String content, int status) {
		super();
		this.senderId = senderId;
		this.title = title;
		this.content = content;
		this.recipientId = recipientId;
		this.status = status;
	}

	public MessagePO() {

	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}

	public String toCommand() {
		return id + "；" + senderId + "；" + recipientId  + "；"
				+ title + "；" + content+ "；" + status;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return ("" + this.id);
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id = Integer.parseInt(id);
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}