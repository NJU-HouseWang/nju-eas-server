package NJU.HouseWang.nju_eas_server.po.Edu;

public class CourseSelectionPO {
	private String courseId;
	private String studentId;
	private int priority;
	
	public CourseSelectionPO(){
		
	}
	
	public CourseSelectionPO(String courseId, String studentId, int priority){
		this.courseId = courseId;
		this.studentId = studentId;
		this.priority = priority;
	}
	
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
}
