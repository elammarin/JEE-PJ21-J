<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html>
    <head>
        <title>Ready orders</title>
        <meta content="text/html" charset="UTF-8" />
    </head>

    <body>
        <h1>Orders to be delivered</h1>

        <div>
            <form action="ready_orders.html" method="post">
                <label for="order_menu">Order</label>
                <select id="order_menu" name="orderId" required>
                    <option value="">--Please choose an order--</option>
                    <c:forEach items="${orders}" var="order">
                        <option value="${order.id}">${order.id}</option>
                    </c:forEach>
                </select>

                <br>
                <br>
                <button type="submit">Delivered</button>
            </form>
        </div>
    </body>
</html>