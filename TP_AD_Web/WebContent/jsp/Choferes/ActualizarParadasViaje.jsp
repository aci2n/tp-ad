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
						</div>
					</div>
					<div>
						<table>
							<thead>
								<tr>
									<th>Seguimiento</th>
									<th>Parada</th>
									<th>Fecha planeada</th>
									<th>Fecha de llegada</th>
								</tr>
							</thead>

							<tbody id="tbody">



							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script>
		$(document).ready(function() {
			$("#buscar").click(function() {
				var idChofer = $("#id_chofer").val();
				$.ajax({
					url : 'ActualizarParadasViaje',
					data : {
						id_chofer : idChofer
					},
					type : 'GET',
					success : function(json) {
						$("#tbody tr").remove();
						imprimirParadasViaje(json);
						$('input[type="checkbox"]').first().removeAttr('disabled');
						//alert("a");
					},
					statusCode: {
						404: function() {
							Materialize.toast("No existe chofer con el id " + idChofer + " o un viaje para el mismo", 5000);
						},
						500: function() {
							Materialize.toast("Error interno del servidor,  intente nuevamente", 5000);	
						}
					}
				});

			});
		});

		function imprimirParadasViaje(json) {

			$.each(json.paradas, function(i, parada) {
				var row = $('<tr>');
				row
					.append($('<td>').text(i + 1))
					.append($('<td>').text(parada.sucursal))
					.append($('<td>').text(parada.llegadaEsperada));
				if (parada.llegada) {
					row.append($('<td>').text(parada.llegada));
				} else {
					var checkId = 'parada_' + parada.id;
					var check = $('<input>').attr('type', 'checkbox').attr('id', checkId).attr('disabled', 'disabled');
					check.change(function() {
						check.attr('disabled', 'disabled');
						$.ajax({
							url: 'reportarParada',
							data: {
								idParada: parada.id
							},
							success: function() {
								row.find('td').last().text(new Date().toLocaleString());
								$('input[type="checkbox"]').first().removeAttr('disabled');
							},
							error: function() {
								Materialize.toast('Debe registrar primero las paradas anteriores', 5000);
							}
						});
					});
					row.append(
						$('<td>')
							.append(check)
							.append(
								$('<label>').attr('for', checkId).text('Marcar como llegado')
						)
					);
				}
				
				$('#tbody').append(row);
			});
			
			var destinoId = 'destino_' + json.id;
			var destinationRow = $('<tr>');
			destinationRow.append($('<td>').text('Fin'));
			destinationRow.append($('<td>').text(json.destino.ciudad + '(Fin recorrido)'));
			destinationRow.append($('<td>').text(json.fechaLlegadaEsperada));
			
			if (json.fechaLlegada) {
				destinationRow.append($('<td>').text(json.fechaLlegada));
			} else {
				var check = $('<input>').attr('type', 'checkbox').attr('disabled', 'disabled').attr('id', destinoId);
				check.change(function() {
					$(this).attr('disabled', 'disabled');
					$.ajax({
						url: 'reportarLlegada',
						data: {
							idViaje: json.id
						},
						success: function() {
							destinationRow.find('td').last().text(new Date().toLocaleString());
						}
					});
				});
				destinationRow.append(
					$('<td>').append(check).append($('<label>').attr('for', destinoId))		
				);
			}
			
			$('#tbody').append(destinationRow);
		}
	</script>

</body>
</html>
