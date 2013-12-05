package NJU.HouseWang.nju_eas_server.dataService;

/**
 * 数据服务类
 * @author 教化场
 * @version 2013-11-14
 */
public interface DataService {
	/**
	 * 初始化数据库
	 */
	public void init();

	/**
	 * 关闭数据库
	 */
	public void finish();
}
