package NJU.HouseWang.nju_eas_server.logicService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Msg.AnnouncementPO;

/**
 * 公告逻辑类
 * @author 教化场
 * @version 2013-11-7
 */
public interface AnnouncementLogicService extends LogicService {
	/**
	 * 显示公告
	 * @param id 公告号
	 * @return 反馈
	 */
	public String showAnnouncement(String id);

	/**
	 * 显示公告列表
	 * @param status 状态
	 * @param uid 用户名
	 * @return 公告列表
	 */
	public ArrayList<String> showAnnouncementList(int status, String uid);

	/**
	 * 添加公告
	 * @param ap 公告PO
	 * @return 反馈
	 */
	public String addAnnouncement(AnnouncementPO ap);

	/**
	 * 编辑公告
	 * @param ap 公告PO
	 * @return 反馈
	 */
	public String editAnnouncement(AnnouncementPO ap);

	/**
	 * 删除公告
	 * @param id 公告号
	 * @return 反馈
	 */
	public String delAnnouncement(String id);

	/**
	 * 显示公告列表表头
	 * @return 表头
	 */
	public String showAnnouncementListHead();
}
