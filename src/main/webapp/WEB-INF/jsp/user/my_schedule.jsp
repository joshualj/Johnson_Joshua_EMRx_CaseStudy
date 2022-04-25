<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp"/>
<jsp:include page="../include/navbar.jsp"/>
<link href="../../../pub/css/my_schedule.css" rel="stylesheet">
<body>
<style>
    h1{
        text-align: center;
        padding-bottom: 20px;
    }
</style>

<div id="appointments" class="gradient-custom">
    <div id="myPersonalDetails">
        <div id="imageContainer">
            <c:if test="${patient.sex == 'M'}">
                <img id="profilePicture" src="../../../pub/images/EMRx_default_photo_male.jpeg" alt="Profile Photo Male">
            </c:if>
            <c:if test="${patient.sex != 'M'}">
                <img id="profilePicture" src="../../../pub/images/EMRx_default_photo_female.jpeg" alt="Profile Photo Female">
            </c:if>
        </div>
        <div id="myInfo">
            <h2><b>${user.firstName} ${user.lastName}</b></h2>
            <h2><c:if test="${!empty patient.preferredName}">"${patient.preferredName}"</c:if></h2>
            <h5>${patient.birthDate}</h5>
        </div>
    </div>
    <section class="myPatientSched">
        <div class="row justify-content-center align-items-center">
            <h1 id="name" name="user" value="${user}">
                <c:if test="${!empty user.firstName}">Appointments</c:if>
                <c:if test="${empty user.firstName}">Appointments</c:if>
            </h1>
            <div class="col-12">
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
                                    <td><b>${months[appointments.indexOf(appt)]} ${daysOfMonth[appointments.indexOf(appt)]}, ${years[appointments.indexOf(appt)]}</b></td>
                                    <td><b>${appt.time}</b></td>
                                    <td><b>${clinUsers[appointments.indexOf(appt)].firstName} ${clinUsers[appointments.indexOf(appt)].lastName}</b></td>
                                    <td>
                                        <c:if test="${!empty appt.paqId}">
                                            <form action="/user/paq/${appt.appointmentId}" method="GET">
                                                <input type="hidden" name="date" value="${appt.date}">
                                                <input type="hidden" name="time" value="${appt.time}">
                                                <input type="hidden" name="clinicianId" value="${appt.clinician.clinicianId}">
                                                <button type="submit" class="edit btn btn-outline-primary btn-md">Edit</button>
                                            </form>
                                        </c:if>
                                        <c:if test="${empty appt.paqId}">
                                            <form action="/user/paq/${appt.appointmentId}" method="GET">
                                                <input type="hidden" name="date" value="${appt.date}">
                                                <input type="hidden" name="time" value="${appt.time}">
                                                <input type="hidden" name="clinicianId" value="${appt.clinician.clinicianId}">
                                                <button type="submit" class="btn btn-outline-primary btn-md">Start</button>
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
    <div id="otherDetails">

    </div>
</div>
</body>
<jsp:include page="../include/footer.jsp"/>
