package NJU.HouseWang.nju_eas_server.po.Msg;

import NJU.HouseWang.nju_eas_server.po.DataPO;

public class MessagePO implements DataPO {
	private String id;
	private String giverId;
	private String time;
	private String content;
	private String achieverId;

	public MessagePO(String id, String giverId, String time, String content,
			String achieverId) {
		super();
		this.id = id;
		this.giverId = giverId;
		this.time = time;
		this.content = content;
		this.achieverId = achieverId;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAchieverId() {
		return achieverId;
	}

	public void setAchieverId(String achieverId) {
		this.achieverId = achieverId;
	}

	@Override
	public String toString() {
		return "MessagePO [id=" + id + ", giverId=" + giverId + ", time="
				+ time + ", content=" + content + ", achieverId=" + achieverId
				+ "]";
	}

	public String toCommand() {
		return id + ";" + giverId + ";" + time + ";" + content + ";"
				+ achieverId;
	}

}