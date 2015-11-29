<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tld/taglib.tld" prefix="mytag" %>
<html>
<head>
    <title>Add new order</title>
</head>
<body>
<mytag:AddNewOrderTag local="${sessionScope.local}"/>
</body>
</html>
