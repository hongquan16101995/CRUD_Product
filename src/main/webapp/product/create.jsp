<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 07/22/2022
  Time: 10:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${product.getId() != null}">
<form action="/product?action=edit&id=${product.getId()}" method="post">
    <label for="id">ID</label>
    <input type="text" name="id" id="id" value="${product.getId()}" readonly>
    </c:if>
    <br>
    <form action="/product?action=create" method="post">
        <label for="name">Name</label>
        <input type="text" name="name" id="name" value="${product.getName()}">
        <br>
        <label for="price">Price</label>
        <input type="text" name="price" id="price" value="${product.getPrice()}">
        <br>
        <label for="image">Image</label>
        <input type="text" name="image" id="image" value="${product.getImage()}">
        <br>
        <button type="submit">Create</button>
    </form>
</body>
</html>
