<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/cms/header.jsp">
    <jsp:param name="title" value="Facturatie" />
</jsp:include>
<%@ page pageEncoding="UTF-8" %>
<div class="tableWrap">
    <table class="dataTable">
        <tr>
            <th>Nummer</th>
            <th>Klant</th>
            <th>Totaal prijs</th>
            <th>Voldaan</th>
            <th>Datum</th>
            <th>Acties</th>
        </tr>
        <c:forEach var="invoice" items="${invoices}">
        <tr>
            <td>${invoice.id}</td>
            <td><a href="/cms">${invoice.customer.name}</a></td>
            <td>${invoice.total}</td>
            <td class="center">${invoice.paid}</td>
            <td class="center">${invoice.date}</td>
            <td class="right">
                <button onclick="window.location.href='factuur/aanpassen'">Printen</button>
                <button onclick="window.location.href='factuur/form?id=${invoice.id}'">Aanpassen</button>
                <button>Uitboeken</button>
            </td>
        </tr>
        </c:forEach>
    </table>
</div>
<footer class="contentMenu">
    <ul class="menu">
        <li class="button">
            <a href="factuur/aanmaken" title="">Factuur toevoegen</a>
        </li>
        <li class="button">
            <a href="/cms/" title="">Alles verzenden</a>
        </li>
        <li class="button">
            <a href="/cms/" title="">Toon openstaand</a>
        </li>
    </ul>
</footer>
<%@include file="/WEB-INF/view/cms/footer.jsp" %>