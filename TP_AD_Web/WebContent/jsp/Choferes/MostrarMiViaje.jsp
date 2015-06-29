<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="views.personal.EmpleadoView"%>
<%@page import="java.util.List"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div id="test1">
		<form id="listarViajes" action="mostrarParadas">
			<div class="card-action row"style="border-bottom: 1px solid rgba(160, 160, 160, 0.2);">
				<div class="input-field col s6">
						<select id="filtrado" name="idChofer">
							<% 
								List<EmpleadoView> choferes = (List<EmpleadoView>) request.getAttribute("choferes");
								for (EmpleadoView chofer : choferes) {
							%>
								<option value="<%=chofer.getId()%>"><%=chofer.getNombre()%> <%=chofer.getApellido()%></option>
							<% 
								}
							%>
						</select>
						<label for="filtrado">Chofer</label>
				</div>
				<input class="btn" type="submit" id="btnSubmit" name="buscar" value="Buscar" />
			</div>
		</form>
	</div>
	<div class="card-content" id="miViaje">
		
	</div>

	<script>
		$(document).ready(function() {
			$('#listarViajes').submit(function(event) {
				event.preventDefault();
				$.ajax({
					url: $(this).attr('action'),
					type: 'GET',
					data: {
						idChofer: $('select[name="idChofer"]').val(),
					},
					success: function(data) {
						$('#miViaje').append(data);
					}
				});
			});
			$('select').material_select();
		});
	</script>

</body>
</html>