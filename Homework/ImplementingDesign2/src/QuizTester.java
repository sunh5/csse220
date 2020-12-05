
public class QuizTester {
	private int id;
	private String searchPrompt;

	public QuizTester(int id, String str) {
		this.id = id;
		this.searchPrompt = str;
	}

	public double getSize(Quiz quiz) {
		return quiz.length();
	}

	public double getScorePrecent(Quiz quiz) {
		return quiz.getQuizScore(this.searchPrompt) / quiz.length();
	}

	public double getOverAllScore(Quiz quiz) {
		return quiz.getQuizScore(this.searchPrompt);
	}
}
