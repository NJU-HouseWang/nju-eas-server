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
	private String uid;

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
		String cmdType = cmdInfo[0] + cmdInfo[1];
		switch (cmdType) {
		case "sendmessage":
			if (!cmd.endsWith("；ok")) {
				feedback = "ip";
			} else {
				uid = am.getGuest(cmdInfo[cmdInfo.length - 1]);
				MessagePO m = new MessagePO(uid, cmdInfo[2], cmdInfo[3],
						cmdInfo[4], "0");
				feedback = this.sendMessage(m);
			}
		case "showmessage":
			feedback = this.showMessage(cmdInfo[2], cmdInfo[3]);
			break;

		case "delmessage":
			feedback = this.delMessage(cmdInfo[2], cmdInfo[3]);
			break;
		case "erasemessage":
			feedback = this.eraseMessage(cmdInfo[2], cmdInfo[3]);
			break;
		case "showmessage_list":
			if (!cmd.endsWith("；ok")) {
				feedback = "ip";
			} else {
				uid = am.getGuest(cmdInfo[cmdInfo.length - 1]);
				feedback = this.showMessageList(cmdInfo[2], uid);
			}
			break;
		case "showmessage_list_head_sender":
			feedback = this.showSenderMessageListHead();
			break;
		case "showmessage_list_head_recipient":
			feedback = this.showRecipientMessageListHead();
			break;
		case "savedraft":
			if (!cmd.endsWith("；ok")) {
				feedback = "ip";
			} else {
				uid = am.getGuest(cmdInfo[cmdInfo.length - 1]);
				MessagePO m = new MessagePO(uid, cmdInfo[2], cmdInfo[3],
						cmdInfo[4], "0");
				feedback = this.saveDraft(m);
			}
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
	public String showMessage(String fromBox, String id) {
		try {
			int list = Integer.parseInt(fromBox);
			MessagePO mp = ml.getMessage(list, id);
			// 如果是收件箱，则标记为已读
			if (list == 0) {
				mp.setStatus(1);
				this.editMessage(list, mp);
			}
			return (mp.toCommand());
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public String addMessage(String fromBox, MessagePO mp) {
		try {
			int list = Integer.parseInt(fromBox);
			return (ml.addMessage(list, mp).toString());
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public String editMessage(int fromBox, MessagePO mp) {

		try {
			return (ml.updateMessage(fromBox, mp).toString());
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public String moveMessage(String fromList, String toList, String id) {
		try {
			MessagePO mp = ml.getMessageWithoutId(Integer.parseInt(fromList),
					id);
			this.delMessage(fromList, id);
			this.addMessage(toList, mp);
			return Feedback.OPERATION_SUCCEED.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public String delMessage(String fromBox, String id) {
		try {
			return moveMessage(fromBox, "3", id);
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public ArrayList<String> showMessageList(String fromBox, String uid) {

		int listNum = Integer.parseInt(fromBox);
		ArrayList<MessagePO> list = ml.getMessageList(listNum, uid);
		ArrayList<String> MessageList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			String MessageInfo = (list.get(i)).toCommand();
			MessageList.add(MessageInfo);
		}
		return MessageList;
	}

	@Override
	public String eraseMessage(String fromBox, String id) {
		try {
			int list = Integer.parseInt(fromBox);
			return (ml.removeMessage(list, id).toString());
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public String showSenderMessageListHead() {
		try {
			return ml.getSenderListHead();
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public String showRecipientMessageListHead() {
		try {
			return ml.getRecipientListHead();
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public String sendMessage(MessagePO mp) {
		try {
			String feedback = "";
			// 加入收件箱
			feedback = this.addMessage("0", mp);
			if (feedback.equals(Feedback.OPERATION_FAIL.toString())) {
				return feedback;
			}
			// 加入发件箱
			feedback = this.addMessage("1", mp);
			if (feedback.equals(Feedback.OPERATION_FAIL.toString())) {
				return feedback;
			}
			return feedback;
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	public String saveDraft(MessagePO mp) {
		try {
			String feedback = this.addMessage("2", mp);
			return feedback;
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

}
