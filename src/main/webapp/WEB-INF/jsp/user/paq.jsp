<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp"/>
<jsp:include page="../include/navbar.jsp"/>

<title>Paq</title>
<link href="../../../pub/css/paq.css" rel="stylesheet">

<body>
<section class="vh-140 gradient-custom">
    <div class="container py-3 h-120">
        <div class="row justify-content-center align-items-center h-120">
            <div class="col-5">
                <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
                    <div class="card-body p-4 p-md-5">
                        <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Pre-Appointment Questions</h3>
                        <form id="paqForm" action="/user/paqSubmit/${appointmentId}" method="post" class="needs-validation" novalidate>
<%--                            <input type="hidden" name="id" value="${form.id}">--%>
<%--                            <div class="col-md-12 mb-4">--%>
<%--                                <input type="text" id="apptId" class="form-control form-control-lg" name="apptId" value="${form.apptId}" required/>--%>
<%--                                <label class="form-label" for="apptId">Enter your medical appointment ID.</label>--%>
<%--                                <div class="invalid-feedback">--%>
<%--                                    Please provide a valid appointment ID.--%>
<%--                                </div>--%>
<%--                            </div>--%>
                            <div class="col-md-12 mb-4">
                                <input type="text" id="complaint" class="form-control form-control-lg" name="complaint" value="${form.complaint}"/>
                                <c:forEach items="${bindingResult.getFieldErrors('complaint')}" var="error">
                                    <div style="color: red;">
                                            ${error.getDefaultMessage()}
                                    </div>
                                </c:forEach>
                                <label class="form-label" for="complaint">Enter your medical concern.<br>(example: headache)</label>
                            </div>
                            <div class="col-md-12 mb-4">
                                <input type="text" id="onset" class="form-control form-control-lg" name="onset" value="${form.onset}"/>
                                <c:forEach items="${bindingResult.getFieldErrors('onset')}" var="error">
                                    <div style="color: red;">
                                            ${error.getDefaultMessage()}
                                    </div>
                                </c:forEach>
                                <label class="form-label" for="onset">Enter the date you first experienced your concern.<br>(example: 2022-04-21)</label>
                            </div>
                            <div class="col-md-12 mb-4">
                                <input type="text" id="location" class="form-control form-control-lg" name="location" value="${form.location}"/>
                                <c:forEach items="${bindingResult.getFieldErrors('location')}" var="error">
                                    <div style="color: red;">
                                            ${error.getDefaultMessage()}
                                    </div>
                                </c:forEach>
                                <label class="form-label" for="location">Enter the location of your concern.<br>(example: left side of chest)</label>
                            </div>
                            <div class="col-md-12 mb-4">
                                <input type="text" id="duration" class="form-control form-control-lg" name="duration" value="${form.duration}" required/>
                                <c:forEach items="${bindingResult.getFieldErrors('duration')}" var="error">
                                    <div style="color: red;">
                                            ${error.getDefaultMessage()}
                                    </div>
                                </c:forEach>
                                <label class="form-label" for="duration">Enter how long your medical concern lasts, when present.<br>(examples: 10 minutes, always)</label>
                            </div>

                            <div class="col-md-12 mb-4">
                                <input type="text" id="description" class="form-control form-control-lg" name="description" value="${form.description}" required/>
                                <c:forEach items="${bindingResult.getFieldErrors('description')}" var="error">
                                    <div style="color: red;">
                                            ${error.getDefaultMessage()}
                                    </div>
                                </c:forEach>
                                <label class="form-label" for="alleviating">Enter characteristics of your concern.<br>(examples: dull, sharp)</label>
                            </div>
                            <div class="col-md-12 mb-4">
                                <input type="text" id="alleviating" class="form-control form-control-lg" name="alleviating" value="${form.alleviating}" required/>
                                <c:forEach items="${bindingResult.getFieldErrors('alleviating')}" var="error">
                                    <div style="color: red;">
                                            ${error.getDefaultMessage()}
                                    </div>
                                </c:forEach>
                                <label class="form-label" for="alleviating">List any medications or actions that relieve your concern.<br>(examples: heat-pad, tylenol)</label>
                            </div>
                            <div class="col-md-12 mb-4">
                                <input type="text" id="radiation" class="form-control form-control-lg" name="radiation" value="${form.radiation}" required/>
                                <c:forEach items="${bindingResult.getFieldErrors('radiation')}" var="error">
                                    <div style="color: red;">
                                            ${error.getDefaultMessage()}
                                    </div>
                                </c:forEach>
                                <label class="form-label" for="radiation">List any locations your pain travels to.<br>(examples: left foot, lower back)</label>
                            </div>
                            <div class="col-md-12 mb-4">
                                <input type="text" id="temporalPatterns" class="form-control form-control-lg" name="temporalPatterns" value="${form.temporalPatterns}" required/>
                                <c:forEach items="${bindingResult.getFieldErrors('temporalPatterns')}" var="error">
                                    <div style="color: red;">
                                            ${error.getDefaultMessage()}
                                    </div>
                                </c:forEach>
                                <label class="form-label" for="temporalPatterns">Enter how often you experience your concern.<br>(examples: always, once daily, randomly)</label>
                            </div>

                            <div class="col-md-12 mb-4">
                                <input type="text" id="symptoms" class="form-control form-control-lg" name="symptoms" value="${form.symptoms}" required/>
                                <c:forEach items="${bindingResult.getFieldErrors('symptoms')}" var="error">
                                    <div style="color: red;">
                                            ${error.getDefaultMessage()}
                                    </div>
                                </c:forEach>
                                <label class="form-label" for="symptoms">List your symptoms.<br>(examples: dizziness, fatigue)</label>
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

<jsp:include page="../include/footer.jsp"/>
