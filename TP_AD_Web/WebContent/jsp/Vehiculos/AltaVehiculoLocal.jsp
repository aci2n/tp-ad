<%@page import="model.impl.vehiculos.TipoVehiculo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Alta Vehiculo Local</title>
</head>
<body>
	<form action="AltaProveedor" id="formVehiculo">
		<table>
			<tr>
				<td>- Proveedor -</td>
			</tr>
			<tr>
				<td>ID:</td>
				<td><input type="text" name="id"></td>
			</tr>
			<tr>
				<td>- Vehiculo -</td>
			</tr>
			<tr>
				<td>Patente:</td>
				<td><input type="text" name="patente"></td>
			</tr>
			<tr>
				<td>Profundidad:</td>
				<td><input type="text" name="profundidad"></td>
			</tr>
			<tr>
				<td>Alto:</td>
				<td><input type="text" name="alto"></td>
			</tr>
			<tr>
				<td>Ancho:</td>
				<td><input type="text" name="ancho"></td>
			</tr>
			<tr>
				<td>Tara:</td>
				<td><input type="text" name="tara"></td>
			</tr>
			<tr>

			</tr>
			<tr>
			<!-- ver como se hace para q sea select -->
				<td>Tipo:</td>
				<td><input type="text" name="tipo"></td>
			</tr>
		</table>
		<br /> <input type="submit" value="Alta">
	</form>
</body>


</html>