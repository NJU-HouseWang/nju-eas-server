package NJU.HouseWang.nju_eas_server.po.Edu;

import NJU.HouseWang.nju_eas_server.po.DataPOService;

public class PlannedCoursePO implements DataPOService {
	private String id;// 课程编号
	private String name;// 课程名称
	private String module;// 所属模块
	private String type;// 课程类别
	private String nature;// 课程性质
	private int credit;// 学分
	private int period;// 学时
	private String term; //开设学期

	public PlannedCoursePO(){
		
	}
	
	public PlannedCoursePO(String id, String name, String module, String type,
			String nature, int credit, int period, String term) {
		this.id = id;
		this.name = name;
		this.module = module;
		this.type = type;
		this.nature = nature;
		this.credit = credit;
		this.period = period;
		this.term = term;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	@Override
	public String getId() {

		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;

	}
	
	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String toCommand() {
		return (id + "；" + name + "；" + module + "；" + type + "；" + nature
				+ "；" + credit + "；" + period + "；" + term);
	}
}
