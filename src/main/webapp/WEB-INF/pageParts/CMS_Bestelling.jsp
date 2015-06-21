<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="nl_NL"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/view/cms/header.jsp">
    <jsp:param name="title" value="Bestellingen" />
</jsp:include>
<%@ page pageEncoding="UTF-8" %>
<div class="tableWrap">
    <table class="dataTable">
        <tr>
            <th>Nummer</th>
            <th>Datum</th>
            <th>Artikel</th>
            <th class="center">Aantal</th>
            <th>Status</th>
            <th>Acties</th>
        </tr>
        
        <c:forEach var="d" items="${requestScope.deliverys}">
            <tr>
                <td>B<fmt:formatNumber minIntegerDigits="8" groupingUsed="" value="${d.id}" /></td>
                <td><fmt:formatDate timeStyle="short" type="both" value="${d.date}" /></td>
                <td><a href="/cms/voorraad/toevoegen?bewerken=${d.article.id}">${d.article.name}</a></td>
                <td class="center">${d.count}</td>
                <td>
                    <c:choose>
                        <c:when test="${d.status == 'STANDAARD'}">
                            In Bestelling
                        </c:when>
                        <c:when test="${d.status == 'GEANNULEERD'}">
                            Geannuleerd
                        </c:when>
                        <c:when test="${d.status == 'GELEVERD'}">
                            Geleverd
                        </c:when>
                    </c:choose>
                </td>
                <c:choose>
                    <c:when test="${d.status == 'STANDAARD'}">
                        <td class="right">
                            <button onclick="location.href='/cms/bestellingen?status=1&id=${d.id}'">Geleverd</button>
                            <button onclick="location.href='/cms/bestellingen?status=3&id=${d.id}'">Annuleren</button>
                        </td>
                    </c:when>
                    <c:otherwise>
                        <td class="right"></td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
    </table>
</div>

<footer class="contentMenu">
    <ul class="menu">
        <li class="button">
            <a href="bestellingen/form" title="">Nieuwe bestelling</a>
        </li>
        <li class="button">
            <a href="bestellingen" title="">Toon In Bestelling</a>
        </li>
        <li class="button">
            <a href="bestellingen?toon=geleverd" title="">Toon Geleverde</a>
        </li>
        <li class="button">
            <a href="bestellingen?toon=geannuleerd" title="">Toon Geannuleerde</a>
        </li>
    </ul>
</footer>
<%@include file="/WEB-INF/view/cms/footer.jsp" %>