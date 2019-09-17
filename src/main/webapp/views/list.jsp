<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: oenga
  Date: 9/17/19
  Time: 2:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <c:if test="${moneyTypeList != null}">
                <c:if test="${productTypeList != null}">
                    <form action="list" method="post">
                        <label for="productChosen">Select product to buy</label>
                        <select name="productChosen" id="productChosen">
                            <c:forEach var="product" items="${productTypeList}">
                                <option value="${product}">${product}</option>
                            </c:forEach>
                        </select>
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th scope="col">Money Type</th>
                                <th scope="col">Count for Each Type</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="money" items="${moneyTypeList}">
                                <tr>
                                    <th scope="row">${money}</th>
                                    <td><input type="number" min="0" name="${money}" value="0"></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <input type="submit" value="Submit" class="btn btn-success" role="button">
                    </form>
                </c:if>
            </c:if>
        </div>
    </div>

</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>
