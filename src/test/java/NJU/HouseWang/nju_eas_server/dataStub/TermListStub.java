package NJU.HouseWang.nju_eas_server.dataStub;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.TermList;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public class TermListStub extends TermList{

	@Override
	public void init() {
		
	}

	@Override
	public void finish() {
		
	}

	@Override
	public ArrayList<String> getTermList() {
		ArrayList<String> al = new ArrayList<String>();
		String str = "TermList";
		al.add(str);
		return al;
	}

	@Override
	public Feedback addTerm(String term) {
		return Feedback.OPERATION_SUCCEED;
	}
	
}
