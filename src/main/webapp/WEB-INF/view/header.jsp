<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
	<head>
		<title>provider</title>

 		<meta charset="utf-8">
     	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
        <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.0/css/font-awesome.css" rel="stylesheet">
        <link rel="shortcut icon" href='<c:url value="/resources/images/favicon.png" />' type="image/x-icon">
        <script src="webjars/jquery/3.1.1-1/jquery.min.js"></script>

        <script type='text/javascript' src='//code.jquery.com/jquery-1.8.3.js'></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/css/bootstrap-datepicker3.min.css">
        <script type='text/javascript' src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/js/bootstrap-datepicker.min.js"></script>

        <script type='text/javascript'>
            $(function(){
                $('.input-daterange').datepicker({
                    autoclose: true,
                    format: "yyyy-mm-dd"
                });
            });
        </script>

        <style>
			<%@include file='styles.css'%>
        </style>
	</head>
	<body>
		<div class="container" id="mainLayout">
		    <div class="col-12" id="header">
		        <h1>Provider</h1>
		    </div>
		    <div class="col-12" id="header">
		        <ul class="nav navbar-nav navbar-right">
                    <li><a href="../">users</a></li>
                    <li><a href="transactions">transactions</a></li>
                </ul>
		    </div>