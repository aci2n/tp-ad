<%@page import="model.impl.vehiculos.TipoVehiculo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <script language="JavaScript" type="text/JavaScript">
	function setFormPlan() {
		var sel = document.getElementById('selectPlan');
		var val = sel.options[sel.selectedIndex].value;
		var e = document.getElementById('formPlan');
		var html;
		switch (val) {
		case "kilometraje":
			html = '<td>Punto control:</td> <td><input type="text" name="puntoControl"></td>';
			break;
		case "kilometrajeRelativo":
			html = '<td>Punto control:</td> <td><input type="text" name="puntoControl"></td>';
			break;
		case "temporal":
			html = '<td>Intervalo mantenimiento:</td> <td><input type="text" name="intervaloMantenimiento"></td>'
			break;
		}
		e.innerHTML = html;
	}
</script> -->
<title>Alta Vehiculo Local</title>
</head>
<body>
	<form action="AltaVehiculoLocal">
		<table>
			<tr>
				<td>- Sucursal -</td>
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
				<td>Peso:</td>
				<td><input type="text" name="peso"></td>
			</tr>
			<tr>
				<td>Tara:</td>
				<td><input type="text" name="tara"></td>
			</tr>
			<tr>
				<td>Tarifa:</td>
				<td><input type="text" name="tarifa"></td>
			</tr>
			<tr>
				<td>Tipo:</td>
				<td><select name="tipo">
						<%
							for (TipoVehiculo t : TipoVehiculo.values()) {
								out.print("<option value=\"" + t.toString() + "\">" + t.toString() + "</option>");
							}
						%>
				</select></td>
			</tr>
			<tr>
				<td>Vencimiento Garantia:</td>
				<td><input type="text" name="vencimientoGarantia"></td>
			</tr>
			<!-- <tr>
				<td>Plan Mantenimiento:</td>
				<td><select id="selectPlan" name="plan"
					onchange="setFormPlan();">
						<option disabled selected></option>
						<option value="kilometraje">Kilometraje</option>
						<option value="kilometrajeRelativo">Kilometraje Relativo</option>
						<option value="temporal">Temporal</option>
				</select></td>
			</tr>
			<tr id="formPlan">
				se setea dinamico
			</tr> -->
		</table>
		<br /> <input type="submit" value="Alta">
	</form>
</body>
</html>