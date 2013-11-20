package NJU.HouseWang.nju_eas_server.po.User;

import NJU.HouseWang.nju_eas_server.systemMessage.UserType;

public class TeacherPO extends UserPO {
	private String name;
	private String department;

	public TeacherPO(String id, String name, String department) {
		super(id, UserType.Teacher);
		this.name = name;
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toCommand() {
		return id + "；" + type + "；" + name + "；" + department;
	}
}