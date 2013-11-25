package NJU.HouseWang.nju_eas_server.po.Msg;

import NJU.HouseWang.nju_eas_server.po.DataPOService;

public class MessagePO implements DataPOService {
	// 消息编号
	private int id;
	// 发信人ID
	private String senderId;
	// 收信人ID
	private String recipientId;
	//操作人ID
	private String operatorId;
	// 标题
	private String title;
	// 正文内容
	private String content;

	public MessagePO(String id, String senderId, String recipientId,String operatorId,
			String title, String content) {
		super();
		this.id = Integer.parseInt(id);
		this.senderId = senderId;
		this.title = title;
		this.content = content;
		this.recipientId = recipientId;
		this.operatorId  = operatorId;
	}
	
	public MessagePO(String senderId, String recipientId,String operatorId,
			String title, String content) {
		super();
		this.senderId = senderId;
		this.title = title;
		this.content = content;
		this.recipientId = recipientId;
		this.operatorId  = operatorId;
	}
	
	public MessagePO(){
		
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
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

	@Override
	public String toString() {
		return "MessagePO [id=" + id + ", senderId=" +  senderId +", recipientId=" + recipientId
				+ ", operatorId=" +  operatorId 	+ ", title="
				+ title + ", content=" + content + "]";
	}

	public String toCommand() {
		return id + "；" + senderId + "；" + recipientId + "；"+ operatorId + "；" + title + "；"
				+ content;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return (""+this.id);
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id =Integer.parseInt(id);
	}

}