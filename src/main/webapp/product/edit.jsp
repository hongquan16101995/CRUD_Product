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
<form action="/product?action=edit" method="post">
    <table>
        <tr>
            <th colspan="2">Edit Form product</th>
        </tr>
        <tr>
            <td>Id</td>
            <td> <input type="text" name="id" id="id" value="${product.getId()}" readonly></td>
        </tr>
        <tr>
            <td>Name</td>
            <td><input type="text" name="name" id="name" value="${product.getName()}"></td>
        </tr>
        <tr>
            <td>Price</td>
            <td><input type="text" name="price" id="price" value="${product.getPrice()}"></td>
        </tr>
        <tr>
            <td>Image</td>
            <td><input type="text" name="image" id="image" value="${product.getImage()}"></td>
        </tr>
        <tr>
            <td colspan="2">
                <button type="submit">Edit</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
