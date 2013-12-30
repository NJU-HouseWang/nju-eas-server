package NJU.HouseWang.nju_eas_server.logicService;

import java.io.File;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.TeachingPlanItemPO;

/**
 * 教学计划逻辑类
 * @author 教化场
 * @version 2013-11-11
 */
public interface TeachingPlanLogicService extends LogicService {
	/**
	 * 查看教学计划
	 * @param dept 院系
	 * @return 教学计划列表
	 */
	public ArrayList<String> showTeachingPlan(String dept);

	/**
	 * 查看教学计划列表
	 * @return 教学计划列表
	 */
	public ArrayList<String> showTeachingPlanList();

	/**
	 * 增加教学计划
	 * @param dept 院系
	 * @param list 列表名
	 * @return 反馈
	 */
	public String addTeachingPlan(String dept,  ArrayList<String> list);

	/**
	 * 修改教学计划
	 * @param dept 院系
	 * @param list 列表名
	 * @return 反馈
	 */
	public String editTeachingPlan(String dept,  ArrayList<String> list);

	/**
	 * 删除教学计划
	 * @param dept 院系
	 * @return 反馈
	 */
	public String delTeachingPlan(String dept);

	/**
	 * 审核教学计划
	 * @param dept 院系
	 * @param status 状态
	 * @return 反馈
	 */
	public String auditTeachingPlan(String dept, int status);

	/**
	 * 上传教学计划doc文档
	 * @param path 路径
	 * @param dept 院系
	 * @return 反馈
	 */
	public String uploadTeachingPlanFile(String path, String dept);

	/**
	 * 下载教学计划doc文档
	 * @param dept 院系
	 * @return 文档
	 */
	public File downloadTeachingPlanFile(String dept);

	/**
	 * 判断教学计划是否合法
	 * @param teachingPlan 教学计划PO列表
	 * @return 教学计划是否合法
	 */
	public boolean judgeTeachingPlan(ArrayList<TeachingPlanItemPO> teachingPlan);

	/**
	 * 返回TeachingPlan表头
	 * @return 表头
	 */
	public String showTeachingPlanHead();

	/**
	 * 返回teachingPlanList表头
	 * @return 表头
	 */
	public String showTeachingPlanListHead();
	
	/**
	 * 返回附件的名称
	 * @param dept 院系
	 * @return 附件名
	 */
	public String showFlieName(String dept);
	
	/**
	 * 查看教学计划的状态
	 * @param dept 院系
	 * @return 教学计划的状态
	 */
	public String showTeachingPlanStatus(String dept);
	
	/**
	 * 返回用于导入的teachingPlan表头
	 * @return 表头
	 */
	public String showImportTeachingPlanHead();
	
	/**
	 * 返回教学计划模板文件
	 * @return 教学计划模板文件
	 */
	public File downloadTeachingPlanTemplate();
	/**
	 * string转换教学计划项
	 * @param str
	 * @return 教学计划项
	 */
	public TeachingPlanItemPO stringToTeachingPlanItem(String str);

}
