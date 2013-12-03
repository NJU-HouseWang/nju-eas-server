package NJU.HouseWang.nju_eas_server.logicService;

import java.util.ArrayList;

public interface LogicService {
	public Object operate(String cmd);
	public Object operate(String cmd, ArrayList<String> list);
}
