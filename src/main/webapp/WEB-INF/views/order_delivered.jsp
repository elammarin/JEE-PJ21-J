<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% String ctxPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Commande livrée	</title>
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
<h3>Le nombre de commande par tranche d'heure pour la journée ${dateVoulu}</h3>
<ul class="list-group">

    <c:choose>
        <c:when test="${empty listHour}">
        	<p>Aucune commande livrée</p>
        	<p>Nombre total de commandes : 0</p>
			<p>Montant total de commandes : 0</p>
        </c:when>
        
        <c:otherwise>
        	<p>Nombre total de commandes : ${nombreTotalCommande}</p>
			<p>Montant total de commandes : ${montantTotal}</p>
        	
        	<c:set var="i" value="0" />
            <c:forEach items="${listHour}" var="nbrCommande">
                <li class="list-group-item">
                	  
                     <p>   Entre : ${i}h - ${i + 1}h </p>
                     <p>   Nombre de commandes livrées :  <fmt:formatNumber value="${nbrCommande}" /> </p><br/>
					 <c:set var="i" value="${i + 1}" />
                </li>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</ul>

</div>
</body>