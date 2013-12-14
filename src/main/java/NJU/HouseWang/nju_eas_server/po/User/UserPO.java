package NJU.HouseWang.nju_eas_server.po.User;

import NJU.HouseWang.nju_eas_server.po.DataPOService;
import NJU.HouseWang.nju_eas_server.systemMessage.UserType;

public class UserPO implements DataPOService {
	protected String id;
	protected UserType type;

	public UserPO() {

	}

	public UserPO(String id, UserType type) {
		this.id = id;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public String toCommand() {
		return id + "；" + type.getChName() + "；";
	}

}