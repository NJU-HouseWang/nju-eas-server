package NJU.HouseWang.nju_eas_server.po.Edu;

import NJU.HouseWang.nju_eas_server.po.DataPOService;

public class DepartmentPO implements DataPOService {
	private String id;
	private String name;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String toCommand() {
		return id + "；" + name;
	}
}
