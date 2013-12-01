package NJU.HouseWang.nju_eas_server.systemService;

import NJU.HouseWang.nju_eas_server.po.Edu.StatusPO;

public interface CourseSelectionSystemService extends SystemService {
	//显示状态
	public void showStatus(String function);
	//编辑状态
	public void editStatus(StatusPO sp);
	//显示状态列表
	public void showStatusList();
	//显示状态列表的表头
	public void showStatusListHead();
	//选课
	public void selectCourse(String courseId, String studentId);
	
	//通识课选课
	public void selectCommonCourse(String courseId, String studentId);
	
	//退选
	public void quitCourse(String courseId, String studentId);
	
	//批量添加选课记录
	public void addCourse_StudentList();
	
	//批量删除选课记录
	public void delCourse_StudentList();
	
	//通识课选课抽签
	public void processCommonCourseSelection();
	
}
