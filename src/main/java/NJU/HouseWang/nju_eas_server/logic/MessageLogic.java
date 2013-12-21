package NJU.HouseWang.nju_eas_server.logic;

import java.io.IOException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.AuthorityManager;
import NJU.HouseWang.nju_eas_server.data.MessageList;
import NJU.HouseWang.nju_eas_server.logicService.MessageLogicService;
import NJU.HouseWang.nju_eas_server.netService.NetService;
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

		Object feedback = null;
		String[] cmdInfo = cmd.split("；");
		String uid = am.getGuest(cmdInfo[cmdInfo.length - 1]);
		String cmdType = cmdInfo[0] + cmdInfo[1];
		switch (cmdType) {
		case "showmessage":
			feedback = this.showMessage(cmdInfo[2], cmdInfo[3]);
			break;
		case "editmessage":
			MessagePO m1 = new MessagePO(cmdInfo[2], cmdInfo[3], cmdInfo[4],
					uid, cmdInfo[5], cmdInfo[6]);
			feedback = this.editMessage(m1);
			break;
		case "addmessage":
			MessagePO m2 = new MessagePO(cmdInfo[3], cmdInfo[4], uid,
					cmdInfo[5], cmdInfo[6]);
			feedback = this.addMessage(cmdInfo[2], m2);
			break;
		case "delmessage":
			feedback = this.delMessage(cmdInfo[2], cmdInfo[3]);
			break;
		case "showmessage_list":
			feedback = this.showMessageList(cmdInfo[2], uid);
			break;
		case "showmessage_list_head":
			feedback = this.showMessageListHead();
			break;
		case "movemessage":
			feedback = this.moveMessage(cmdInfo[2], cmdInfo[3], cmdInfo[4]);
			break;
		default:
			break;
		}
		ml.finish();
		return feedback;
	}

	@Override
	public Object operate(String cmd, ArrayList<String> list) {

		return null;
	}

	@Override
	public String showMessage(String listName, String id) {
		try {
			int list = Integer.parseInt(listName);
			return (ml.getMessage(list, id).toCommand());
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public String addMessage(String listName, MessagePO mp) {
		try {
			int list = Integer.parseInt(listName);
			return (ml.addMessage(list, mp).toString());
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public String editMessage(MessagePO mp) {

		try {
			return (ml.updateMessage(2, mp).toString());
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public String moveMessage(String fromList, String toList, String id) {
		try {
			MessagePO mp = ml.getMessage(Integer.parseInt(fromList), id);
			this.delMessage(fromList, id);
			this.addMessage(toList, mp);
			return Feedback.OPERATION_SUCCEED.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public String delMessage(String listName, String id) {
		try {
			int list = Integer.parseInt(listName);
			return (ml.removeMessage(list, id).toString());
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public ArrayList<String> showMessageList(String listName, String uid) {

		int listNum = Integer.parseInt(listName);
		ArrayList<MessagePO> list = ml.getMessageList(listNum, uid);
		ArrayList<String> MessageList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			String MessageInfo = (list.get(i)).toCommand();
			MessageList.add(MessageInfo);
		}
		return MessageList;
	}

	@Override
	public String showMessageListHead() {
		try {
			return (ml.getListHead().toString());
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

}
