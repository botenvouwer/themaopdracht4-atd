<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="nl_NL"/>
<jsp:include page="/WEB-INF/view/cms/header.jsp">
    <jsp:param name="title" value="Gebruikers" />
</jsp:include>
<%@ page pageEncoding="UTF-8" %>
<div class="tableWrap">
    <table class="dataTable">
        <tr>
            <th>Nummer</th>
            <th>Naam</th>
            <th>Email</th>
            <th>Type</th>
            <th>Acties</th>
        </tr>
        <c:forEach var="user" items="${users}">
        <tr>
            <td>P<fmt:formatNumber minIntegerDigits="8" groupingUsed="" value="${user.id}" /></td>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>
                <c:choose>
                    <c:when test="${user.role == 'BOSS'}">
                        <i>Chef</i>
                    </c:when>
                    <c:when test="${user.role == 'CUSTOMER'}">
                        <i>Klant</i>
                    </c:when>
                    <c:when test="${user.role == 'EMPLOYEE'}">
                        <i>Werknemer</i>
                    </c:when>
                    
                    <c:otherwise>
                        <i>Onbekend</i>
                    </c:otherwise>
              </c:choose>
            </td>
            <td class="right">
                <button onclick="window.location.href='/cms/gebruiker/form?id=${user.id}'">Aanpassen</button>
            </td>
        </tr>
        </c:forEach>
    </table>
</div>
<footer class="contentMenu">
    <ul class="menu">
        <li class="button">
            <a href="/cms/gebruiker/form" title="Nieuwe gebruiker aanmaken">Gebruiker aanmaken</a>
        </li>
    </ul>
</footer>
<%@include file="/WEB-INF/view/cms/footer.jsp" %>