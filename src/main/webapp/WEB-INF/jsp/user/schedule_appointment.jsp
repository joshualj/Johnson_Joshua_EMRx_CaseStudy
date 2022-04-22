<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp"/>
<jsp:include page="../include/navbar.jsp"/>
<link href="../../../pub/css/schedule_appointment.css" rel="stylesheet">
<body>
    <section class="appointment-cont vh-120 gradient-custom">
        <div class="physDate">
            <form class="physDateForm row justify-content-center align-items-center" action="/user/schedule_appointment">
                <label for="userId">Select a Clinician</label>
                <select id="userId" class="form-control-md mb-3" name="userId" value="${clinUser.userId}" required>
                    <option value="${clinUser.userId}">${clinUser.firstName} ${clinUser.lastName}</option>
                    <c:forEach var="clinician" items="${clinicianUsers}">
                        <c:if test="${clinUser.userId != clinician.userId}">
                            <option value="${clinician.userId}">${clinician.firstName} ${clinician.lastName}</option>
                        </c:if>
                    </c:forEach>
                </select>
                <hr>
                <label for="date">Select a Date</label>
                <input type="date" id="date" name="date" placeholder="Date" class="form-control-md mb-3" value="${localDate}">
                <hr>
                <%--            <select class="selectYear form-control-md" name="year" value="${form.year}" required>--%>
                <input class="btn btn-outline-primary btn-md" type="submit" value="Submit">
            </form>
        </div>

        <div id="schedule" class="row justify-content-center align-items-center h-100">
            <div id="scheduleHeader">
            <h1 name="date" value="${localDate}">${dayOfWeek}, ${monthName} ${dayDate}, ${yearDate}</h1>
            <h5 name="clinUser" value="${clinUser}">Dr. ${clinUser.firstName} ${clinUser.lastName}</h5>
            </div>
            <hr>
            <div class="col-12">
                <div class="card shadow-2-strong" style="border-radius: 15px">
                    <div style="line-height: 2.0">
                        <table id="appointmentsTable" class="table table-striped">
                            <thead class="table-dark">
                            <tr>
                                <th style="border-top-left-radius: 15px"><b>Appointment Time</b></th>
                                <th style="border-top-right-radius: 15px"><b>Availability</b></th>
                            </tr>
                            </thead>
                            <c:forEach var="apptTime" items="${appointmentTimes}">
                                <tr>
                                    <td><b>${apptTime}</b></td>
                                    <td>
                                        <c:if test="${scheduledTime.contains(apptTime)}">N/A</c:if>
                                        <c:if test="${!scheduledTime.contains(apptTime)}">
                                            <form action="/user/schedule_appointmentSubmit" method="post">
                                                <input type="hidden" name="date" value="${localDate}">
                                                <input type="hidden" name="time" value="${apptTime}">
                                                <input type="hidden" name="userId" value="${clinUser.userId}">
                                                <button type="submit" class="btn btn-outline-primary btn-md">Schedule</button>
                                            </form>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </section>
</body>

<jsp:include page="../include/footer.jsp"/>

