package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import dao.declaration.QuizDAO;
import transferObjects.QuestionTO;

public class JDBCQuizDAO extends CoreDAO implements QuizDAO{
	private String RETRIEVE_QUESTIONS = "SELECT rfn, question, crrct_ans, expl, "
			+ "num_crrct, category FROM questions;";
	private String ADD_QUESTION = "INSERT INTO questions("+
            " question, crrct_ans, expl, num_crrct, category,test)"+
    "VALUES ( ?, ?, ?, ?, ?);";
	private String ADD_QUESTION_CHOICE = "INSERT INTO choices("+
            " question_rfn, key, value)"+
    "VALUES ( ?, ?, ?);";
	private String ADD_TEST = "INSERT INTO tests (description) values (?); ";
	private String ADD_CATEGORY = "INSERT INTO categories (description) values (?); ";
	private String GET_TESTS = "SELECT * FROM tests";
	private String GET_CATEGORIES = "SELECT * FROM categories";
	private String RETRIEVE_CHOICES = "SELECT * FROM choices where question_rfn =?;";
	public QuestionTO[] getQuestions(){
		QuestionTO[] questions = new QuestionTO[0];
		ArrayList<QuestionTO> questionList = new ArrayList<QuestionTO>();
		Connection connection = getConnection();
		try {
			PreparedStatement preStatement = connection.prepareStatement(RETRIEVE_QUESTIONS);
			ResultSet rs = preStatement.executeQuery();
			while(rs.next()){
				QuestionTO questionTO = new QuestionTO();
				questionTO.setId(rs.getInt("rfn"));
				questionTO.setQuestion(rs.getString("question"));
				questionTO.setCorrectAnswer(rs.getString("crrct_ans"));
				questionTO.setExplanation(rs.getString("expl"));
				questionTO.setCategory(rs.getInt("category"));
				questionTO.setTest(rs.getInt("test"));
				questionTO.setChoicesMap(getChoicesForQuestion(questionTO.getId()));
				questionList.add(questionTO);
			}
			questions = new QuestionTO[questionList.size()];
			questionList.toArray(questions);
			disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return questions;
	}
	public boolean addQuestion(QuestionTO questionTO){
		Connection connection = getConnection();
		boolean success = false;
		
		try {
			connection.setAutoCommit(false);
			PreparedStatement preStatement = connection.prepareStatement(ADD_QUESTION);
			
			int parameterIndex = 1;
			preStatement.setString(parameterIndex++, questionTO.getQuestion());
			preStatement.setString(parameterIndex++, questionTO.getCorrectAnswer());
			preStatement.setString(parameterIndex++, questionTO.getExplanation());
			preStatement.setInt(parameterIndex++, questionTO.getCorrectAnswer().split(",").length);
			preStatement.setInt(parameterIndex++, questionTO.getCategory());
			preStatement.setInt(parameterIndex++, questionTO.getTest());
			int questionId = -1;
			
			ResultSet rs =  preStatement.executeQuery();
			while(rs.next()){
				questionId = rs.getInt(1);
				addQuestionChoices(questionId, questionTO.getChoicesMap(), connection);
			}
			
			connection.commit();
			success = true;
			disconnect();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return success;
	}
	private void addQuestionChoices(int questionId,TreeMap<String, String> choiceMap,Connection connection) {
		try {
			PreparedStatement preStatement = connection.prepareStatement(ADD_QUESTION_CHOICE);
			Set<String> choiceMapKeySet = choiceMap.keySet();
			for (Iterator<String> iterator = choiceMapKeySet.iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				String value = choiceMap.get(key);
				int parameterIndex = 1;
				preStatement.setInt(parameterIndex++, questionId);
				preStatement.setString(parameterIndex++, key);
				preStatement.setString(parameterIndex++, value);
				preStatement.execute();
			}
			
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public boolean addTest(String testDescription) {
		Connection connection = getConnection();
		boolean success = false;
		
		try {
			PreparedStatement preStatement = connection.prepareStatement(ADD_TEST);
			int parameterIndex = 1;
			preStatement.setString(parameterIndex++,testDescription);
			preStatement.execute();
			success = true;
			disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}
	@Override
	public boolean addCategory(String categoryDescription) {
		Connection connection = getConnection();
		boolean success = false;
		
		try {
			PreparedStatement preStatement = connection.prepareStatement(ADD_CATEGORY);
			int parameterIndex = 1;
			preStatement.setString(parameterIndex++,categoryDescription);
			preStatement.execute();
			success = true;
			disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}
	@Override
	public TreeMap<String, String> getChoicesForQuestion(int qid){
		TreeMap<String, String> choicesMap = new TreeMap<String, String>();
		Connection connection = getConnection();
		try {
			PreparedStatement preStatement = connection.prepareStatement(RETRIEVE_CHOICES);
			preStatement.setInt(1, qid);
			ResultSet rs = preStatement.executeQuery();
			
			while(rs.next()){
				choicesMap.put(rs.getString("key"), rs.getString("value"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return choicesMap;
		
	}
	
	@Override
	public TreeMap<String, String> getTests() {
		TreeMap<String, String> testMap = new TreeMap<String, String>();
		Connection connection = getConnection();
		try {
			PreparedStatement preStatement = connection.prepareStatement(GET_TESTS);
			ResultSet rs = preStatement.executeQuery();
			
			while(rs.next()){
				testMap.put(rs.getString("rfn"), rs.getString("description"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testMap;
	}
	@Override
	public TreeMap<String, String> getCategories() {
		TreeMap<String, String> categoryMap = new TreeMap<String, String>();
		Connection connection = getConnection();
		try {
			PreparedStatement preStatement = connection.prepareStatement(GET_CATEGORIES);
			ResultSet rs = preStatement.executeQuery();
			
			while(rs.next()){
				categoryMap.put(rs.getString("rfn"), rs.getString("description"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categoryMap;
	}
	
	
 }
