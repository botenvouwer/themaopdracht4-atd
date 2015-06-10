<%@include file="/WEB-INF/view/website/header.jsp" %>
<%@ page pageEncoding="UTF-8" %>
<h2>Welkom ${sessionScope.user.name}</h2>
<p>U hier u gegevens beheren</p>
<div>
    Todo: Klant pagina maken
</div>

<input type="button" value="Uitloggen" onclick="window.location.href='/uitloggen'">

<%@include file="/WEB-INF/view/website/footer.jsp" %>