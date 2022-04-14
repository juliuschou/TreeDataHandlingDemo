package pr.treedemo.relationold;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EventTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetRegisters() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddRegister() {
		Event e = new Event();
		List<Register> registers = new ArrayList<Register>();
		registers.add(new Register());
		e.setRegisters(registers);
	}

}
