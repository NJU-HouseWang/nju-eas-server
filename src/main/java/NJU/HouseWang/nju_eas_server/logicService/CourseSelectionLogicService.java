package NJU.HouseWang.nju_eas_server.logicService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.CourseSelectionPO;
import NJU.HouseWang.nju_eas_server.po.Edu.StatusPO;

/**
 * 选课逻辑类
 * @author 教化场
 * @version 2013-11-8
 */
public interface CourseSelectionLogicService extends LogicService {
	/**
	 * 显示状态
	 * @param function 功能
	 * @return 反馈
	 */
	public String showStatus(String function);

	/**
	 * 编辑状态
	 * @param sp 状态PO
	 * @return 反馈
	 */
	public String editStatus(StatusPO sp);

	/**
	 * 显示状态列表
	 * @return 状态列表
	 */
	public ArrayList<String> showStatusList();

	/**
	 * 显示状态列表的表头
	 * @return 表头
	 */
	public String showStatusListHead();

	/**
	 * 通识课选课
	 * @param courseId 课程号
	 * @param studentId 学号
	 * @return 反馈
	 */
	public String selectCommonCourse(String courseId, String studentId);

	/**
	 * 补选
	 * @param courseId 课程号
	 * @param studentId 学号
	 * @return 反馈
	 */
	public String byElectCourse(String courseId,
			String studentId);

	/**
	 * 退选
	 * @param courseId 课程号
	 * @param studentId 学号
	 * @return 反馈
	 */
	public String quitCourse(String courseId, String studentId);

	/**
	 * 删除课程_学生
	 * @param listName 列表名
	 * @param courseId 课程号
	 * @param studentId 学号
	 * @return 反馈
	 */
	public String delCourse_StudentPO(String listName, String courseId,
			String studentId);

	/**
	 * 通识课选课抽签
	 * @return 反馈
	 */
	public String processCommonCourseSelection();

	/**
	 * 返回最大选课数量
	 * @return 最大选课数量
	 */
	public String showMaxSelectionNum();

	/**
	 * 判断是否达到最大选课数量
	 * @param studentId
	 * @return 是否达到最大选课数量
	 */
	public boolean isMax(String studentId);

	/**
	 * 显示已选通识课列表
	 * @param studentId 学号
	 * @return 已选通识课列表
	 */
	public ArrayList<String> showSelectedCouse(String studentId);
	
	/**
	 * 取消已选的通识课
	 * @param courseId 课程号
	 * @param studentId 学号
	 * @return 反馈
	 */
	public String cancelSelection(String courseId, String studentId);
	/**
	 * 抽签逻辑
	 * @param list 列表
	 * @param totalNum 总数
	 * @return 抽签结果
	 */
	public ArrayList<CourseSelectionPO> lot(ArrayList<CourseSelectionPO> list,
			int totalNum);
	/**
	 * 获取优先级
	 * @param studentId 学生Id
	 * @return 优先级
	 */
	public int getPriority(String studentId);
	
}
