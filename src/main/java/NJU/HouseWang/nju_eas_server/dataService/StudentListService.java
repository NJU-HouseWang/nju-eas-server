package NJU.HouseWang.nju_eas_server.dataService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.User.StudentPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public interface StudentListService {
	public boolean containsID(String id);

	public StudentPO getStudent(String id);

	public Feedback removeStudent(String id);

	public Feedback addStudent(StudentPO user);

	public Feedback updateStudent(StudentPO user);

	public ArrayList<StudentPO> getStudentList(String grade, String department);

	public ArrayList<StudentPO> getStudentList(String department);

	public ArrayList<StudentPO> getStudentList();

	public ArrayList<String> getGradeList();

	public String getListHead();

}
