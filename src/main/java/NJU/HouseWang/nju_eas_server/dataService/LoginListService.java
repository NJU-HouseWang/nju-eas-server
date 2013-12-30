package NJU.HouseWang.nju_eas_server.dataService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.User.GuestPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

/**
 * 登录列表类
 * @author 教化场
 * @version 2013-11-15
 */
public interface LoginListService {
	/**
	 * 判断是否存在用户id
	 * @param id 用户id
	 * @return 是否存在用户id
	 */
	public boolean containsID(String id);

	/**
	 * 获取登录者
	 * @param id 用户id
	 * @return 客户PO
	 */
	public GuestPO getLoginer(String id);

	/**
	 * 删除登录者
	 * @param id 用户id
	 * @return 反馈
	 */
	public Feedback removeLoginer(String id);

	/**
	 * 添加登录者
	 * @param user 客户PO
	 * @return 反馈
	 */
	public Feedback addLoginer(GuestPO user);

	/**
	 * 编辑登录者
	 * @param user 客户PO
	 * @return 反馈
	 */
	public Feedback updateLoginer(GuestPO user);

	/**
	 * 获取登录列表
	 * @return 登录列表
	 */
	public ArrayList<GuestPO> getLoginList();

	/**
	 * 获取列表表头
	 * @return 表头
	 */
	public String getListHead();
}
