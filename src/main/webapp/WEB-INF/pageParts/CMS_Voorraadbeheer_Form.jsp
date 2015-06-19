<%@page import="domain.Article"%>
<c:set var="article" value="${requestScope.article}"/>
<jsp:include page="/WEB-INF/view/cms/header.jsp">
    <jsp:param name="title" value="Artikel ${article.id == null ? 'Toevoegen' : 'Aanpassen'}" />
</jsp:include>


<form class="form validate" method="POST" action="/cms/voorraad/form">
    <c:choose>
        <c:when test="${article.id != null}">
            <input type="hidden" name="id" value="${article.id}" />
        </c:when>
    </c:choose>

    <div class="tableWrap content">
        <div class="form-group">
            <label for="artikel">Artikel:</label>
            <input data-rule-required="true" type="text" name="artikel" id="artikel" value="${article.name}" />
        </div>

        <div class="form-group">
            <label for="prijs">Prijs:</label>
            <input data-rule-min="0" data-rule-required="true" data-rule-currency="" type="number" name="prijs" id="prijs" step="0.01" value="${article.price}" />
        </div>

        <div class="form-group">
            <label for="voorraad">Voorraad:</label>
            <input data-rule-min="0" data-rule-required="true" type="number" name="voorraad" id="voorraad" value="${article.stock}" />
        </div>
    </div>
    <footer class="contentMenu">
        <button name="send" type="submit">Opslaan</button>
        <a class="button" href="/cms/voorraad" title="">Annuleren</a>
    </footer>
</form>

<%@include file="/WEB-INF/view/cms/footer.jsp" %>