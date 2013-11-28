package NJU.HouseWang.nju_eas_server.systemService;

import java.io.File;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.CoursePO;
import NJU.HouseWang.nju_eas_server.po.Edu.EduFrameworkItemPO;
import NJU.HouseWang.nju_eas_server.po.Edu.TeachingPlanItemPO;

public interface TeachingPlanSystemService extends SystemService {
	//	查看教学计划
	public void showTeachingPlan(String dept);
	
	//	查看教学计划列表
	public void showTeachingPlanList();
	
	//	增加教学计划
	public void addTeachingPlan(String dept);

	//	修改教学计划
	public void editTeachingPlan(String dept);
	
	//	删除教学计划
	public void delTeachingPlan(String dept);
	
	//	审核教学计划
	public void auditTeachingPlan(String dept, int status);
	
	//	上传教学计划doc文档
	public void uploadTeachingPlanFile(String dept, String filePath);
	
	//	下载教学计划doc文档
	public void downloadTeachingPlanFile(String dept,String filePath);
	
	//	判断教学计划是否合法
	public boolean judgeTeachingPlan(ArrayList<TeachingPlanItemPO> teachingPlan);

	//返回TeachingPlan表头
	public void showTeachingPlanHead();
	
	//返回teachingPlanList表头
	public void showTeachingPlanListHead();
}
