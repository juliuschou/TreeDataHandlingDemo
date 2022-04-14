package pr.treedemo.dao;

import java.util.ArrayList;
import java.util.List;

import pr.treedemo.entity.Division;
import pr.treedemo.entity.Employee;

public class QueryOrgInfoDao {
	public List<Division> queryDivisions() {
		List<Division> divisions = new ArrayList<>();
		
		Division presidentOffice = new Division("G00B", 3, "president");
		presidentOffice.setParentDivision(null);
		divisions.add(presidentOffice);		

		Division i000 = new Division("I000", 4, "i000Mgr");
		i000.setParentDivision("G00B");
		divisions.add(i000);
		
		Division i100 = new Division("I100", 5, "i100Mgr");
		i100.setParentDivision("I000");
		divisions.add(i100);
		
		Division i400 = new Division("I400", 5, "i400Mgr");
		i400.setParentDivision("I000");
		divisions.add(i400);
		

		Division q000 = new Division("Q000", 4, "q000Mgr");
		q000.setParentDivision("G00B");
		divisions.add(q000);
		
		Division q100 = new Division("Q100", 5, "q100Mgr");
		q100.setParentDivision("Q000");
		divisions.add(q100);
		
		Division q400 = new Division("Q400", 5, "q400Mgr");
		q400.setParentDivision("Q000");
		divisions.add(q400);
		
		return divisions;
	}
	
	public List<Employee> queryEmployees() {
		List<Employee> emps = new ArrayList<>();
		
		Employee g00bEmp1 = new Employee("g00b01", "G00B", "president", 20);
		emps.add(g00bEmp1);
		
		Employee g00bEmp2 = new Employee("g00b02", "G00B", "g00bEmp2", 10);
		emps.add(g00bEmp2);
		
		Employee i000Mgr = new Employee("i00001", "I000", "i000Mgr", 25);
		emps.add(i000Mgr);
		
		Employee i000Emp2 = new Employee("i00002", "I000", "i000Emp2", 20);
		emps.add(i000Emp2);
		
		Employee i100Mgr = new Employee("i10001", "I100", "i100Mgr", 20);
		emps.add(i100Mgr);
		
		Employee i100Emp2 = new Employee("i10002", "I100", "i100Emp2", 10);
		emps.add(i100Emp2);
		
		Employee i400Mgr = new Employee("i40001", "I400", "i400Mgr", 20);
		emps.add(i400Mgr);
		
		Employee i400Emp2 = new Employee("i40002", "I400", "i400Emp2", 10);
		emps.add(i400Emp2);
		
		Employee q000Mgr = new Employee("q00001", "Q000", "Q000Mgr", 20);
		emps.add(q000Mgr);
		
		Employee q000Emp2 = new Employee("q00002", "Q000", "Q000Emp2", 10);
		emps.add(q000Emp2);
		
		Employee q100Mgr = new Employee("q10001", "Q100", "q100Mgr", 20);
		emps.add(q100Mgr);
		
		Employee q100Emp2 = new Employee("q10002", "Q100", "q100Emp2", 10);
		emps.add(q100Emp2);
		
		Employee q400Mgr = new Employee("q40001", "Q400", "q400Mgr", 20);
		emps.add(q400Mgr);
		
		Employee q400Emp2 = new Employee("q40002", "Q400", "q400Emp2", 10);
		emps.add(q400Emp2);
		
		return emps;
	}
	
}
