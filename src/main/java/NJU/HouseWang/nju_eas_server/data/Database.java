package NJU.HouseWang.nju_eas_server.data;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.dataService.DataService;
import NJU.HouseWang.nju_eas_server.po.DataPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public class Database implements DataService {
	
	@Override
	public void init() {
		
	}
	
	@Override
	public Feedback setListName(String listName) {

		return null;
	}

	@Override
	public DataPO getData(String id) {

		return null;
	}

	@Override
	public Feedback removeData(String id) {

		return null;
	}

	@Override
	public Feedback addData(DataPO data) {

		return null;
	}

	@Override
	public Feedback updateData(DataPO data) {

		return null;
	}

	@Override
	public boolean containsID(String id) {

		return false;
	}

	@Override
	public boolean containsData(DataPO data) {

		return false;
	}

	@Override
	public ArrayList<DataPO> getDataList() {

		return null;
	}

}
