<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp"/>
<jsp:include page="../include/navbar.jsp"/>
<link href="../../../pub/css/search.css" rel="stylesheet">

<section class="vh-140 gradient-custom">

<h1><b>Clinician Search</b></h1>

<div class="formDiv">
<form action="/user/search" method="get">

    <div>
    <label for="clinician">Clinician:</label>
    <input type="text" name="searchLastName" id="clinician" placeholder = "Enter Last Name" value="${searchLastName}">
    </div>
    <div>
    <label for="identifier">Search by:</label>
    <select name="identifier" id="identifier">
        <option value="lastName">Last Name</option>
        <option value="Department">Department</option>
        <option value="Language">Language</option>
    </select>
    </div>
    <div>
    <button id ="searchId" type = "submit">Submit</button>
    </div>
</form>
</div>

<table class = "table">
    <tr>
<%--        <th>Id</th>--%>
        <th><strong>First Name</strong></th>
        <th><strong>Last Name</strong></th>
        <th><strong>Title</strong></th>
        <th><strong>Department</strong></th>
        <th><strong></em>Action</strong></th>
    </tr>
    <c:forEach items="${clinicians}" var="clinician" varStatus="status">
        <tr scope="row">
            <td>${users[status.index].firstName}</td>
            <td>${users[status.index].lastName}</td>
            <td>${clinician.title}</td>
            <td>${clinician.department}</td>
            <td><a href=/user/schedule_appointment">View Schedule</a></td>
        </tr>
    </c:forEach>
</table>
</section>


<jsp:include page="../include/footer.jsp"/>