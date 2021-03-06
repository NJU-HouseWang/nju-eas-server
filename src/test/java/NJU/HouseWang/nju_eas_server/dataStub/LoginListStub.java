package NJU.HouseWang.nju_eas_server.dataStub;

import java.sql.SQLException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.LoginList;
import NJU.HouseWang.nju_eas_server.po.User.GuestPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_server.systemMessage.UserType;

public class LoginListStub extends LoginList{
	// 连接数据库
		public void init() {
			
		}

		// 关闭数据库
		public void finish() {
			
		}
		
		@Override
		public ArrayList<GuestPO> getLoginList() {
			ArrayList<GuestPO> list = new ArrayList<GuestPO>();
			GuestPO gp = new GuestPO("id", UserType.Student, "password");
			list.add(gp);
			return list;
		}

		@Override
		public String getListHead() {
			return "ListHead";
		}

		public GuestPO getLoginer(String id) {
			GuestPO result = new GuestPO("121250157",UserType.Student,"bilicrazy123");
			
			return result;
		}
		
		public boolean containsID(String id) {
			Boolean result = true;
			return result;
		}

		public Feedback removeLoginer(String id) {
			return Feedback.OPERATION_SUCCEED;
		}

		public Feedback addLoginer(GuestPO user) {
			return Feedback.OPERATION_SUCCEED;
		}

		public Feedback updateLoginer(GuestPO user) {
			return Feedback.OPERATION_SUCCEED;
		}

		public ArrayList<GuestPO> getLoginList(String conditions) {
			ArrayList<GuestPO> result = new ArrayList<GuestPO>();
			GuestPO g1 = new GuestPO("121250157",UserType.Student,"crazybili123");
			GuestPO g2 = new GuestPO("0001",UserType.Teacher,"123456");
			result.add(g1);
			result.add(g2);
			return result;
		}

		
}
