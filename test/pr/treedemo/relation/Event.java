package pr.treedemo.relation;

import java.util.ArrayList;
import java.util.List;

public class Event {
	private final List<Register> registers = new ArrayList<Register>();

	public List<Register> getRegisters() {
		return registers;
	}
	
	public void addRegister(Register r) {
		this.registers.add(r);
	}
	
}
