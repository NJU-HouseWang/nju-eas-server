package NJU.HouseWang.nju_eas_server.dataService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.DataPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public interface DataService {
	
	// 设置列表名
	public Feedback setListName(String listName);
	
	// 得到数据实体
	public DataPO getData(String id);
	
	// 删除数据实体
	public Feedback removeData(String id);
	
	// 加入数据实体
	public Feedback addData(DataPO data);
	
	// 更新数据实体
	public Feedback updateData(DataPO data);
	
	// 判断数据ID是否存在
	public boolean containsID(String id);
	
	// 判断数据对象是否存在
	public boolean containsData(DataPO data);
	
	// 得到数据列表
	public ArrayList<DataPO> getDataList();
}
