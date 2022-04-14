package pr.treedemo.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import pr.treedemo.dao.QueryOrgInfoDao;
import pr.treedemo.entity.Division;
import pr.treedemo.entity.Employee;
import pr.treedemo.util.WriteObjectAsJson;
import pr.treedemo.vo.DivisionVo;
import pr.treedemo.vo.EmployeeVo;
import pr.treedemo.vo.OrgInfo;

public class QueryOrgInfoService {
	
	QueryOrgInfoDao dao = new QueryOrgInfoDao();
	private WriteObjectAsJson oaj = new WriteObjectAsJson("d:/temp/");

	public OrgInfo buildOrgInfo() {
		List<Division> divisions = dao.queryDivisions();
		
//		oaj.writeObjectValues(divisions);
		
		List<DivisionVo> divisionVos = new ArrayList<>();
		
		// key: division，方便後續運算。(盡量不要使用朝狀的Map)
		Map<String, DivisionVo> divMap = new LinkedHashMap<>();
		
		for(Division each: divisions) {
			
			DivisionVo divisionVo = each.makeNewDivisionVo(); // 從DB撈出來的 entity 產生物件
			divisionVos.add(divisionVo); // 將 DivisionVo 置入 Collection

			divMap.put(each.getDivision(), divisionVo); 
		}
		
		// 找出 root
		DivisionVo rootDivisionVo = divisionVos.stream().filter(e->e.getParentDivision()==null).findFirst().orElse(null);
		
		List<Employee> employees = dao.queryEmployees();

//		oaj.writeObjectValues(employees);
		
		// 處理 DB 撈出來的 Entity
		List<EmployeeVo> empVos = buildEmpVos(employees, divMap);
		
		// 建立部門父子關聯
		buildParentChildDivRelation(divMap, divisions);	
		
		OrgInfo orgInfo = new OrgInfo();
		orgInfo.setDivisions(divisionVos);
		orgInfo.setEmployees(empVos);
		orgInfo.setRootDivision(rootDivisionVo);
		
		// 問題: EmployeeVo, DivisionVo 物件數會增加嗎?
		
		return orgInfo;
	}

	public List<String> queryFlowApproversByUser(String userId) {
		OrgInfo orgInfo = this.buildOrgInfo();
		return orgInfo.queryFlowApproversByUser(userId);
	}

	
	private void buildParentChildDivRelation(Map<String, DivisionVo> divVoMap, List<Division> divisions) {
		
		for(Division each: divisions) {
			DivisionVo divisionVoOfEach = divVoMap.get(each.getDivision());

			if(each.getParentDivision() == null) {
				continue;
			}
			
			// 找出這個部門的父階部門 
			DivisionVo parentOfEach = divVoMap.get(each.getParentDivision());

			// 設定這個部門的子->父關聯
			divisionVoOfEach.setParentDivision(parentOfEach);
			
			// 設定這個部門父->子關聯
			parentOfEach.addChildDivision(divisionVoOfEach);
		}
	}

	private List<EmployeeVo> buildEmpVos(List<Employee> employees, Map<String, DivisionVo> divMap) {
		List<EmployeeVo> empVos = new ArrayList<EmployeeVo>();
		
		for(Employee each: employees) { // db 撈出來的
			DivisionVo divisionVo = divMap.get(each.getDivision());
			
			EmployeeVo employeeVo = each.makeNewEmployeeVo();
			employeeVo.setDivision(divisionVo); // 設定 Employee 的所屬的 Division
			
			empVos.add(employeeVo); // 將 EmployeeVo 放入 Collection
			
			divisionVo.addEmployee(employeeVo); // 將 Employee 加入到 Division
		}
		
		return empVos;
	}
	
	public int averageDivisionWorkyear(String division) {
		OrgInfo orgInfo = this.buildOrgInfo();
		return orgInfo.averageWorkyear(division);
		
	}
	
}
