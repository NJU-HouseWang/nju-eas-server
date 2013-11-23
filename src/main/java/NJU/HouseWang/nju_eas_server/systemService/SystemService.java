package NJU.HouseWang.nju_eas_server.systemService;

import NJU.HouseWang.nju_eas_server.netService.NetService;

public interface SystemService {
	public void operate(String uid, String cmd);

	public void initNetService(NetService ns);
}
