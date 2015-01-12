<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
           
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
Question nr.${questionNum+1} <br/>
${currentQuestion.question}
<c:forEach var="choices" items="${currentQuestion.choicesMap }">
   <p><input type="radio" name="givenAnswer" value="${choices.key}"> <c:out value="${choices.value}"/></p>
</c:forEach> 
<br/>
 </body>
</html>