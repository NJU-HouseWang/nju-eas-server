package NJU.HouseWang.nju_eas_server.dataService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.TeachingPlanItemPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

/**
 * 教学计划类
 * @author 教化场
 * @version 2013-11-17
 */
public interface TeachingPlanService {
	/**
	 * 获取教学计划
	 * @param deptId 教学计划号
	 * @return 教学计划列表
	 */
	public ArrayList<TeachingPlanItemPO> getTeachingPlan(String deptId);

	/**
	 * 删除教学计划
	 * @param dept 院系
	 * @return 反馈
	 */
	public Feedback delTeachingPlan(String dept);

	/**
	 * 添加教学计划
	 * @param dept 院系
	 * @param ep 教学计划PO
	 * @return 反馈
	 */
	public Feedback addTeachingPlanItem(String dept, TeachingPlanItemPO ep);

	/**
	 * 获取列表表头
	 * @return 表头
	 */
	public String getListHead();

	/**
	 * 创建教学计划
	 * @param deptId 院系号
	 * @return 反馈
	 */
	public Feedback createTeachingPlan(String deptId);

	/**
	 * 放弃教学计划
	 * @param deptId 教学计划号
	 * @return 反馈
	 */
	public Feedback dropTeachingPlan(String deptId);
	
	/**
	 * 获取导入列表表头
	 * @return 表头
	 */
	public String getImportListHead();

}
