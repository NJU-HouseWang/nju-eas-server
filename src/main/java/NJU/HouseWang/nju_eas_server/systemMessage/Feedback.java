package NJU.HouseWang.nju_eas_server.systemMessage;

public enum Feedback {
	OPERATION_SUCCESS("操作成功"), 
	DATALIST_NOT_FOUND("数据列表不存在"), 
	LISTNAME_EMPTY("未设置列表名"), 
	DATA_NOT_FOUND("数据不存在"), 
	DATA_ALREADY_EXISTED("数据已存在");

	private String content;

	private Feedback(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}
}
