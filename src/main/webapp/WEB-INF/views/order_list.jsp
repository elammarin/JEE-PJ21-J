<%--
  Created by IntelliJ IDEA.
  User: elammari
  Date: 10/20/21
  Time: 7:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <title>order</title>
    <meta http-equiv='Content-Type' content='text/html' />
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.min.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.min.js"></script>
    <script src="https://unpkg.com/bootstrap-datepicker@1.9.0/dist/locales/bootstrap-datepicker.fr.min.js" charset="UTF-8"></script>
</head>
<body>
<h1>Liste des commandes</h1>
<ul>
    <c:choose>
        <c:when test="${empty orders}">Aucune commande</c:when>
        <c:otherwise>
            <c:forEach items="${orders}" var="order">
                <li>
                    <p>
                        Amount : <fmt:formatNumber type="currency" pattern ="#,##" value="${order.amount}" currencySymbol="&euro; " />
                            <%-- Amount : <fmt:formatNumber type="number" value="${order.amount/100.0}" currencySymbol="&euro; " /> --%><br/>
                        Status : <c:out value = "${order.currentStatus}"/>
                    </p>
                </li>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</ul>
    </body>