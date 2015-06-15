<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            lineTable.append('<tr> <td> <input type="text" name="description[]" value="" /> </td> <td> <input type="number" name="price[]" stepsize="0.01" min="0" value=""> </td> <td> <input type="number" name="quantity[]" min="0"> </td> <td> <i class="discountRangeFeedback"></i> <input class="discountRange" oninput="silde(this);" type="range" name="discount[]" min="0" value="0" max="100"> </td> <td></td> <td><button class="removeLine">Verwijder</button></td></tr>');

            return false;
        });
        
        //verwijder factuur lijn
        $(document).on('click', '.removeLine', function(){

            $(this).parents('tr').remove();

            return false;
        });
        
        //silde feedback voor range van discount control
        $(document).on('change', '.discountRange', function(){
            silde(this);
        });
        
        //Bij laden van pagina zet alle discount feedback
        $('.discountRange').each(function(i, e){
            $(e).prev('.discountRangeFeedback').html(e.value+'%');
        });
        
        //Klant wordt gekozen
        $(document).on('click', '#chooseCustomer', function(){
            
            var id = $('[name=customer]').val();
            $.getJSON("/service/person?id="+id, function(customer) {
                $('#name').val(customer.name);
                $('#adress').val(customer.adress);
                $('#zipcode').val(customer.zipcode);
                $('#place').val(customer.place);
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
                $("[name=customer]").html(list);
            });
        }

    });

    function silde(slider){
        $(slider).prev('.discountRangeFeedback').html(slider.value+'%');
    }

</script>
<form class="form" method="POST" action="./form" >
    <div class="tableWrap content">
        <div class="form-group">
            <label for="name">Zoek:</label>
            <input type="text" id="searchCustomer" />
            <select name="customer">
                <c:forEach var="person" items="${requestScope.persons}">
                    <option value="${person.id}">${person.name}</option>
                </c:forEach>
            </select>
            <button id="chooseCustomer">Kies</button>
        </div>
        <hr>
        <div class="form-group">
            <label for="name">Klant:</label>
            <input disabled="" type="text" id="name" value="${invoice.customer.name}" />
            <small class="error">${requestScope.customerError}</small>
        </div>
        <div class="form-group">
            <label for="name">Adres:</label>
            <input disabled="" type="text" id="adress" value="${invoice.customer.adress}" />
            <small class="error">${requestScope.nameError}</small>
        </div>
        <div class="form-group">
            <label for="name">Postcode:</label>
            <input disabled="" type="text" id="zipcode" value="${invoice.customer.zipcode}" />
            <small class="error">${requestScope.nameError}</small>
        </div>
        <div class="form-group">
            <label for="name">Plaats:</label>
            <input disabled="" type="text" id="place" value="${invoice.customer.place}" />
            <small class="error">${requestScope.nameError}</small>
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
                <th>Stuk/uur prijs</th>
                <th>Aantal/Uren</th>
                <th>Korting in %</th>
                <th>Totaal</th>
                <th></th>
            </tr>
            <c:choose>
                <c:when test="${invoice != null}">
                    <c:set var="count" value="0" ></c:set>
                    <c:forEach var="line" items="${invoice.lines}">
                        <tr>
                            <td>${requestScope.errorList[count]['descriptionError']}</td>
                            <td>${count}</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>
                                <input type="text" name="description[]" value="${line.description}" />
                            </td>
                            <td>
                                <input type="number" name="price[]" min="0" step="0.01" value="${line.price}" />
                            </td>
                            <td>
                                <input type="number" name="quantity[]" min="0" value="${line.quantity}">
                            </td>
                            <td>
                                <i class="discountRangeFeedback"></i> <input oninput="silde(this);" class="discountRange" type="range" name="discount[]" min="0" value="${line.discountPercentage}" max="100">
                            </td>
                            <td>${line.total}</td>
                            <td><button class="removeLine">Verwijder</button></td>
                        </tr>
                        <c:set var="count" value="${count + 1}" ></c:set>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td>
                            <input type="text" name="description[]" value="" />
                        </td>
                        <td>
                            <input type="number" name="price[]" min="0" value="">
                        </td>
                        <td>
                            <input type="number" name="quantity[]" min="0">
                        </td>
                        <td>
                            <i class="discountRangeFeedback"></i> <input oninput="silde(this);" class="discountRange" type="range" name="discount[]" min="0" value="0" max="100">
                        </td>
                        <td></td>
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