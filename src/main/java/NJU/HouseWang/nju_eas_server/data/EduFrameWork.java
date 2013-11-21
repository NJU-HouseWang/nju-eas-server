package NJU.HouseWang.nju_eas_server.data;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.CoursePO;

public class EduFrameWork {
	private ArrayList<CourseModule> moduleList = new ArrayList<CourseModule>();

	public EduFrameWork() {

	}

	public void init() {

	}
}

class CourseModule {
	private String name;
	private int credit_low;
	private int credit_high;
	private ArrayList<CourseType> typeList = new ArrayList<CourseType>();

	public CourseModule(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCredit_low() {
		return credit_low;
	}

	public void setCredit_low(int credit_low) {
		this.credit_low = credit_low;
	}

	public int getCredit_high() {
		return credit_high;
	}

	public void setCredit_high(int credit_high) {
		this.credit_high = credit_high;
	}

	public ArrayList<CourseType> getTypeList() {
		return typeList;
	}

	public void setTypeList(ArrayList<CourseType> typeList) {
		this.typeList = typeList;
	}

}

class CourseType {
	private String name;
	private String nature;
	private int credit_low;
	private int credit_high;
	private ArrayList<CoursePO> courseList = new ArrayList<CoursePO>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public int getCredit_low() {
		return credit_low;
	}

	public void setCredit_low(int credit_low) {
		this.credit_low = credit_low;
	}

	public int getCredit_high() {
		return credit_high;
	}

	public void setCredit_high(int credit_high) {
		this.credit_high = credit_high;
	}

}
