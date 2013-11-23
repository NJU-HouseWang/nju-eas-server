package NJU.HouseWang.nju_eas_server.po.User;

import NJU.HouseWang.nju_eas_server.systemMessage.UserType;

public class TeacherPO extends UserPO {
	private String name;
	private String company;

	public TeacherPO() {
	}

	public TeacherPO(String id, String type,String name, String company) {
		super(id, UserType.valueOf(type));
		this.name = name;
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "TeacherPO [name=" + name + ", company=" + company + "]";
	}

	@Override
	public String toCommand() {
		return id + "；" + type + "；" + name + "；" + company;
	}


}