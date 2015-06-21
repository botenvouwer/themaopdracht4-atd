<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="nl_NL"/>
<jsp:include page="/WEB-INF/view/cms/header.jsp">
    <jsp:param name="title" value="Voorraadbeheer" />
</jsp:include>
<%@ page pageEncoding="UTF-8" %>
<div class="tableWrap">
    <table class="dataTable">
        <tr>
            <th>Nummer</th>
            <th>Artikel</th>
            <th>Verkoop prijs p/s</th>
            <th class="center">Voorraad</th>
            <th>Acties</th>
        </tr>
        <c:forEach var="a" items="${requestScope.articles}">
            <tr>
                <td>A<fmt:formatNumber minIntegerDigits="8" groupingUsed="" value="${a.id}" /></td>
                <td>${a.name}</td>
                <td><fmt:formatNumber type="currency" value="${a.price}" currencyCode="EUR" currencySymbol="€" /></td>
                <td class="center">${a.stock}</td>
                <td class="right">
                    <button onclick="location.href='/cms/voorraad/form?bewerken=${a.id}'">Aanpassen</button>
                    <button onclick="location.href='/cms/voorraad?verwijderen=true&id=${a.id}'">Verwijderen</button>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<footer class="contentMenu">
    <ul class="menu">
        <li class="button">
            <a href="/cms/voorraad/form" title="">Artikel Toevoegen</a>
        </li>
    </ul>
</footer>
<%@include file="/WEB-INF/view/cms/footer.jsp" %>