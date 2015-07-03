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
							<i class="material-icons prefix">list</i> <input id="id_carga"
								type="number" class="validate"> <label for="icon_list">Buscar
								carga</label>
						</div>
						<div class="col s6">
							<button class="waves-effect waves-light btn" id="buscar"
								type="button">Buscar</button>
							<button class="waves-effect waves-light btn" id="limpiar"
								type="button">Limpiar tabla</button>
						</div>
					</div>
					<div>
						<table>
							<thead>
								<tr>
									<th class="">N° Seguimiento</th>
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
			$("#buscar").click(function() {

				$.ajax({
					url : 'SeguimientoCargas',
					data : {
						id_carga : $("#id_carga").val()
					},
					type : 'GET',
					dateType : 'json',
					success : function(json) {
						$("#tbody tr").remove();
						imprimirSeguimientoCarga(json);
					},
					error : function() {
						alert("Error");
					}
				});

			});
		});

		function imprimirSeguimientoCarga(json) {

			$.each(json, function(i, seCarga) {
				
				var locacion;
				if(seCarga.idViaje == null)
					locacion = seCarga.carga.origen.provincia;
				else
					locacion = seCarga.idViaje

				$("#tbody").append(
						"<tr><td>" + ++i + "</td><td>" + seCarga.carga.id
								+ " </td><td>" + seCarga.estadoCarga
								+ " </td><td>" + locacion
								+ " </td><td>" + seCarga.fecha
								+ " </td></tr>");
				
				
			});
		};

		$("#limpiar").click(function() {
			$("#tbody tr").remove();
		});
	</script>

</body>
</html>
