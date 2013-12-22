package NJU.HouseWang.nju_eas_server.dataService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.User.TeacherPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public interface TeacherListService {
	public boolean containsID(String id);

	public TeacherPO getTeacher(String id);

	public Feedback removeTeacher(String id);

	public Feedback addTeacher(TeacherPO teacher);

	public Feedback updateTeacher(TeacherPO teacher);

	public ArrayList<TeacherPO> getTeacherList();

	public ArrayList<TeacherPO> getTeacherList(String type);

	public String getListHead();
}