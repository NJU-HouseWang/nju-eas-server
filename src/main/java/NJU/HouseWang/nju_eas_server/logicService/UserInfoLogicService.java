package NJU.HouseWang.nju_eas_server.logicService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.User.GuestPO;
import NJU.HouseWang.nju_eas_server.po.User.StudentPO;
import NJU.HouseWang.nju_eas_server.po.User.TeacherPO;
import NJU.HouseWang.nju_eas_server.po.User.UserPO;

public interface UserInfoLogicService extends LogicService {
	// 查看个人信息
	public String showSelfInformation();

	// 修改个人信息
	public String editSelfInformation(UserPO u);

	// 新增用户
	public String addUser(UserPO u);

	// 修改用户
	public String editUser(GuestPO u);

	// 修改教师
	public String editTeacher(TeacherPO tp);

	// 修改学生
	public String editStudent(StudentPO sp);

	// 删除用户
	public String delUser(String id);

	// 新增老师
	public String addTeacher(TeacherPO tp);

	// 新增学生
	public String addStudent(StudentPO sp);

	// 查看用户列表
	public ArrayList<String> showLoginList();

	// 查看教师列表
	public ArrayList<String> showTeacherList();

	// 查看学生列表
	public ArrayList<String> showStudentList();

	// 显示用户列表的表头
	public String showLoginListHead();

	// 显示教师列表的表头
	public String showTeacherListHead();

	// 显示学生列表的表头
	public String showStudentListHead();

	// 批量新增用户
	public String addUserList(ArrayList<String> list);

	// 批量删除用户
	public String delUserList(ArrayList<String> list);

	// 修改密码
	public String editPassword(String oldPW, String newPW);

	// 生成初始密码
	public String generateInitialPassword(UserPO u);
}
