package NJU.HouseWang.nju_eas_server.logicTest;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.AuthorityManager;
import NJU.HouseWang.nju_eas_server.data.EduFramework;
import NJU.HouseWang.nju_eas_server.data.LogList;
import NJU.HouseWang.nju_eas_server.dataStub.EduFrameworkStub;
import NJU.HouseWang.nju_eas_server.dataStub.LogListStub;
import NJU.HouseWang.nju_eas_server.logic.EduFrameworkLogic;
import NJU.HouseWang.nju_eas_server.logic.LogLogic;
import NJU.HouseWang.nju_eas_server.po.Edu.EduFrameworkItemPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;
import junit.framework.TestCase;

public class EduFrameworkLogicTest extends TestCase{
	private EduFrameworkStub ef;
	private AuthorityManager am;
	
	public EduFrameworkLogic efl;
	
	public void setUp() throws Exception {
		am = AuthorityManager.getInstance();
		ef = new EduFrameworkStub();
		
		efl = new EduFrameworkLogic(){
			@Override
			public EduFramework intEduFramework() {
				return ef;
			}
			@Override
			public AuthorityManager initAuthorityManager() {
				return am;
			}
		};
	}
	
	protected void tearDown() throws Exception {}
	
	public void testAddEduFramework(){
		ArrayList<String> list = new ArrayList<String>();
		list.add("EduFramework1；1；2；3；4；5；6；7；8；9；10；11；12；13");
		list.add("EduFramework1；1；2；3；4；5；6；7；8；9；10；11；12；13");
		String result = efl.addEduFramework(list);
		assertTrue(result.equals(Feedback.FORMAT_ERROR.toString()));
	}
	
	public void testDelEduFramework(){
		String result = efl.delEduFramework();
		assertTrue(result.equals(Feedback.OPERATION_SUCCEED.toString()));
	}
	
	public void testShowEduFramework(){
		ArrayList<String> al = new ArrayList<String>();
		EduFrameworkItemPO efp = new EduFrameworkItemPO("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14");
		al.add(efp.toCommand());
		ArrayList<String> result = efl.showEduFramework();
		assertTrue(al.equals(result));
	}
	
	public void testStringToEduFrameworkItemPO(){
		EduFrameworkItemPO result = efl.stringToEduFrameworkItemPO("1；2；3；4；5；6；7；8；9；10；11；12；13；14");
		assertTrue(result.toCommand().equals("1 2(3-4)；5；6；7(8-9)；10；11-12；13-14"));
	}
	
	public void testShowModuleNum(){
		String result = efl.showModuleNum();
		assertTrue(result.equals("2"));
	}
	
	public void testShowEduFrameworkHead(){
		String result = efl.showEduFrameworkHead();
		assertTrue(result.equals("ListHead"));
	}
}
