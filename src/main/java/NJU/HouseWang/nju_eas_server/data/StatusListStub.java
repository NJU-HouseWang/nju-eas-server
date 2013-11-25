package NJU.HouseWang.nju_eas_server.data;

import java.sql.SQLException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.StatusPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public class StatusListStub extends StatusList{
	// 连接数据库
		public void init() {
			
		}

		// 关闭数据库
		public void finish() {
			
		}


		public StatusPO getStatus(String function) {
			StatusPO result = new StatusPO(function,true);
			return result;
		}

		

		public Feedback updateStatus(StatusPO status) {
			return Feedback.OPERATION_SUCCEED;
		}

		public ArrayList<StatusPO> getStatusList() {
			ArrayList<StatusPO> result = new ArrayList<StatusPO>();
			StatusPO s1 = new StatusPO("selectCourse",true);
			StatusPO s2 = new StatusPO("byelectCourse",false);
			StatusPO s3 = new StatusPO("quitCourse",false);
			result.add(s1);
			result.add(s2);
			result.add(s3);
			return result;
		}
}
