package NJU.HouseWang.nju_eas_server.dataService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Msg.LogPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

/**
 * 日志列表类
 * @author 教化场
 * @version 2013-11-15
 */
public interface LogListService {
	/**
	 * 添加日志
	 * @param log 日志
	 * @return 反馈
	 */
	public Feedback addLog(LogPO log);

	/**
	 * 获取日志列表
	 * @return 日志列表
	 */
	public ArrayList<LogPO> getLogList();

	/**
	 * 获取列表表头
	 * @return 表头
	 */
	public String getListHead();
	
	/**
	 * 清空日志列表
	 * @return 反馈
	 */
	public Feedback emptyLogList();
}
