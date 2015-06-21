<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="task" value="${requestScope.task}" ></c:set>
<c:set var="mechanics" value="${requestScope.mechanics}" ></c:set>
<jsp:include page="/WEB-INF/view/cms/header.jsp">
    <jsp:param name="title" value="Taak Toevoegen" />
</jsp:include>
<%@ page pageEncoding="UTF-8" %>
<form class="form validate" method="POST" action="./plannen">
<div class="tableWrap content">
    <div class="form-group">
        <label for="taskid">Taak:</label>
        <input type="hidden" name="id" id="taskid" value="${task.id}" />
        <input type="text" disabled="" value="T<fmt:formatNumber minIntegerDigits="8" groupingUsed="" value="${task.id}" />" />
    </div>
    <div class="form-group">
        <label for="monteur">Monteur:</label>
        <select name="monteur" id="monteur">
            <c:forEach var="mechanic" items="${mechanics}">
                <option <c:if test="${mechanic.id == task.mechanic.id}">selected=""</c:if> value="${mechanic.id}">${mechanic.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <label for="date">Plandatum:</label>
        <input data-rule-required="true" type="date" name="date" id="date" value="${param.date}"/>
        <small class="error">${requestScope.plannedForError}</small>
    </div>
</div>
<footer class="contentMenu">
    <button name="send" type="submit">Opslaan</button>
    <a class="button" href="/cms/werkplaats" title="">Annuleren</a>
</footer>
</form>
<%@include file="/WEB-INF/view/cms/footer.jsp" %>