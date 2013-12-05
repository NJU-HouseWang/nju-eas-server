package NJU.HouseWang.nju_eas_server.dataService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Msg.AnnouncementPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_server.systemMessage.UserType;

/**
 * 公告列表类
 * @author 教化场
 * @version 2013-11-12
 */
public interface AnnouncementListService {
	/**
	 * 判断是否存在id
	 * @param id id
	 * @return 是否存在id
	 */
	public boolean containsID(String id);
	
	/**
	 * 获取公告
	 * @param id 公告号
	 * @return 公告
	 */
	public AnnouncementPO getAnnouncement(String id);
	
	/**
	 * 删除公告
	 * @param id 公告号
	 * @return 反馈
	 */
	public Feedback removeAnnouncement(String id);
	
	/**
	 * 添加公告
	 * @param Announcement 公告
	 * @return 反馈
	 */
	public Feedback addAnnouncement(AnnouncementPO Announcement);
	
	/**
	 * 更新公告
	 * @param Announcement 公告
	 * @return 反馈
	 */
	public Feedback updateAnnouncement(AnnouncementPO Announcement);
	
	/**
	 * 获取公告列表
	 * @param status 状态
	 * @param recipient 接收者类型
	 * @return 公告列表
	 */
	public ArrayList<AnnouncementPO> getAnnouncementList(int status, UserType recipient);
	
	/**
	 * 获取列表表头
	 * @return 表头
	 */
	public String getListHead();
}
