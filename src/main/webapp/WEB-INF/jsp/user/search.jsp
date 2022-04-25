<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp"/>
<jsp:include page="../include/navbar.jsp"/>
<link href="../../../pub/css/search.css" rel="stylesheet">

<section class="searchSection vh-140 gradient-custom">

    <h1><b>Clinician Search</b></h1>

    <div class="formDiv">
        <form class= "searchForm" action="/user/search" method="get">

            <div>
                <label for="clinician">Search</label>
                <input type="text" name="searchEntry" id="clinician" class="form-control form-control-md mb-3"
                       placeholder="Name, Dept, or Language" value="${searchEntry}">
            </div>

            <div id="identifier">
                <label for="searchType">By</label>
                <select class="form-select mb-3" id="searchType" name="searchType" value="${searchType}">
                    <option value="lastName">Last Name</option>
                    <option value="department">Department</option>
                    <option value="language">Language</option>
                </select>
            </div>

            <div class="button">
                <button id ="searchId" class="btn btn-outline-primary btn-md" type="submit">Submit</button>
            </div>
        </form>
    </div>

    <table class = "results table">
        <tr>
            <th><strong>First Name</strong></th>
            <th><strong>Last Name</strong></th>
            <th><strong>Title</strong></th>
            <th><strong>Department</strong></th>
            <th><strong>Language</strong></th>
        </tr>
        <c:forEach items="${clinicians}" var="clinician" varStatus="status">
            <tr>
                <td>${users[status.index].firstName}</td>
                <td>${users[status.index].lastName}</td>
                <td>${clinician.title}</td>
                <td>${clinician.department}</td>
                <td>${clinician.languages}</td>
            </tr>
        </c:forEach>
    </table>
</section>


<jsp:include page="../include/footer.jsp"/>