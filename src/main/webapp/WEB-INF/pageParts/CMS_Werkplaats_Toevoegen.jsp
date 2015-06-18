<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="persons" value="${requestScope.persons}" ></c:set>
<c:set var="cars" value="${requestScope.cars}" ></c:set>
<c:set var="mechanics" value="${requestScope.mechanics}" ></c:set>
    
<%@page import="domain.Car"%>
<%@page import="java.util.List"%>
<%@page import="domain.Person"%>
<jsp:include page="/WEB-INF/view/cms/header.jsp">
    <jsp:param name="title" value="Taak Toevoegen" />
</jsp:include>
<%@ page pageEncoding="UTF-8" %>
<form class="form validate" method="POST" action="/cms/werkplaats/toevoegen">
<div class="tableWrap content">
    <div class="form-group">
        <label for="type">Taak Type:</label>
        <select name="type" id="type">
            <option value="APK">APK</option>
            <option value="REPAID">Reparatie</option>
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
                                <option value="${car.id}">${car.licensePlate} (${car.brand} ${car.model})</option>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </optgroup>
            </c:forEach>
        </select>
    </div>
    
    <div class="form-group">
        <label for="monteur">Monteur:</label>
        <select name="monteur" id="monteur">
            <c:forEach var="mechanic" items="${mechanics}">
                <option value="${mechanic.id}">${mechanic.name}</option>
            </c:forEach>
        </select>
    </div>
    
    <div class="form-group">
        <label for="date">Ingeplande Datum:</label>
        <input data-rule-required="true" type="date" name="date" id="date" />
    </div>
    
    <div class="form-group">
        <label for="hours">Gewerkte Uren:</label>
        <input data-rule-required="true" data-rule-min="0" type="number" name="hours" id="hours" />
    </div>
    
    <div class="form-group">
        <label for="notem">Notitie Monteur:</label>
        <textarea id="notem" name="notem"></textarea>
    </div>
    
    <div class="form-group">
        <label for="notek">Notitie Klant:</label>
        <textarea data-rule-required="true" id="notek" name="notek"></textarea>
    </div>
</div>
<footer class="contentMenu">
    <button name="send" type="submit">Opslaan</button>
    <a class="button" href="/cms/werkplaats" title="">Annuleren</a>
</footer>
</form>
<%@include file="/WEB-INF/view/cms/footer.jsp" %>