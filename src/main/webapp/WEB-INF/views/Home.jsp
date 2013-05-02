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
            <li><a href="./Portfolio"><div class="menu-header">Portfolio</div></a></li>
            <li><a href="./About"><div class="menu-header">About</div></a></li>
            <li><a href="./Contact"><div class="menu-header">Contact</div></a></li>
        </ul>
    </div>
</div>

<div class="container" style="padding-top: 10px;">
    <img src="${homeArt}">
</div>

<script>
</script>

</body>
</html>
