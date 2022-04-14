package pr.treedemo.entity;

import org.springframework.beans.BeanUtils;

import pr.treedemo.vo.DivisionVo;

public class Division{

	
	private String division;
	private Integer divisionLevel;
	private String divisionManagerNetid;
	private String parentDivision;
	

	public Division(String division, Integer divisionLevel, String divisionManagerNetid) {
		super();
		this.division = division;
		this.divisionLevel = divisionLevel;
		this.divisionManagerNetid = divisionManagerNetid;
	}
	
	public DivisionVo makeNewDivisionVo() {
		DivisionVo div = new DivisionVo();
		BeanUtils.copyProperties(this, div, "parentDivision");
		return div;
	}
	
	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public Integer getDivisionLevel() {
		return divisionLevel;
	}

	public void setDivisionLevel(Integer divisionLevel) {
		this.divisionLevel = divisionLevel;
	}

	public String getDivisionManagerNetid() {
		return divisionManagerNetid;
	}

	public void setDivisionManagerNetid(String divisionManagerNetid) {
		this.divisionManagerNetid = divisionManagerNetid;
	}

	public String getParentDivision() {
		return parentDivision;
	}

	public void setParentDivision(String parentDivision) {
		this.parentDivision = parentDivision;
	}
}
