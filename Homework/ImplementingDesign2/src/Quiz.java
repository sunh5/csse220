import java.util.ArrayList;
import java.util.HashMap;

public class Quiz {
	private int id;
	private HashMap<Integer, Question> questions; 
//	private ArrayList<Question> questions;
	
	public Quiz(int id) {
		this.id = id;
		this.questions = new HashMap<Integer, Question>();
//		this.questions = new ArrayList<Question>();

	}
	public int getID() {
		return this.id;
	}
	
	public void addQuestion(int questionId, Question question) {//ArrayList<Question> newQuestion) {
//		this.questions = newQuestion;
		this.questions.put(questionId, question);
	}

	public String getQuestions(){
		String prompt = "";
//		for(int i = 0; i < this.questions.size(); i++){
//			if (i == this.questions.get(i).getID()) {
//				prompt += this.questions.get(i).getPrompt()+"\n";
//			}
//		}
		for(int i: this.questions.keySet()){
			prompt += this.questions.get(i).getPrompt()+"\n";
		}
		return prompt;
	}
	public int length() {
		return this.questions.size();
	}
	public double getQuizScore(String prompt) {
		double score = 0;
//		for (int i = 0; i < questions.size(); i++) {
//			if (this.questions.get(i).getPrompt().contains(prompt) == this.questions.get(i).getAnswer()) {
//				score ++;
//			}
//		}
		for(int i:this.questions.keySet()){
			if(this.questions.get(i).getPrompt().contains(prompt) == this.questions.get(i).getAnswer()){
				score++; 
			}
		}
		return score;
	}
	

}