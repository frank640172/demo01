<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>123</h1>
<c:if test="true">123</c:if>
<%-- 在域中存一个数字，使用chose取出数字使用when来判断数字，otherwise其他情况--%>
<%
    request.setAttribute("number",5);
%>
<c:choose >
    <c:when test="${number == 1}">星期一</c:when>
    <c:when test="${number == 2}">星期二</c:when>
    <c:when test="${number == 3}">星期三</c:when>
    <c:when test="${number == 4}">星期四</c:when>
    <c:when test="${number == 5}">星期五</c:when>
    <c:when test="${number == 6}">星期六</c:when>
    <c:when test="${number == 7}">星期日</c:when>
    <c:otherwise>输入数字错误</c:otherwise>
</c:choose>

<script>
    var p = {name: "张三", age: 18, gender: "男"};
    document.writeln(p.name);
    document.writeln(p.age);
    document.writeln(p["gender"]);
</script>

</body>
</html>
