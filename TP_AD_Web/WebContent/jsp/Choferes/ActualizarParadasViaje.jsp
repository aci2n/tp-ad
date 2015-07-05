<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Actualizar estado</title>
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
					<span class="card-title">Actualización de Viajes</span>
				</div>

				<div class="card-content">
					<div class="row">
						<div class="input-field col s6">
							<i class="material-icons prefix">list</i> <input id="id_chofer"
								type="number" class="validate"> <label for="icon_list">Buscar
								chofer</label>
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
									<th>Seguimiento</th>
									<th>Parada</th>
									<th>Fecha planeada</th>
									<th>Checked</th>
								</tr>
							</thead>

							<tbody id="tbody">



							</tbody>
						</table>
						<div>
							<button class="waves-effect waves-light btn" id="confirmar"
								type="button">Confirmar</button>
						</div>
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
					url : 'ActualizarParadasViaje',
					data : {
						id_chofer : $("#id_chofer").val()
					},
					type : 'GET',
					dateType : 'json',
					success : function(json) {
						$("#tbody tr").remove();
						imprimirParadasViaje(json);
						//alert("a");
					},
					error : function() {
						alert("Error");
					}
				});

			});
		});

		function imprimirParadasViaje(json) {

			$.each(json.paradas, function(i, parada) {

				$("#tbody").append(
						"<tr><td>" + ++i + "</td><td>" + parada.sucursal
								+ " </td><td>" + parada.llegadaEsperada
								+ " </td><td><input type='checkbox' id='parada"
								+ (i) + "' /><label for='parada" + (i)
								+ "'></label>" + " </td></tr>");
			});
		};

		$("#limpiar").click(function() {
			$("#tbody tr").remove();
		});

		$(document).ready(function() {
			$("#confirmar").click(function() {

				$.ajax({
					url : 'ActualizarParadasViaje',
					data : {
						id_chofer : $("#id_chofer").val()
					},
					type : 'POST',
					dateType : 'json',
					success : function(json) {
						$("#tbody tr").remove();
						imprimirParadasViaje(json);
						//alert("a");
					},
					error : function() {
						alert("Error");
					}
				});

			});
		});
	</script>

</body>
</html>
