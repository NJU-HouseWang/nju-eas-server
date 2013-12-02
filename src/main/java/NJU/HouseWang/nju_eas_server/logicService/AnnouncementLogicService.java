package NJU.HouseWang.nju_eas_server.logicService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Msg.AnnouncementPO;

public interface AnnouncementLogicService extends LogicService {
	// 显示公告
	public String showAnnouncement(String id);

	// 显示指定状态的公告列表
	public ArrayList<String> showAnnouncementList(int status, String uid);

	// 添加公告
	public String addAnnouncement(AnnouncementPO ap);

	// 编辑公告
	public String editAnnouncement(AnnouncementPO ap);

	// 删除公告
	public String delAnnouncement(String id);

	// 显示公告列表的表头
	public String showAnnouncementListHead();
}
