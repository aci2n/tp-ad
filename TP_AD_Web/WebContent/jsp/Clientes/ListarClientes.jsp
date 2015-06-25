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


	<div class="row z-depth-1"">
	    <div class="col s12 m4 l2"><p></p></div>
	
        <div class="col s12 m4 l8 ">
          <div class="card">
            <div class="card-content white-text pink">
              <span class="card-title">Listado de Clientes</span>
            </div>
            <div class="card-action" style="border-bottom: 1px solid rgba(160,160,160,0.2);">
		        <div class="input-field">
		          <i class="material-icons prefix">list</i>
		          <input id="filtrado" type="text" class="validate">
		          <label for="icon_list">Filtrar tabla</label>
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
          </div>
        </div>
            <div class="col s12 m4 l2"><p></p></div>
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