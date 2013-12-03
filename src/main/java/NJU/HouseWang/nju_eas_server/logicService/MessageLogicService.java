package NJU.HouseWang.nju_eas_server.logicService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Msg.MessagePO;

public interface MessageLogicService extends LogicService {
	// 显示指定列表指定编号的私信
	public String showMessage(String listName, String id);

	// 显示该用户的指定列表的私信列表
	public ArrayList<String> showMessageList(String listName, String uid);

	// 添加私信到指定的列表
	public String addMessage(String listName, MessagePO mp);

	// 编辑私信
	public String editMessage(MessagePO mp);

	// 把私信从一个列表转移到另一个列表
	public String moveMessage(String fromList, String toList, String id);

	// 彻底删除私信
	public String delMessage(String listName, String id);

	// 显示私信列表的表头
	public String showMessageListHead();
}
