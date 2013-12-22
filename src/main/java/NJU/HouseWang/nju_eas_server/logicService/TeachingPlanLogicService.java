package NJU.HouseWang.nju_eas_server.logicService;

import java.io.File;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.TeachingPlanItemPO;

public interface TeachingPlanLogicService extends LogicService {
	// 查看教学计划
	public ArrayList<String> showTeachingPlan(String dept);

	// 查看教学计划列表
	public ArrayList<String> showTeachingPlanList();

	// 增加教学计划
	public String addTeachingPlan(String dept,  ArrayList<String> list);

	// 修改教学计划
	public String editTeachingPlan(String dept,  ArrayList<String> list);

	// 删除教学计划
	public String delTeachingPlan(String dept);

	// 审核教学计划
	public String auditTeachingPlan(String dept, int status);

	// 上传教学计划doc文档
	public String uploadTeachingPlanFile(String dept);

	// 下载教学计划doc文档
	public File downloadTeachingPlanFile(String dept);

	// 判断教学计划是否合法
	public boolean judgeTeachingPlan(ArrayList<TeachingPlanItemPO> teachingPlan);

	// 返回TeachingPlan表头
	public String showTeachingPlanHead();

	// 返回teachingPlanList表头
	public String showTeachingPlanListHead();
	
	//返回附件的名称
	public String showFlieName(String dept);
	
	//查看教学计划的状态
	public String showTeachingPlanStatus(String dept);
	
	//返回用于导入的teachingPlan表头
	public String showImportTeachingPlanHead();
}
