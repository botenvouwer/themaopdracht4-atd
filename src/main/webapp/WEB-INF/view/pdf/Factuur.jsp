<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="invoice" value="${requestScope.invoice}"/>
<fmt:setLocale value="nl_NL"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="/css/main.css">
        <link type="text/css" rel="stylesheet" href="/css/factuur.css">
        <title>Factuur F<fmt:formatNumber minIntegerDigits="8" groupingUsed="" value="${invoice.id}" /></title>
    </head>
    <body>
        <script>
            document.addEventListener("DOMContentLoaded", function(event) { 
                window.print();
            });
        </script>
        <div id="invoiceBody">
            <div id="customer">
                <h1>Factuur</h1>
                <table>
                    <tr>
                        <td>Ter name van:</td>
                        <td>${invoice.customer.name}</td>
                    </tr>
                    <tr>
                        <td>Klantnummer:</td>
                        <td>P<fmt:formatNumber minIntegerDigits="8" groupingUsed="" value="${invoice.customer.id}" /></td>
                    </tr>
                    <tr>
                        <td>Adres:</td>
                        <td>${invoice.customer.adress}</td>
                    </tr>
                    <tr>
                        <td>Postcode:</td>
                        <td>${invoice.customer.zipcode}</td>
                    </tr>
                    <tr>
                        <td>Woonplaats:</td>
                        <td>${invoice.customer.place}</td>
                    </tr>
                </table>
            </div>
            <div id="company">
                <center>
                    <img src="/img/logo.png">
                    <h2>Auto Totaal Dienst</h2>
                </center>
                <table>
                    <tr>
                        <td>Adres:</td>
                        <td>Cocacolalaan 22</td>
                    </tr>
                    <tr>
                        <td>Postcode:</td>
                        <td>6666HF</td>
                    </tr>
                    <tr>
                        <td>Plaats:</td>
                        <td>Utrecht</td>
                    </tr>
                    <tr>
                        <td>KVK:</td>
                        <td>17551138</td>
                    </tr>
                    <tr>
                        <td>BTW nummer:</td>
                        <td>2328.62.054.A.02</td>
                    </tr>
                    <tr>
                        <td>Website:</td>
                        <td>atd.nl</td>
                    </tr>
                </table>
            </div>
            <div id="invoice">
                <table id="info">
                    <tr>
                        <th>Factuurnummer</th>
                        <th>Klantnummer</th>
                        <th>Factuurdatum</th>
                        <th>Verzenddatum</th>
                    </tr>
                    <tr>
                        <td>F<fmt:formatNumber minIntegerDigits="8" groupingUsed="" value="${invoice.id}" /></td>
                        <td>P<fmt:formatNumber minIntegerDigits="8" groupingUsed="" value="${invoice.customer.id}" /></td>
                        <td><fmt:formatDate timeStyle="short" type="both" value="${invoice.date}" /></td>
                        <td><fmt:formatDate timeStyle="short" type="both" value="${invoice.send}" /></td>
                    </tr>
                </table>
                <table id="lines">
                    <tr>
                        <th>Beschrijving</th>
                        <th>Prijs per Aantal/Uur</th>
                        <th>Aantal/Uur</th>
                        <th>Totaal inc BTW</th>
                    </tr>
                    <c:forEach var="line" items="${invoice.lines}">
                    <tr>
                        <td>${line.description}</td>
                        <td><fmt:formatNumber type="currency" value="${line.price}" currencyCode="EUR" currencySymbol="€" /></td>
                        <td>${line.quantity}</td>
                        <td><fmt:formatNumber type="currency" value="${line.total}" currencyCode="EUR" currencySymbol="€" /></td>
                    </tr>
                    </c:forEach>
                    <tr id="totalLine">
                        <th colspan="3" class="total">Subtotaal:</th>
                        <td><fmt:formatNumber type="currency" value="${invoice.taxFree}" currencyCode="EUR" currencySymbol="€" /></td>
                    </tr>
                    <tr>
                        <th colspan="3" class="total">BTW:</th>
                        <td><fmt:formatNumber type="currency" value="${invoice.BTW}" currencyCode="EUR" currencySymbol="€" /> <sup>%${invoice.taxPercentage}</sup></td>
                    </tr>
                    <tr>
                        <th colspan="3" class="total">Totaal:</th>
                        <td><fmt:formatNumber type="currency" value="${invoice.total}" currencyCode="EUR" currencySymbol="€" /></td>
                    </tr>
                </table>
            </div>
            <div id="footer">Wij verzoeken u dit bedrag binnen 14 dagen te voldoen op ons rekeningnummer 123456789 van de Rabobank t.n.v. Auto Totaal Dienst o.v.v. Factuurnummer F<fmt:formatNumber minIntegerDigits="8" groupingUsed="" value="${invoice.id}" /></div>
        </div>
    </body>
</html>
