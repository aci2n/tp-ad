<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Agregar Receptor</title>
</head>
<body>
	<form action="AgregarReceptor">
		<table>
			<tr>
				<td>- Cliente Particular -</td>
			</tr>
			<tr>
				<td>ID:</td>
				<td><input type="text" name="id"></td>
			</tr>
			<tr>
				<td>- Receptor -</td>
			</tr>
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
			<tr>
				<td>- Ubicacion -</td>
			</tr>
			<tr>
				<%@include file="FormUbicacion.html"%>
			</tr>
		</table>
		<br /> <input type="submit" value="Agregar">
	</form>
</body>
</html>