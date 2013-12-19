package NJU.HouseWang.nju_eas_server.logicService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.StatusPO;

public interface CourseSelectionLogicService extends LogicService {
	// 显示状态
	public String showStatus(String function);

	// 编辑状态
	public String editStatus(StatusPO sp);

	// 显示状态列表
	public ArrayList<String> showStatusList();

	// 显示状态列表的表头
	public String showStatusListHead();

	// 选课
	public String selectCourse(String courseId,
			String studentId);

	// 通识课选课
	public String selectCommonCourse(String courseId, String studentId);

	// 补选
	public String byElectCourse(String courseId,
			String studentId);

	// 退选
	public String quitCourse(String courseId, String studentId);

	// 批量添加选课记录
	public String addCourse_StudentList(String listName, ArrayList<String> list);

	// 单独添加选课记录
	public String delCourse_StudentPO(String listName, String courseId,
			String studentId);

	// 批量删除选课记录
	public String delCourse_StudentList(String listName, ArrayList<String> list);

	// 通识课选课抽签
	public String processCommonCourseSelection();

	// 返回最大选课数量
	public String showMaxSelectionNum();

	// 判断是否达到最大选课数量
	public boolean isMax(String studentId);

	// 显示已选通识课列表
	public ArrayList<String> showSelectedCouse(String studentId);
}
