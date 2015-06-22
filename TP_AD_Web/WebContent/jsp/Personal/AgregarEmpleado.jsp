<%@page import="model.impl.personal.TipoPuesto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Agregar Empleado</title>
</head>
<body>
	<form action="AgregarEmpleado">
		<table>
			<tr>
				<td>- Sucursal -</td>
			</tr>
			<tr>
				<td>ID:</td>
				<td><input type="text" name="id"></td>
			</tr>
			<tr>
				<td>- Empleado -</td>
			</tr>
			<tr>
				<td>CUIT:</td>
				<td><input type="text" name="cuit"></td>
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
				<td>Fecha nacimiento:</td>
				<td><input type="text" name="fechaNacimiento"></td>
			</tr>
			<tr>
				<td>Tipo:</td>
				<td><select name="tipo">
						<%
							for (TipoPuesto t : TipoPuesto.values()) {
								out.print("<option value=\"" + t.toString() + "\">" + t.toString() + "</option>");
							}
						%>
				</select></td>
			</tr>
		</table>
		<br /> <input type="submit" value="Alta">
	</form>
</body>
</html>