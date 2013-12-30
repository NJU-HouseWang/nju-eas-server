package NJU.HouseWang.nju_eas_server.logicService;

import java.util.ArrayList;

/**
 * 逻辑类
 * @author 教化场
 * @version 2013-11-9
 */
public interface LogicService {
	/**
	 * 命令解析
	 * @param cmd 命令
	 * @return 反馈
	 */
	public Object operate(String cmd);
	
	/**
	 * 命令解析
	 * @param cmd 命令
	 * @param list 列表名
	 * @return 反馈
	 */
	public Object operate(String cmd, ArrayList<String> list);
}
