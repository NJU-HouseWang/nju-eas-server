package NJU.HouseWang.nju_eas_server.logicService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Msg.MessagePO;

public interface MessageLogicService extends LogicService {
	// 显示指定列表指定编号的私信
	public String showMessage(String fromBox, String id);

	// 显示该用户的指定列表的私信列表
	public ArrayList<String> showMessageList(String fromBox, String uid);

	// 添加私信到指定的列表
	public String addMessage(String fromBox, MessagePO mp);

	// 编辑私信
	public String editMessage(int fromBox,MessagePO mp);

	// 把私信从一个列表转移到另一个列表
	public String moveMessage(String fromBox, String toBox, String id);

	// 彻底删除私信
	public String eraseMessage(String fromBox, String id);

	// 显示私信列表的表头1
	public String showSenderMessageListHead();
	
	// 显示私信列表的表头2
	public String showRecipientMessageListHead();
	
	//发送私信
	public String sendMessage(MessagePO mp);
	
	//保存草稿
	public String saveDraft(MessagePO mp);
	
	//删除私信
	public String delMessage(String fromBox, String id);
	
	
}
