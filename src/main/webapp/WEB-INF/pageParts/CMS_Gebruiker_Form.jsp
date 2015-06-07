<jsp:include page="/WEB-INF/view/cms/header.jsp">
    <jsp:param name="title" value="Gebruiker Aanmaken" />
</jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="user" value="${requestScope.user}"/>
<c:set var="types" value="<%=domain.Person.Role.values()%>"/>
<%@ page pageEncoding="UTF-8" %>
<form class="form">
<div class="tableWrap content">
    <div class="form-group">
        <label for="name">Naam:</label>
        <input type="text" name="name" id="name" value="${user.name}" />
        <small>${requestScope.nameError}</small>
    </div>
    <div class="form-group">
        <label for="email">E-mailadres:</label>
        <input type="email" name="email" id="email" value="${user.email}" />
        <small>${requestScope.emailError}</small>
    </div>
    <div class="form-group">
        <label for="password">Wachtwoord:</label>
        <input type="password" name="password" id="password" value="" />
        <small>${requestScope.passwordError}</small>
    </div>
    <div class="form-group">
        <label for="repeat">Herhaal wachtwoord:</label>
        <input type="password" name="repeat" id="repeat" value="" />
    </div>
    <div class="form-group">
        <label for="role">Type:</label>
        <select name="role" id="role">
            <c:forEach var="type" items="${types}">
                <option value="type">${type}</option>
            </c:forEach>
        </select>
        <small>${requestScope.roleError}</small>
    </div>
    <div class="form-group">
        <label for="adress">Adres:</label>
        <input type="text" name="adress" id="adress" value="${user.adress}" />
        <small>${requestScope.adressError}</small>
    </div>
        <div class="form-group">
        <label for="zipcode">Postcode:</label>
        <input type="text" name="zipcode" id="zipcode" value="${user.zipcode}" />
        <small>${requestScope.zipcodeError}</small>
    </div>
    <div class="form-group">
        <label for="place">Plaats:</label>
        <input type="text" name="place" id="place" value="${user.place}" />
        <small>${requestScope.placeError}</small>
    </div>
</div>
<footer class="contentMenu">
    <button type="submit">Gebruiker aanmaken</button>
</footer>
</form>
<%@include file="/WEB-INF/view/cms/footer.jsp" %>