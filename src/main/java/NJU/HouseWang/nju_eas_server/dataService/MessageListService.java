package NJU.HouseWang.nju_eas_server.dataService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Msg.MessagePO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

/**
 * 私信列表类
 * @author 教化场
 * @version 2013-11-15
 */
public interface MessageListService {
	/**
	 * 判断是否存在id
	 * @param listType 列表类型
	 * @param id id
	 * @return 是否存在id
	 */
	public boolean containsID(int listType, String id);

	/**
	 * 获取私信
	 * @param listType 列表类型
	 * @param id 私信号
	 * @return 私信PO
	 */
	
	public MessagePO getMessage(int listType, String id);
	/**
	 * 不通过id获取私信
	 * @param listType 列表类型
	 * @param id id
	 * @return 表头
	 */
	public MessagePO getMessageWithoutId(int listType, String id);
	/**
	 * 删除私信
	 * @param listType 列表类型
	 * @param id 私信号
	 * @return 反馈
	 */
	public Feedback removeMessage(int listType, String id);

	/**
	 * 添加私信
	 * @param listType 列表类型
	 * @param Message 私信PO
	 * @return 反馈
	 */
	public Feedback addMessage(int listType, MessagePO Message);

	/**
	 * 编辑私信
	 * @param listType 列表类型
	 * @param Message 私信PO
	 * @return 反馈
	 */
	public Feedback updateMessage(int listType, MessagePO Message);

	/**
	 * 获取私信列表
	 * @param listType 列表类型
	 * @param operatorId 操作者id
	 * @return 私信列表
	 */
	public ArrayList<MessagePO> getMessageList(int listType, String operatorId);

	/**
	 * 获取发送者列表表头
	 * @return 表头
	 */
	public String getSenderListHead();
	
	/**
	 * 获取接收者列表表头
	 * @return 表头
	 */
	public String getRecipientListHead();
	
	
}
