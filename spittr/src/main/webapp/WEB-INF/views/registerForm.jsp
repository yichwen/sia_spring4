<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>
<html>
<head>
    <title>Spittr</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
</head>
<body>
    <h1>Register</h1>
    <%-- commandName here is required controller model returns with key "spitter" --%>
    <sf:form method="POST" commandName="spitter">

        First Name: <sf:input path="firstName" />
        <sf:errors path="firstName" cssClass="error" /><br/>

        Last Name: <sf:input path="lastName" />
        <sf:errors path="lastName" cssClass="error" /><br/>

        Username: <sf:input path="username" />
        <sf:errors path="username" cssClass="error" /><br/>

        Password: <sf:input path="password" type="password" />
        <sf:errors path="password" cssClass="error" /><br/>

        Email: <sf:input path="email" />
        <sf:errors path="email" cssClass="error" /><br/>

        <input type="submit" value="Register" /><br/>

    </sf:form>
</body>
</html>