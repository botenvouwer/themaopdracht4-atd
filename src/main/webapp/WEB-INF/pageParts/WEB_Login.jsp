<%@include file="/WEB-INF/view/website/header.jsp" %>
<%@ page pageEncoding="UTF-8" %>
<h1>Login</h1>
<div style="padding: 80px 0px; text-align: center">
    <i>${requestScope.registered}</i>
    <form action="/login" method="POST">
        <i class="error">${requestScope.usernameError}</i><br>
        <input type="text" style="width: 400px" placeholder="Gebruikersnaam" name="username" value="${param.username}"><br>
        <i class="error">${requestScope.passwordError}</i>
        <input type="password" style="width: 400px" placeholder="Wachtwoord" name="password"><br>
        <input type="submit" style="width:300px" name="send" value="Log in"><br>
        <input type="hidden" name="returnUrl" value="${requestScope.returnUrl}">
    </form>
    <a href="/registreer" style="color:#000">Registreer</a>
</div>
<%@include file="/WEB-INF/view/website/footer.jsp" %>