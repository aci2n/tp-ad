<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>



	<div id="test1">
		<div class="card-action row"
			style="border-bottom: 1px solid rgba(160, 160, 160, 0.2);">
			<div class="input-field col s6">
				<i class="material-icons prefix">list</i> <input id="filtrado"
					type="text" class="validate"> <label for="icon_list">Ingrese
					su ID</label>
			</div>
		</div>
		<div class="card-content">
			<table>
				<thead>
					<tr>
						<th class="">ID Viaje</th>
						<th class="">ID Conductor</th>
						<th class="">ID Seguro</th>
						<th data-field="name">ID Sucursal</th>
						<th data-field="item-name">Origen</th>
						<th data-field="price">último Destino</th>
						<th data-field="price">Fecha de Salida</th>
						<th data-field="price">Fecha de Llegada</th>
						<th data-field="price">Condición Especial</th>
					</tr>
				</thead>

				<tbody id="tbody">

				</tbody>
			</table>
			<div></div>
		</div>
	</div>

	<div class="col s12 m4 l1">
		<p></p>
	</div>
	</div>
</body>
</html>