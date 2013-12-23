package NJU.HouseWang.nju_eas_server.systemMessage;

public enum Feedback {
	OPERATION_SUCCEED("操作成功"), 
	OPERATION_FAIL("操作失败"), 
	DATALIST_NOT_FOUND("数据列表不存在"), 
	LISTNAME_EMPTY("未设置列表名"), 
	DATA_NOT_FOUND("数据不存在"), 
	DATA_ALREADY_EXISTED("数据已存在"),
	FILE_NOT_FOUND("文件不存在"), 
	FILE_ALREADY_EXISTED("文件已存在"),
	IP_ALREADY_EXISTED("该IP已登录"),
	ID_ALREADY_EXISTED("该账号已在别地登录"),
	ID_PW_NOT_FOUND("登录信息错误"),
	PW_WRONG_INPUT("旧密码输入错误"),
	PW_REPEATED("新密码不得与旧密码重复"),
	PW_TOO_SHORT("新密码不得少于6位"),
	FORMAT_ERROR("提交的学分不符合规范"),
	AUDIT_REPEATED("该教学计划已审核"),
	MAX_SELECTION("已达到选课上限"),
	SELECTION_REPEATED("该选课记录已存在"),
	COMMAND_ERROR("命令不存在"),
	COURSE_STUDENT_NOT_FOUND("该学生不在课程名单中");
	
	private String content;

	private Feedback(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}
}
