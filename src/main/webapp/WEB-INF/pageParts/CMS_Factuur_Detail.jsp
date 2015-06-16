<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/view/cms/header.jsp">
    <jsp:param name="title" value="Factuur detail" />
</jsp:include>
<jsp:include page="/WEB-INF/view/pdf/Factuur.jsp">
    <jsp:param name="print" value="no" />
</jsp:include>
<%@include file="/WEB-INF/view/cms/footer.jsp" %>