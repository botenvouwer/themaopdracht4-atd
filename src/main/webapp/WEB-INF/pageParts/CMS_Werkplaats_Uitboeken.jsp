<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="task" value="${requestScope.task}" ></c:set>
<jsp:include page="/WEB-INF/view/cms/header.jsp">
    <jsp:param name="title" value="Taak uitboeken" />
</jsp:include>
<%@ page pageEncoding="UTF-8" %>
<form class="form validate" method="POST" action="./uitboeken">
<div class="tableWrap content">
    <div class="form-group">
        <label for="taskid">Taak:</label>
        <input type="hidden" name="id" id="taskid" value="${task.id}" />
        <input type="text" disabled="" value="T<fmt:formatNumber minIntegerDigits="8" groupingUsed="" value="${task.id}" />" />
    </div>
    <div class="form-group">
        <label for="mechanicNote">Reparatie notitite:</label>
        <textarea id="mechanicNote" name="mechanicNote">${task.mechanicNote}</textarea>
        <small class="error">${requestScope.mechanicNoteError}</small>
    </div>
    <div class="form-group">
        <label for="hours">Uren:</label>
        <input type="number" step="0.01" name="hours" id="hours" value="${task.hours}" />
        <small class="error">${requestScope.hoursError}</small>
    </div>
</div>
<footer class="contentMenu">
    <button name="send" type="submit">Opslaan</button>
    <a class="button" href="/cms/werkplaats" title="">Annuleren</a>
</footer>
</form>
<%@include file="/WEB-INF/view/cms/footer.jsp" %>