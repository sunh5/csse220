import java.util.ArrayList;


public class Team {
	private String TeamName;
	private ArrayList<String> memberNames;
	private ArrayList<Double> grade;
	private int numberOfGrades = 0;
	private int teamAverage = 0;
	
	// You'll need to add methods, constructors and fields here
	public Team(String newName, ArrayList<String> members) {
		//TODO: initialize fields here
		this.TeamName = newName;
		this.memberNames = members;
		this.grade = new ArrayList<>();
	}

	/**
	 * gets the name of the team
	 * 
	 * @return the name of the team
	 */
	public String getName() {
		return TeamName;
		
	}
	
	public void addMember(ArrayList<String> teamMember){
//		for (int i = 0; i < teamMember.size(); i++) {
//			this.memberNames.add(i, teamMember.get(i));
//		}
		this.memberNames = teamMember;
	}

	public ArrayList<String> getMember(){
		return this.memberNames;
		
	}
	
	public String searchStudent(String name){
		for (String str : this.memberNames) {
			if (name.equals(this.memberNames)) {
				return "name";
			}
		}
		return null;
	}
	public void addGrade(double newGrade) {
		this.grade.add(newGrade);
	}
	public void addnumberOfGrades() {
		this.numberOfGrades +=1;
	}
	public ArrayList<Double> getGrade(){
		return this.grade;
	}
	public int getNumberOfGrades(){
		return this.numberOfGrades;
	}
	
	
	public void TeamAverage(int teamAverage){
		this.teamAverage = teamAverage;
	}
	
	public double getAvegrade(){
		double sum = 0;
		for (double dou : grade) {
			sum += dou;
		}
		double result = sum / grade.size();
		return result;
	}
	
}
