package NJU.HouseWang.nju_eas_server.dataService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.DataPOService;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public interface DataService {

	// 得到数据实体
	public DataPOService getData(String list, String id);

	// 删除数据实体
	public Feedback removeData(String list, String id);

	// 加入数据实体
	public Feedback addData(String list, DataPOService data);

	// 更新数据实体
	public Feedback updateData(String list, DataPOService data);

	// 判断数据ID是否存在
	public boolean containsID(String list, String id);

	// 判断数据对象是否存在
	public boolean containsData(String list, DataPOService data);

	// 得到数据列表
	public ArrayList<DataPOService> getDataList(String list, String conditions);
}
