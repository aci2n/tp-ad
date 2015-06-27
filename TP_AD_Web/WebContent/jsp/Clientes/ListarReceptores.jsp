<%@page import="com.sun.media.sound.ModelAbstractChannelMixer"%>
<%@page import="java.util.*"%>
<%@page import="impl.clientes.*"%>
<%@page import="views.clientes.*"%>
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

	<div class="input-field col s6">
		<i class="material-icons prefix">list</i> <input id="filtrado"
			type="text" class="validate"> <label for="icon_list">Buscar Cliente Particular</label>
			<input class="btn btn-primary" type="submit" value="Buscar">
	</div>
	<div>
		<table>
			<thead>
				<tr>
					<th class="">ID</th>
					<th data-field="name">DNI</th>
					<th data-field="item-name">Nombre</th>
					<th data-field="price">Apellido</th>
				</tr>
			</thead>

			<tbody id="tbody">
				<%-- <%
									List<ParticularView> clientes = ControladorPrincipal.getInstance().getAdministradorClientes().obtenerClientesParticulares();
									for(ParticularView c : clientes)
									{
								%>
								<tr>
									<td style="color: #1565C0; font-weight: bold;"><%=c.getId()%></td>
									<td><%=c.getDni()%></td>
									<td><%=c.getNombre()%></td>
									<td><%=c.getApellido()%></td>
								</tr>
								<%
									}
								%> --%>
			</tbody>
		</table>
		<div></div>
	</div>


	<script>
		
	</script>
</body>