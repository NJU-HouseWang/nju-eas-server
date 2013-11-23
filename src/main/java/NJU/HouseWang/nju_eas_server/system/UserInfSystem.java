package NJU.HouseWang.nju_eas_server.system;

import java.io.IOException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.dataService.DataService;
import NJU.HouseWang.nju_eas_server.netService.NetService;
import NJU.HouseWang.nju_eas_server.po.DataPOService;
import NJU.HouseWang.nju_eas_server.po.User.GuestPO;
import NJU.HouseWang.nju_eas_server.po.User.UserPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_server.systemMessage.UserType;
import NJU.HouseWang.nju_eas_server.systemService.UserInfSystemService;

public class UserInfSystem implements UserInfSystemService {
	private DataService ds;
	private String uid;
	private GuestPO guest = null;
	private NetService ns;

	@Override
	public void operate(String uid, String cmd) {
		this.uid = uid;
		guest = (GuestPO) ds.getData("login_list", uid);	
		String[] cmdpart = cmd.split("；");
		
		if(cmdpart[0].equals("show")){
			if(cmdpart[1].equals("SelfInformation")){
				showSelfInformation();
				}
			
			if(cmdpart[1].equals("UserList")){
				showUserList(cmdpart[2] + "_List");
				}
			}
		
		else if(cmdpart[0].equals("edit")){
			if(cmdpart[1].equals("SelfInformation")){
				String userType = ((GuestPO)ds.getData("LoginList", uid)).getType().toString();
				UserPO upo = new UserPO(uid, UserType.valueOf(cmdpart[2]));
				editSelfInformation(upo);
				}
			
			if(cmdpart[1].equals("User")){
				String userType = ((GuestPO)ds.getData("LoginList", uid)).getType().toString();
				UserPO upo = new UserPO(uid, UserType.valueOf(cmdpart[2]));
				editUser(upo);
				}
			}
		
		else if(cmdpart[0].equals("add")){
			if(cmdpart[1].equals("User")){
				GuestPO gp = new GuestPO(cmdpart[2],UserType.valueOf(cmdpart[3]),cmdpart[4]);
				addUser(gp);
			}
			if(cmdpart[1].equals("UserList")){
				addUserList();
			}
		}
		
		else if(cmdpart[0].equals("del")){
			if(cmdpart[1].equals("User")){
				deleteUser(cmdpart[2]);
			}
			
			if(cmdpart[1].equals("UserList")){
				delUserList();
			}
		}
	}

	@Override
	public void showSelfInformation() {
		String userType = guest.getType().toString();
		String listName = new String();
		String result = null;
		listName = userType.toLowerCase() + "_list";
		UserPO u = (UserPO)ds.getData(listName, uid);
		result = u.toCommand();
		try {
			ns.sendFeedback(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void editSelfInformation(UserPO u) {
		ds.updateData(u.getType().toString() + "_list", u);
		try {
			ns.sendFeedback(Feedback.OPERATION_SUCCEED.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addUser(GuestPO u) {
		ds.addData(u.getType().toString() + "_list", u);
		try {
			ns.sendFeedback(Feedback.OPERATION_SUCCEED.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void editUser(UserPO u) {
		ds.updateData("login_list", u);
		try {
			ns.sendFeedback(Feedback.OPERATION_SUCCEED.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(String id) {
		GuestPO gpo = (GuestPO)ds.getData("login_list", id);
		ds.removeData(gpo.getType().toString() + "_list", id);
		ds.removeData("login_list", id);
		try {
			ns.sendFeedback(Feedback.OPERATION_SUCCEED.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void showUserList(String conditions) {
		ArrayList<String> slist = null;
		ArrayList<DataPOService> glist = ds.getDataList("login_list",null);

		for(int i = 0;i < glist.size();i++){
			slist.add(((GuestPO)glist.get(i)).toString());
		}
		
		try {
			ns.sendList(slist);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addUserList() {
		ArrayList<String> list = null;

		try {
			list = ns.receiveList();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(String str: list) {
			String[] strpart = str.split("；");	
			GuestPO upo = new GuestPO(strpart[0], UserType.valueOf(strpart[1]), strpart[2]);
			addUser(upo);
		}
		
		try {
			ns.sendFeedback(Feedback.OPERATION_SUCCEED.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delUserList() {
		ArrayList<String> list = null;

		try {
			list = ns.receiveList();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(String str: list) {
			deleteUser(str);
		}
		
		try {
			ns.sendFeedback(Feedback.OPERATION_SUCCEED.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
