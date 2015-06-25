<%@page import="com.sun.media.sound.ModelAbstractChannelMixer"%>
<%@page import="java.util.*"%>
<%@page import="impl.clientes.*"%>
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
		<div class="container">
			<div class="card blue-grey darken-1">
				<div class="card-content white-text">
					<span class="card-title">Listado de Clientes</span>
				</div>
			</div>
		</div>
	</div>
	<div class="row container z-depth-1">

		<div class="input-field col s6">
			<input id="filtrado" type="text" class="validate"> <label
				for="first_name">Filtrado de Tabla</label>
		</div>

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
					Session s = HibernateUtil.getSessionFactory().openSession();
							
								List<Particular> clientes = new ArrayList<Particular>();
								Query q = s.createQuery("from Particular");
								clientes = (List<Particular>) q.list();
								for(Particular c : clientes)
							{ %>
							<tr>
								<td style="color: #1565C0; font-weight: bold;"><%=c.getId() %></td>
								<td><%=c.getDni() %></td>
								<td><%=c.getNombre() %></td>
								<td><%=c.getApellido() %></td>
							</tr>
							<% }
				%>
			</tbody>
		</table>
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
	</script>
</body>