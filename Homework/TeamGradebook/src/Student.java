import java.util.ArrayList;


public class Student {
	private String name;
	//TODO: You'll probably need to have some more fields here
	private double grade;
	
	private int numOfAbsence = 0;
	
	
	/**
	 * makes a new student object
	 * 
	 * @param newName the name of the student
	 * @param  
	 */
	public Student(String newName) {
		//TODO: initialize fields here
		this.name = newName;
	}

	/**
	 * gets the name of the student
	 * 
	 * @return the name of the student
	 */
	public String getName() {
		// TODO your code here
		
		return name;
	}
	
	public void addAbsence() {
		// TODO Auto-generated method stub
		this.numOfAbsence += 1;
	}
	
	public int getAbsence() {
		return this.numOfAbsence;
	}
	public void addGrade(double newGrade) {
		this.grade = newGrade;
	}
	public double getGrade(){
		return this.grade;
	}

	

	
	
	//TODO: You'll need to add some new methods here
}
