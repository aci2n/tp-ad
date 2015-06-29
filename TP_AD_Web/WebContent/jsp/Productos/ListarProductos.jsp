<%@page import="com.sun.media.sound.ModelAbstractChannelMixer"%>
<%@page import="java.util.*"%>
<%@page import="impl.productos.*"%>
<%@page import="views.productos.*"%>
<%@page import="controllers.*"%>
<%@page import="persistence.*"%>
<%@page import="org.hibernate.Query"%>
<%@page import="org.hibernate.Session"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Listado de Productos</title>
</head>
<body>


	<div class="row z-depth-1">
		<div class="">
			<p></p>
		</div>

		<div class="col s12 m4 l12 ">
			<div class="card">
				<div class="card-content white-text pink">
					<span class="card-title">Listado de Productos</span>
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
								<th>Nombre</th>
								<th>Fragilidad</th>
								<th>Tratamiento</th>
								<th>Profundidad</th>
								<th>Ancho</th>
								<th>Alto</th>
								<th>Apilable</th>
								<th>Material</th>
								<th>Manipulación</th>
								<th>Consideraciones</th>
								<th>Refrigerada</th>
							</tr>
						</thead>

						<tbody id="tbody">
							<%
								 List<Producto> productos = ProductoDAO.getInstance().getAll();
																			for(Producto p : productos)
																			{
							%>
							<tr>
								<td style="color: #1565C0; font-weight: bold;">/*p.getId())*/</td>
								<td><%=p.getNombre()%></td>
								<td><%=p.getFragilidad().toString()%></td>
								<td><%=p.getTratamiento()%></td>
								<td><%=p.getTamano().getProfundidad()%></td>
								<td><%=p.getTamano().getAncho()%></td>
								<td><%=p.getTamano().getAlto()%></td>
								<td><%=p.getApilable()%></td>
								<td><%=p.getMaterial()%></td>
								<td><%=p.getManipulacion()%></td>
								<td><%=p.getConsideraciones()%></td>
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