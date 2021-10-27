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
<% String ctxPath = request.getContextPath();
%>
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
<div class="container">
<h1>Votre commande</h1>
<ul class="list-group">
    <c:choose>
        <c:when test="${empty order}">Aucune commande</c:when>
        <c:otherwise>
            <!--c:forEach items="${orders}" var="order"-->
                <li class="list-group-item">
                    <p>
						Id commande : <c:out value = "${order.id}"/> <br/>
                        Date : <fmt:formatDate pattern = "d/M/YY" value="${order.createdOn}"/><br/>
                        Amount : <fmt:formatNumber type="currency" value="${order.amount}" /> <br/>
                        Status : <c:out value = "${order.currentStatus}"/>
                    </p> 
                </li>
            <!--/c:forEach-->
        <form action="../${order.id}/ticket.html">
            <button type="submit" class="btn btn-primary" >Récupérer</button>
        </form>
        </c:otherwise>
    </c:choose>
</ul>
</div>
</body>