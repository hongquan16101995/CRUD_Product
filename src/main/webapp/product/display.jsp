<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 07/22/2022
  Time: 9:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/product/create.jsp">Create new product</a>
<table border="1" width="300px">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Image</th>
        <th colspan="2">Action</th>
    </tr>
    <c:forEach items="${products}" var="p">
        <tr>
            <td>${p.getId()}</td>
            <td>${p.getName()}</td>
            <td>${p.getPrice()}</td>
            <td><img style="width: 200px; height: 200px" src="${p.getImage()}" alt=""></td>
            <td><button><a href="/product?action=edit&id=${p.getId()}">Edit</a></button></td>
            <td><button><a href="/product?action=delete&id=${p.getId()}">Delete</a></button></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
