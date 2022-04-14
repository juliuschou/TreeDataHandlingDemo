package pr.treedemo.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pr.treedemo.util.WriteObjectAsJson;
import pr.treedemo.vo.OrgInfo;
import pr.treedemo.vo.WorkYearInfo;

public class QueryOrgInfoServiceTest {
	
	private QueryOrgInfoService service = new QueryOrgInfoService();
	private WriteObjectAsJson oaj = new WriteObjectAsJson("d:/temp/");

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBuildDivision() {
		OrgInfo orgInfo = service.buildOrgInfo();
		oaj.writeObjectValues(orgInfo.getRootDivision());
	}
	
	@Test
	public void testQueryBosses() {
		List<String> queryBossManagerNetids = service.queryFlowApproversByUser("i40002");
		System.out.println(queryBossManagerNetids);
		assertTrue(queryBossManagerNetids.size()==3);
	}
	
	@Test
	public void testAverageDivisionI400WorkYear() {
		int averageDivisionWorkyear = service.averageDivisionWorkyear("I400");
		assertTrue(averageDivisionWorkyear==15);
		System.out.println(averageDivisionWorkyear);
	}

	@Test
	public void testAverageDivisionI000WorkYear() {
		int averageDivisionWorkyear = service.averageDivisionWorkyear("I000");
		System.out.println(averageDivisionWorkyear);
		assertTrue(averageDivisionWorkyear==17);
	}

	@Test
	public void testAverageDivisionG00BWorkYear() {
		int averageDivisionWorkyear = service.averageDivisionWorkyear("G00B");
		System.out.println(averageDivisionWorkyear);
//		assertTrue(averageDivisionWorkyear==17);
	}

	
	@Test
	public void testAverageWorkYear() {
		OrgInfo orgInfo = service.buildOrgInfo();
		
		WorkYearInfo workYearInfo = new WorkYearInfo();
		orgInfo.assembleWorkYearInfo(workYearInfo);
		System.out.println(workYearInfo);
	}
	
}
