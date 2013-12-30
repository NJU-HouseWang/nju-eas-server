package NJU.HouseWang.nju_eas_server.logic;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.AuthorityManager;
import NJU.HouseWang.nju_eas_server.data.MessageList;
import NJU.HouseWang.nju_eas_server.logicService.MessageLogicService;
import NJU.HouseWang.nju_eas_server.po.Msg.MessagePO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;
/**
 * 私信逻辑类
 * @author 教化场
 * @version 2013-11-10
 */
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
				uid = am.getGuest(cmdInfo[cmdInfo.length - 2]);
				MessagePO m = new MessagePO(uid, cmdInfo[3], cmdInfo[4],
						cmdInfo[5], "0");
				feedback = this.sendMessage(m);
			}
			break;
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
				uid = am.getGuest(cmdInfo[cmdInfo.length - 2]);
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
				uid = am.getGuest(cmdInfo[cmdInfo.length - 2]);
				MessagePO m = new MessagePO(uid, cmdInfo[3], cmdInfo[4],
						cmdInfo[5], "0");
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
	/**
	 * 显示指定列表指定编号的私信 
	 * @param fromBox 发送者
	 * @param id 私信号
	 * @return 私信内容
	 */
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
	/**
	 * 添加私信到指定的列表
	 * @param fromBox 发送者
	 * @param mp 私信PO
	 * @return 反馈
	 */
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
	/**
	 * 编辑私信
	 * @param fromBox 发送者
	 * @param mp 私信PO
	 * @return 反馈
	 */
	public String editMessage(int fromBox, MessagePO mp) {

		try {
			return (ml.updateMessage(fromBox, mp).toString());
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	/**
	 * 把私信从一个列表转移到另一个列表
	 * @param fromBox 发送者
	 * @param toBox 接收者
	 * @param id 私信号
	 * @return 反馈
	 */
	public String moveMessage(String fromList, String toList, String id) {
		try {
			MessagePO mp = ml.getMessageWithoutId(Integer.parseInt(fromList),
					id);
			this.eraseMessage(fromList, id);
			this.addMessage(toList, mp);
			return Feedback.OPERATION_SUCCEED.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	/**
	 * 删除私信
	 * @param fromBox 发送者
	 * @param id 私信号
	 * @return 反馈
	 */
	public String delMessage(String fromBox, String id) {
		try {
			return moveMessage(fromBox, "3", id);
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	/**
	 * 显示该用户的指定列表的私信列表
	 * @param fromBox 发送者
	 * @param uid 用户名
	 * @return 私信列表
	 */
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
	/**
	 * 彻底删除私信
	 * @param fromBox 发送者
	 * @param id 私信号
	 * @return 反馈
	 */
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
	/**
	 * 显示私信列表的表头1
	 * @return 表头1
	 */
	public String showSenderMessageListHead() {
		try {
			return ml.getSenderListHead();
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	/**
	 * 显示私信列表的表头2
	 * @return 表头2
	 */
	public String showRecipientMessageListHead() {
		try {
			return ml.getRecipientListHead();
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	/**
	 * 发送私信
	 * @param mp 私信PO
	 * @return 反馈
	 */
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
	/**
	 * 保存草稿
	 * @param mp 私信PO
	 * @return 反馈
	 */
	public String saveDraft(MessagePO mp) {
		try {
			String feedback = this.addMessage("2", mp);
			return feedback;
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	/**
	 * 返回未读邮件数量
	 * @param uid 用户名
	 * @return 未读邮件数量
	 */
	public String showNewMessageNum(String uid) {
		try {
			int num = 0;
			ArrayList<MessagePO> list = ml.getMessageList(0, uid);
			if (!list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getStatus() == 0) {
						num++;
					}
				}
			}
			return ("" + num);
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

}
