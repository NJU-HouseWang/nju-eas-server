package NJU.HouseWang.nju_eas_server.data;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.dataService.DataService;
import NJU.HouseWang.nju_eas_server.po.DataPOService;
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
	public DataPOService getData(String id) {

		return null;
	}

	@Override
	public Feedback removeData(String id) {

		return null;
	}

	@Override
	public Feedback addData(DataPOService data) {

		return null;
	}

	@Override
	public Feedback updateData(DataPOService data) {

		return null;
	}

	@Override
	public boolean containsID(String id) {

		return false;
	}

	@Override
	public boolean containsData(DataPOService data) {

		return false;
	}

	@Override
	public ArrayList<DataPOService> getDataList() {

		return null;
	}

}
