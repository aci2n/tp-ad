<%@page import="controllers.ControladorPrincipal"%>
<%@page import="views.sucursales.SucursalView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alta Ubicacion</title>
</head>
<body>

	<form id="" action="" class="box-padding" class="form-control">
		<div id="ubicacion_sucursal">
			<select name"idSucursalDestino">
				<option value="" selected></option>
				<%
					for (SucursalView suc : ControladorPrincipal.getInstance().obtenerSucursales()) 								
																							{
				%>
				<option value="<%=suc.getId()%>"><%=suc.getNombre()%></option>
				<%
					}
				%>
			</select> <label>Sucursal</label>
		</div>
		<div id="ubicacion_nueva">
			<div class="input-field col s12">
				<input type="text" class="validate" name="dni"> <label
					for="email">País</label>
			</div>
			<div class="input-field col s12">
				<input type="text" class="validate" name="dni"> <label
					for="email">Provincia</label>
			</div>
			<div class="input-field col s12">
				<input type="text" class="validate" name="nombre"> <label
					for="email">Ciudad</label>
			</div>
			<div class="input-field col s12">
				<input type="text" class="validate" name="apellido"> <label
					for="email">Calle</label>
			</div>
			<div class="input-field col s12">
				<input type="text" class="validate" name="dni"> <label
					for="email">Altura</label>
			</div>
			<div class="input-field col s12">
				<input type="text" class="validate" name="apellido"> <label
					for="email">Departamento</label>
			</div>
			<div class="input-field col s12">
				<input type="number" class="validate" name="nombre"> <label
					for="email">Piso</label>
			</div>
			<div class="input-field col s12">
				<input type="text" class="validate" name="nombre"> <label
					for="email">Longitud</label>
			</div>
			<div class="input-field col s12">
				<input type="text" class="validate" name="apellido"> <label
					for="email">Latitud</label>
			</div>
		</div>



	</form>
		
	<script>
		$('select[name="idSucursalDestino"]').change(function() {
			if ($(this).val() != '') {
				$('#ubicacion_nueva input').attr('disabled', 'disabled');
			} else {
				$('#ubicacion_nueva input').removeAttr('disabled');
			}
		});
	</script>


</body>
</html>