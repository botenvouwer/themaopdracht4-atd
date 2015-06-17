<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/WEB-INF/view/website/header.jsp" %>
<%@ page pageEncoding="UTF-8" %>
<h2>Factuur</h2>

<c:choose>
    <c:when test="${!empty facturen}">
        <p>Hieronder kunt u al uw facturen zien.</p>
        
        <table>
            <tr>
                <th style="width: 67px;">Nummer</th>
                <th>Datum</th>
                <th>Status</th>
                <th style="width: 110px;" class="right">Acties</th>
            </tr>

            <c:forEach var="factuur" items="${facturen}">
                 <c:choose>
                    <c:when test="${factuur.status == 'OFFER' || factuur.statis == 'PAID'}">
                        <tr>
                            <td class="center">${factuur.id}</td>
                            <td><fmt:formatDate pattern="dd-MM-yyyy" value="${factuur.date}" /></td>

                            <td>
                                <c:choose>
                                    <c:when test="${factuur.status == 'OFFER'}">
                                        Openstaand
                                    </c:when>
                                    <c:when test="${factuur.status == 'PAID'}">
                                        Betaald
                                    </c:when>
                                </c:choose>
                            </td>

                            <td class="right"><a target="_blank" href="/klant/factuur/pdf?id=${factuur.id}">Download PDF</a></td>
                        </tr>
                    </c:when>
                 </c:choose>
            </c:forEach>
        </table>
    </c:when>
    
    <c:when test="${empty facturen}">
        <p>Er zijn geen facturen gevonden!</p>
    </c:when>
</c:choose>

<%@include file="/WEB-INF/view/website/footer.jsp" %>