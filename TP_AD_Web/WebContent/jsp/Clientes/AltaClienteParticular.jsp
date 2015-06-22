<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/principal.css">


<title>Alta Cliente Particular</title>
</head>
<body>
	<!-- <form class="form-group" >
		<table>
			<tr>
				<td>DNI:</td>
				<td><input type="text" name="dni"></td>
			</tr>
			<tr>
				<td>Nombre:</td>
				<td><input type="text" name="nombre"></td>
			</tr>
			<tr>
				<td>Apellido:</td>
				<td><input type="text" name="apellido"></td>
			</tr>
		</table>
		<br /> <input class="btn btn-primary" type="submit" value="Alta">
	</form> -->

	<form action="AltaClienteParticular">
		<div class="form-group">
			<label for="dni">DNI</label> <input type="text"
				class="form-control" placeholder="DNI" name="dni">
		</div>
		<div class="form-group">
			<label for="nombre">Nombre</label> <input type="text"
				class="form-control" placeholder="Nombre" name="nombre">
		</div>
		<div class="form-group">
			<label for="password">Apellido</label> <input
				type="password" class="form-control"
				placeholder="Apellido" name="apellido">
		</div>
		<input class="btn btn-primary" type="submit" value="Alta">
	</form>


	<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<!-- Latest compiled and minified JavaScript -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>