<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp"/>
<jsp:include page="../include/navbar.jsp"/>

<title>Clinician Registration</title>
<link href="../../../pub/css/register_clinician.css" rel="stylesheet">

<body>
<section class="clinRegisterSection vh-140 gradient-custom">
    <div class="container py-3 h-120">
        <div class="row justify-content-center align-items-center h-120">
            <div class="col-5">
                <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
                    <div class="card-body p-4 p-md-5">
                        <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Clinician Registration</h3>
                        <form id="registerForm" action="/clinician/register_clinicianSubmit" class="needs-validation" novalidate>

                            <div class="col-md-12 mb-4">
                                <label class="form-label" for="firstName">First Name</label>
                                <input type="text" id="firstName" class="form-control form-control-lg" name="firstName" value="${form.firstName}"/>
                                <c:forEach items="${bindingResult.getFieldErrors('firstName')}" var="error">
                                    <div style="color: red;">
                                            ${error.getDefaultMessage()}
                                    </div>
                                </c:forEach>

                            </div>

                            <div class="col-md-12 mb-4">
                                <label class="form-label" for="lastName">Last Name</label>
                                <input type="text" id="lastName" class="form-control form-control-lg" name="lastName" value="${form.lastName}"/>
                                <c:forEach items="${bindingResult.getFieldErrors('lastName')}" var="error">
                                    <div style="color: red;">
                                            ${error.getDefaultMessage()}
                                    </div>
                                </c:forEach>
                            </div>

                            <div class="col-md-12 mb-4">
                                <label for="title">Title</label>
                                <select class="form-select mb-3" id="title" name="title" value="${form.title}">
                                    <option value="${form.title}">${form.title}</option>
                                    <c:forEach var="title" items="${titles}">
                                        <c:if test="${form.title != title}">
                                            <option value="${title}">${title}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                                <c:forEach items="${bindingResult.getFieldErrors('title')}" var="error">
                                    <div style="color: red;">
                                            ${error.getDefaultMessage()}
                                    </div>
                                </c:forEach>
                            </div>

                            <div class="col-md-12 mb-4">
                                <label for="department">Department</label>
                                <select class="form-select mb-3" id="department" name="department" value="${form.department}">
                                    <option value="${form.department}">${form.department}</option>
                                    <c:forEach var="department" items="${departments}">
                                        <c:if test="${form.department != department}">
                                            <option value="${department}">${department}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                                <c:forEach items="${bindingResult.getFieldErrors('department')}" var="error">
                                    <div style="color: red;">
                                            ${error.getDefaultMessage()}
                                    </div>
                                </c:forEach>
                            </div>

                            <div class="col-md-12 mb-4">
                                <label class="form-label" for="languages">Languages</label>
                                <input type="text" id="languages" class="form-control form-control-lg" name="languages" value="${form.languages}"/>
                                <c:forEach items="${bindingResult.getFieldErrors('languages')}" var="error">
                                    <div style="color: red;">
                                            ${error.getDefaultMessage()}
                                    </div>
                                </c:forEach>
                            </div>

                            <div class="col-md-12 mb-4">
                                <label class="email" for="email">Email</label>
                                <input type="text" id="email" class="form-control form-control-lg" name="email" value="${form.email}"/>
                                <c:forEach items="${bindingResult.getFieldErrors('email')}" var="error">
                                    <div style="color: red;">
                                            ${error.getDefaultMessage()}
                                    </div>
                                </c:forEach>
                            </div>

                            <div class="col-md-12 mb-4">
                                <label class="form-label" for="password">Password</label>
                                <input type="password" id="password" class="form-control form-control-lg" name="password" value="${form.password}"/>
                                <c:forEach items="${bindingResult.getFieldErrors('password')}" var="error">
                                    <div style="color: red;">
                                            ${error.getDefaultMessage()}
                                    </div>
                                </c:forEach>
                            </div>

                            <div class="col-md-12 mb-4">
                                <label class="form-label" for="confirmPassword">Confirm Password</label>
                                <input type="password" id="confirmPassword" class="form-control form-control-lg"
                                       onkeyup='check();' name="confirmPassword" value="${form.confirmPassword}"/>
                                <div class="invalid-feedback">
                                    Please provide a valid password.
                                </div>
                                <c:forEach items="${bindingResult.getFieldErrors('confirmPassword')}" var="error">
                                    <div style="color: red;">
                                            ${error.getDefaultMessage()}
                                    </div>
                                </c:forEach>
                            </div>

                            <div class="row">
                                <div class="col-md-3 mt-4 pt-2">
                                    <input class="btn btn-outline-primary btn-md" type="submit" value="Submit" />
                                </div>
                                <div class="col-md-3 mt-4 pt-2">
                                </div>
                            </div>
                            <div class="col-md-6 mt-4 pt-2" id="message"></div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>

<script>
    let check = function() {
        if (document.getElementById('password').value ==
            document.getElementById('confirmPassword').value) {
            document.getElementById('message').style.color = 'green';
            document.getElementById('message').innerHTML = 'Password confirmed';
        } else {
            document.getElementById('message').style.color = 'red';
            document.getElementById('message').innerHTML = 'Password does not match';
        }
    }
</script>

<script src= "../../../pub/js/register_clinician.js"></script>

<%--Old template code--%>
<%--<form action="/user/registerSubmit" method="post">--%>
<%--    <input type="hidden" name="id" value="${form.id}">--%>

<%--    Email <input type="email" name="email" id="emailId" value="${form.email}">--%>
<%--    <br>--%>
<%--    First Name <input type="text" name="firstName" id="firstNameId" value="${form.firstName}">--%>
<%--    <br>--%>
<%--    Last Name <input type="text" name="lastName" id="lastNameId" value="${form.lastName}">--%>
<%--    <br>--%>
<%--    Password <input type="password" name="password" id="passwordId" value="${form.password}">--%>
<%--    <br>--%>
<%--    Confirm Password <input type="password" name="confirmPassword" id="confirmPasswordId" value="${form.confirmPassword}">--%>
<%--    <br>--%>
<%--    <button type="submit">Submit</button>--%>
<%--</form>--%>
<jsp:include page="../include/footer.jsp"/>
