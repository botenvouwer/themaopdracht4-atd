<jsp:include page="/WEB-INF/view/cms/header.jsp">
    <jsp:param name="title" value="Gebruiker ${person.id == null ? 'Aanmaken' : 'Aanpassen'}" />
</jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="person" value="${requestScope.person}"/>
<c:set var="types" value="<%=domain.Person.Role.values()%>"/>
<%@ page pageEncoding="UTF-8" %>
<form class="form" method="POST" action="./form" >
<div class="tableWrap content">
    <c:choose>
        <c:when test="${person.id != null}">
            <input type="hidden" name="id" value="${person.id}" />
        </c:when>
    </c:choose>
    <div class="form-group">
        <label for="name">Naam:</label>
        <input type="text" name="name" id="name" value="${person.name}" />
        <small class="error">${requestScope.nameError}</small>
    </div>
    <div class="form-group">
        <label for="email">E-mailadres:</label>
        <input type="email" name="email" id="email" value="${person.email}" />
        <small class="error">${requestScope.emailError}</small>
    </div>
    <c:choose>
        <c:when test="${person.id == null}">
            <div class="form-group">
                <label for="password">Wachtwoord:</label>
                <input type="password" name="password" id="password" value="" />
                <small class="error">${requestScope.passwordError}</small>
            </div>
            <div class="form-group">
                <label for="repeat">Herhaal wachtwoord:</label>
                <input type="password" name="repeat" id="repeat" value="" />
            </div>
        </c:when>
    </c:choose>
    <div class="form-group">
        <label for="role">Type:</label>
        <select name="role" id="role">
            <c:forEach var="type" items="${types}">
                <c:choose>
                    <c:when test="${type == person.role}">
                        <option selected="" value="${type}">${type}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${type}">${type}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
        <small class="error">${requestScope.roleError}</small>
    </div>
    <div class="form-group">
        <label for="adress">Adres:</label>
        <input type="text" name="adress" id="adress" value="${person.adress}" />
        <small class="error">${requestScope.adressError}</small>
    </div>
        <div class="form-group">
        <label for="zipcode">Postcode:</label>
        <input type="text" name="zipcode" id="zipcode" value="${person.zipcode}" />
        <small class="error">${requestScope.zipcodeError}</small>
    </div>
    <div class="form-group">
        <label for="place">Plaats:</label>
        <input type="text" name="place" id="place" value="${person.place}" />
        <small class="error">${requestScope.placeError}</small>
    </div>
</div>
<footer class="contentMenu">
    <button name="send" value="Opslaan">Opslaan</button>
</footer>
</form>
<%@include file="/WEB-INF/view/cms/footer.jsp" %>