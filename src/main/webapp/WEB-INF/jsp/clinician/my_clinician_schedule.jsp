<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp"/>
<jsp:include page="../include/navbar.jsp"/>
<link href="../../../pub/css/my_schedule.css" rel="stylesheet">
<body>
<section class="vh-120 gradient-custom">
<%--    <div class="row justify-content-center align-items-center">--%>
<%--    <form class="appointmentDaySelect" action="/user/my_schedule">--%>
<%--        <input type="text" id="userId" class="form-control form-control-lg" name="userId" value="${form.userId}">--%>
<%--        <input class="btn btn-outline-primary btn-md" type="submit" value="Submit" />--%>
<%--    </form>--%>
    <div class="row justify-content-center align-items-center h-100">
        <h1 id="name" name="user" value="${user}">
            <c:if test="${!empty user.firstName}">${user.firstName}'s Appointments</c:if>
            <c:if test="${empty user.firstName}">My Appointments</c:if>
        </h1>
        <div class="col-8">
            <div class="card shadow-2-strong" style="border-radius: 15px">
                <div style="line-height: 2.0">
                    <table id="appointmentsTable" class="table table-striped">
                        <thead class="table-dark">
                        <tr>
                            <th style="border-top-left-radius: 15px"><b>Date</b></th>
                            <th><b>Time</b></th>
                            <th><b>Patient</b></th>
                            <th><b>Birthdate</b></th>
                            <th><b>Sex/Gender</b></th>
                            <th><b>Complaint</b></th>
                            <th><b>PAQ</b></th>
                            <th><b>Options</b></th>
                            <th style="border-top-right-radius: 15px"><b></b></th>
                        </tr>
                        </thead>
                        <c:forEach var="appt" items="${appointmentTimes}"> <%-- varStatus="status"> --%>
                            <tr>
                                <td><b>${localDate}</b></td>
                                <td><b>${appt}</b></td>
                                <c:if test="${scheduledTime.contains(appt)}">
                                    <td><b>${users[scheduledTime.indexOf(appt)].firstName} ${users[scheduledTime.indexOf(appt)].lastName}</b></td>
                                    <td><b>${patients[scheduledTime.indexOf(appt)].birthDate}</b></td>
                                    <td><b>${patients[scheduledTime.indexOf(appt)].sex}/${patients[scheduledTime.indexOf(appt)].gender}</b></td>
                                    <td><b>${appointments[scheduledTime.indexOf(appt)].chiefComplaint}</b></td>
                                    <c:if test="${!empty appointments[scheduledTime.indexOf(appt)].paqId}">
                                        <td>
                                        <form action="/clinician/my_clinician_schedule/${appointments[scheduledTime.indexOf(appt)].appointmentId}" method="GET">
                                            <input type="hidden" name="date" value="${appointments[scheduledTime.indexOf(appt)].date}">
                                            <input type="hidden" name="time" value="${appointments[scheduledTime.indexOf(appt)].time}">
                                            <input type="hidden" name="clinicianId" value="${appointments[scheduledTime.indexOf(appt)].clinician.clinicianId}">
                                            <button type="submit">View</button>
                                        </form>
                                        </td>
                                    </c:if>
                                    <c:if test="${empty appointments[scheduledTime.indexOf(appt)].paqId}">
                                        <td>N/A</td>
                                    </c:if>
                                    <td>
                                        <form action="/clinician/my_clinician_schedule/edit/${appointments[scheduledTime.indexOf(appt)].appointmentId}" method="GET">
                                            <input type="hidden" name="apptId" value="${appointments[scheduledTime.indexOf(appt)].appointmentId}">
                                            <button type="submit">Edit</button>
                                        </form>
                                    </td>
                                    <td>
                                        <form action=/clinician/my_clinician_schedule/cancel/${appointments[scheduledTime.indexOf(appt)].appointmentId}" method="GET">
                                            <input type="hidden" name="apptId" value="${appointments[scheduledTime.indexOf(appt)].appointmentId}">
                                            <button type="submit">Cancel</button>
                                        </form>
                                    </td>
                                </c:if>
                                <c:if test="${!scheduledTime.contains(appt)}">
                                    <td><b></b></td>
                                    <td><b></b></td>
                                    <td><b></b></td>
                                    <td><b></b></td>
                                    <td><b> </b></td>
                                    <td><b> </b></td>
                                    <td><b> </b></td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
<%--<script src= "../../../pub/js/schedule_appointment.js"></script>--%>
