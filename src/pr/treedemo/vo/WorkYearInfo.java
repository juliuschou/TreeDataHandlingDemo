package pr.treedemo.vo;

public class WorkYearInfo {
	private int totalWorkYear = 0;
	private int totalEmployee = 0;
	
	public int getTotalWorkYear() {
		return totalWorkYear;
	}
	public void setTotalWorkYear(int totalWorkYear) {
		this.totalWorkYear = totalWorkYear;
	}
	public int getTotalEmployee() {
		return totalEmployee;
	}
	public void setTotalEmployee(int totalEmployee) {
		this.totalEmployee = totalEmployee;
	}		
	
	public int getAverage() {
		if(totalEmployee==0)
			return 0;
		
		return totalWorkYear/totalEmployee;
		
	}
	@Override
	public String toString() {
		return "WorkYearInfo [totalWorkYear=" + totalWorkYear + ", totalEmployee=" + totalEmployee + ", getAverage()="
				+ getAverage() + "]";
	}
	
	
	
}