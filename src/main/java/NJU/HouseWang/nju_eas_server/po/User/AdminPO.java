package NJU.HouseWang.nju_eas_server.po.User;

import NJU.HouseWang.nju_eas_server.systemMessage.UserType;

public class AdminPO extends UserPO {

	public AdminPO(String id) {
		super(id, UserType.Admin);
		this.id = id;
	}

	@Override
	public String toString() {
		return id + "；" + type;
	}

}