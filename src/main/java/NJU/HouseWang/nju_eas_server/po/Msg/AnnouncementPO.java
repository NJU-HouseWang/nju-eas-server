package NJU.HouseWang.nju_eas_server.po.Msg;

import NJU.HouseWang.nju_eas_server.po.DataPOService;
import NJU.HouseWang.nju_eas_server.systemMessage.UserType;

public class AnnouncementPO implements DataPOService {
	private String id;// 公告编号
	private String senderId;// 发信人ID
	private UserType recipient;// 接受者的用户类型
	private String title;// 标题
	private String content;// 内容
	private int status;// 公告的状态 0：草稿 1：已发布 2：已收到的公告 3：已删除

	// edit时使用
	public AnnouncementPO(String id, String senderId, String title,
			String content, UserType recipient, int status) {
		this.id = id;
		this.senderId = senderId;
		this.title = title;
		this.content = content;
		this.recipient = recipient;
		this.status = status;
	}

	// 非edit时使用
	public AnnouncementPO(String senderId, UserType recipient, String content,
			String title, int status) {
		this.senderId = senderId;
		this.recipient = recipient;
		this.content = content;
		this.title = title;
		this.status = status;
	}

	public AnnouncementPO(String str) {
		String[] info = str.split("；");
		this.senderId = info[0];
		this.recipient = UserType.valueOf(info[1]);
		this.content = info[2];
		this.title = info[3];
		this.status = Integer.parseInt(info[4]);
	}

	public AnnouncementPO() {

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

	public void setStatus(int status) {
		this.status = status;
	}

	public int getStatus() {
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
