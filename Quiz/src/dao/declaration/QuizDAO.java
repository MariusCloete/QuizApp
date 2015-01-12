package dao.declaration;

import java.util.TreeMap;

import transferObjects.QuestionTO;

public interface QuizDAO {
	public QuestionTO[] getQuestions();
	public boolean addQuestion(QuestionTO questionTO);
	public boolean addTest(String testDescription);
	public TreeMap<String, String> getTests();
	public boolean addCategory(String categoryDescription);
	public TreeMap<String, String> getCategories();
	public TreeMap<String, String> getChoicesForQuestion(int qid);
}
