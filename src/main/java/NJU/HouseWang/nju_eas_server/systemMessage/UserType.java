package NJU.HouseWang.nju_eas_server.systemMessage;

public enum UserType {
	Admin("管理员"), 
	SchoolDean("学校教务老师"), 
	DeptAD("院系教务老师"), 
	Teacher("任课老师"), 
	Student("学生");

	private String chName;

	private UserType(String chName) {
		this.chName = chName;
	}

	public String getChName() {
		return chName;
	}
}
