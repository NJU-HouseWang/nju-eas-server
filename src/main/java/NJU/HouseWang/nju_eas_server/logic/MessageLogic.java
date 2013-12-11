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
	
	public MessageLogic(){
		 ml = this.initMessageList();
		 am = this.initAuthorityManager();
	}
	
	public MessageList initMessageList(){
		MessageList m = new MessageList();
		m.init();
		return m;
	}
	
	public AuthorityManager initAuthorityManager(){
		AuthorityManager a = AuthorityManager.getInstance();
		return a;
	}

	@Override
	public Object operate(String cmd) {
		// TODO Auto-generated method stub
		Object feedback = null;
		String[] cmdInfo = cmd.split("；");
		String uid = am.getGuest(cmdInfo[cmdInfo.length-1]);
		String cmdType = cmdInfo[0] + cmdInfo[1];
		switch (cmdType) {
		case "showmessage":
			feedback = this.showMessage(cmdInfo[2],cmdInfo[3]);
			break;
		case "editmessage":
			MessagePO m1 = new MessagePO(cmdInfo[2],cmdInfo[3],cmdInfo[4],uid,cmdInfo[5],cmdInfo[6]);
			feedback = this.editMessage(m1);
			break;
		case "addmessage":
			MessagePO m2 =  new MessagePO(cmdInfo[3],cmdInfo[4],uid,cmdInfo[5],cmdInfo[6]);
			feedback = this.addMessage(cmdInfo[2],m2);
			break;
		case "delmessage":
			feedback = this.delMessage(cmdInfo[2],cmdInfo[3]);
			break;
		case "showmessage_list":
			feedback = this.showMessageList(cmdInfo[2],uid);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String showMessage(String listName, String id) {
		int list = Integer.parseInt(listName);
		return (ml.getMessage(list, id).toCommand());
	}

	@Override
	public String addMessage(String listName, MessagePO mp) {
		int list = Integer.parseInt(listName);
		return (ml.addMessage(list, mp).toString());
	}

	@Override
	public String editMessage(MessagePO mp) {
		return (ml.updateMessage(2,mp).toString());
	}
	@Override
	public String moveMessage(String fromList, String toList, String id) {
		// TODO Auto-generated method stub
		MessagePO mp = ml.getMessage(Integer.parseInt(fromList), id);
		this.delMessage(fromList, id);
		this.addMessage(toList, mp);
		return Feedback.OPERATION_SUCCEED.toString();
	}

	@Override
	public String delMessage(String listName, String id) {
		int list = Integer.parseInt(listName);
		return (ml.removeMessage(list, id).toString());
	}

	@Override
	public ArrayList<String> showMessageList(String listName, String uid) {
		// TODO Auto-generated method stub
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
		return (ml.getListHead().toString());
	}

}
