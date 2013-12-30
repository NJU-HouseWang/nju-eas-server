package NJU.HouseWang.nju_eas_server.logic;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.AuthorityManager;
import NJU.HouseWang.nju_eas_server.data.DeptList;
import NJU.HouseWang.nju_eas_server.logicService.SystemInfoLogicService;
import NJU.HouseWang.nju_eas_server.po.Edu.DepartmentPO;
import NJU.HouseWang.nju_eas_server.po.Msg.LogPO;
/**
 * 系统信息逻辑类
 * @author 教化场
 * @version 2013-11-11
 */
public class SystemInfoLogic implements SystemInfoLogicService{
	private DeptList dl;
	private AuthorityManager am;
	private String uid;
	
	public DeptList initDeptList(){
		DeptList d = new DeptList();
		d.init();
		return d;
	}
	
	public AuthorityManager initAuthorityManager() {
		AuthorityManager a = AuthorityManager.getInstance();
		return a;
	}
	
	public SystemInfoLogic(){
		dl = this.initDeptList();
		am =this.initAuthorityManager();				
	}
	
	@Override
	public Object operate(String cmd) {
		Object feedback = null;
		String[] cmdInfo = cmd.split("；");
		String cmdType = cmdInfo[0] + cmdInfo[1];
		switch (cmdType) {

		case "showdept_list":
			feedback = this.showDeptList();
			break;
		default:
			break;
		}
		dl.finish();
		return feedback;
	}

	@Override
	public Object operate(String cmd, ArrayList<String> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * 显示院系列表
	 * @return 院系列表
	 */
	public ArrayList<String> showDeptList() {
		// TODO Auto-generated method stub
		ArrayList<String> feedback = new ArrayList<String>();
		ArrayList<DepartmentPO> list = dl.getDeptList();
		for(int i = 0; i < list.size(); i++){
			feedback.add(list.get(i).toCommand());
		}
		return feedback;
	}

}
