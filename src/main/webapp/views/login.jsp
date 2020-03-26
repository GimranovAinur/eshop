<%--
  Created by IntelliJ IDEA.
  User: Айнур
  Date: 25.03.2020
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form action='http://localhost:8080/e_shop_war_exploded/login' method='POST'>
        <label>Email: <br>
            <input type='text' name='email'></label><br>
        <label>Пароль: <br>
            <input type='password' name='password'></label><br>
        <input type='submit' value='Войти'>
    </form>
</body>
</html>
