<%@page import="impl.cargas.TipoCarga"%>
<%@page import="impl.personal.TipoPuesto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Alta Carga</title>
</head>
<body>

	<div id="test1" class="col s12">

		<form id="alta-empleado" action="AgregarEmpleado" class="box-padding"
			class="form-control">
	
	<div style="padding-top: 20px; padding-bottom: 20px;">
			<p>
				<input type="checkbox" id="test6" checked="checked" /> <label
					for="test6">Envío dentro del país</label>
			</p>
			</div>
			<div class="input-field col s12">
				<select >
					<option value="" disabled selected></option>
					<%
						for (TipoCarga t : TipoCarga.values()) 								
																								{
					%>
					<option><%=t.getTipo().toString()%></option>
					<%
						}
					%>
				</select> <label>Tipo de Carga</label>
			</div>

			<div class="input-field col s12">
				<input type="number" class="validate" name="id"> <label
					for="email">ID de Sucursal</label>
			</div>
			<div class="input-field col s12">
				<input type="number" class="validate" name="cuit"> <label
					for="email">ID Cliente</label>
			</div>
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