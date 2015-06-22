<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Agregar Distancia Entre Sucursales</title>
</head>
<body>
	<form action="AgregarDistanciaEntreSucursales">
		<table>
			<tr>
				<td>ID Sucursal A:</td>
				<td><input type="text" name="idA"></td>
			</tr>
			<tr>
				<td>ID Sucursal B:</td>
				<td><input type="text" name="idB"></td>
			</tr>
			<tr>
				<td>Distancia en kilometros:</td>
				<td><input type="text" name="km"></td>
			</tr>
			<tr>
				<td>Duracion en horas:</td>
				<td><input type="text" name="horas"></td>
			</tr>
			<tr>
				<td>Costo:</td>
				<td><input type="text" name="costo"></td>
			</tr>
		</table>
		<br /> <input type="submit" value="Alta">
	</form>
</body>
</html>