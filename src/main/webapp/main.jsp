
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages" var="msg" />

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Kubicki's Budget</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        footer {
            background-color: #f2f2f2;
            padding: 25px;
        }
        .carousel-inner img {
            width: 100%; /* Set width to 100% */
            margin: auto;
            min-height: 200px;
        }
        @media (max-width: 600px) {

        }
    </style>
</head>

<body>

<h1>${surname}</h1>

<%--<ul class="list-group" a>--%>
    <%--<c:forEach items="${budgetentries}" var="budgetentries">--%>
        <%--<li class="list-group-item">--%>
            <%--<div class="checkbox">--%>
                <%--<p type="text">--%>
                    <%--<input type="checkbox" value="${budgetentries.budgetItem.getValue()}" name="newAdmins">${budgetentries.user.login}--%>
                    <%--/ ${budgetentries.user.password}--%>

                <%--</p>--%>
            <%--</div>--%>
        <%--</li>--%>
    <%--</c:forEach>--%>
<%--</ul>--%>
<div>
    <form method="post" action="createUser">
        Login         : <input type="text" name="login">
        First Name    : <input type="text" name="firstname">
        Last Name     : <input type="text" name="lastname">
        email         : <input type="text" name="email">
        password      : <input type="password" name="password">
        <input type="submit" value="createUser">
    </form>

</div>

<div>

    <c:forEach items="${users}" var="eb">
        <li>
            <div class="checkbox">
                <label>
                    <input type="checkbox" value="${eb.login}+${eb.email}">
                   </input>
                </label>
            </div>
        </li>
    </c:forEach>
</div>

</body>



</html>