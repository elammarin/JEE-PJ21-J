<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    String ctxPath = request.getContextPath();

%>
<html>
<head>
    <title>Docks</title>


</head>
<body>
<H1>DOCKERS</H1>

<form id="form-add">
    <p id="error" style="background-color: #ec7b7b;display: none;width: 30%" ></p>
    <label>x:</label>
    <input name="x" required type="number" id="x" value=""/>
    <label>y:</label>
    <input name="y" required type="number" id="y" value=""/>
    <label>num:</label>
    <input name="numDocker" required type="number" id="numDocker" value=""/>
    <button type="submit" id="submit-add">ADD</button>
</form>


<c:choose>
    <c:when test="${empty docks}">
        <p>Aucun dock</p>
    </c:when>
    <c:otherwise>

            <c:forEach items="${docks}" var="dock">

                <div> id: ${dock.id} | n°: ${dock.numDocker} | position: ${dock.x},${dock.y}
                <button class="remove" data-id="${dock.id}">X</button>
                </div>

            </c:forEach>

    </c:otherwise>
</c:choose>

<div>
    <canvas style="background-color: #eae1dc" id="canvas" width="300" height="300"></canvas>
</div>

<script src="<%= ctxPath %>/js/jquery.min.js" type="text/javascript"></script>
<script src="<%= ctxPath %>/js/dock.js" type="text/javascript"></script>
</body>
</html>
