<%--
  Created by IntelliJ IDEA.
  User: anyat
  Date: 02.05.2018
  Time: 23:47
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Drafts</title>
    <link href="${contextPath}/resources/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
    <script src="${contextPath}/resources/vendor/bootstrap/js/jquery-1.11.1.min.js"></script>
</head>
<body>
<div class="row">
    <jsp:include page="${contextPath}/WEB-INF/views/account/navbar/navbar.jsp"/>
    <div class="col-md-2" style="height:100vh;">
        <jsp:include page="${contextPath}/WEB-INF/views/account/menu/menu.jsp"/>
    </div>

    <div class="col-md-10 content">
        <jsp:include page="${contextPath}/WEB-INF/views/account/notification.jsp"></jsp:include>
        <p>
            <a class="btn btn-primary" href="/account/available" role="button">All events</a>
            <a class="btn btn-primary" href="/account/subscriptions" role="button">Subscriptions</a>
            <a class="btn btn-primary" href="/account/managed" role="button">Managed events</a>
            <a class="btn btn-primary" href="/account/draft" role="button">Drafts</a>
            <sec:authorize access="hasRole('USER')">
                <a class="btn btn-success" href="<c:url value='/account/eventList/createNewEvent' />">Add new event</a>
            </sec:authorize>
        </p>
        <h3>${message}</h3>
        <%--<div class="row">--%>
        <%--<table class="table">--%>
        <%--<c:forEach var="draft" items="${draftList}">--%>
        <%--<tbody>--%>
        <%--<tr>--%>
        <%--<td> <a href="/account/eventList/event-${draft.eventId}"> ${draft.name} </a></td>--%>
        <%--<td> Description : ${draft.description} </td>--%>
        <%--</tr>--%>
        <%--</tbody>--%>
        <%--</c:forEach>--%>

        <%--</table>--%>
        <%--</div>--%>

        <div class="row">
            <table class="table">
                <c:forEach var="draft" items="${draftList}">
                    <a class="notes-item" href="<c:url value='/account/eventList/event-${draft.eventId}'/>">
                        <img src="${contextPath}/resources/img/draftExample.png" alt="notepad">
                        <ul class="list-unstyled mt-3 mb-4 eventCardItem notesEventCardItem">
                            <li>${draft.name}</li>
                        </ul>
                    </a>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>