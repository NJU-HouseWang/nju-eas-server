package NJU.HouseWang.nju_eas_server.logic;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.AuthorityManager;
import NJU.HouseWang.nju_eas_server.data.MessageList;
import NJU.HouseWang.nju_eas_server.logicService.MessageLogicService;
import NJU.HouseWang.nju_eas_server.po.Msg.MessagePO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public class MessageLogic implements MessageLogicService {
	private MessageList ml;
	private AuthorityManager am;

	public MessageLogic() {
		ml = this.initMessageList();
		am = this.initAuthorityManager();
	}

	public MessageList initMessageList() {
		MessageList m = new MessageList();
		m.init();
		return m;
	}

	public AuthorityManager initAuthorityManager() {
		AuthorityManager a = AuthorityManager.getInstance();
		return a;
	}

	@Override
	public Object operate(String cmd) {
		// TODO Auto-generated method stub
		String[] cmdInfo = cmd.split("；");
		String uid = am.getGuest(cmdInfo[cmdInfo.length - 1]);
		String cmdType = cmdInfo[0] + cmdInfo[1];
		switch (cmdType) {
		case "showmessage":
			return this.showMessage(cmdInfo[2], cmdInfo[3]);

		case "editmessage":
			MessagePO m1 = new MessagePO(cmdInfo[2], cmdInfo[3], cmdInfo[4],
					uid, cmdInfo[5], cmdInfo[6]);
			return this.editMessage(m1);

		case "addmessage":
			MessagePO m2 = new MessagePO(cmdInfo[3], cmdInfo[4], uid,
					cmdInfo[5], cmdInfo[6]);
			return this.addMessage(cmdInfo[2], m2);

		case "delmessage":
			return this.delMessage(cmdInfo[2], cmdInfo[3]);

		case "showmessage_list":
			return this.showMessageList(cmdInfo[2], uid);

		case "showmessage_list_head":
			return this.showMessageListHead();

		case "movemessage":
			return this.moveMessage(cmdInfo[2], cmdInfo[3], cmdInfo[4]);

		default:
			return null;
		}
	}

	@Override
	public Object operate(String cmd, ArrayList<String> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String showMessage(String listName, String id) {
		return (ml.getMessage(listName, id).toCommand());
	}

	@Override
	public String addMessage(String listName, MessagePO mp) {
		return (ml.addMessage(listName, mp).toString());
	}

	@Override
	public String editMessage(MessagePO mp) {
		return (ml.updateMessage("unsent_message_list", mp).toString());
	}

	@Override
	public String moveMessage(String fromList, String toList, String id) {
		// TODO Auto-generated method stub
		MessagePO mp = ml.getMessage(fromList, id);
		this.delMessage(fromList, id);
		this.addMessage(toList, mp);
		return Feedback.OPERATION_SUCCEED.toString();
	}

	@Override
	public String delMessage(String listName, String id) {
		return (ml.removeMessage(listName, id).toString());
	}

	@Override
	public ArrayList<String> showMessageList(String listName, String uid) {
		// TODO Auto-generated method stub
		ArrayList<MessagePO> list = ml.getMessageList(listName, uid);
		ArrayList<String> MessageList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			String MessageInfo = (list.get(i)).toCommand();
			MessageList.add(MessageInfo);
		}
		return MessageList;
	}

	@Override
	public String showMessageListHead() {
		return (ml.getListHead().toString());
	}

}
