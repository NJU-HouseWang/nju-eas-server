package NJU.HouseWang.nju_eas_server.logicService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Msg.LogPO;

/**
 * 日志逻辑类
 * @author 教化场
 * @version 2013-11-10
 */
public interface LogLogicService extends LogicService {
	/**
	 * 新增一条日志
	 * @param lp 登录PO
	 */
	public void addLog(LogPO lp);

	/**
	 * 显示日志列表
	 * @return 日志列表
	 */
	public ArrayList<String> showLogList();

	/**
	 * 显示日志列表的表头
	 * @return 表头
	 */
	public String showLogListHead();
	
	/**
	 * 清空日志列表
	 * @return 反馈
	 */
	public String emptyLogList();
}
