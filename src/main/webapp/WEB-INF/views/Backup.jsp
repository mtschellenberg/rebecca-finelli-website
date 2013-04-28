<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Rebecca Finelli, Medical Illustrator</title>
    <link href="css/bootstrap.css" rel="stylesheet" media="screen">
    <link href="css/bootstrap-responsive.css" rel="stylesheet" media="screen">
    <link href="css/style.css" rel="stylesheet" media="screen">
    <script src="js/jquery-1.9.1.js"></script>
    <script src="js/bootstrap.js"></script>
</head>

<body style="padding-top: 60px;">

<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
        <div class="row-fluid">
        <div class="span12">
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="brand" href="./"><img class="nav-img" src="images/header.png"/></a>
            <div class="nav-collapse collapse">
                <ul class="nav pull-right">
                    <li class="active"><a href="./">Home</a></li>
                    <li><a href="./Portfolio">Portfolio</a></li>
                    <li><a href="./About">About</a></li>
                    <li><a href="./Contact">Contact</a></li>
                    <%--
                    <li class="active"><a href="./"><img class="nav-img" src="images/home.png"/></a></li>
                    <li><a href="/Portfolio"><img class="nav-img" src="images/portfolio.png"/></a></li>
                    <li><a href="/About"><img class="nav-img" src="images/about.png"/></a></li>
                    <li><a href="/Contact"><img class="nav-img" src="images/contact.png"/></a></li>
                    --%>
                </ul>
            </div>
        </div>
        </div>
        </div>
    </div>
</div>

<div class="container">

<center>
<img src="images/artwork.png"/>
</center>

</div> <!-- container -->

</body>
</html>
