<%@page import="domain.Article"%>
<jsp:include page="/WEB-INF/view/cms/header.jsp">
    <jsp:param name="title" value="Artikel Toevoegen" />
</jsp:include>

<%@ page pageEncoding="UTF-8" %>
<% 
    String name = "";
    double price = 0.00;
    int stock = 0;
    Article a = null; 
    if (request.getAttribute("article") != null) { 
        a = (Article) request.getAttribute("article"); 
        name = a.getName();
        price = a.getPrice();
        stock = a.getStock();
    } 
%>  
<% if (a != null) { %>
     <form class="form" method="POST" action="/cms/voorraad/toevoegen?bewerken=<%= a.getId() %>">   
<% } else { %>
    <form class="form" method="POST" action="/cms/voorraad/toevoegen">
<% } %>

<div class="tableWrap content">
    <div class="form-group">
        <label for="artikel">Artikel:</label>
        <input type="text" name="artikel" id="artikel" value="<%= name %>" />
    </div>
    
    <div class="form-group">
        <label for="prijs">Prijs:</label>
        <input type="number" name="prijs" id="prijs" value="<%= price %>" />
    </div>
    
    <div class="form-group">
        <label for="voorraad">Voorraad:</label>
        <input type="number" name="voorraad" id="voorraad" value="<%= stock %>" />
    </div>
</div>
<footer class="contentMenu">
    <button name="send" type="submit">Opslaan</button>
    <a class="button" href="/cms/voorraad" title="">Annuleren</a>
</footer>
</form>

<%@include file="/WEB-INF/view/cms/footer.jsp" %>