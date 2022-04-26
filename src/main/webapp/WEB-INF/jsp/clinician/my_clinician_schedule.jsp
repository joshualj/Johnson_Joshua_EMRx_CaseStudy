<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp"/>
<jsp:include page="../include/navbar.jsp"/>
<link href="../../../pub/css/my_clinician_schedule.css" rel="stylesheet">

<section class="myClinSchedSection vh-120 gradient-custom">
    <div class="physDate">
        <form class="physDateForm form-inline" action="/clinician/my_clinician_schedule">
                <div class="col">
                    <label for="userId"><center>Clinician</center></label>
                    <select id="userId" class="form-select mb-3" name="userId" value="${clinUser.userId}" required>
                        <option value="${clinUser.userId}">${clinUser.firstName} ${clinUser.lastName}</option>
                        <c:forEach var="clinician" items="${clinicianUsers}">
                            <c:if test="${clinUser.userId != clinician.userId}">
                                <option value="${clinician.userId}">${clinician.firstName} ${clinician.lastName}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <div class="col">
                    <label for="date"><center>Date</center></label>
                    <input type="date" id="date" name="date" placeholder="Date" class="form-control mb-3" value="${localDate}">
                </div>
                <div class="col">
                    <input class="btn btn-outline-primary btn-sm" type="submit" value="Submit">
                </div>
        </form>
    </div>
    <hr>
    <div class="row justify-content-center align-items-center h-100">
        <div id="scheduleHeader">
        <h1>${dayOfWeek}, ${monthName} ${dayDate}, ${yearDate}</h1>
        <h5 id="name">
            <b>
                <c:if test="${!empty clinUser.firstName}">Dr. ${clinUser.firstName} ${clinUser.lastName}'s Appointment Schedule</c:if>
            </b>
        </h5>
        </div>
        <div class="col-10">
            <div class="card shadow-2-strong" style="border-radius: 15px">
                <div style="line-height: 2.0">
                    <table id="appointmentsTable" class="table table-striped">
                        <thead class="table-dark">
                        <tr>
                            <th style="border-top-left-radius: 15px"><b>Time</b></th>
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
                                <td><b>${appt}</b></td>
                                <c:if test="${scheduledTime.contains(appt)}">
                                    <td><b>${users[scheduledTime.indexOf(appt)].firstName} ${users[scheduledTime.indexOf(appt)].lastName}</b></td>
                                    <td><b>${patients[scheduledTime.indexOf(appt)].birthDate}</b></td>
                                    <td><b>${patients[scheduledTime.indexOf(appt)].sex}/${patients[scheduledTime.indexOf(appt)].gender}</b></td>
                                    <td><b>${appointments[scheduledTime.indexOf(appt)].chiefComplaint}</b></td>
                                    <c:if test="${!empty appointments[scheduledTime.indexOf(appt)].paqId}">
                                        <td>
                                        <form action="/user/paq/${appointments[scheduledTime.indexOf(appt)].appointmentId}" method="GET">
                                            <input type="hidden" name="date" value="${appointments[scheduledTime.indexOf(appt)].date}">
                                            <input type="hidden" name="time" value="${appointments[scheduledTime.indexOf(appt)].time}">
                                            <input type="hidden" name="clinicianId" value="${appointments[scheduledTime.indexOf(appt)].clinician.clinicianId}">
                                            <button type="submit" class="btn view btn-outline-primary btn-md">View</button>
                                        </form>
                                        </td>
                                    </c:if>
                                    <c:if test="${empty appointments[scheduledTime.indexOf(appt)].paqId}">
                                        <td>N/A</td>
                                    </c:if>
                                    <td>
                                        <form action="/clinician/my_clinician_schedule/edit/${appointments[scheduledTime.indexOf(appt)].appointmentId}" method="GET">
                                            <input type="hidden" name="apptId" value="${appointments[scheduledTime.indexOf(appt)].appointmentId}">
                                            <button type="submit" class="edit btn btn-outline-primary btn-md">Edit</button>
                                        </form>
                                    </td>
                                    <td>
                                        <form action="/clinician/my_clinician_schedule/cancel/${appointments[scheduledTime.indexOf(appt)].appointmentId}" method="POST">
                                            <input type="hidden" name="apptId" value="${appointments[scheduledTime.indexOf(appt)].appointmentId}">
                                            <button type="submit" class="cancelBtn btn btn-outline-primary btn-md">Cancel</button>
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

<jsp:include page="../include/footer.jsp"/>
