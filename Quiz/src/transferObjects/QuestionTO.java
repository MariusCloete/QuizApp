package transferObjects;

import java.util.TreeMap;


public class QuestionTO {
	private int id;
	private String question;
	private String correctAnswer;
	private String explanation;
	private String answer;
	private int numberOfCorrectAnswers;
	private int category;
	private int test;
	private TreeMap<String, String> choicesMap;
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getNumberOfCorrectAnswers() {
		return numberOfCorrectAnswers;
	}
	public void setNumberOfCorrectAnswers(int numberOfCorrectAnswers) {
		this.numberOfCorrectAnswers = numberOfCorrectAnswers;
	}
	
	public TreeMap<String, String> getChoicesMap() {
		return choicesMap;
	}
	public void setChoicesMap(TreeMap<String, String> choicesMap) {
		this.choicesMap = choicesMap;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public int getTest() {
		return test;
	}
	public void setTest(int test) {
		this.test = test;
	}
	
}
