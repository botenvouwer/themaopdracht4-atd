<%@page import="domain.Article"%>
<%@page import="domain.Article"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<jsp:include page="/WEB-INF/view/cms/header.jsp">
    <jsp:param name="title" value="Bestelling Aanmaken" />
</jsp:include>
<%@ page pageEncoding="UTF-8" %>
<form class="form validate" method="POST" action="/cms/bestellingen/aanmaken">
<div class="tableWrap content">
    <div class="form-group">
        <label for="article">Artikel:</label>
        
        <select name="article" id="article">
            <% List<Article> articles = (List<Article>) request.getAttribute("articles"); %>
            <% for(Article a : articles) { %>
            <option value="<%= a.getId() %>"><%= a.getName() %></option>
            <% } %>
        </select>
        
        <small>Wil je een nieuw artikel bestellen? Maak hem dan eerst aan!</small>
    </div>
    
    <div class="form-group">
        <label for="count">Aantal</label>
        <input data-rule-min="1" data-rule-required="true" type="number" name="count" id="price" />
    </div>
</div>
<footer class="contentMenu">
    <button name="send" type="submit">Opslaan</button>
    <a class="button" href="/cms/bestellingen" title="">Annuleren</a>
    <a class="button" href="/cms/voorraad/toevoegen" title="">Artikel aanmaken</a>
</footer>
</form>
<%@include file="/WEB-INF/view/cms/footer.jsp" %>