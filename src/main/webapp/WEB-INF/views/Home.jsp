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

<center>
    <div id="nav">
        <img id="nav-img" src="images/RebeccaFinelliNav.png" usemap="#nav-map"/>
        <map id="nav-map" name="nav-map">
            <area shape="rect" coords="0,0,982,222" href="./">
            <area shape="rect" coords="1775,100,2105,135" href="./Portfolio">
            <area shape="rect" coords="2190,100,2370,135" href="./About">
            <area shape="rect" coords="2460,100,2715,135" href="./Contact">
        </map>
    </div>
</center>

<center>
    <img src="${homeArt}">
</center>

<script src="js/image-map-script.js"></script>

</body>
</html>
