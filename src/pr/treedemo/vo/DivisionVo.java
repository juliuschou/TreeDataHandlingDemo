package pr.treedemo.vo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DivisionVo{

	
	private String division;
	private Integer divisionLevel;
	private String divisionManagerNetid;
	
	@JsonIgnore
	private DivisionVo parentDivision = null;
	private final List<DivisionVo> childDivision = new ArrayList<>();
	
	private final List<EmployeeVo> employees = new ArrayList<EmployeeVo>(); 
	
	public int sumDivisionWorkyear() {
		int sum = 0;
		
		for(EmployeeVo each: employees)
			sum = sum + each.getWorkYear();
		
		return sum;
	}
	
	public int countDivisionEmployee() {
		return this.employees.size();
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

	public DivisionVo getParentDivision() {
		return parentDivision;
	}

	public void setParentDivision(DivisionVo parentDivision) {
		this.parentDivision = parentDivision;
	}

	public List<DivisionVo> getChildDivision() {
		return childDivision;
	}
	
	public void addChildDivision(DivisionVo div) {
		this.childDivision.add(div);
	}

	public List<EmployeeVo> getEmployees() {
		return employees;
	}
   
	public void addEmployee(EmployeeVo emp) {
		this.employees.add(emp);
	}

	@Override
	public String toString() {
		return "DivisionVo [division=" + division + ", divisionLevel=" + divisionLevel + ", divisionManagerNetid="
				+ divisionManagerNetid + "]";
	}
	
	
}
