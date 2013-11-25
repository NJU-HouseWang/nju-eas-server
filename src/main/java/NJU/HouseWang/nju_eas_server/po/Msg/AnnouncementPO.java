package NJU.HouseWang.nju_eas_server.po.Msg;

import NJU.HouseWang.nju_eas_server.po.DataPOService;
import NJU.HouseWang.nju_eas_server.systemMessage.UserType;

public class AnnouncementPO implements DataPOService {
	// 公告编号
	private String id;
	// 发信人ID
	private String senderId;
	// 接受者的用户类型
	private UserType recipient;
	// 标题
	private String title;
	// 内容
	private String content;
	// 公告的状态 0：草稿 1：已发布 2：已收到的公告 3：已删除
	private int status;

	public AnnouncementPO(String id, String senderId, String title,
			String content, UserType recipient, int status) {
		super();
		this.id = id;
		this.senderId = senderId;
		this.title = title;
		this.content = content;
		this.recipient = recipient;
		this.status = status;
	}

	public AnnouncementPO(String senderId, UserType recipient, String content,
			String title, int status) {
		super();
		this.senderId = senderId;
		this.title = title;
		this.content = content;
		this.recipient = recipient;
		this.status = status;
	}
	
	public AnnouncementPO(){
		
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public UserType getRecipient() {
		return this.recipient;
	}

	public void setRecipient(UserType recipient) {
		this.recipient = recipient;
	}
	
	public void setStatus(int status){
		this.status = status;
	}
	
	public int getStatus(){
		return this.status;
	}

	@Override
	public String toString() {
		return "AnnouncementPO [id=" + id + ", senderId=" + senderId
				+ ", recipient=" + recipient.toString() + ", title=" + title
				+ ", content=" + content + ", status=" + status;
	}

	public String toCommand() {
		String re = id + "；" + senderId + "；" + recipient.toString() + "；"
				+ title + "；" + content + "；" + status;

		return re;
	}
}
