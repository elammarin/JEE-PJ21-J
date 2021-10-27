<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html>
    <head>
        <title>Connection</title>
        <meta content="text/html" charset="UTF-8" />
    </head>

    <body>
        <h1>Choose your name on the list</h1>

        <div>
            <form action="connect.html" method="post">
                <label for="employee_menu">Employee</label>
                <select id="employee_menu" name="employeeId" required>
                    <option value="">--Please choose an employee--</option>
                    <c:forEach items="${employees}" var="employee">
                        <option value="${employee.id}">${employee.lastname} ${employee.firstname}</option>
                    </c:forEach>
                </select>

                <button type="submit">Connect !</button>
            </form>
        </div>
    </body>