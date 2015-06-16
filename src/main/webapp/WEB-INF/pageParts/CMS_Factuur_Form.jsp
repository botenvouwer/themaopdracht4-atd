<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="nl_NL"/>
<jsp:include page="/WEB-INF/view/cms/header.jsp">
    <jsp:param name="title" value="Factuur Aanmaken" />
</jsp:include>
<c:set var="invoice" value="${requestScope.invoice}" ></c:set>
<%@ page pageEncoding="UTF-8" %>
<script>
    $(document).ready(function(){

        //voeg factuur lijn toe
        window.lineTable = $('#lines');
        $('#addLine').on('click', function(){
            lineTable.append('<tr> <td> <input type="hidden" name="lineid" value="0"><input type="text" name="description" value="" /> </td> <td> <input type="number" class="price" name="price" step="0.01" min="0" value=""> </td> <td> <input type="number" class="quantity" name="quantity" min="0" value="1"> </td> <td> <i class="discountRangeFeedback">0%</i> <input class="discountRange" oninput="silde(this);" type="range" name="discount" min="0" value="0" max="100"> </td> <td class="total">€ 0</td> <td><button class="removeLine">Verwijder</button></td></tr>');

            return false;
        });
        
        //verwijder factuur lijn
        $(document).on('click', '.removeLine', function(){
            
            var tr = $(this).parents('tr');
            tr.prev('tr.error').remove();
            
            tr.remove();

            return false;
        });
        
        //silde feedback voor range van discount control
        $(document).on('change', '.discountRange', function(){
            silde(this);
        });
        
        //zet totaal prijs als lijnen veranderen
        $(document).on('change', '.price', function(){
            setPrice($(this).parents('tr'));
        });
        
        $(document).on('change', '.quantity', function(){
            setPrice($(this).parents('tr'));
        });
        
        //Bij laden van pagina zet alle discount feedback
        $('.discountRange').each(function(i, e){
            $(e).prev('.discountRangeFeedback').html(e.value+'%');
        });
        
        //Klant wordt gekozen
        $(document).on('click', '#chooseCustomer', function(){
            
            var id = $('#customerSelect').val();
            $.getJSON("/service/person?id="+id, function(customer) {
                $('#name').val(customer.name);
                $('#adress').val(customer.adress);
                $('#zipcode').val(customer.zipcode);
                $('#place').val(customer.place);
                $('#customer').val(id);
            });
            
            return false;
        });
        
        //Als gebruiker stopt met typen gaan we zoeken
        var searchTimeout;
        var searchBox = document.getElementById('searchCustomer');
        searchBox.onkeydown = function () {
            if (searchTimeout != undefined) clearTimeout(searchTimeout);
            searchTimeout = setTimeout(getCustomers, 400);
        };
        
        function getCustomers() {
            $.getJSON("/service/persons?filter="+searchBox.value, function(customers) {
                var list = "";
                $.each(customers, function(i, customer){
                    list += '<option value="'+customer.id+'">'+customer.name+'</option>';
                });
                $("#customerSelect").html(list);
            });
        }

    });

    function silde(slider){
        $(slider).prev('.discountRangeFeedback').html(slider.value+'%');
        setPrice($(slider).parents('tr'));
    }
    
    function setPrice(tr){
        var price = tr.find('.price').val();
        var quantity = tr.find('.quantity').val();
        var discount = tr.find('.discountRange').val();
        
        //berekenen
        var total = (price * quantity * (1 - discount / 100));
        //afronden
        total = Math.round(total * 100) / 100;
        
        tr.find('.total').html("€ "+total);
    }

