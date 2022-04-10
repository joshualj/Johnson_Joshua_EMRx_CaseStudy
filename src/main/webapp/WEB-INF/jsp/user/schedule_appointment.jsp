<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp"/>
<jsp:include page="../include/navbar.jsp"/>
<link href="../../../pub/css/schedule_appointment.css" rel="stylesheet">
<body>
<section class="vh-120 gradient-custom">
<%--    <div class="row justify-content-center align-items-center">--%>
        <form class="appointmentDaySelect" action="/user/schedule_appointment">
            <select class="selectYear form-control-md" name="clinicianId" value="${form.clinicianId}" required>
                <option value="0" disabled></option>
                <c:forEach var="clinician" items="${clinician}">
                    <option value="${clinician.clinicianId}">${clinician.firstName} ${clinician.lastName}</option>
                </c:forEach>
            </select>
            <select class="selectYear form-control-md" name="year" value="${form.year}" required>
                <option value="0" disabled></option>
                <option value="2022">2022</option>
                <option value="2023">2023</option>
            </select>
            <select class="selectMonth form-control-md" name="month" value="${form.month}" required>
                <option value="0" disabled></option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
                <option value="7">7</option>
                <option value="8">8</option>
                <option value="9">9</option>
                <option value="10">10</option>
                <option value="11">11</option>
                <option value="12">12</option>
            </select>
            <select class="selectDay form-control-md" name="day" value="${form.day}" required>
                <option value="0" disabled></option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
                <option value="7">7</option>
                <option value="8">8</option>
                <option value="9">9</option>
                <option value="10">10</option>
                <option value="11">11</option>
                <option value="12">12</option>
                <option value="13">13</option>
                <option value="14">14</option>
                <option value="15">15</option>
                <option value="16">16</option>
                <option value="17">17</option>
                <option value="18">18</option>
                <option value="19">19</option>
                <option value="20">20</option>
                <option value="21">21</option>
                <option value="22">22</option>
                <option value="23">23</option>
                <option value="24">24</option>
                <option value="25">25</option>
                <option value="26">26</option>
                <option value="27">27</option>
                <option value="28">28</option>
                <option value="29">29</option>
                <option value="30">30</option>
                <option value="31">31</option>
            </select>
            <input class="btn btn-outline-primary btn-md" type="submit" value="Submit" />
<%--        </div>--%>
    </form>
    <div class="row justify-content-center align-items-center h-100">
        <h1 id="date" name="date" value="${localDate}">${localDate}</h1>
        <h5 name="defaultClinician" value="${defaultClinician}">Dr. ${defaultClinician.firstName} ${defaultClinician.lastName}</h5>
        <div class="col-5">
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
                                            <input type="hidden" name="clinicianId" value="${clinicianId}">
                                            <button type="submit">Schedule</button>
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
<%--<script src= "../../../pub/js/schedule_appointment.js"></script>--%>
