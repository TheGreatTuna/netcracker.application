<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Create New Event </title>
    <link href="${contextPath}/resources/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom fonts for this template-->
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<div class="row">
    <jsp:include page="${contextPath}/WEB-INF/views/account/navbar/navbar.jsp"/>
    <div class="col-md-3"
    <jsp:include page="${contextPath}/WEB-INF/views/account/menu/menu.jsp"/>
</div>


<div class="col-md-9 content">
    <div class="card card-register">
        <div class="card-header">Update Event</div>
        <div class="card-body">
            <form:form method="POST" modelAttribute="editEvent" class="forms_form" enctype="multipart/form-data">
                <div class="col-md-6">
                    <div class="form-group">
                        <label>Photo: </label>
                        <img class="img-circle" style="width: 200px;height: 200px"
                             src="<c:url value="/account/image/${editEvent.photo}.jpg"/>">
                        <input type="hidden" name="photo" value="${editEvent.photo}">
                        <br><span class="btn btn-default btn-file">
                            Browse <input type="file" name="photoFile" accept="image/*">
                            </span>
                        <span class="has-error">${message}</span>
                        <form:errors path="name" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <label>Event Name: </label>
                        <form:input path="name" id="name" type="text" class="form-control"
                                    placeholder="Enter event name"/>
                        <form:errors path="name" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <label>Description: </label>
                        <form:input path="description" id="description" type="text" class="form-control"
                                    placeholder="Enter event description"/>
                        <form:errors path="description" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <label>Start_date: </label>
                        <form:input path="dateStart" id="dateStart" type="date" class="form-control dateValid"
                                    placeholder="Enter event start date"/>
                        <form:errors path="dateStart" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <label>End_date: </label>
                        <form:input path="dateEnd" id="dateEnd" type="date" class="form-control dateValid"
                                    placeholder="Enter event end date"/>
                        <form:errors path="dateEnd" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <label>Event type: </label>
                        <form:select path="type" class="form-control">
                            <form:options items="${eventTypes}" itemValue="typeId" itemLabel="name"/>
                        </form:select>
                    </div>
                </div>
                <div class="col-md-6">
                    <form:input path="width" type="hidden" id="latitude"></form:input>
                    <form:input path="longitude" type="hidden" id="longitude"></form:input>
                    <div class="form-group">
                        <label>Event place</label>
                        <form:input path="eventPlaceName" id="eventPlaceName" type="text" class="form-control"/>
                        <div id="map"></div>
                        <script src='${contextPath}/resources/js/pamCode.js' async defer></script>
                        <script async defer
                                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAFJb-oxFvvvPRvwubCZwYkPQC0rRUbtOM&callback=initMap&language=en">
                        </script>
                    </div>
                </div>
                <input type="submit" value="Update" class="btn btn-dark text-center"/>
            </form:form>
        </div>
    </div>
</div>

</html>

