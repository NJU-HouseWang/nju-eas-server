package NJU.HouseWang.nju_eas_server.dataStub;

import java.sql.SQLException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.TeacherList;
import NJU.HouseWang.nju_eas_server.po.User.TeacherPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_server.systemMessage.UserType;

public class TeacherListStub extends TeacherList{
	public void init() {
		
	}

	// 关闭数据库
	public void finish() {
		
	}

	@Override
	public ArrayList<TeacherPO> getTeacherList() {
		ArrayList<TeacherPO> result = new ArrayList<TeacherPO>();
		TeacherPO t1 = new TeacherPO("0001","丁二玉","SchoolDean","教务处");
		TeacherPO t2 = new TeacherPO("0002","王东霞","DeptAD","软件学院");
		TeacherPO t3 = new TeacherPO("0003","刘钦","Teacher","软件学院");
		result.add(t1);
		result.add(t2);
		result.add(t3);
		return result;
	}

	@Override
	public String getListHead() {
		return "ListHead";
	}

	// 是否包含ID
	public boolean containsID(String id) {
		Boolean result = true;
		return result;
	}

	public TeacherPO getTeacher(String id) {
		TeacherPO result = new TeacherPO("0001",UserType.SchoolDean.toString(),"丁二玉","教务处");
		return result;
	}

	public Feedback removeTeacher(String id) {
		return Feedback.OPERATION_SUCCEED;
	}

	public Feedback addTeacher(TeacherPO teacher) {
		return Feedback.OPERATION_SUCCEED;
	}

	public Feedback updateTeacher(TeacherPO teacher) {
		return Feedback.OPERATION_SUCCEED;
	}

	public ArrayList<TeacherPO> getTeacherList(String conditions) {
		ArrayList<TeacherPO> result = new ArrayList<TeacherPO>();
		TeacherPO t1 = new TeacherPO("0001","丁二玉","SchoolDean","教务处");
		TeacherPO t2 = new TeacherPO("0002","王东霞","DeptAD","软件学院");
		TeacherPO t3 = new TeacherPO("0003","刘钦","Teacher","软件学院");
		result.add(t1);
		result.add(t2);
		result.add(t3);
		return result;
	}

}
