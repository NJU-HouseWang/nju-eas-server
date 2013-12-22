package NJU.HouseWang.nju_eas_server.po.Edu;

import NJU.HouseWang.nju_eas_server.po.DataPOService;

public class EduFrameworkItemPO implements DataPOService {
	private String moduleId;
	private String moduleName;
	private int moduleMinCredit;
	private int moduleMaxCredit;
	private String courseNature;
	private String serialNum;
	private String courseType;
	private int typeMinCredit;
	private int typeMaxCredit;
	private String courseName;
	private int courseMinCredit;
	private int courseMaxCredit;
	private int startTerm;
	private int endTerm;

	public EduFrameworkItemPO() {

	}

	public EduFrameworkItemPO(String moduleId, String moduleName,
			int moduleMinCredit, int moduleMaxCredit, String courseNature,
			String serialNum, String courseType, int typeMinCredit,
			int typeMaxCredit, String courseName, int courseMinCredit,
			int courseMaxCredit, int startTerm, int endTerm) {
		super();
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.moduleMinCredit = moduleMinCredit;
		this.moduleMaxCredit = moduleMaxCredit;
		this.courseNature = courseNature;
		this.serialNum = serialNum;
		this.courseType = courseType;
		this.typeMinCredit = typeMinCredit;
		this.typeMaxCredit = typeMaxCredit;
		this.courseName = courseName;
		this.courseMinCredit = courseMinCredit;
		this.courseMaxCredit = courseMaxCredit;
		this.startTerm = startTerm;
		this.endTerm = endTerm;
	}

	public EduFrameworkItemPO(String moduleId, String moduleName,
			String moduleMinCredit, String moduleMaxCredit,
			String courseNature, String serialNum, String courseType,
			String typeMinCredit, String typeMaxCredit, String courseName,
			String courseMinCredit, String courseMaxCredit, String startTerm,
			String endTerm) {
		super();
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.moduleMinCredit = Integer.parseInt(moduleMinCredit);
		this.moduleMaxCredit = Integer.parseInt(moduleMaxCredit);
		this.courseNature = courseNature;
		this.serialNum = serialNum;
		this.courseType = courseType;
		this.typeMinCredit = Integer.parseInt(typeMinCredit);
		this.typeMaxCredit = Integer.parseInt(typeMaxCredit);
		// this.courseId = courseId;
		this.courseName = courseName;
		this.courseMinCredit = Integer.parseInt(courseMinCredit);
		this.courseMaxCredit = Integer.parseInt(courseMaxCredit);
		this.startTerm = Integer.parseInt(startTerm);
		this.endTerm = Integer.parseInt(endTerm);
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public int getModuleMinCredit() {
		return moduleMinCredit;
	}

	public void setModuleMinCredit(int moduleMinCredit) {
		this.moduleMinCredit = moduleMinCredit;
	}

	public int getModuleMaxCredit() {
		return moduleMaxCredit;
	}

	public void setModuleMaxCredit(int moduleMaxCredit) {
		this.moduleMaxCredit = moduleMaxCredit;
	}

	public String getCourseNature() {
		return courseNature;
	}

	public void setCourseNature(String courseNature) {
		this.courseNature = courseNature;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public int getTypeMinCredit() {
		return typeMinCredit;
	}

	public void setTypeMinCredit(int typeMinCredit) {
		this.typeMinCredit = typeMinCredit;
	}

	public int getTypeMaxCredit() {
		return typeMaxCredit;
	}

	public void setTypeMaxCredit(int typeMaxCredit) {
		this.typeMaxCredit = typeMaxCredit;
	}

	// public String getCourseId() {
	// return courseId;
	// }
	//
	// public void setCourseId(String courseId) {
	// this.courseId = courseId;
	// }

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCourseMinCredit() {
		return courseMinCredit;
	}

	public void setCourseMinCredit(int courseMinCredit) {
		this.courseMinCredit = courseMinCredit;
	}

	public int getCourseMaxCredit() {
		return courseMaxCredit;
	}

	public void setCourseMaxCredit(int courseMaxCredit) {
		this.courseMaxCredit = courseMaxCredit;
	}

	public int getStartTerm() {
		return startTerm;
	}

	public void setStartTerm(int startTerm) {
		this.startTerm = startTerm;
	}

	public int getEndTerm() {
		return endTerm;
	}

	public void setEndTerm(int endTerm) {
		this.endTerm = endTerm;
	}

	public String toCommand() {
		String _typeMinCredit = "" + typeMinCredit;
		String _typeMaxCredit = "" + typeMaxCredit;
		String _courseMinCredit = "" + courseMinCredit;
		String _courseMaxCredit = "" + courseMaxCredit;
		if (typeMinCredit < 0) {
			_typeMinCredit = "null";
			_typeMaxCredit = "null";
		}
		if (courseMinCredit < 0) {
			_courseMinCredit = "null";
			_courseMaxCredit = "null";
		}
		return (moduleId + " " + moduleName + "(" + moduleMinCredit + "-"
				+ moduleMaxCredit + ")；" + courseNature + "；" + serialNum + "；"
				+ courseType + "(" + _typeMinCredit + "-" + _typeMaxCredit
				+ ")；" + courseName + "；" + _courseMinCredit + "-"
				+ _courseMaxCredit + "；" + startTerm + "-" + endTerm);
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return "EduFrameworkItemPO [moduleId=" + moduleId + ", moduleName="
				+ moduleName + ", moduleMinCredit=" + moduleMinCredit
				+ ", moduleMaxCredit=" + moduleMaxCredit + ", courseNature="
				+ courseNature + ", serialNum=" + serialNum + ", courseType="
				+ courseType + ", typeMinCredit=" + typeMinCredit
				+ ", typeMaxCredit=" + typeMaxCredit + ", courseName="
				+ courseName + ", courseMinCredit=" + courseMinCredit
				+ ", courseMaxCredit=" + courseMaxCredit + ", startTerm="
				+ startTerm + ", endTerm=" + endTerm + "]";
	}

}
