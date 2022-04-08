<jsp:include page="../include/header.jsp"/>
<jsp:include page="../include/navbar.jsp"/>
<link href="../../../pub/css/schedule_appointment.css" rel="stylesheet">
<body>
<section class="vh-120 gradient-custom">
    <div class="row justify-content-center align-items-center h-100">
        <h1 id="date">Thursday, March 24</h1>
        <h5 id="clinicianName">Dr. Jackson Smith</h5>
        <div class="col-5">
            <div class="card shadow-2-strong" style="border-radius: 15px">
                <div style="line-height: 2.0">
                    <table id="appointmentsTable" class="table table-striped">
                        <thead class="table-dark">
                        <tr>
                            <th style="border-top-left-radius: 15px"><b>Appointment Time</b></th>
                            <th style="border-top-right-radius: 15px"><b>Availability</b></th>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
<script src= "../../../pub/js/schedule_appointment.js"></script>
