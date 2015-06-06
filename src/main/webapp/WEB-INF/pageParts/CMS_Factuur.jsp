<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/view/cms/header.jsp">
    <jsp:param name="title" value="Facturatie" />
</jsp:include>
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
        <tr><fmt:setLocale value="nl_NL"/>
            <td>F<fmt:formatNumber minIntegerDigits="8" groupingUsed="" value="${invoice.id}" /></td>
            <td><a href="/cms">${invoice.customer.name}</a></td>
            <td><fmt:formatNumber type="currency" value="${invoice.total}" currencyCode="EUR" currencySymbol="€" /></td>
            <td class="center">${invoice.paid ? 'Betaald': 'Open'}</td>
            <td class="center"><fmt:formatDate timeStyle="short" type="both" value="${invoice.date}" /></td>
            <td class="right">
                <button onclick="window.location.href='factuur/pdf?id=${invoice.id}'">PDF</button>
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