package pr.treedemo.entity;

import org.springframework.beans.BeanUtils;

import pr.treedemo.vo.EmployeeVo;

public class Employee {

	private String userId;
	private String division;
	private String userNetid;
	private Integer workYear;
	
	
	
	public Employee(String userId, String division, String userNetid, Integer workYear) {
		super();
		this.userId = userId;
		this.division = division;
		this.userNetid = userNetid;
		this.workYear = workYear;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getUserNetid() {
		return userNetid;
	}
	public void setUserNetid(String userNetid) {
		this.userNetid = userNetid;
	}
	public Integer getWorkYear() {
		return workYear;
	}
	public void setWorkYear(Integer workYear) {
		this.workYear = workYear;
	}

	public EmployeeVo makeNewEmployeeVo() {
		EmployeeVo vo = new EmployeeVo();
		BeanUtils.copyProperties(this, vo, "division");
		return vo;
	}
	
}
