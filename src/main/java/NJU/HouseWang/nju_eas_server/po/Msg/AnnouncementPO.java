package NJU.HouseWang.nju_eas_server.po.Msg;

import java.util.Arrays;

import NJU.HouseWang.nju_eas_server.po.DataPOService;

public class AnnouncementPO implements DataPOService {
	private String id;
	private String giverId;
	private String title;
	private String content;
	private String[] receiverType;

	public AnnouncementPO(String id, String giverId, String title,
			String content, String[] receiverType) {
		super();
		this.id = id;
		this.giverId = giverId;
		this.title = title;
		this.content = content;
		this.receiverType = receiverType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGiverId() {
		return giverId;
	}

	public void setGiverId(String giverId) {
		this.giverId = giverId;
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

	public String[] getReceiverType() {
		return receiverType;
	}

	public void setReceiverType(String[] receiverType) {
		this.receiverType = receiverType;
	}

	@Override
	public String toString() {
		return "NoticePO [id=" + id + ", giverId=" + giverId + ", title="
				+ title + ", content=" + content + ", receiverType="
				+ Arrays.toString(receiverType) + "]";
	}

	public String toCommand() {
		String re = id + "；" + giverId + "；" + title + "；" + content + "；";
		for (int i = 0; i < receiverType.length; i++) {
			re += receiverType[i] + "，";
		}
		return re;
	}
}
