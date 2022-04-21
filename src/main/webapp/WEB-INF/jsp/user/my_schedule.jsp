<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp"/>
<jsp:include page="../include/navbar.jsp"/>
<link href="../../../pub/css/my_schedule.css" rel="stylesheet">
<style>
    h1{
        text-align: center;
        padding-bottom: 20px;
    }
</style>
<body id="appointments">
    <section class="myPatientSched vh-120 gradient-custom">
    <%--    <div class="row justify-content-center align-items-center">--%>
    <%--    <form class="appointmentDaySelect" action="/user/my_schedule">--%>
    <%--        <input type="text" id="userId" class="form-control form-control-lg" name="userId" value="${form.userId}">--%>
    <%--        <input class="btn btn-outline-primary btn-md" type="submit" value="Submit" />--%>
    <%--    </form>--%>
        <div class="row justify-content-center align-items-center">
            <h1 id="name" name="user" value="${user}">
                <c:if test="${!empty user.firstName}">${user.firstName}'s Appointments</c:if>
                <c:if test="${empty user.firstName}">My Appointments</c:if>
            </h1>
            <div class="col-5">
                <div class="card shadow-2-strong" style="border-radius: 15px">
                    <div style="line-height: 2.0">
                        <table id="appointmentsTable" class="table table-striped">
                            <thead class="table-dark">
                            <tr>
                                <th style="border-top-left-radius: 15px"><b>Date</b></th>
                                <th><b>Time</b></th>
                                <th><b>Clinician</b></th>
                                <th style="border-top-right-radius: 15px"><b>Questionnaire</b></th>
                            </tr>
                            </thead>
                            <c:forEach var="appt" items="${appointments}">
                                <tr>
                                    <td><b>${appt.date}</b></td>
                                    <td><b>${appt.time}</b></td>
                                    <td><b>${appt.clinician.clinicianId}</b></td>
                                    <td>
                                        <c:if test="${!empty appt.paqId}">
                                            <form action="/user/paq/${appt.appointmentId}" method="GET">
                                                <input type="hidden" name="date" value="${appt.date}">
                                                <input type="hidden" name="time" value="${appt.time}">
                                                <input type="hidden" name="clinicianId" value="${appt.clinician.clinicianId}">
                                                <button type="submit">Edit</button>
                                            </form>
                                        </c:if>
                                        <c:if test="${empty appt.paqId}">
                                            <form action="/user/paq/${appt.appointmentId}" method="GET">
                                                <input type="hidden" name="date" value="${appt.date}">
                                                <input type="hidden" name="time" value="${appt.time}">
                                                <input type="hidden" name="clinicianId" value="${appt.clinician.clinicianId}">
                                                <button type="submit">Start</button>
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
<%--</body>--%>
<jsp:include page="../include/footer.jsp"/>


<%--<script src= "../../../pub/js/schedule_appointment.js"></script>--%>
