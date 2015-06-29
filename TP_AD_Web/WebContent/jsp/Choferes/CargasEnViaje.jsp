<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="impl.viajes.*"%>
<%@page import="impl.cargas.*"%>
<%@page import="views.viajes.*"%>
<%@page import="persistence.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div class="card-action row"
		style="border-bottom: 1px solid rgba(160, 160, 160, 0.2);">
		<div class="input-field col s6">
			<i class="material-icons prefix">list</i> <input id="filtrado"
				type="number" class="validate"> <label for="icon_list">Ingrese
				el ID del Viaje</label>
		</div>
		<input class="btn" type="submit" id="btnSubmit" name="btnSubmit" value="Buscar" />
	</div>



	<%
		Viaje viaje = ViajeDAO.getInstance().get(1);
		for(ItemCarga c : viaje.getCargas())
		{
	%>
	<ul class="collapsible" data-collapsible="accordion">
		<li>
			<div class="collapsible-header">
				<i class="material-icons">description</i>Item Carga ID: <%=c.getId() %>
			</div>
			<div class="collapsible-body">
				<p>Manifiesto: <%=c.getCarga().getManifiesto()%></p>
				<p>Cliente: <%=c.getCarga().getCliente().getNombre()%></p>
				<p>Destino: <%=c.getCarga().getDestino().getCiudad()%></p>
			</div>
		</li>
	</ul>
	<%
		}
	%>



	<script>
		$(document).ready(function() {
			$('.collapsible').collapsible({
				accordion : false
			// A setting that changes the collapsible behavior to expandable instead of the default accordion style
			});
		});
	</script>

</body>
</html>