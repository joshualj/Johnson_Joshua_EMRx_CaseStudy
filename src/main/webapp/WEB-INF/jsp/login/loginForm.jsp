<jsp:include page="../include/header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="../../pub/css/index.css">

<%--<jsp:include page="include/header.jsp"/>--%>
<div class="body gradient-custom">
    <div id="logo-login">
        <div id="form">
            <form action="/login/loginSubmit" method="POST" style="width: 20vw">
                <div id="header">
                    <center><h1 id="emrx" style="color: white"><b>EMRx</b></h1></center>
                    <center><h2 style="font-size: 23px; color: white">Healthcare Portal</h2></center>
                </div>
                <img id="logo" src="../../../pub/images/hand_medicalcross_logo.jpg">
                <div class="form-group">
                    <label for="exampleInputEmail1"></label>
                    <input type="email" name="username" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Email">
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1"></label>
                    <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                </div>
<%--                <div class="form-check">--%>
<%--                    <input type="checkbox" class="form-check-input" id="exampleCheck1">--%>
<%--                    <label class="form-check-label" for="exampleCheck1">Check me out</label>--%>
<%--                </div>--%>
                <a href="https://www.youtube.com/embed/jh5U5BnpGN8?autoplay=1&mute=1" style="color: white; text-decoration:none;">Info</a>
                <button type="submit" value="Submit" class="btn btn-outline-primary btn-md">Submit</button>
                <a href="/user/register" style="color: white; text-decoration:none; margin-bottom: 7px;" target="_blank" rel="noopener noreferrer">Register</a>
            </form>
        </div>
<%--        <div id="video">--%>
<%--            <iframe width="560" height="315" src="https://www.youtube.com/embed/jh5U5BnpGN8?autoplay=1&mute=1"--%>
<%--                    title="YouTube video player"--%>
<%--                    allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>--%>
<%--            </iframe>--%>
<%--        </div>--%>
    </div>
</div>

<jsp:include page="../include/footer.jsp" />