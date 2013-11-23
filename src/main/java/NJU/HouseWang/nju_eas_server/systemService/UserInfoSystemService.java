package NJU.HouseWang.nju_eas_server.systemService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.User.GuestPO;
import NJU.HouseWang.nju_eas_server.po.User.UserPO;

public interface UserInfoSystemService extends SystemService {
	// 查看个人信息
	public void showSelfInformation();

	// 修改个人信息
	public void editSelfInformation(UserPO u);

	// 新增用户
	public void addUser(UserPO u);

	// 修改用户
	public void editUser(GuestPO u);

	// 删除用户
	public void delUser(String id);

	// 查看用户列表
	public void showUserList(String listName, String conditions);

	// 批量新增用户
	public void addUserList();

	// 批量删除用户
	public void delUserList();
	
	//修改密码
	public void editPassword(String oldPW, String newPW);
	
	//生成初始密码
	public String generateInitialPassword(UserPO u);
}
