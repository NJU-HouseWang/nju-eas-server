package NJU.HouseWang.nju_eas_server.logicTest;

import java.io.File;
import java.util.ArrayList;

import junit.framework.TestCase;
import NJU.HouseWang.nju_eas_server.data.AuthorityManager;
import NJU.HouseWang.nju_eas_server.data.EduFramework;
import NJU.HouseWang.nju_eas_server.data.TeachingPlan;
import NJU.HouseWang.nju_eas_server.data.TeachingPlanList;
import NJU.HouseWang.nju_eas_server.dataStub.EduFrameworkStub;
import NJU.HouseWang.nju_eas_server.dataStub.TeachingPlanListStub;
import NJU.HouseWang.nju_eas_server.dataStub.TeachingPlanStub;
import NJU.HouseWang.nju_eas_server.logic.TeachingPlanLogic;
import NJU.HouseWang.nju_eas_server.po.Edu.TeachingPlanItemPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public class TeachingPlanLogicTest extends TestCase{
	private TeachingPlanStub tp;
	private TeachingPlanListStub tl;
	private EduFrameworkStub ef;
	private AuthorityManager am;
	
	public TeachingPlanLogic tpl;
	
	public void setUp() throws Exception {
		tp = new TeachingPlanStub();
		tl = new TeachingPlanListStub();
		ef = new EduFrameworkStub();
		am = AuthorityManager.getInstance();
		
		tpl = new TeachingPlanLogic(){
			@Override
			public TeachingPlan initTeachingPlan() {
				return tp;
			}
			@Override
			public TeachingPlanList initTeachingPlanList() {
				return tl;
			}
			@Override
			public EduFramework initEduFramework() {
				return ef;
			}
			@Override
			public AuthorityManager initAuthorityManager() {
				return am;
			}	
		};
	}
	
	protected void tearDown() throws Exception {}
	
	public void testShowTeachingPlan(){
		ArrayList<String> result = tpl.showTeachingPlan("");
		assertTrue(result.get(0).equals("moduleId moduleName(1)；courseNature；courseType(2)；courseId；courseName；3；4；5"));
	}
	
	public void testShowTeachingPlanList(){
		ArrayList<String> result = tpl.showTeachingPlanList();
		assertTrue(result.get(0).equals("；true；0；d"));
	}
	
	public void testAddTeachingPlan(){
		ArrayList<String> list = new ArrayList<String>();
		String result = tpl.addTeachingPlan("dept", list);
		assertTrue(result.equals(Feedback.DATA_ALREADY_EXISTED.toString()));
	}
	
	public void testEditTeachingPlan(){
		ArrayList<String> list = new ArrayList<String>();
		String result = tpl.editTeachingPlan("dept", list);
		assertTrue(result.equals(Feedback.DATA_ALREADY_EXISTED.toString()));
	}
	
	public void testDelTeachingPlan(){
		String result = tpl.delTeachingPlan("dept");
		assertTrue(result.equals(Feedback.OPERATION_SUCCEED.toString()));
	}
	
	public void testAuditTeachingPlan(){
		String result = tpl.auditTeachingPlan("dept", 1);
		assertTrue(result.equals(Feedback.OPERATION_SUCCEED.toString()));
	}
	
	public void testUploadTeachingPlanFile(){
		String result = tpl.uploadTeachingPlanFile("path","dept");
		assertTrue(result.equals(Feedback.OPERATION_SUCCEED.toString()));
	}
	
	public void testDownloadTeachingPlanFile(){
		File f = new File("d:/tianhaiyi");
		File result = tpl.downloadTeachingPlanFile("dept");
		assertTrue(result.equals(f));
	}
	
	public void testJudgeTeachingPlan(){
		ArrayList<TeachingPlanItemPO> teachingPlan = new ArrayList<TeachingPlanItemPO>();
		TeachingPlanItemPO tpip = new TeachingPlanItemPO("moduleId1", "moduleName1", "1", "courseNature1", "courseType1", "2", "courseId1", "courseName1", "3", "period1", "4");
		teachingPlan.add(tpip);
		boolean result = tpl.judgeTeachingPlan(teachingPlan);
		assertTrue(result == true);
	}
	
	public void testStringToTeachingPlanItem(){
		TeachingPlanItemPO tpip = new TeachingPlanItemPO("moduleId1", "moduleName1", "1", "courseNature1", "courseType1", "2", "courseId1", "courseName1", "3", "period1", "4");
		String str = "moduleId1；moduleName1；1；courseNature1；courseType1；2；courseId1；courseName1；3；period1；4";
		TeachingPlanItemPO result = tpl.stringToTeachingPlanItem(str);
		assertTrue(result.toCommand().equals(tpip.toCommand()));
	}
	
	public void testShowTeachingPlanHead(){
		String result = tpl.showTeachingPlanHead();
		assertTrue(result.equals("ListHead"));
	}
	
	public void testShowTeachingPlanListHead(){
		String result = tpl.showTeachingPlanListHead();
		assertTrue(result.equals("ListHead"));
	}
	
	public void testShowFlieName(){
		String result = tpl.showFlieName("dept");
		assertTrue(result.equals(Feedback.FILE_NOT_FOUND.toString()));
	}
}
