<jsp:include page="../include/header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="../../pub/css/index.css">

<%--<jsp:include page="include/header.jsp"/>--%>
<html>

<body>
<body class="body gradient-custom">
<header>
    <div id="header">
        <h1 id="emrx"><b>EMRx</b></h1>
        <h3>Patient Portal</h3>
    </div>
</header>
<div id="form">
    <form action="/login/loginSubmit" method="POST">
        <img id="logo" src="../../../pub/images/hand_medicalcross_logo.jpg">
        <div class="form-group">
            <label for="exampleInputEmail1"></label>
            <input type="email" name="username" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Email">
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1"></label>
            <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
        </div>
        <div class="form-check">
            <input type="checkbox" class="form-check-input" id="exampleCheck1">
            <label class="form-check-label" for="exampleCheck1">Check me out</label>
        </div>
        <button type="submit" value="Submit" class="btn btn-outline-primary btn-md">Submit</button>
<%--        <a href="#" target="_blank" rel="noopener noreferrer"><input class="btn btn-outline-primary btn-md" value="Submit" /></a>--%>
        <a href="/user/register" target="_blank" rel="noopener noreferrer">Register New User</a>
    </form>
</div>
<div id="video">
    <iframe width="200" height="200" src="https://www.youtube.com/embed/jh5U5BnpGN8?autoplay=1&mute=1" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
</div>
</body>

<%--<h1>This is an index page</h1>--%>
<%--<c:forEach var="user" items="${users}">--%>
<%--    <div>--%>
<%--            ${user}--%>
<%--    </div>--%>
<%--</c:forEach>--%>

<script>

</script>

</body>

</html>




<%--<form action="/login/loginSubmit" method="POST">--%>

<%--    Username: <input type="text" name="username" >--%>
<%--    <br>--%>
<%--    Password: <input type="password" name="password" >--%>
<%--    <button type="submit">Submit</button>--%>
<%--</form>--%>


<jsp:include page="../include/footer.jsp" />