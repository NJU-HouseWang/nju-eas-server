package NJU.HouseWang.nju_eas_server.logicTest;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.AuthorityManager;
import NJU.HouseWang.nju_eas_server.data.MessageList;
import NJU.HouseWang.nju_eas_server.dataStub.MessageListStub;
import NJU.HouseWang.nju_eas_server.logic.MessageLogic;
import NJU.HouseWang.nju_eas_server.po.Msg.MessagePO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;
import junit.framework.TestCase;

public class MessageLogicTest extends TestCase{
	private MessageListStub ml;
	private AuthorityManager am;
	public MessageLogic mlo;
	
	public void setUp() throws Exception {
		am = AuthorityManager.getInstance();
		ml = new MessageListStub();
		
		mlo = new MessageLogic(){
			@Override
			public MessageList initMessageList() {
				return ml;
			}
			@Override
			public AuthorityManager initAuthorityManager() {
				return am;
			}	
		};
	}
	
	protected void tearDown() throws Exception {}
	
	public void testShowMessage(){
		String result = mlo.showMessage("0", "id");
		System.out.println(result);
		assertTrue(result.equals("157；senderId；recipientId；title；content；1"));
	}
	
	public void testAddMessage(){
		MessagePO mp = new MessagePO();
		String result = mlo.addMessage("1", mp);
		assertTrue(result.equals(Feedback.OPERATION_SUCCEED.toString()));
	}
	
	public void testEditMessage(){
		MessagePO mp = new MessagePO();
		String result = mlo.editMessage(1,mp);
		assertTrue(result.equals(Feedback.OPERATION_SUCCEED.toString()));
	}
	
	public void testMoveMessage(){
		String result = mlo.moveMessage("1", "2", "id");
		assertTrue(result.equals(Feedback.OPERATION_SUCCEED.toString()));
	}
	
	public void testDelMessage(){
		String result = mlo.delMessage("1", "id");
		assertTrue(result.equals(Feedback.OPERATION_SUCCEED.toString()));
	}
	
	public void testShowMessageList(){
		ArrayList<MessagePO> ml1 = new ArrayList<MessagePO>();
		MessagePO mp = new MessagePO("157", "senderId", "recipientId", "title", "content", "1");
		ml1.add(mp);
		ArrayList<String> result = mlo.showMessageList("1", "uid");
		assertTrue(result.get(0).equals(ml1.get(0).toCommand()));
	}
	
	public void testShowSenderMessageListHead(){
		String result = mlo.showSenderMessageListHead();
		assertTrue(result.equals("ListHead"));
	}
	
	public void testShowRecipientMessageListHead(){
		String result = mlo.showRecipientMessageListHead();
		assertTrue(result.equals("ListHead"));
	}
}
