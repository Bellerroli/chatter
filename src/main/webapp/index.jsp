<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
  <script src="static/utils.js"></script>
</head>
<body>
<h1><%= "Hello World!" %></h1>
<br/>
<div id="user">Guest</div>

<a href="hello-servlet">Hello Servlet</a><br>
<a href="register_user">Register</a><br>
<a href="login">Login</a><br>
<a href="all_users">All Users</a><br>
<a href="chatroom.html">Chatroom</a><br>


<div id="msg"></div>
<input id="to-send" type="text" maxlength="200">
<button id="sender">Send</button>

<style>
  div{
    border: black 1px solid;
  }
</style>
<script>
  document.getElementById("user").innerText = getUsername();
</script>

</body>
</html>