package core;

import java.io.IOException;
import java.util.Arrays;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;
import transferObjects.QuestionTO;
import dao.JDBCQuizDAO;
import dao.factory.DAOFactory;

public class CoreServlet extends AbstractNavigationServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action = req.getParameter("action");
		QuestionTO[] questions = new QuestionTO[0];
		int questionNum = -1;
		switch (action) {
		case "start_test":
			JDBCQuizDAO dao = new JDBCQuizDAO();
			req.getSession().setAttribute("questions", dao.getQuestions()); 
			req.getSession().setAttribute("questionNum", 0); 
			req.getSession().setAttribute("currentQuestion", dao.getQuestions()[0]); 
			req.getRequestDispatcher("/core/question.jsp").forward(req, resp);
			break;
		case "next":
			questions = (QuestionTO[])req.getSession().getAttribute("questions");
			questionNum = new Integer((String)req.getSession().getAttribute("questionNum")).intValue();
			req.getSession().setAttribute("CurrentQuestion", questions[++questionNum]); 
			req.getRequestDispatcher("/core/question.jsp").forward(req, resp);
			break;
		case "back":
			questions = (QuestionTO[])req.getSession().getAttribute("questions");
			questionNum = new Integer((String)req.getSession().getAttribute("questionNum")).intValue();
			req.getSession().setAttribute("CurrentQuestion", questions[--questionNum]); 
			req.getRequestDispatcher("/core/question.jsp").forward(req, resp);
			break;
		case "load_question_add":
			req.setAttribute("categories", getCategoryDropDownValues());
			req.setAttribute("tests", getTestDropDownValues());
			clearCapturedDataOnSession(req);
			req.getRequestDispatcher("/core/question_add.jsp").forward(req, resp);
			break;
		case "add_question":
			QuestionTO questionTO = new QuestionTO();
			questionTO.setQuestion(getParameter("question", req));
			String[] correctChoices = getParameterValues("correct_choices", req);
			questionTO.setCorrectAnswer(Arrays.toString(correctChoices));
			String[] choiceIndexs = {"a","b","c","d","e","f"};
			TreeMap<String, String> choicesMap = new TreeMap<String, String>();
			for (int i = 0; i < choiceIndexs.length; i++) {
				choicesMap.put(choiceIndexs[i],getParameter("option_"+choiceIndexs[i], req));
			}
			questionTO.setChoicesMap(choicesMap);
			questionTO.setExplanation(getParameter("explainsion", req));
			int testId = new Integer(getParameter("test", req)).intValue();
			questionTO.setTest(testId);
			int categoryId = new Integer(getParameter("category", req)).intValue();
			questionTO.setCategory(categoryId);
			DAOFactory.getQuizDAO().addQuestion(questionTO);
			req.setAttribute("categories", getCategoryDropDownValues());
			req.setAttribute("tests", getTestDropDownValues());
			loadCapturedDataOnSession(req);
			req.getRequestDispatcher("/core/question_add.jsp").forward(req, resp);
			break;
		case "add_test":
			DAOFactory.getQuizDAO().addTest(getParameter("new_test", req));
			req.setAttribute("categories", getCategoryDropDownValues());
			req.setAttribute("tests", getTestDropDownValues());
			loadCapturedDataOnSession(req);
			req.getRequestDispatcher("/core/question_add.jsp").forward(req, resp);
			break;
		case "add_category":
			DAOFactory.getQuizDAO().addCategory(getParameter("new_category", req));
			req.setAttribute("categories", getCategoryDropDownValues());
			req.setAttribute("tests", getTestDropDownValues());
			loadCapturedDataOnSession(req);
			req.getRequestDispatcher("/core/question_add.jsp").forward(req, resp);
			break;
		default:
			break;
		}
	}
	private void loadCapturedDataOnSession(HttpServletRequest req){
		req.getSession().setAttribute("question", getParameter("question", req));
		req.getSession().setAttribute("correct_choices", getParameter("correct_choices", req));
		req.getSession().setAttribute("option_a", getParameter("option_a", req));
		req.getSession().setAttribute("option_b", getParameter("option_b", req));
		req.getSession().setAttribute("option_c", getParameter("option_c", req));
		req.getSession().setAttribute("option_d", getParameter("option_d", req));
		req.getSession().setAttribute("option_e", getParameter("option_s", req));
		req.getSession().setAttribute("option_f", getParameter("option_f", req));
		req.getSession().setAttribute("explainsion", getParameter("explainsion", req));
		req.getSession().setAttribute("test", getParameter("test", req));
		req.getSession().setAttribute("category", getParameter("category", req));
	}
	private void clearCapturedDataOnSession(HttpServletRequest req){
		req.getSession().setAttribute("question", "");
		req.getSession().setAttribute("correct_choices", "");
		req.getSession().setAttribute("option_a", "");
		req.getSession().setAttribute("option_b", "");
		req.getSession().setAttribute("option_c", "");
		req.getSession().setAttribute("option_d", "");
		req.getSession().setAttribute("option_e", "");
		req.getSession().setAttribute("option_f", "");
		req.getSession().setAttribute("explainsion", "");
		req.getSession().setAttribute("test", "");
		req.getSession().setAttribute("category", "");
	}
	private TreeMap<String, String> getTestDropDownValues(){
		return DAOFactory.getQuizDAO().getTests();
	}
	private TreeMap<String, String> getCategoryDropDownValues(){
		return DAOFactory.getQuizDAO().getCategories();
	}
}
