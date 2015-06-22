<%@page import="impl.vehiculos.TipoVehiculo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/JavaScript">
	function setFormPlan() {
		var s = document.getElementById('selectPlan');
		var val = s.options[s.selectedIndex].value;
		var holder = document.getElementById('holderPlan');
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
		holder.innerHTML = html;
	}
</script>
<title>Alta Vehiculo Local</title>
</head>
<body>
	<!-- HAY Q PONER Q EL PLAN TENGA IDVEHICULO NO AL REVESSSSSS -->
	<!-- TAMBIEN HAY Q HACER Q SIEMPRE APAREZCA EL CAMPO AL FINAL -->
	<!-- IF YOU'RE TIRED OF STARTING OVER STOP GIVING UP -->
	<form action="AltaVehiculoLocal">
		<table style="table-layout: fixed; width: 400px">
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
			<tr>
				<td>Plan Mantenimiento:</td>
				<td><select id="selectPlan" name="tipoPlan"
					onchange="setFormPlan();">
						<option disabled selected></option>
						<option value="kilometraje">Kilometraje</option>
						<option value="kilometrajeRelativo">Kilometraje Relativo</option>
						<option value="temporal">Temporal</option>
				</select></td>
			</tr>
			<tr id="holderPlan">
				<!-- se setea dinamico -->
			</tr>
		</table>
		<br /> <input type="submit" value="Alta">
	</form>
</body>
</html>