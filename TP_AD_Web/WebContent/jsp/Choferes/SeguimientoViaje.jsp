<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
				su ID</label>
		</div>
		<input class="btn" type="submit" id="btnSubmit" name="btnSubmit"
				value="Buscar" />
	</div>
	<div class="card-content">
		<table>
			<thead>
				<tr>
					<th class="">ID Seguimiento</th>
					<th class="">ID Viaje</th>
					<th class="">Parada Intermedia</th>
					<th>Fecha confirmación</th>
					<th>Confirmación</th>
				</tr>
			</thead>

			<tbody id="tbody">
				<tr>
					<td>1</td>
					<td>1</td>
					<td>Cordoba</td>
					<td class="fecha">-</td>
					<td><p>
							<input type="checkbox" id="check" /> <label for="check"></label>
						</p></td>
				</tr>
			</tbody>
		</table>
		<div></div>
	</div>

	<script>
		$(document).ready(function() {
			$('#check').change(function() {
				if ($(this).is(":checked")) {
					$('.fecha').html('<%=new Date()%>');
				}
				else{
					$('.fecha').html('-');
				}
				$('#test5').val($(this).is(':checked'));
			});
		});
	</script>

</body>
</html>