<%@page import="impl.productos.TipoFragilidad"%>
<%@page import="impl.productos.TipoTratamiento"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Alta Producto</title>
</head>
<body>
	<form action="AltaProducto">
		<table>
			<tr>
				<td>Nombre:</td>
				<td><input type="text" name="id"></td>
			</tr>
			<tr>
				<td>Tipo fragilidad:</td>
				<td><select name="tipoFragilidad">
						<%
							for (TipoFragilidad t : TipoFragilidad.values()) {
								out.print("<option value=\"" + t.toString() + "\">" + t.toString() + "</option>");
							}
						%>
				</select></td>
			</tr>
			<tr>
				<td>Tipo tratamiento:</td>
				<td><select name="tipoTratamiento">
						<%
							for (TipoTratamiento t : TipoTratamiento.values()) {
								out.print("<option value=\"" + t.toString() + "\">" + t.toString() + "</option>");
							}
						%>
				</select></td>
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
				<td>Peso:</td>
				<td><input type="text" name="peso"></td>
			</tr>
			<tr>
				<td>Apilable:</td>
				<td><input type="text" name="apilable"></td>
			</tr>
			<tr>
				<td>Manipulacion:</td>
				<td><input type="text" name="manipulacion"></td>
			</tr>
			<tr>
				<td>Material:</td>
				<td><input type="text" name="material"></td>
			</tr>
			<tr>
				<td>Consideraciones:</td>
				<td><input type="text" name="consideraciones"></td>
			</tr>
			<tr>
				<td>Refrigerado:</td>
				<td><input type="checkbox" name="refrigerado"></td>
			</tr>
		</table>
		<br /> <input type="submit" value="Alta">
	</form>
</body>
</html>