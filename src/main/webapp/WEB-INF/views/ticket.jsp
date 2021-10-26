<%--
  Created by IntelliJ IDEA.
  User: elammari
  Date: 10/23/21
  Time: 2:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Votre numéro de quai</title>
</head>
<body>
<h1>Votre numéro de quai</h1>
<c:choose>
    <c:when test = "${ticket == -1}">
        <p>Il n'y a aucun quai disponible</p>
    </c:when>
    <c:otherwise>
        <p>Veuillez vous présenter au quai numéro ${ticket+1} pour récuperer votre commande</p>
    </c:otherwise>
</c:choose>
</body>
</html>
