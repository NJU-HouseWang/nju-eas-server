package NJU.HouseWang.nju_eas_server.systemService;

import java.io.File;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.CoursePO;

public interface TeachingPlanSystemService {
	// 查看整体框架策略
	public void showFrameWork();

	// 修改整体框架策略
	public void editFrameWork(ArrayList<CoursePO> list);

	// 查看教学计划
	public void showTeachingPlan(String dept);
	
	// 增加教学计划
	public void addTeachingPlan(ArrayList<CoursePO> teachingPlan, File file);

	// 审核教学计划
	public void auditTeachingPlan(String dept, boolean audit);

	// 修改本院系的教学计划
	public void uploadTeachingPlan(ArrayList<CoursePO> teachingPlan, File file);
}
