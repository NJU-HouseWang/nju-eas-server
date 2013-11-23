package NJU.HouseWang.nju_eas_server.data;

import java.sql.SQLException;

import NJU.HouseWang.nju_eas_server.po.User.GuestPO;
import NJU.HouseWang.nju_eas_server.systemMessage.UserType;

public class LoginListStub extends LoginList{
	// 连接数据库
		public void init() {
			
		}

		// 关闭数据库
		public void finish() {
			
		}
		
		public GuestPO getLoginer(String id) {
			GuestPO result = new GuestPO("121250157",UserType.Student,"bilicrazy123");
			
			return result;
		}
}
