package NJU.HouseWang.nju_eas_server.systemService;

import java.io.File;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.CoursePO;

public interface TeachingPlanSystemService extends SystemService {
	// 查看整体框架策略
	public void showFrameWork();

	// 修改整体框架策略
	public void editFrameWork();

	// 查看教学计划
	public void showTeachingPlan(String dept);
	
	// 增加教学计划
	public void addTeachingPlan();

	// 审核教学计划
	public void auditTeachingPlan(String dept, boolean audit);

}
