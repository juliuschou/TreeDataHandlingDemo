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
		
		// key: division�A��K����B��C(�ɶq���n�ϥδª���Map)
		Map<String, DivisionVo> divMap = new LinkedHashMap<>();
		
		for(Division each: divisions) {
			
			DivisionVo divisionVo = each.makeNewDivisionVo(); // �qDB���X�Ӫ� entity ���ͪ���
			divisionVos.add(divisionVo); // �N DivisionVo �m�J Collection

			divMap.put(each.getDivision(), divisionVo); 
		}
		
		// ��X root
		DivisionVo rootDivisionVo = divisionVos.stream().filter(e->e.getParentDivision()==null).findFirst().orElse(null);
		
		List<Employee> employees = dao.queryEmployees();

//		oaj.writeObjectValues(employees);
		
		// �B�z DB ���X�Ӫ� Entity
		List<EmployeeVo> empVos = buildEmpVos(employees, divMap);
		
		// �إ߳������l���p
		buildParentChildDivRelation(divMap, divisions);	
		
		OrgInfo orgInfo = new OrgInfo();
		orgInfo.setDivisions(divisionVos);
		orgInfo.setEmployees(empVos);
		orgInfo.setRootDivision(rootDivisionVo);
		
		// ���D: EmployeeVo, DivisionVo ����Ʒ|�W�[��?
		
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
			
			// ��X�o�ӳ������������� 
			DivisionVo parentOfEach = divVoMap.get(each.getParentDivision());

			// �]�w�o�ӳ������l->�����p
			divisionVoOfEach.setParentDivision(parentOfEach);
			
			// �]�w�o�ӳ�����->�l���p
			parentOfEach.addChildDivision(divisionVoOfEach);
		}
	}

	private List<EmployeeVo> buildEmpVos(List<Employee> employees, Map<String, DivisionVo> divMap) {
		List<EmployeeVo> empVos = new ArrayList<EmployeeVo>();
		
		for(Employee each: employees) { // db ���X�Ӫ�
			DivisionVo divisionVo = divMap.get(each.getDivision());
			
			EmployeeVo employeeVo = each.makeNewEmployeeVo();
			employeeVo.setDivision(divisionVo); // �]�w Employee �����ݪ� Division
			
			empVos.add(employeeVo); // �N EmployeeVo ��J Collection
			
			divisionVo.addEmployee(employeeVo); // �N Employee �[�J�� Division
		}
		
		return empVos;
	}
	
	public int averageDivisionWorkyear(String division) {
		OrgInfo orgInfo = this.buildOrgInfo();
		return orgInfo.averageWorkyear(division);
		
	}
	
}
