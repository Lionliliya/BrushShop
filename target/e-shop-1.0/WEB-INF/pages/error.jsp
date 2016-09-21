<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Error Page</title>
</head>
<body>
<h2>Error, please contact support or try another request</h2>

<h3>Debug Information:</h3>

Requested URL= ${url}<br><br>

<c:forEach items="${error}" var="trace">
    <p>${trace}</p>
</c:forEach>

</body>
</html>
