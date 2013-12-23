package NJU.HouseWang.nju_eas_server.dataService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Msg.MessagePO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public interface MessageListService {
	public boolean containsID(int listType, String id);

	public MessagePO getMessage(int listType, String id);

	public Feedback removeMessage(int listType, String id);

	public Feedback addMessage(int listType, MessagePO Message);

	public Feedback updateMessage(int listType, MessagePO Message);

	public ArrayList<MessagePO> getMessageList(int listType, String operatorId);

	public String getSenderListHead();
	
	public String getRecipientListHead();
	
	public MessagePO getMessageWithoutId(int listType, String id);
}
