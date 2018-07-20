<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Entities</title>
</head>
<body>
a: <%= request.getAttribute("a")%>
${a}
<h2>Publishers:</h2>
<c:forEach var="publisher" items="${publishers}">
    ${publisher} <br/>
</c:forEach>
<h2>Authors:</h2>
<c:forEach var="author" items="${authors}">
    ${author.name} <br/>
</c:forEach>
</body>
</html>
