<nav class="navbar navbar-expand-lg navbar-light gradient-custom">
    <div class="container-fluid">
        <a class="navbar-brand" href="/index"><b>EMRx</b></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/login/login">| Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/user/register">| Register</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        | Patient Options
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="/user/schedule_appointment">Schedule an Appointment</a></li>
                        <li><a class="dropdown-item" href="/user/my_schedule">My Appointments</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <sec:authorize access="hasAuthority('CLINICIAN')">
                            <li><a class="dropdown-item" href="/clinician/my_clinician_schedule">My Clinician Schedule</a></li>
                        </sec:authorize>
                        <sec:authorize access="hasAuthority('CLINICIAN')">
                            <li><a class="dropdown-item" href="/user/search">Find a Clinician</a></li>
                        </sec:authorize>
<%--                        <li><a class="dropdown-item" href="#">Add Pre-Visit Details</a></li>--%>
                    </ul>
                </li>
                <sec:authorize access="isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link" href="/login/logout">| Logout</a>
                    </li>
                    <sec:authentication property="principal.username"/>
                </sec:authorize>
            </ul>
<%--            <form class="d-flex">--%>
<%--                <input class="form-control me-2" type="search" placeholder="Find a Clinician" aria-label="Search">--%>
<%--                <button class="btn btn-outline-success" type="submit">Search</button>--%>
<%--            </form>--%>
        </div>
    </div>
</nav>