package NJU.HouseWang.nju_eas_server.system;

import java.io.IOException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.MessageList;
import NJU.HouseWang.nju_eas_server.netService.NetService;
import NJU.HouseWang.nju_eas_server.po.Msg.MessagePO;
import NJU.HouseWang.nju_eas_server.systemService.MessageSystemService;

public class MessageSystem implements MessageSystemService {
	private NetService ns;
	private MessageList ml;
	
	public MessageSystem(){
		ml = new MessageList();
		ml.init();
	}
	@Override
	public void operate(String uid, String cmd) {
		// TODO Auto-generated method stub
		String[] cmdInfo = cmd.split("；");
		String cmdType = cmdInfo[0] + cmdInfo[1];
		switch (cmdType) {
		case "showmessage":
			this.showMessage(cmdInfo[2],cmdInfo[3]);
			break;
		case "editmessage":
			MessagePO m1 = new MessagePO(cmdInfo[2],cmdInfo[3],cmdInfo[4],uid,cmdInfo[5],cmdInfo[6]);
			this.editMessage(m1);
			break;
		case "addmessage":
			MessagePO m2 =  new MessagePO(cmdInfo[3],cmdInfo[4],uid,cmdInfo[5],cmdInfo[6]);
			this.addMessage(cmdInfo[2],m2);
			break;
		case "delmessage":
			this.delMessage(cmdInfo[2],cmdInfo[3]);
			break;
		case "showmessage_list":
			this.showMessageList(cmdInfo[2],uid);
			break;
		case "showmessage_list_head":
			this.showMessageListHead();
			break;
		case "movemessage":
			this.moveMessage(cmdInfo[2], cmdInfo[3], cmdInfo[4]);
			break;
		default:
			break;
		}
	}

	@Override
	public void initNetService(NetService ns) {
		// TODO Auto-generated method stub
		this.ns = ns;
	}

	@Override
	public void showMessage(String listName, String id) {
		// TODO Auto-generated method stub
		try {
			ns.sendFeedback(ml.getMessage(listName, id).toCommand());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addMessage(String listName, MessagePO mp) {
		// TODO Auto-generated method stub
		try {
			ns.sendFeedback(ml.addMessage(listName, mp).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void editMessage(MessagePO mp) {
		// TODO Auto-generated method stub
		try {
			ns.sendFeedback(ml.updateMessage("unsent_message_list",mp).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void moveMessage(String fromList, String toList, String id) {
		// TODO Auto-generated method stub
		MessagePO mp = ml.getMessage(fromList, id);
		this.delMessage(fromList, id);
		this.addMessage(toList, mp);
	}

	@Override
	public void delMessage(String listName, String id) {
		// TODO Auto-generated method stub
		try {
			ns.sendFeedback(ml.removeMessage(listName, id).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void showMessageList(String listName, String uid) {
		// TODO Auto-generated method stub
		ArrayList<MessagePO> list = ml.getMessageList(listName, uid);
		ArrayList<String> MessageList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			String MessageInfo = (list.get(i)).toCommand();
			MessageList.add(MessageInfo);
		}
		try {
			ns.sendList(MessageList);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void showMessageListHead() {
		// TODO Auto-generated method stub
		try {
			ns.sendFeedback(ml.getListHead().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
