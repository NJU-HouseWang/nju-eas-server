package NJU.HouseWang.nju_eas_server.dataService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.StatusPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

/**
 * 状态列表类
 * @author 教化场
 * @version 2013-11-16
 */
public interface StatusListService {
	/**
	 * 获取状态
	 * @param status 状态
	 * @return 状态PO
	 */
	public StatusPO getStatus(String status);

	/**
	 * 编辑状态
	 * @param status 状态
	 * @return 反馈
	 */
	public Feedback updateStatus(StatusPO status);

	/**
	 * 获取状态列表
	 * @return 状态列表
	 */
	public ArrayList<StatusPO> getStatusList();

	/**
	 * 获取列表表头
	 * @return 表头
	 */
	public String getListHead();
}
