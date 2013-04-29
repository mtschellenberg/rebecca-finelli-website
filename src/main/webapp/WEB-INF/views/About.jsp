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
    <!--<link href="css/bootstrap-responsive.css" rel="stylesheet" media="screen">-->
    <link href="css/style.css" rel="stylesheet" media="screen">
    <script src="js/jquery-1.9.1.js"></script>
    <script src="js/bootstrap.js"></script>
</head>

<body>

<div class="container container-menu">
    <div class="menu-background">
        <a href="./">
            <img class="menu-logo" src="images/RebeccaFinelliLogo.png">
        </a>
        <ul class="nav nav-pills pull-right">
            <li><a href="./Portfolio"><h3 class="menu-header">Portfolio</h3></a></li>
            <li class="active"><a href="./About"><h3 class="menu-header">About</h3></a></li>
            <li><a href="./Contact"><h3 class="menu-header">Contact</h3></a></li>
        </ul>
    </div>
</div>

<div class="container container-boxed">

<center>

<div id="aboutDiv" class="padded">
    ${aboutText}
</div>

</center>

</div> <!-- container -->

</body>
</html>
