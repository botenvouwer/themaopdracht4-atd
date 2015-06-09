<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/cms/header.jsp">
    <jsp:param name="title" value="Factuur Aanmaken" />
</jsp:include>
<%@ page pageEncoding="UTF-8" %>
<form class="form" method="POST" action="./form" >
    <div class="tableWrap content">
        <div class="form-group">
            <label for="name">Klant:</label>
            <input type="text" name="name" id="name" value="${person.name}" />
            <small class="error">${requestScope.nameError}</small>
        </div>
        <div class="form-group">
            <label for="name">Tax:</label>
            <input type="text" name="name" id="name" value="${person.name}" />
            <small class="error">${requestScope.nameError}</small>
        </div>
        <div class="form-group">
            <label for="name">Naam:</label>
            <input type="text" name="name" id="name" value="${person.name}" />
            <small class="error">${requestScope.nameError}</small>
        </div>
        <div class="form-group">
            <label for="name">Naam:</label>
            <input type="text" name="name" id="name" value="${person.name}" />
            <small class="error">${requestScope.nameError}</small>
        </div>
        <hr>
        <script>
            $(document).ready(function(){
                
                window.lineTable = $('#lines');
                $('#addLine').on('click', function(){
                    lineTable.append('<tr> <td> <input type="text" name="description[]" value="" /> </td> <td> <input type="number" name="price[]" min="0" value=""> </td> <td> <input type="number" name="quantity[]" min="0"> </td> <td> <i class="discountRangeFeedback">%0</i> <input class="discountRange" oninput="silde(this);" type="range" name="discount[]" min="0" value="0" max="100"> </td> <td></td> <td><button class="removeLine">Verwijder</button></td></tr>');
                    
                    return false;
                });
                
                $(document).on('click', '.removeLine', function(){
                    
                    $(this).parents('tr').remove();
                    
                    return false;
                });
                
                $(document).on('change', '.discountRange', function(){
                    silde(this);
                });
                
                
                
            });
            
            function silde(slider){
                $(slider).prev('.discountRangeFeedback').html('%'+slider.value);
            }
            
        </script>
        <table id="lines" class="full">
            <tr>
                <th>Beschrijving</th>
                <th>Stuk/uur prijs</th>
                <th>Aantal/Uren</th>
                <th>Korting in %</th>
                <th>Totaal</th>
                <th></th>
            </tr>
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
                    <i class="discountRangeFeedback">%0</i> <input oninput="silde(this);" class="discountRange" type="range" name="discount[]" min="0" value="0" max="100">
                </td>
                <td></td>
                <td></td>
            </tr>
        </table>
        <button id="addLine">add line</button>
    </div>
    <footer class="contentMenu">
        <button name="send" value="Opslaan">Opslaan</button>
    </footer>
</form>
<%@include file="/WEB-INF/view/cms/footer.jsp" %>