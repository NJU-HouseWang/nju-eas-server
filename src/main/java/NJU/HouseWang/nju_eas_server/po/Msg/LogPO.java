package NJU.HouseWang.nju_eas_server.po.Msg;

import NJU.HouseWang.nju_eas_server.dataService.DataListService;
import NJU.HouseWang.nju_eas_server.po.DataPOService;

public class LogPO implements DataPOService{
	private String name;
	private String ip;
	private String time;
	private String content;

	public LogPO(String name, String ip, String time, String content) {
		super();
		this.name = name;
		this.ip = ip;
		this.time = time;
		this.content = content;
	}
	
	public LogPO(){	
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String toCommand() {
		// TODO Auto-generated method stub
		return (name + "；" + ip + "；" + time + "；" + content);
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		
	}

}
