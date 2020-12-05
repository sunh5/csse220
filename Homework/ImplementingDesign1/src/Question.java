/**
  * 
  * TODO This class should documented with a purpose!
  *
  * 
 */
public class Question {
	private int id;
	private String content;
	private String answer;
	
	public Question (int id, String content, String answer) {
		this.id = id;
		this.content = content;
		this.answer = answer;
	}
	
	public int getID() {
		return this.id;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public void updateQuestion(String newQuestion){
		this.content = newQuestion;
	}

}
