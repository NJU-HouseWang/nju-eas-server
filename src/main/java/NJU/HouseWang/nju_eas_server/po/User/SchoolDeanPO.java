package NJU.HouseWang.nju_eas_server.po.User;

import NJU.HouseWang.nju_eas_server.systemMessage.UserType;

public class SchoolDeanPO extends UserPO {
	private String name;

	public SchoolDeanPO(String id, String name) {
		super(id, UserType.SchoolDean);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SchoolDeanPO [id=" + id + ", type=" + type + ", name=" + name
				+ "]";
	}

	public String toCommand() {
		return id + "；" + type + "；" + name;
	}
}