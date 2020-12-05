import java.util.ArrayList;

/**
  * 
  * TODO This class should documented with a purpose!
  *
  * 
 */
public class Quiz {
	private int id;
	private ArrayList<Question> questions; 
	
	public Quiz(int id, ArrayList<Question> newQuestions) {
		this.id = id;
		this.questions = newQuestions;

	}
	public int getID() {
		return this.id;
	}
	public void displayQuestion() {
		System.out.println("quiz"+this.id+": ");
//		for (int i)
//		System.out.print("Question"++": What is ");
		for (Question q : this.questions) {
//			System.out.println("quiz"+this.id+": ");
			System.out.print("     Question"+"["+q.getID()+"]"+": What is :");
			System.out.println(q.getContent());
		}
	}

}
