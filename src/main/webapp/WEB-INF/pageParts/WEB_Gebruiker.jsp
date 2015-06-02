<%@include file="/WEB-INF/view/website/header.jsp" %>
<%@ page pageEncoding="UTF-8" %>
<h2>Welkom [Gebruiker hier]</h2>
<p>U kunt zich nu aanmelden voor een nieuwe klus</p>
<div>
    <a href="/reparatie">Reparatie</a>
    <a href="/tankbeurt">Tankbeurt</a>
    <a href="/parkeren">Parkeren</a>
    <a href="/autobeheer">Auto's in behandeling</a>
</div>
<form action="post">
    <input type="submit" value="Uitloggen">
</form>
<%@include file="/WEB-INF/view/website/footer.jsp" %>