package NJU.HouseWang.nju_eas_server.dataService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.CoursePO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

/**
 * 通识课列表
 * @author 教化场
 * @version 2013-11-12
 */
public interface CommonCourseListService {
	/**
	 * 判断是否包含课程
	 * @param id 课程号
	 * @return 是否包含课程
	 */
	public boolean containsCourse(String id);

	/**
	 * 获取课程
	 * @param id 课程号
	 * @return 课程PO
	 */
	public CoursePO getCourse(String id);

	/**
	 * 删除课程
	 * @param id 课程号
	 * @return 反馈
 	 */
	public Feedback removeCourse(String id);

	/**
	 * 添加课程
	 * @param Course 课程PO
	 * @return 反馈
	 */
	public Feedback addCourse(CoursePO Course);

	/**
	 * 编辑课程
	 * @param Course 课程PO
	 * @return 反馈
	 */
	public Feedback updateCourse(CoursePO Course);

	/**
	 * 获取课程列表
	 * @return 课程列表
	 */
	public ArrayList<CoursePO> getCourseList();

	/**
	 * 获取已选通识课列表表头
	 * @return 表头
	 */
	public String getSelectedCommonCourseListHead();

	/**
	 * 获取通识课列表表头
	 * @return 表头
	 */
	public String getCommonCourseListHead();
	
	/**
	 * 获取编辑通识课列表表头
	 * @return 表头
	 */
	public String getEditCommonCourseListHead();
}
