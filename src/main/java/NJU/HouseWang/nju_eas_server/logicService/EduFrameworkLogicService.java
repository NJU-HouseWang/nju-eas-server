package NJU.HouseWang.nju_eas_server.logicService;

import java.util.ArrayList;

/**
 * 教学框架逻辑类
 * @author 教化场
 * @version 2013-11-8
 */
public interface EduFrameworkLogicService extends LogicService {
	/**
	 * 添加教学框架
	 * @param list
	 * @return 反馈
	 */
	public String addEduFramework(ArrayList<String> list);

	/**
	 * 删除教学框架
	 * @return 反馈
	 */
	public String delEduFramework();

	/**
	 * 显示教学框架
	 * @return 教学框架列表
	 */
	public ArrayList<String> showEduFramework();

	/**
	 * 显示模块数量
	 * @return 模块数量
	 */
	public String showModuleNum();
	
	/**
	 * 显示教学框架列表表头
	 * @return 表头
	 */
	public String showEduFrameworkHead();
	
	/**
	 * 显示导入教学框架表头
	 * @return 表头
	 */
	public String showImportEduFrameworkHead();
}
