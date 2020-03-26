<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Регистрация</title>
  </head>
  <body>
    <div>
      <form id="form" name="registration_form" action="http://localhost:8080/e_shop_war_exploded/registration" method='POST'>
        <label>Ваш e-mail: <br>
          <input type='text' name='email'></label><br>
        <label>Пароль: <br>
          <input type='password' name='password'></label><br>
        <input type='submit' value='Регистрация'>
      </form>
    </div>
  </body>
</html>
