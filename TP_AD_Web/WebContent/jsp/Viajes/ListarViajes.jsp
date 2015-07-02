<%@page import="com.sun.media.sound.ModelAbstractChannelMixer"%>
<%@page import="java.util.*"%>
<%@page import="impl.viajes.*"%>
<%@page import="views.viajes.*"%>
<%@page import="controllers.*"%>
<%@page import="persistence.*"%>
<%@page import="org.hibernate.Query"%>
<%@page import="org.hibernate.Session"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Listado de Clientes</title>
</head>
<body>

	<div class="row">
		<div class="">
			<p></p>
		</div>

		<div class="col s12 m4 l12 ">
			<div class="card">
				<div class="card-content white-text pink">
					<span class="card-title">Listado de Viajes</span>
				</div>



				<div class="card-action row"
					style="border-bottom: 1px solid rgba(160, 160, 160, 0.2);">
					<div class="input-field col s6">
						<i class="material-icons prefix">list</i> <input id="filtrado"
							type="text" class="validate"> <label for="icon_list">Filtrar
							tabla</label>
					</div>
				</div>
				<div class="card-content">
					<table>
						<thead>
							<tr>
								<th class="">ID</th>
								<th>Origen</th>
								<th>Destino</th>
								<th>Fecha de Salida</th>
								<th>Fecha de LLegada</th>
							</tr>
						</thead>

						<tbody id="tbody">
							<%
								List<ViajeView> viajes = ControladorPrincipal.getInstance().obtenerViajesView();
														for(ViajeView v : viajes)
														{
							%>
							<tr>
								<td style="color: #1565C0; font-weight: bold;"><%=v.getId()%></td>
								<td><%=v.getOrigen().getCiudad()%></td>
								<td><%=v.getDestino().getCiudad()%></td>
								<td><%=v.getFechaSalida()%></td>
								<td><%=v.getFechaLlegada()%></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
					<div></div>
				</div>
			</div>
		</div>

	</div>

	<script>
		
	</script>
</body>