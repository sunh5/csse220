
public class Question {
	private int id;
	private String prompt;
	private boolean answer;
	
	public Question (int id, boolean answer, String prompt) {
		this.id = id;
		this.prompt = prompt;
		this.answer = answer;
	}
	
	public int getID() {
		return this.id;
	}
	
	public boolean getAnswer() {
		return this.answer;
	}
	public String getPrompt(){
		return this.prompt;
	}
	
	public void updateQuestion(boolean answer, String prompt) {
		this.answer = answer;
		this.prompt = prompt;
	}
//	public void updateQuestion(String newQuestion){
//		this.content = newQuestion;
//	}

}