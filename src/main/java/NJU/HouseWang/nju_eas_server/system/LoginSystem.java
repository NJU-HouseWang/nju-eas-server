package NJU.HouseWang.nju_eas_server.system;

import java.io.IOException;

import NJU.HouseWang.nju_eas_server.SystemFactory.AuthorityManager;
import NJU.HouseWang.nju_eas_server.dataService.DataService;
import NJU.HouseWang.nju_eas_server.netService.NetService;
import NJU.HouseWang.nju_eas_server.po.User.GuestPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_server.systemMessage.UserType;
import NJU.HouseWang.nju_eas_server.systemService.LoginSystemService;

public class LoginSystem implements LoginSystemService{
	private NetService ns;
	private DataService ds;
	private AuthorityManager am = AuthorityManager.getInstance();;
	
	@Override
	//命令格式：login/logout;(用户类型);(用户名);(密码)
	public void operate(String uid, String cmd) {
		
		String[] cmdInfo = cmd.split(";");
		if(cmd.startsWith("login")){
			UserType ut  = UserType.valueOf(cmdInfo[1]);
			GuestPO newGuest = new GuestPO(cmdInfo[2],ut,cmdInfo[3]);
			try {
				this.login(newGuest,ns.getIp());
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}

	}

	@Override
	public void login(GuestPO logger, String ip) {
		
		String id = logger.getId();
		String pw = logger.getPassword();
		String ut = logger.getType().getChName();
		if(!am.containsIP(ip)){
			try {
				ns.sendFeedback(Feedback.IP_ALREADY_EXISTED);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		} else if(!am.containsGuest(id)){
			try {
				ns.sendFeedback(Feedback.ID_ALREADY_EXISTED);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}	else{
			am.addGuest(ip, id);
			try {
				ns.sendFeedback(Feedback.OPERATION_SUCCEED);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}

	@Override
	public void logout(String ip) {
		
		am.removeGuest(ip);
		try {
			ns.sendFeedback(Feedback.OPERATION_SUCCEED);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
