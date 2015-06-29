<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="controllers.ControladorPrincipal"%>
<%@page import="impl.viajes.*"%>
<%@page import="persistence.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>



	<div id="test1">
		<form id="listarViajes" action="ListarMisViajes">
			<div class="card-action row"
				style="border-bottom: 1px solid rgba(160, 160, 160, 0.2);">
				<div class="input-field col s6">
					<i class="material-icons prefix">list</i> <input id="filtrado"
						type="number" class="validate" name="idChofer"> <label
						for="icon_list">Ingrese su ID</label>
				</div>
				<input class="btn" type="submit" id="btnSubmit" name="buscar"
					value="Buscar" />
		</form>
	</div>
	<div class="card-content">
		<table>
			<thead>
				<tr>
					<th>ID Viaje</th>
					<th>ID Conductor</th>
					<th>ID Seguro</th>
					<th>ID Sucursal</th>
					<th>Origen</th>
					<th>último Destino</th>
					<th>Fecha de Salida</th>
					<th>Fecha de Llegada</th>
					<th>Condición Especial</th>
				</tr>
			</thead>

			<tbody id="tbody">

				<%
					// retrieve your list from the request, with casting 
						ArrayList<Viaje> list = (ArrayList<Viaje>) request.getAttribute("misViajes");

						// print the information about every category of the list
									System.out.println("Tamaño: "+list.size());
				%>



			</tbody>
		</table>
		<div></div>
	</div>

	<div class="col s12 m4 l1">
		<p></p>
	</div>

	<script>
		
	</script>

</body>
</html>