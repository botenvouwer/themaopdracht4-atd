<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link type="text/css" rel="stylesheet" href="/css/main.css" />
        <link type="text/css" rel="stylesheet" href="/css/ATDWebsite.css" />
        <script src="/javascript/jquery-2.1.4.min.js" ></script>
        <title>Auto Totaal Dienst</title>
    </head>
    <body>
        <header id="mainHeader" class="main">
            <div id="topHeader">
                <div class="columnWith">
                    <div id="logo"><img src="/img/logo.png"> <div>Auto Totaal Dienst</div></div>
                    <div id="topInfo">
                        <div>Bel ons nu:</div>
                        <div>+(31) 202 600 084</div>
                    </div>
                </div>
            </div>
            <div id="menuHeader" class="columnWith">
                <c:choose>
                    <c:when test="${sessionScope.user.role == 'CUSTOMER'}">
                        <ul id="mainMenu" class="menu">
                            <li><a href="/">Home</a></li>
                            <li><a href="/klant">Uw ATD</a></li>
                            <li><a href="/reparatie">Reparatie</a></li>
                            <li><a href="/parkeren">Parkeren</a></li>
                            <li><a href="/contact">Contact</a></li>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <ul id="mainMenu" class="menu">
                            <li><a href="/">Home</a></li>
                            <li><a href="/contact">Contact</a></li>
                            <li><a href="/registreer">Registreren</a></li>
                            <li><a href="/login">Inloggen</a></li>
                        </ul>
                    </c:otherwise>
                </c:choose>
            </div>
        </header>
        <div class="mainWrap">
            <div id="mainContent" class="content columnWith">