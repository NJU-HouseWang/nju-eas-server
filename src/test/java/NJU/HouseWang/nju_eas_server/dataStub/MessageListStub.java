package NJU.HouseWang.nju_eas_server.dataStub;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.MessageList;
import NJU.HouseWang.nju_eas_server.po.Msg.MessagePO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public class MessageListStub extends MessageList{

	@Override
	public void init() {

	}

	@Override
	public void finish() {

	}

	@Override
	public MessagePO getMessageWithoutId(int listType, String id) {
		MessagePO mp = new MessagePO("157", "senderId", "recipientId", "title", "content", "1");
		return mp;
	}

	@Override
	public String getSenderListHead() {
		return "ListHead";
	}

	@Override
	public String getRecipientListHead() {
		return "ListHead";
	}

	@Override
	public boolean containsID(int listType, String id) {
		return false;
	}

	@Override
	public MessagePO getMessage(int listType, String id) {
		MessagePO mp = new MessagePO("157", "senderId", "recipientId", "operatorId", "title", "content");
		return mp;
	}

	@Override
	public Feedback removeMessage(int listType, String id) {
		return Feedback.OPERATION_SUCCEED;
	}

	@Override
	public Feedback addMessage(int listType, MessagePO Message) {
		return Feedback.OPERATION_SUCCEED;
	}

	
	@Override
	public Feedback updateMessage(int listType, MessagePO Message) {
		return Feedback.OPERATION_SUCCEED;
	}

	@Override
	public ArrayList<MessagePO> getMessageList(int listType, String operatorId) {
		ArrayList<MessagePO> ml = new ArrayList<MessagePO>();
		MessagePO mp = new MessagePO("157", "senderId", "recipientId", "title", "content", "1");
		ml.add(mp);
		return ml;
	}

	
}
