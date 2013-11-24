package NJU.HouseWang.nju_eas_server.po.Edu;

public class StatusPO {
	private String function; //选课、补选、退选功能名
	private Boolean isopen;	//功能是否开启
	
	public StatusPO(){		
	}
	
	public StatusPO(String function, Boolean isopen){
		this.function = function;
		this.isopen = isopen;
	}
	
	public void setFunction(String function){
		this.function = function;
	}
	
	public void setIsopen(Boolean isopen){
		this.isopen  = isopen;
	}
	
	public void setIsopen(String status){
		if(status.toLowerCase().equals("true")){
			this.isopen  = true;
		} else{
			this.isopen = false;
		}
	}
	
	public String getFunction(){
		return this.function;
	}
	
	public Boolean getIsopen(){
		return this.isopen;
	}
	
	public String getIsopenToString(){
		Boolean s = this.getIsopen();
		String str = "false";
		if(s){
			str = "true";
		}
		return str;
	}
	
	public String toCommand(){
		return (function + ";" + isopen);
	}
}
