package NJU.HouseWang.nju_eas_server.dataService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public interface TermListService {
	public ArrayList<String> getTermList();

	public Feedback addTerm(String term);

}
