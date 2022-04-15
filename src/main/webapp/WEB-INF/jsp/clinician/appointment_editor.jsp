<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp"/>
<jsp:include page="../include/navbar.jsp"/>

<title>Appointment Editor</title>
<link href="../../../pub/css/register.css" rel="stylesheet">

<body>
<section class="vh-140 gradient-custom">
    <div class="container py-3 h-120">
        <div class="row justify-content-center align-items-center h-120">
            <div class="col-5">
                <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
                    <div class="card-body p-4 p-md-5">
                        <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Appointment Editor</h3>
                        <form id="registerForm" action="/clinician/my_clinician_scheduleSubmit/${appointmentId}" method="POST" class="needs-validation" novalidate>
<%--                            <input type="hidden" name="id" value="${form.id}">--%>
                            <div class="col-md-12 mb-4">
                                <input type="date" id="date" class="form-control form-control-lg" name="date" value="${form.date}" required/>
                                <label class="form-label" for="date">Date</label>
                                <div class="invalid-feedback">
                                    Please provide a valid date.
                                </div>
                            </div>

                            <div class="col-md-12 mb-4">
                                <input type="time" id="time" class="form-control form-control-lg" name="time" value="${form.time}" required/>
                                <label class="form-label" for="time">Last Name</label>
                                <div class="invalid-feedback">
                                    Please provide a valid time.
                                </div>
                            </div>

                            <div class="col-md-12 mb-4">
                                <input type="text" id="clinicianId" class="form-control form-control-lg" name="clinicianId" value="${form.clinicianId}" required/>
                                <label class="email" for="clinicianId">Clinician Id</label>
                                <div class="invalid-feedback">
                                    Please provide a clinicianId.
                                </div>
                            </div>

                            <div class="col-md-12 mb-4">
                                <input type="text" id="patientId" class="form-control form-control-lg" name="patientId" value="${form.patientId}" required/>
                                <label class="form-label" for="patientId">Patient Id</label>
                                <div class="invalid-feedback">
                                    Please provide a valid password.
                                </div>
                            </div>

                            <div class="col-md-12 mb-4">
                                <input type="text" id="chiefComplaint" class="form-control form-control-lg" name="chiefComplaint" value="${form.chiefComplaint}"/>
                                <label class="form-label" for="chiefComplaint">Chief Complaint</label>
                                <div class="invalid-feedback">
                                    Please provide a chief complaint.
                                </div>
                            </div>

                            <div class="col-md-12 mb-4">
                                <input type="text" id="paqId" class="form-control form-control-lg" name="paqId" value="${form.paqId}"/>
                                <label class="form-label" for="paqId">Pre-Appointment Questionnaire Id</label>
                                <div class="invalid-feedback">
                                    Please provide a PAQ Id.
                                </div>
                            </div>
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
                                    <input class="btn btn-outline-primary btn-md" type="submit" value="Submit"/>
                                </div>
                                <div class="col-md-3 mt-4 pt-2">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>

<%--<script>--%>
<%--    let check = function() {--%>
<%--        if (document.getElementById('password').value ==--%>
<%--            document.getElementById('confirmPassword').value) {--%>
<%--            document.getElementById('message').style.color = 'green';--%>
<%--            document.getElementById('message').innerHTML = 'Password confirmed';--%>
<%--        } else {--%>
<%--            document.getElementById('message').style.color = 'red';--%>
<%--            document.getElementById('message').innerHTML = 'Password does not match';--%>
<%--        }--%>
<%--    }--%>
<%--</script>--%>

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
