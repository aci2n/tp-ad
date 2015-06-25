<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Agregar Parada Intermedia</title>
</head>
<body>
	<form action="AgregarParadaIntermedia">
		<table>
			<tr>
				<td>ID Viaje:</td>
				<td><input type="text" name="id"></td>
			</tr>
			<tr>
				<td>Fecha llegada:</td>
				<td><input type="text" name="llegada"></td>
			</tr>
			<tr>
				<%@include file="/jsp/Misc/FormUbicacion.html"%>
			</tr>
		</table>
		<br /> <input type="submit" value="Alta">
	</form>
</body>
</html>