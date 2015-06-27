<%@page import="com.sun.media.sound.ModelAbstractChannelMixer"%>
<%@page import="java.util.*"%>
<%@page import="impl.cargas.*"%>
<%@page import="views.cargas.*"%>
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
					<span class="card-title">Listado de Cargas</span>
				</div>

				<div class="col s12">
					<ul class="tabs">
						<li class="tab col s3"><a class="active" href="#test1">Listar
								Cargas</a></li>
						<li class="tab col s3"><a href="#test2">Algo</a></li>
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
									<th data-field="name">ID Sucursal</th>
									<th class="">ID Destino</th>
									<th data-field="name">Tipo Carga</th>
									<th data-field="item-name">Fecha Entrega Máxima</th>
									<th data-field="price">Estado Carga</th>
								</tr>
							</thead>

							<tbody id="tbody">
								<%
									List<CargaView> cargas = ControladorPrincipal.getInstance().obtenerCargasView();
									for(CargaView c : cargas)
									{
								%>
								<tr>
									<td style="color: #1565C0; font-weight: bold;"><%=c.getId()%></td>
									<td><%=c.getOrigen()%></td>
									<td><%=c.getDestino()%></td>
									<td><%=c.getTipo()%></td>
									<td><%=c.getFechaMaximaEntrega()%></td>
									<td><%=c.getEstadoCarga()%></td>
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