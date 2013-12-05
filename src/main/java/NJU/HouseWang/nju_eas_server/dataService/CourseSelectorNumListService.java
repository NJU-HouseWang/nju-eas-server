package NJU.HouseWang.nju_eas_server.dataService;

import NJU.HouseWang.nju_eas_server.po.Edu.CourseSelectorNumPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

/**
 * 选课器数目类
 * @author 教化场
 * @version 2013-11-13
 */
public interface CourseSelectorNumListService {
	/**
	 * 删除列表
	 * @return 反馈
	 */
	public Feedback delList();

	/**
	 * 编辑选课器数目PO
	 * @param p 选课器数目PO
	 * @return 反馈
	 */
	public Feedback updateCourseSelectorNumPO(CourseSelectorNumPO p);

	/**
	 * 添加选课器数目PO
	 * @param p 选课器数目PO
	 * @return 反馈
	 */
	public Feedback addCourseSelectorNumPO(CourseSelectorNumPO p);

	/**
	 * 获取选课器数目PO
	 * @param courseId 课程号
	 * @return 选课器数目PO
	 */
	public CourseSelectorNumPO getCourseSelectorNumPO(String courseId);

}
