package NJU.HouseWang.nju_eas_server.systemService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.User.GuestPO;
import NJU.HouseWang.nju_eas_server.po.User.UserPO;

public interface UserInfSystemService extends SystemService {
	// 查看个人信息
	public void showSelfInformation();

	// 修改个人信息
	public void editSelfInformation(UserPO u);

	// 新增用户
	public void addUser(GuestPO u);

	// 修改用户
	public void editUser(UserPO u);

	// 删除用户
	public void deleteUser(String id);

	// 查看用户列表
	public void showUserList(String conditions);

	// 批量新增用户
	public void addUserList();

	// 批量删除用户
	public void delUserList();
}
