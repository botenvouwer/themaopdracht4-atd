<jsp:include page="/WEB-INF/view/cms/header.jsp">
    <jsp:param name="title" value="Gebruiker Aanmaken" />
</jsp:include>
<%@ page pageEncoding="UTF-8" %>
<form class="form">
<div class="tableWrap content">
    <div class="form-group">
        <label for="name">Naam:</label>
        <input type="text" name="name" id="name" value="Arnoud de Kip" />
        <small>Let op: de naam mag nog niet in gebruik zijn bij een andere gebruiker.</small>
    </div>
    
    <div class="form-group">
        <label for="email">E-mailadres:</label>
        <input type="email" name="email" id="email" value="arni@hotmail.com" />
        <small>[email]@[provider].[extensie]</small>
    </div>
    
    <div class="form-group">
        <label for="role">Type:</label>
        
        <select name="role" id="role">
            <option>Klant</option>
            <option selected>Monteur</option>
            <option>Chef</option>
        </select>
    </div>
</div>
<footer class="contentMenu">
    <button type="submit">Gebruiker bijwerken</button>
</footer>
</form>
<%@include file="/WEB-INF/view/cms/footer.jsp" %>