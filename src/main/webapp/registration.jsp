<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="localization" var="loc" />
    <fmt:message bundle="${loc}" key="registration.login" var="login" />
    <fmt:message bundle="${loc}" key="registration.password" var="password" />
    <fmt:message bundle="${loc}" key="registration.address" var="address" />
    <fmt:message bundle="${loc}" key="registration.submit" var="submit" />

</head>
<body>

<form action="RegistrationServlet" method="post">
    <input name="login" type="text" placeholder="${login}"><br>
    <input name="password" type="password" placeholder="${password}"><br>
    <input name="address" type="text" placeholder="${address}"><br>
    <input type="submit" class="pure-button pure-button-primary" value="${submit}">
</form>

</body>
</html>
