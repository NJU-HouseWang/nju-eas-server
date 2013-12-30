package NJU.HouseWang.nju_eas_server.logicService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.User.GuestPO;
import NJU.HouseWang.nju_eas_server.po.User.StudentPO;
import NJU.HouseWang.nju_eas_server.po.User.TeacherPO;
import NJU.HouseWang.nju_eas_server.po.User.UserPO;

/**
 * 用户信息逻辑类
 * @author 教化场
 * @version 2013-11-12
 */
public interface UserInfoLogicService extends LogicService {
	/**
	 * 查看个人信息
	 * @return 反馈
	 */
	public String showSelfInformation();

	/**
	 * 修改个人信息
	 * @param u 用户PO
	 * @return 反馈
	 */
	public String editSelfInformation(UserPO u);

	/**
	 * 新增用户
	 * @param u 用户PO
	 * @return 反馈
	 */
	public String addUser(UserPO u);

	/**
	 * 修改用户
	 * @param u 客户PO
	 * @return 反馈
	 */
	public String editUser(GuestPO u);

	/**
	 * 修改教师
	 * @param tp 任课教师PO
	 * @return 反馈
	 */
	public String editTeacher(TeacherPO tp);

	/**
	 * 修改学生
	 * @param sp 学生PO
	 * @return 反馈
	 */
	public String editStudent(StudentPO sp);

	/**
	 * 删除用户
	 * @param id 用户名
	 * @return 反馈
	 */
	public String delUser(String id);

	/**
	 * 新增老师
	 * @param tp 教师PO
	 * @return 反馈
	 */
	public String addTeacher(TeacherPO tp);

	/**
	 * 新增学生
	 * @param sp 学生PO
	 * @return 反馈
	 */
	public String addStudent(StudentPO sp);

	/**
	 * 查看用户列表
	 * @return 登录列表
	 */
	public ArrayList<String> showLoginList();

	/**
	 * 查看教师列表
	 * @param condition 条件
	 * @return 教师列表
	 */
	public ArrayList<String> showTeacherList(String condition);

	/**
	 * 查看学生列表
	 * @param condition 条件
	 * @return 学生列表
	 */
	public ArrayList<String> showStudentList(String condition);

	/**
	 * 显示用户列表的表头
	 * @return 表头
	 */
	public String showLoginListHead();

	/**
	 * 显示教师列表的表头
	 * @return 表头
 	 */
	public String showTeacherListHead();

	/**
	 * 显示学生列表的表头
	 * @return 表头
	 */
	public String showStudentListHead();

	/**
	 * 批量新增用户
	 * @param list 列表名
	 * @return 反馈
	 */
	public String addUserList(ArrayList<String> list);

	/**
	 * 批量删除用户
	 * @param list 列表名
	 * @return 反馈
	 */
	public String delUserList(ArrayList<String> list);

	/**
	 * 修改密码
	 * @param oldPW 旧密码
	 * @param newPW 新密码
	 * @return 反馈
	 */
	public String editPassword(String oldPW, String newPW);

	/**
	 * 生成初始密码
	 * @param u 用户PO
 	 * @return 反馈
	 */
	public String generateInitialPassword(UserPO u);
	
	/**
	 * 返回所有的年级的列表
	 * @return 年级列表
	 */
	public ArrayList<String> showGradeList();
	
	/**
	 * 返回Ip对应的院系
	 * @param ip ip地址
	 * @return 院系
	 */
	public String showSelfDept(String ip);
	
	/**
	 * 返回id对应的教师名称,若无则返回null
	 * @param id 教师工号
	 * @return 教师名称
	 */
	public String showTeacherName(String id);
	
	/**
	 * 返回id对应的学生名称,若无则返回null
	 * @param id 学号
	 * @return 学生名称
	 */
	public String showStudentName(String id);
	
	/**
	 * 返回id对应的用户姓名，若无则返回null
	 * @param id 用户名
	 * @return 用户名称
	 */
	public String showUserName(String id);
	/**
	 * string转换用户
	 * @param str
	 * @return 用户
	 */
	public UserPO stringToUserPO(String str);
}
