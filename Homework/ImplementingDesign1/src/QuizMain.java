import java.util.ArrayList;

import java.util.HashMap;

/**
 * This class is used to demonstrate a functional design involving Quizzes and
 * Questions which can be updated and displayed
 * 
 * 
 * 
 */

public class QuizMain {
	private HashMap<Integer, Quiz> quizs = new HashMap<Integer, Quiz>();
	private HashMap<Integer, Question> questions = new HashMap<Integer, Question>();
	// TODO add instance variables here

	public QuizMain() {

		// TODO In order to demonstrate functionality, please follow the TODOs below
		
		// You will have to create questions and quizzes when a QuizMain is created

		// TODO 1 Create five questions (can be silly/basic questions) use id 1,2,3,4,5
		// ...
		Question q1 = new Question(1, "3 x 1=?", "3");
		Question q2 = new Question(2, "2 x 2=?", "4");
		Question q3 = new Question(3, "2 x 5=?", "10");
		Question q4 = new Question(4, "4 x 4=?", "16");
		Question q5 = new Question(5, "5 x 5=?", "25");
		this.questions.put(q1.getID(), q1);
		this.questions.put(q2.getID(), q2);
		this.questions.put(q3.getID(), q3);
		this.questions.put(q4.getID(), q4);
		this.questions.put(q5.getID(), q5);
		// TODO 2 Create three or more quizzes use id 1,2,3...
		ArrayList <Question> questions1 = new ArrayList<Question>();
		ArrayList <Question> questions2 = new ArrayList<Question>();
		ArrayList <Question> questions3 = new ArrayList<Question>();
		questions1.add(q1);
		questions1.add(q2);
		questions1.add(q3);
		questions2.add(q2);
		questions2.add(q3);
		questions2.add(q4);
		questions3.add(q2);
		questions3.add(q5);
		Quiz quiz1 = new Quiz(1, questions1);
		Quiz quiz2 = new Quiz(2, questions2);
		Quiz quiz3 = new Quiz(3, questions3);
		this.quizs.put(quiz1.getID(), quiz1);
		this.quizs.put(quiz2.getID(), quiz2);
		this.quizs.put(quiz3.getID(), quiz3);
		
		// (One quiz should share at least one question with another )

	}

	public static void main(String[] args) {

		// We want to use instance variables of the QuizMain class so we need to
		// construct a QuizMain object

		QuizMain myQuizSimulator = new QuizMain();

		// TODO 3 Display three or more different quizzes

		System.out.println("--------------------------------------------------");

		System.out.println("Showing three or more original quizzes:");

		System.out.println("--------------------------------------------------");

		myQuizSimulator.handleDisplayQuiz(1);

		myQuizSimulator.handleDisplayQuiz(2);

		myQuizSimulator.handleDisplayQuiz(3);

		// TODO 4 Change two quiz questions

		// A. (One should be shared with two or more quizzes)

		// B. (One should be unique to one quiz)

		myQuizSimulator.handleUpdateQuizQuestion(1, "What is different 1?");

		myQuizSimulator.handleUpdateQuizQuestion(2, "What is different 2?");

		// TODO 5 Display the same three (or more) quizzes

		// A. One that has a unique question which changed

		// B. Two which share a question that has been changed

		System.out.println("--------------------------------------------------");

		System.out.println("Showing three or more changed quizzes:");

		System.out.println("--------------------------------------------------");

		myQuizSimulator.handleDisplayQuiz(1);

		myQuizSimulator.handleDisplayQuiz(2);

		myQuizSimulator.handleDisplayQuiz(3);

	}

	/**
	 * 
	 * This method should display a quiz in a very similar fashion to the output
	 * provided
	 * 
	 * in example_output.txt, which is located in your repository
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @param quizId
	 * 
	 */

	public void handleDisplayQuiz(int quizId) {

		// TODO complete this method
		for (int i : this.quizs.keySet()) {
			if (i == quizId) {
				this.quizs.get(i).displayQuestion();
			}
		}

	}

	/**
	 * 
	 * 
	 * 
	 * This method should replace the data in the question with id=questionId with
	 * the new questionData
	 * 
	 * 
	 * 
	 * @param questionId
	 * 
	 * @param questionData
	 * 
	 */

	public void handleUpdateQuizQuestion(int questionId, String questionData) {

		// TODO complete this method
		for (int i : this.questions.keySet()) {
			if (i == questionId) {
				this.questions.get(i).updateQuestion(questionData);
			}
		}

	}

}