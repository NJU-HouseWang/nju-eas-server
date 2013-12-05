package NJU.HouseWang.nju_eas_server.dataService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

/**
 * 学期列表
 * @author 教化场
 * @version 2013-11-17
 */
public interface TermListService {
	/**
	 * 获取学期列表
	 * @return 学期列表
	 */
	public ArrayList<String> getTermList();

	/**
	 * 添加学期
	 * @param term 学期
	 * @return 反馈
	 */
	public Feedback addTerm(String term);

}
