package NJU.HouseWang.nju_eas_server.dataStub;

import java.util.ArrayList;
import java.util.Date;

import NJU.HouseWang.nju_eas_server.data.LogList;
import NJU.HouseWang.nju_eas_server.po.Msg.LogPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public class LogListStub extends LogList{
	// 连接数据库
		public void init() {
			
		}

		// 关闭数据库
		public void finish() {
			
		}

		public Feedback addLog(LogPO log) {
			
			return Feedback.OPERATION_SUCCEED;
		}
		
		public ArrayList<LogPO> getLogList(String conditions) {
			ArrayList<LogPO> result = new ArrayList<LogPO>();
			LogPO l1 = new LogPO("121250157","128.0.0.0.1",new Date().toString(),"fucked himself");
			LogPO l2 = new LogPO("admin","128.0.0.0.2",new Date().toString(),"do nothing");
			result.add(l1);
			result.add(l2);
			return result;
		}

}
