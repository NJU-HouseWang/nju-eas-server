package NJU.HouseWang.nju_eas_server.dataService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.EduFrameworkItemPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

/**
 * 教学框架类
 * @author 教化场
 * @version 2013-11-14
 */
public interface EduFrameworkService {
	/**
	 * 获取教学框架
	 * @return 教学框架列表
	 */
	public ArrayList<EduFrameworkItemPO> getEduFramework();

	/**
	 * 删除教学框架
	 * @return 反馈
	 */
	public Feedback delEduFramework();

	/**
	 * 添加教学框架
	 * @param ep 教学框架PO
	 * @return 反馈
	 */
	public Feedback addEduFrameworkItem(EduFrameworkItemPO ep);

	/**
	 * 获取列表表头
	 * @return 表头
 	 */
	public String getListHead();
	/**
	 * 获取导入列表表头
	 * @return 导入列表表头
	 */
	public String getImportListHead();
}
