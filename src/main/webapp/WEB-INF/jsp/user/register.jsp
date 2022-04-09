<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp"/>
<jsp:include page="../include/navbar.jsp"/>

<title>User Registration</title>
<link href="../../../pub/css/register.css" rel="stylesheet">

<%--<c:if test="${form.userId == null}">--%>
<%--    <h1>Sign Up</h1>--%>
<%--</c:if>--%>
<%--<c:if test="${form.userId != null}">--%>
<%--    <h1>Edit User</h1>--%>
<%--</c:if>--%>

<body>
<section class="vh-140 gradient-custom">
    <div class="container py-3 h-120">
        <div class="row justify-content-center align-items-center h-120">
            <div class="col-5">
                <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
                    <div class="card-body p-4 p-md-5">
                        <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">User Registration</h3>
                        <form id="registerForm" action="/user/registerSubmit" class="needs-validation" novalidate>
                            <input type="hidden" name="id" value="${form.id}">
                            <div class="col-md-12 mb-4">
                                <input type="text" id="firstName" class="form-control form-control-lg" name="firstName" value="${form.firstName}" required/>
                                <label class="form-label" for="firstName">First Name</label>
                                <div class="invalid-feedback">
                                    Please provide a valid first name.
                                </div>
                            </div>

                            <div class="col-md-12 mb-4">
                                <input type="text" id="lastName" class="form-control form-control-lg" name="lastName" value="${form.lastName}" required/>
                                <label class="form-label" for="lastName">Last Name</label>
                                <div class="invalid-feedback">
                                    Please provide a valid last name.
                                </div>
                            </div>

                            <div class="col-md-12 mb-4">
                                <input type="text" id="email" class="form-control form-control-lg" name="email" value="${form.email}" required/>
                                <label class="email" for="email">Email</label>
                                <div class="invalid-feedback">
                                    Please provide a valid email.
                                </div>
                            </div>

                            <div class="col-md-12 mb-4">
                                <input type="password" id="password" class="form-control form-control-lg" name="password" value="${form.password}" required/>
                                <label class="form-label" for="password">Password</label>
                                <div class="invalid-feedback">
                                    Please provide a valid password.
                                </div>
                            </div>

                            <div class="col-md-12 mb-4">
                                <input type="password" id="confirmPassword" class="form-control form-control-lg"
                                       onkeyup='check();' name="confirmPassword" value="${form.confirmPassword}"required/>
                                <label class="form-label" for="confirmPassword">Confirm Password</label>
                                <div class="invalid-feedback">
                                    Please provide a valid password.
                                </div>
                            </div>

<%--                            <div class="col-md-12 mb-4">--%>
<%--                                <h6 class="mb-2 pb-1">User Type:</h6>--%>
<%--                                <div class="form-check form-check-inline">--%>
<%--                                    <input class="form-check-input" type="radio" name="inlineRadioOptions"--%>
<%--                                           id="admin" value="option1"/>--%>
<%--                                    <label class="form-check-label" for="admin">Admin</label>--%>
<%--                                </div>--%>

<%--                                <div class="form-check form-check-inline">--%>
<%--                                    <input--%>
<%--                                            class="form-check-input" type="radio" name="inlineRadioOptions"--%>
<%--                                            id="user" value="option2" checked />--%>
<%--                                    <label class="form-check-label" for="user">User</label>--%>
<%--                                </div>--%>
<%--                            </div>--%>

<%--                            <div class="col-6">--%>
<%--                                <select class="select form-control-md">--%>
<%--                                    <option value="1" disabled>Choose option</option>--%>
<%--                                    <option value="2">Patient</option>--%>
<%--                                    <option value="3">Clinician</option>--%>
<%--                                    <option value="4">Administrator</option>--%>
<%--                                </select>--%>
<%--                                <label class="form-label select-label">Choose User Type</label>--%>
<%--                            </div>--%>

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

<script src= "../../../pub/js/register.js"></script>

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
