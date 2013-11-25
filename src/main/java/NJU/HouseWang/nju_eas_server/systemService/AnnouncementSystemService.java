package NJU.HouseWang.nju_eas_server.systemService;

import NJU.HouseWang.nju_eas_server.po.Msg.AnnouncementPO;

public interface AnnouncementSystemService extends SystemService {
	//显示公告
	public void showAnnouncement(String id);
	//显示指定状态的公告列表
	public void showAnnouncementList(int status, String uid);
	//添加公告
	public void addAnnouncement(AnnouncementPO ap);
	//编辑公告
	public void editAnnouncement(AnnouncementPO ap);
	//删除公告
	public void delAnnouncement(String id);
	//显示公告列表的表头
	public void showAnnouncementListHead();
}
