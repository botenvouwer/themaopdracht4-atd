<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="persons" value="${requestScope.persons}" ></c:set>
<c:set var="cars" value="${requestScope.cars}" ></c:set>
<c:set var="types" value="<%=domain.Task.Type.values()%>"/>
<c:set var="mechanics" value="${requestScope.mechanics}" ></c:set>
<jsp:include page="/WEB-INF/view/cms/header.jsp">
    <jsp:param name="title" value="Taak Toevoegen" />
</jsp:include>
<%@ page pageEncoding="UTF-8" %>
<form class="form validate" method="POST" action="./toevoegen">
<div class="tableWrap content">
    <div class="form-group">
        <label for="type">Taak Type:</label>
        <select name="type" id="type">
            <c:forEach var="type" items="${types}">
                <option <c:if test="${type == task.type}">selected=""</c:if> value="${type}">${type}</option>                    
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <label for="klant">Klant:</label>
        <select name="klant" id="klant">
            <c:forEach var="person" items="${persons}">
                <optgroup label="${person.name}">
                    <c:forEach var="car" items="${cars}">
                        <c:choose>
                            <c:when test="${car.owner == person}">
                                <option <c:if test="${car.id == task.car.id}">selected=""</c:if> value="${car.id}">${car.licensePlate} (${car.brand} ${car.model})</option>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </optgroup>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <label for="customerNote">Notitie van klant:</label>
        <textarea id="customerNote" name="customerNote">${task.customerNote}</textarea>
        <small class="error">${requestScope.customerNoteError}</small>
    </div>
    <hr>
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
        <input data-rule-required="true" type="date" name="date" id="date" value="${param.date}" />
        <small class="error">${requestScope.plannedForError}</small>
    </div>
</div>
<footer class="contentMenu">
    <button name="send" type="submit">Opslaan</button>
    <a class="button" href="/cms/werkplaats" title="">Annuleren</a>
</footer>
</form>
<%@include file="/WEB-INF/view/cms/footer.jsp" %>