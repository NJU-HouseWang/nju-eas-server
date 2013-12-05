package NJU.HouseWang.nju_eas_server.dataService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.TeachingPlanPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

/**
 * 教学计划列表
 * @author 教化场
 * @version 2013-11-17
 */
public interface TeachingPlanListService {
	/**
	 * 获取教学计划
	 * @param dept 院系
	 * @return 教学计划PO
	 */
	public TeachingPlanPO getTeachingPlan(String dept);

	/**
	 * 编辑教学计划
	 * @param TeachingPlan 教学计划
	 * @return 反馈
	 */
	public Feedback updateTeachingPlanItem(TeachingPlanPO TeachingPlan);

	/**
	 * 获取教学计划列表
	 * @return 教学计划列表
	 */
	public ArrayList<TeachingPlanPO> getTeachingPlanList();

	/**
	 * 获取列表表头
	 * @return 表头
	 */
	public String getListHead();

}
