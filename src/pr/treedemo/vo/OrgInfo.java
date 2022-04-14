package pr.treedemo.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

public class OrgInfo {
	
	private DivisionVo rootDivision;
	private List<DivisionVo> divisions = null;
	private List<EmployeeVo> employees = null;
	
	public DivisionVo getRootDivision() {
		return rootDivision;
	}
	public void setRootDivision(DivisionVo rootDivision) {
		this.rootDivision = rootDivision;
	}
	public List<DivisionVo> getDivisions() {
		return divisions;
	}
	public void setDivisions(List<DivisionVo> divisions) {
		this.divisions = divisions;
	}
	public List<EmployeeVo> getEmployees() {
		return employees;
	}
	public void setEmployees(List<EmployeeVo> employees) {
		this.employees = employees;
	}
	
	public List<String> queryFlowApproversByUser(String userId) {
		
		List<String> approverContainer = new ArrayList<String>();
		
		EmployeeVo employeeVo = employees.stream().filter(e->e.getUserId().equals(userId))
			.findFirst().orElse(null);
		
		assembleApprovers(employeeVo.getDivision(), approverContainer);
		return approverContainer;
	}
	
	private void assembleApprovers(DivisionVo division, List<String> approverContainer) {
		approverContainer.add(division.getDivisionManagerNetid());
		
		if(division.getParentDivision()==null)
			return;
		
		assembleApprovers(division.getParentDivision(), approverContainer);
	}
	
	public int averageWorkyear(String div) {
		DivisionVo division = divisions.stream().
				filter(e->e.getDivision().equals(div)).findFirst().orElse(null);
		
		Integer totalWorkYear = sumTotalWorkYear(division, 0);
		Integer countEmployee = countTotalEmp(division, 0);
		
		return totalWorkYear/countEmployee;
	}
	
	private int countTotalEmp(DivisionVo division, int accumulatedCount) {
		Integer countEmployee = accumulatedCount + division.countDivisionEmployee();
		
		
		if(CollectionUtils.isEmpty(division.getChildDivision())) {
			return countEmployee;
		}

		for(DivisionVo each : division.getChildDivision()) {
			System.out.println("before countTotalEmp("+ each.getDivision() +","+ countEmployee+")");
			countEmployee = countTotalEmp(each, countEmployee);
			System.out.println("after countTotalEmp("+ each.getDivision() +","+ countEmployee+")");
		} 
		
		return countEmployee;  
	}	
	
	
	private int countTotalEmp1(DivisionVo division, int accumulatedCount) {
		Integer countEmployee = accumulatedCount + division.countDivisionEmployee();
		
		if(CollectionUtils.isNotEmpty(division.getChildDivision())) {
			for(DivisionVo each : division.getChildDivision()) 
				countEmployee = countTotalEmp(each, countEmployee);
		}
		
		return countEmployee;
		
	}	
	
	private int sumTotalWorkYear(DivisionVo division, Integer accumulatedTotalWorkYear) {
		Integer totalWorkYear = accumulatedTotalWorkYear + division.sumDivisionWorkyear();
		
		if(CollectionUtils.isEmpty(division.getChildDivision())) {
			return totalWorkYear;
		}
		
		for(DivisionVo each : division.getChildDivision()) 
			totalWorkYear = sumTotalWorkYear(each, totalWorkYear);
		
		return totalWorkYear;
	}
	
	
	public void assembleWorkYearInfo(WorkYearInfo workYearInfo) {
		assembleWorkYearInfo(workYearInfo, rootDivision);
	}
	
	private void assembleWorkYearInfo(WorkYearInfo workYearInfo, DivisionVo div) {
		
		for(EmployeeVo each : div.getEmployees()) {
			workYearInfo.setTotalEmployee(workYearInfo.getTotalEmployee()+1);
			workYearInfo.setTotalWorkYear(workYearInfo.getTotalWorkYear()+each.getWorkYear());
		}
		
		if(CollectionUtils.isEmpty(div.getChildDivision())) 
			return;
		
		for(DivisionVo each : div.getChildDivision()) {
			assembleWorkYearInfo(workYearInfo, each);
		}
		
	}
	
	
	
	
}
