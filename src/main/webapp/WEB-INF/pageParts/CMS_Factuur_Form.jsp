<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/cms/header.jsp">
    <jsp:param name="title" value="Factuur Aanmaken" />
</jsp:include>
<%@ page pageEncoding="UTF-8" %>
<h1>Factuur</h1>
<p>nummer: ${invoice.id}</p>
<p>klant: ${invoice.customer.name}</p>

<c:forEach var="line" items="${invoice.lines}">
    <p> &nbsp;&nbsp;&nbsp; - ${line.description} ${line.price} ${line.quantity}</p>
</c:forEach>

<%@include file="/WEB-INF/view/cms/footer.jsp" %>