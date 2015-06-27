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


	<div class="row z-depth-1"">
		<div class="col s12 m4 l1">
			<p></p>
		</div>

		<div class="col s12 m4 l10 ">
			<div class="card">
				<div class="card-content white-text pink">
					<span class="card-title">Listado de Clientes</span>
				</div>

				<div class="col s12">
					<ul class="tabs">
						<li class="tab col s3"><a class="active" href="#test1">Listar
								Particular</a></li>
						<li class="tab col s3"><a href="#test2">Listar Empresa</a></li>
					</ul>
				</div>



				<div id="test1">
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
									<th data-field="name">DNI</th>
									<th data-field="item-name">Nombre</th>
									<th data-field="price">Apellido</th>
								</tr>
							</thead>

							<tbody id="tbody">
								<%
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
								%>
							</tbody>
						</table>
						<div></div>
					</div>
				</div>


				<div id="test2">
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
									<th data-field="nombre">Nombre</th>
									<th data-field="regular">Regular</th>
									<th data-field="regular">Monto Actual</th>

								</tr>
							</thead>

							<tbody id="tbody">
								<%
									List<EmpresaView> empresas = ControladorPrincipal.getInstance().getAdministradorClientes().obtenerClientesEmpresas();
																				for(EmpresaView e : empresas)
																			{
								%>
								<tr>
									<td style="color: #1565C0; font-weight: bold;"><%=e.getId()%></td>
									<td><%=e.getNombre()%></td>
									<td><%=e.getCuentaCorriente().getMontoActual()%></td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
						<div></div>
					</div>
				</div>



				<div class="col s12 m4 l1">
					<p></p>
				</div>
			</div>


			<script>
				$("#filtrado").keyup(function() {
					//split the current value of searchInput
					var datos = this.value.split(" ");
					//create a jquery object of the rows
					var tabla = $("tbody").find("tr");
					if (this.value == "") {
						tabla.show();
						return;
					}
					//hide all the rows
					tabla.hide();

					//Recusively filter the jquery object to get results.
					tabla.filter(function(i, v) {
						var $t = $(this);
						for (var d = 0; d < datos.length; ++d) {
							if ($t.is(":contains('" + datos[d] + "')")) {
								return true;
							}
						}
						return false;
					})
					//show the rows that match.
					.show();
				}).focus(function() {
					this.value = "";
					$(this).css({
						"color" : "black"
					});
					$(this).unbind('focus');
				}).css({
					"color" : "#C0C0C0"
				});

				$(document).ready(function() {
					$('ul.tabs').tabs();
				});
			</script>
</body>