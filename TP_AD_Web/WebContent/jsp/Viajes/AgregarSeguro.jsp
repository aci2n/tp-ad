<%@page import="impl.cargas.TipoCarga"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Agregar Seguro</title>
</head>
<body>
	<form action="AgregarSeguro">
		<table>
			<tr>
				<td>ID Compania:</td>
				<td><input type="text" name="id"></td>
			</tr>
			<tr>
				<td>Nombre:</td>
				<td><input type="text" name="nombre"></td>
			</tr>
			<tr>
				<td>Tipo:</td>
				<td><select name="tipo">
						<%
							for (TipoCarga t : TipoCarga.values()) {
								out.print("<option value=\"" + t.toString() + "\">" + t.toString() + "</option>");
							}
						%>
				</select></td>
			</tr>
			<tr>
				<td>Tarifa:</td>
				<td><input type="text" name="tarifa"></td>
			</tr>
		</table>
		<br /> <input type="submit" value="Alta">
	</form>
	<script>
		$(document).ready(function() {
			$('select').material_select();
		});
	</script>
</body>
</html>