<%@page import="views.viajes.ParadaIntermediaView"%>
<%@page import="views.viajes.ViajeView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	ViajeView viaje = (ViajeView) request.getAttribute("viaje");
	if (viaje == null) {
%>

<div>No tiene un viaje actualmente.</div>

<%
	} else {
%>

<table>
	<thead>
		<tr>
			<th>Viaje: <%=viaje.getId()%></th>
		</tr>
		<tr>
			<th>Parada</th>
			<th>Fecha planeada</th>
			<th>Fecha llegada</th>
		</tr>
	</thead>

	<tbody id="tbody">

		<%
			for (ParadaIntermediaView parada : viaje.getParadas()) {
		%>
		<tr>
			<td>
				<%
					if (parada.getSucursal() != null) {
				%> <%=parada.getSucursal()%> <%
 	} else {
 %> <%=parada.getUbicacion().getCalle()%> <%=parada.getUbicacion().getAltura()%>
				<%=parada.getUbicacion().getCiudad()%> <%
 	}
 %>
			</td>
			<td><%=parada.getLlegadaEsperada()%></td>
			<td>
				<%
					if (parada.getLlegada() != null) {
				%> <%=parada.getLlegada()%> <%
 	} else {
 %> <input type="checkbox" id="parada_<%=parada.getOrden()%>"> <label
				for="parada_<%=parada.getOrden()%>">Llegué</label> <%
 	}
 %>

			</td>
		</tr>
		<%
			}
		%>

	</tbody>
</table>

<div class="col s12">
	<input class="btn btn-primary" type="button"
		value="Reportar siguiente parada">
</div>

<script>
	$(document).ready(function() {
		$('input[type="checkbox"]}').change(function() {
			var id = $(this).attr('id').match(/\d+/)[0];
			$.post('reportarParada', {
				idParada : id
			});
		});
	});
</script>


<%
	}
%>