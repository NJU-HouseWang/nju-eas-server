package NJU.HouseWang.nju_eas_server.dataService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.DataPOService;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public interface DataService {
	
	// 初始化数据库
	public void init();
	
	// 设置列表名
	public Feedback setListName(String listName);
	
	// 得到数据实体
	public DataPOService getData(String id);
	
	// 删除数据实体
	public Feedback removeData(String id);
	
	// 加入数据实体
	public Feedback addData(DataPOService data);
	
	// 更新数据实体
	public Feedback updateData(DataPOService data);
	
	// 判断数据ID是否存在
	public boolean containsID(String id);
	
	// 判断数据对象是否存在
	public boolean containsData(DataPOService data);
	
	// 得到数据列表
	public ArrayList<DataPOService> getDataList();
}
