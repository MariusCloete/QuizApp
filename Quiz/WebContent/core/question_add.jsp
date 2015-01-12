<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.1/jquery.mobile-1.3.1.min.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
    <script src="http://code.jquery.com/mobile/1.3.1/jquery.mobile-1.3.1.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a question</title>
<script type="text/javascript">
function submit(){
	document.getElementsByName("QUESTION_FORM")[0].submit();
}
function showAddCategory(){
	if (document.getElementById("add_category_row").style.display == "none"){
		document.getElementById("add_category_row").style.display = "";
	}else {
		document.getElementById("add_category_row").style.display="none";
	}
}
function addCategory(){
	document.getElementById("action").value ="add_category";
	document.getElementsByName("QUESTION_FORM")[0].submit();
}
function showAddTest(){
	if (document.getElementById("add_test_row").style.display == "none"){
		document.getElementById("add_test_row").style.display = "";
	}else {
		document.getElementById("add_test_row").style.display="none";
	}
}
function addTest(){
	document.getElementById("action").value ="add_test";
	document.getElementsByName("QUESTION_FORM")[0].submit();
}
function addIndent(txtArea){
		var txtArea = document.getElementById("question");
		var slctStart = 0;   
		var slctEnd = 0;
		if (document.selection) {
			// IE Support
			txtArea.focus ();
			var Sel = document.selection.createRange ();
			Sel.moveStart ('character', -txtArea.value.length);
			slctStart = Sel.text.length;
		} else if (txtArea.selectionStart || txtArea.selectionStart == '0'){
			// Firefox support
			slctStart = txtArea.selectionStart;
			slctEnd = txtArea.selectionEnd;
		}
		var txtAreaValue = txtArea.value;
		if (slctStart!=slctEnd){
			var selectedText = txtAreaValue.substring(slctStart,slctEnd).replace(/[\r\n]/g, '\r\n\t');
			txtArea.value = txtAreaValue.substring(0,slctStart)+"\t"+selectedText+txtAreaValue.substring(slctEnd,txtAreaValue.length);
		}else{
			txtArea.value = txtAreaValue.substring(0,slctStart)+"\t"+txtAreaValue.substring(slctStart,txtAreaValue.length);
		}
		
		
}
</script>

</head>
<body>
<form method="post" name="QUESTION_FORM" action="core.cs">

<table width="80%" id="options">
<tr>
	<td>Question</td>
	<td colspan="2" >
	<input type="button" value="indent" onclick="addIndent();" data-role="button" size="20" >
	<textarea rows="12" cols="4" name="question" id="question" style="width: 100%;height: 300px;" >${question}</textarea></td>
</tr>	
<tr>
	<td><input name="correct_choices" id="checkbox-v-1a" type="checkbox" value="a"  <c:if test="${fn:contains(correct_choices, 'a')}"> checked="checked"</c:if>>
        <label for="checkbox-v-1a">A</label></td>
    <td colspan="2"><textarea rows="2" cols="4" name="option_a" style="width: 100%;" >${option_a}</textarea></td>
</tr>
<tr>
	<td><input name="correct_choices" id="checkbox-v-2a" type="checkbox" value="b">
        <label for="checkbox-v-2a">B</label></td>
    <td colspan="2"><textarea rows="2" cols="4" name="option_b" style="width: 100%;" >${option_b}</textarea></td>
</tr>
<tr>
	<td><input name="correct_choices" id="checkbox-v-3a" type="checkbox" value="c">
        <label for="checkbox-v-3a">C</label></td>
    <td colspan="2"><textarea rows="2" cols="4" name="option_c" style="width: 100%;" >${option_c}</textarea></td>
</tr>
<tr>
	<td><input name="correct_choices" id="checkbox-v-4a" type="checkbox" value="d">
        <label for="checkbox-v-4a">D</label></td>
    <td colspan="2"><textarea rows="2" cols="4" name="option_d" style="width: 100%;" >${option_d}</textarea></td>
</tr>
<tr>
	<td><input name="correct_choices" id="checkbox-v-5a" type="checkbox" value="e">
        <label for="checkbox-v-5a">E</label></td>
    <td colspan="2"><textarea rows="2" cols="4" name="option_e" style="width: 100%;" >${option_e}</textarea></td>
</tr>
<tr>
	<td><input name="correct_choices" id="checkbox-v-6a" type="checkbox" value="f">
        <label for="checkbox-v-6a">F</label></td>
    <td colspan="2"><textarea rows="2" cols="4" name="option_f" style="width: 100%;" >${option_f}</textarea></td>
</tr>
<tr>
	<td>Explainsion</td>
	<td colspan="2">
	<textarea rows="12" cols="4" name="explainsion" style="width: 100%;height: 200px;" >${explainsion }</textarea>
	</td>
</tr>
<tr>
	<td>Test</td>
    <td><select name="test" >
    <c:forEach items="${tests}" var="i" >
    <option value="${i.key }">${i.value }</option>
    </c:forEach>
    </select></td>
    <td><input type="button" value="add" onclick="showAddTest();" data-role="button" size="20" ></td>
</tr>
<tr id="add_test_row" style="display:none;" class="ui-btn-active">
	<td>Test name</td>
	<td ><input type="text" name="new_test" ></td>
	<td><input type="button" value="Add Test" onclick="addTest();" data-role="button" size="20" ></td>
</tr>
<tr>
	<td>Category</td>
    <td><select name="category" >
    <c:forEach items="${categories}" var="i" >
    <option value="${i.key }">${i.value }</option>
    </c:forEach></select></td>
    <td><input type="button" value="add" onclick="showAddCategory();" data-role="button" size="20" ></td>
</tr>
<tr id="add_category_row" style="display:none;" class="ui-btn-active">
	<td>Category name</td>
	<td ><input type="text" name="new_category" ></td>
	<td ><input type="button" value="Add Category" onclick="addCategory();" data-role="button" size="20" ></td>
</tr>
<tr>
	<td><input type="button" value="Back" onclick="submit();" data-role="button" size="20" ></td>
    <td colspan="2"><input type="button" value="Add Question" onclick="submit();" data-role="button" size="20" ></td>
</tr>
</table>
<input type="hidden" name="action" id="action" value="add_question"/>
</form>
</body>
</html>