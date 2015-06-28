<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Seguimiento Carga</title>
</head>
<body>
	<!-- <input id="idCarga" type="text">
	<button id="search">Buscar</button>
	<div id="tabla"></div> -->

	<div class="row">
		<div class="col s12 m4 l1">
			<p></p>
		</div>
		<div class="col s12 m4 l10 ">
			<div class="card">
				<div class="card-content white-text pink">
					<span class="card-title">Seguimiento de Cargas</span>
				</div>

				<div class="card-content">
					<div class="row">
					<div class="input-field col s6">
						<i class="material-icons prefix">list</i> <input id="filtrado"
							type="text" class="validate"> <label for="icon_list">Buscar
							carga</label> <input class="btn btn-primary col s6" type="submit" value="Buscar">
					</div>
					</div>
					<div>
						<table>
							<thead>
								<tr>
									<th class="">ID Seguimiento</th>
									<th class="">ID Carga</th>
									<th>Estado</th>
									<th>Locación</th>
									<th>Fecha</th>
								</tr>
							</thead>

							<tbody id="tbody">

							</tbody>
						</table>
						<div></div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script>
		$(document).ready(function() {
			$('#search').click(function(event) {
				var idCarga = $('#idCarga').val();
				$.get('ObtenerInformacionCarga', {
					idCarga : idCarga
				}, function(responseText) {
					$('#tabla').html(responseText);
				});
			});
		});
	</script>

</body>
</html>
