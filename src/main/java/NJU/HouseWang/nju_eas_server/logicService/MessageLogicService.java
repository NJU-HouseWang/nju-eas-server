package NJU.HouseWang.nju_eas_server.logicService;

import NJU.HouseWang.nju_eas_server.po.Msg.MessagePO;

public interface MessageLogicService extends LogicService {
	// 显示指定列表指定编号的私信
	public void showMessage(String listName, String id);

	// 显示该用户的指定列表的私信列表
	public void showMessageList(String listName, String uid);

	// 添加私信到指定的列表
	public void addMessage(String listName, MessagePO mp);

	// 编辑私信
	public void editMessage(MessagePO mp);

	// 把私信从一个列表转移到另一个列表
	public void moveMessage(String fromList, String toList, String id);

	// 彻底删除私信
	public void delMessage(String listName, String id);

	// 显示私信列表的表头
	public void showMessageListHead();
}
