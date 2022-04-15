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
                            <th><b>Chief Complaint</b></th>
                            <th><b>PAQ</b></th>
                            <th><b>Edit Appt</b></th>
                            <th style="border-top-right-radius: 15px"><b>Cancel</b></th>
                        </tr>
                        </thead>
                        <c:forEach var="appt" items="${appointments}" varStatus="status">
                            <tr>
                                <td><b>${appt.date}</b></td>
                                <td><b>${appt.time}</b></td>
                                <td><b>${users[status.index].firstName} ${users[status.index].lastName}</b></td>
                                <td><b>${patients[status.index].birthDate}</b></td>
                                <td><b>${patients[status.index].sex}/${patients[status.index].gender}</b></td>
                                <td><b>${appt.chiefComplaint}</b></td>
                                <td>
                                    <c:if test="${!empty appt.paqId}">
                                        <form action="/clinician/my_clinician_schedule/${appt.appointmentId}" method="GET">
                                            <input type="hidden" name="date" value="${appt.date}">
                                            <input type="hidden" name="time" value="${appt.time}">
                                            <input type="hidden" name="clinicianId" value="${appt.clinician.clinicianId}">
                                            <button type="submit">View</button>
                                        </form>
                                    </c:if>
                                    <c:if test="${empty appt.paqId}">N/A</c:if>
                                </td>
                                <td>
                                    <form action=/clinician/my_clinician_schedule/edit/${appt.appointmentId}" method="POST">
                                        <input type="hidden" name="apptId" value="${appt.appointmentId}">
                                        <button type="submit">Edit</button>
                                    </form>
                                </td>
                                <td>
                                    <form action=/clinician/my_clinician_schedule/cancel/${appt.appointmentId}" method="POST">
                                        <input type="hidden" name="apptId" value="${appt.appointmentId}">
                                        <button type="submit">Cancel</button>
                                    </form>
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
<%--<script src= "../../../pub/js/schedule_appointment.js"></script>--%>
