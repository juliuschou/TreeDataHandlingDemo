package pr.treedemo.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class EmployeeVo {
	private String userId;
	private String userNetid;
	private Integer workYear;
	
	@JsonIgnore
	private DivisionVo division;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public DivisionVo getDivision() {
		return division;
	}

	public void setDivision(DivisionVo division) {
		this.division = division;
	}
	
}
