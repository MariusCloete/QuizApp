package dao.factory;

import dao.JDBCQuizDAO;
import dao.declaration.QuizDAO;

public class DAOFactory {
	public static QuizDAO getQuizDAO() { 
        return new JDBCQuizDAO();
    }

}
