<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp"/>
<jsp:include page="../include/navbar.jsp"/>
<link href="../../../pub/css/search.css" rel="stylesheet">

<section class="vh-140 gradient-custom">
<form action="/user/search" method="get">

<input type="text" name="searchLastName" id="searchLastName" placeholder = "Enter Last Name" value="${searchLastName}">
<br>
    <button id ="searchId" type = "submit">Submit</button>
</form>
<h1>Clinician Search</h1>

<table class = "table">
    <tr>
<%--        <th>Id</th>--%>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Title</th>
        <th>Department</th>
        <th>Edit</th>
    </tr>
    <c:forEach items="${clinicians}" var="clinician" varStatus="status">
        <tr scope="row">
<%--            <td>${clinician.clinicianId}</td>--%>
            <td>${users[status.index].firstName}</td>
            <td>${users[status.index].lastName}</td>
<%--            <td>${clinician.firstName}</td>--%>
<%--            <td>${clinician.lastName}</td>--%>
            <td>${clinician.title}</td>
            <td>${clinician.department}</td>
            <td><a href=/user/edit/${user.id}">Edit</a></td>
        </tr>
    </c:forEach>
</table>
</section>


<jsp:include page="../include/footer.jsp"/>