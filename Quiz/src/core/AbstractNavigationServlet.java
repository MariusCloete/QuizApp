package core;

import java.util.LinkedList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public abstract class AbstractNavigationServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String getParameter(String parameter,HttpServletRequest req){
		String returnedValue = "";
		if (req.getParameter(parameter)!=null && !req.getParameter(parameter).trim().equals("")){
			returnedValue  =req.getParameter(parameter);
		}
		return returnedValue;
	}
	protected String[] getParameterValues(String parameter,HttpServletRequest req){
		String[] returnedValues = new String[0];
		LinkedList<String> returnedValueList = new LinkedList<String>();
		if (req.getParameterValues(parameter)!=null && req.getParameterValues(parameter).length>0){
			for (int i = 0; i < req.getParameterValues(parameter).length; i++) {
				if(req.getParameterValues(parameter)[i] != null){
					returnedValueList.add(req.getParameterValues(parameter)[i]);
				}
			}
			returnedValues = new String[returnedValueList.size()];
			returnedValueList.toArray(returnedValues);
		}
		return returnedValues;
	}

}
