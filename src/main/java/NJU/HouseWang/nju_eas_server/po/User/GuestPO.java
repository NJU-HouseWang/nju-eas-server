package NJU.HouseWang.nju_eas_server.po.User;

import NJU.HouseWang.nju_eas_server.systemMessage.UserType;

public class GuestPO extends UserPO {
	private String password;

	public GuestPO(String id, UserType type, String password) {
		super(id, type);
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "GuestPO [id=" + id + ", type=" + type + ", password="
				+ password + "]";
	}
	
	@Override
	public String toCommand() {
		return id + "；" + type + "；" + password;
	}

	@Override
	public boolean equals(Object obj) {
		GuestPO u = (GuestPO) obj;

		return (type.equals(u.getType()) && id.equals(u.getId()) && password
				.equals(u.getPassword()));
	}
}
