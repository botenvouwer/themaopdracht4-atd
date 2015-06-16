<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/view/cms/header.jsp">
    <jsp:param name="title" value="Facturatie" />
</jsp:include>
<script>
    $(document).ready(function(){
        $(document).on('click', '[url]', function(){
            var url = $(this).attr('url');
            
            if(confirm("Weet je dit zeker als je de status veranderd kun je dit niet meer wijzigen. Als u het op betaling afwachten zet dan kunt u het niet meer wijzigen.")){
                window.location.href=url;
            }
        });
    })
</script>
<div class="tableWrap">
    <table class="dataTable">
        <tr>
            <th>Nummer</th>
            <th>Klant</th>
            <th>Totaal prijs</th>
            <th>Status</th>
            <th>Datum</th>
            <th>Verzonden</th>
            <th>Acties</th>
        </tr>
        <c:forEach var="invoice" items="${invoices}">
        <tr><fmt:setLocale value="nl_NL"/>
            <td><a href="/cms/factuur?id=${invoice.id}">F<fmt:formatNumber minIntegerDigits="8" groupingUsed="" value="${invoice.id}" /></a></td>
            <td><a href="/cms/gebruiker/form?id=${invoice.customer.id}">${invoice.customer.name}</a></td>
            <td><fmt:formatNumber type="currency" value="${invoice.total}" currencyCode="EUR" currencySymbol="€" /></td>
            <td class="center">${invoice.status}</td>
            <td class="center"><fmt:formatDate timeStyle="short" type="both" value="${invoice.date}" /></td>
            <td class="center"><fmt:formatDate timeStyle="short" type="both" value="${invoice.send}" /></td>
            <td class="right">
                <c:choose>
                    <c:when test="${invoice.status == 'OFFER'}">
                        <button onclick="window.location.href='factuur/form?id=${invoice.id}'">Aanpassen</button>
                        <button url="factuur?id=${invoice.id}&action=send">Verzenden</button>
                        <button url="factuur?id=${invoice.id}&action=cancel">Annuleren</button>
                    </c:when>
                    <c:when test="${invoice.status == 'NOTPAID'}">
                        <button onclick="window.open('factuur/pdf?id=${invoice.id}','_blank');">Printen</button>
                        <button url="factuur?id=${invoice.id}&action=paid">Betaald</button>
                        <button url="factuur?id=${invoice.id}&action=cancel">Annuleren</button>
                    </c:when>
                    <c:otherwise>
                        <button onclick="window.open('factuur/pdf?id=${invoice.id}','_blank');">Printen</button>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        </c:forEach>
    </table>
</div>
<footer class="contentMenu">
    <ul class="menu">
        <li class="button">
            <a href="factuur/form" title="">Factuur toevoegen</a>
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