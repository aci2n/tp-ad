<%@page import="impl.productos.CondicionEspecial"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Agregar Condicion Especial a Viaje</title>
</head>
<body>
	<form action="AgregarCondicionEspecialAViaje">
		<table>
			<tr>
				<td>ID Viaje:</td>
				<td><input type="text" name="id"></td>
			</tr>
			<tr>
				<td>Condicion especial:</td>
				<td><select name="condicionEspecial">
						<%
							for (CondicionEspecial t : CondicionEspecial.values()) {
								out.print("<option value=\"" + t.toString() + "\">" + t.toString() + "</option>");
							}
						%>
				</select></td>
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