</script>
<form class="form" method="POST" action="./form" >
    <div class="tableWrap content">
        <div class="form-group">
            <label for="name">Zoek:</label>
            <input type="text" id="searchCustomer" />
            <select id="customerSelect">
                <c:forEach var="person" items="${requestScope.persons}">
                    <option value="${person.id}">${person.name}</option>
                </c:forEach>
            </select>
            <button id="chooseCustomer">Kies</button>
        </div>
        <hr>
        <small class="error">${requestScope.invoiceError}</small>
        <div class="form-group">
            <label for="name">Klant:</label>
            <input type="hidden" id="customer" name="customer" value="${invoice.customer.id}">
            <input type="hidden" id="id" name="id" value="${invoice.id}">
            <input disabled="" type="text" id="name" value="${invoice.customer.name}" />
            <small class="error">${requestScope.customerError}</small>
        </div>
        <div class="form-group">
            <label for="name">Adres:</label>
            <input disabled="" type="text" id="adress" value="${invoice.customer.adress}" />
        </div>
        <div class="form-group">
            <label for="name">Postcode:</label>
            <input disabled="" type="text" id="zipcode" value="${invoice.customer.zipcode}" />
        </div>
        <div class="form-group">
            <label for="name">Plaats:</label>
            <input disabled="" type="text" id="place" value="${invoice.customer.place}" />
        </div>
        <div class="form-group">
            <label for="name">BTW:</label>
            <input disabled="" type="text" name="tax" id="teax" value="${invoice.taxPercentage}%" />
            <small>Pas BTW precentage aan in de configuratie</small>
        </div>
        <hr>
        <table id="lines" class="full">
            <tr>
                <th>Beschrijving</th>
                <th style=" width: 120px; ">Stuk/uur prijs</th>
                <th style=" width: 120px; ">Aantal/Uren</th>
                <th style=" width: 202px; ">Korting in %</th>
                <th style=" width: 150px; ">Totaal</th>
                <th style=" width: 120px; "></th>
            </tr>
            <c:choose>
                <c:when test="${invoice.lineCount != 0}">
                    <c:set var="count" value="0" ></c:set>
                    <c:forEach var="line" items="${invoice.lines}">
                        <tr class="error">
                            <td><small class="error">${requestScope.errorList[count]['descriptionError']}</small></td>
                            <td><small class="error">${requestScope.errorList[count]['priceError']}</small></td>
                            <td><small class="error">${requestScope.errorList[count]['quantityError']}</small></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>
                                <input type="hidden" name="lineid" value="${line.id != null ? line.id : '0'}">
                                <input type="text" name="description" value="${line.description}" />
                            </td>
                            <td>
                                <input type="number" class="price" name="price" min="0" step="0.01" value="${line.price}" />
                            </td>
                            <td>
                                <input type="number" class="quantity" name="quantity" min="0" value="${line.quantity}">
                            </td>
                            <td>
                                <i class="discountRangeFeedback"></i><input oninput="silde(this);" class="discountRange" type="range" name="discount" min="0" value="${line.discount}" max="100">
                            </td>
                            <td class="total">
                                <fmt:formatNumber type="currency" value="${line.total}" currencyCode="EUR" currencySymbol="€" />
                            </td>
                            <td>
                                <c:if test="${count != 0}">
                                    <button class="removeLine">Verwijder</button>
                                </c:if>
                            </td>
                        </tr>
                        <c:set var="count" value="${count + 1}" ></c:set>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td>
                            <input type="hidden" name="lineid" value="0">
                            <input type="text" name="description" value="" />
                        </td>
                        <td>
                            <input type="number" class="price" name="price" step="0.01" min="0" >
                        </td>
                        <td>
                            <input type="number" class="quantity" name="quantity" min="0" value="1">
                        </td>
                        <td>
                            <i class="discountRangeFeedback"></i><input oninput="silde(this);" class="discountRange" type="range" name="discount" min="0" value="0" max="100">
                        </td>
                        <td class="total"></td>
                        <td></td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </table>
        <button id="addLine">add line</button>
    </div>
    <footer class="contentMenu">
        <button name="send" value="Opslaan">Opslaan</button>
    </footer>
</form>
<%@include file="/WEB-INF/view/cms/footer.jsp" %>