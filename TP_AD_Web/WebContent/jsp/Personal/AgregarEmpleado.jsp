<%@page import="impl.personal.TipoPuesto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Agregar Empleado</title>
</head>
<body>
	<%-- <form action="AgregarEmpleado">
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
								out.print("<option value=\"" + t.toString() + "\">"
										+ t.toString() + "</option>");
							}
						%>
				</select></td>
			</tr>
		</table>
		<br /> <input type="submit" value="Alta">
	</form> --%>

	<div id="test1" class="col s12">

		<form id="alta-empleado" action="AgregarEmpleado" class="box-padding"
			class="form-control">

			<div class="input-field col s12">
				<input type="number" class="validate" name="id"> <label
					for="email">ID de Sucursal</label>
			</div>
			<div class="input-field col s12">
				<input type="text" class="validate" name="cuit"> <label
					for="email">CUIT</label>
			</div>
			<div class="input-field col s12">
				<input type="number" class="validate" name="dni"> <label
					for="email">DNI</label>
			</div>
			<div class="input-field col s12">
				<input type="text" class="validate" name="nombre"> <label
					for="email">Nombre</label>
			</div>
			<div class="input-field col s12">
				<input type="text" class="validate" name="apellido"> <label
					for="email">Apellido</label>
			</div>
			<div class="input-field col s12">
				<select>
					<option value="" disabled selected></option>
					<%
						for (TipoPuesto t : TipoPuesto.values()) 								
									{
					%>
					<option><%=t.getPuesto().toString()%></option>
					<%
						}
					%>
				</select> <label>Tipo de Puesto</label>
			</div>

			<div class="input-field col s12">
				<input type="date" class="datepicker" name="fechaNacimiento">
				<label>Fecha de Nacimiento</label>
			</div>

			<input class="btn btn-primary" type="submit" value="Alta">
		</form>

	</div>


	<script>
		$(document).ready(function() {
			$('select').material_select();
		});

		$('.datepicker').pickadate({
			selectMonths : true, // Creates a dropdown to control month
			selectYears : 15
		// Creates a dropdown of 15 years to control year
		});
	</script>


</body>
</html>