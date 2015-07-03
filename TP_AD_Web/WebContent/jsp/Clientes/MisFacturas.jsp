<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Mis Facturas</title>
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
					<span class="card-title">Listar Facturas</span>
				</div>

				<div class="col s12 card-content">
					<ul class="tabs">
						<li class="tab col s3"><a class="active" href="#test1">Listar
								Particular</a></li>
						<li class="tab col s3"><a href="#test2">Listar Empresa</a></li>
					</ul>
				</div>

				<div id="test1">
					<div>
						<div class="row card-action">
							<div class="input-field col s6">
								<i class="material-icons prefix">list</i> <input id="id_cliente"
									type="number" class="validate"> <label for="icon_list">Buscar
									Cliente</label>
							</div>
							<div class="col s6">
								<button class="waves-effect waves-light btn" id="buscar"
									type="button">Buscar</button>
								<button class="waves-effect waves-light btn" id="limpiar"
									type="button">Limpiar tabla</button>
							</div>
						</div>
						<div class="card-content" id="descripcion">
						
						</div>
						<div class="card-action">
							<table>
								<thead>
									<tr>
										<th class="">ID Factura</th>
										<th>ID Carga</th>
										<th>Tipo Factura</th>
										<th>Fecha Emisión</th>
										<th>Monto Total</th>
									</tr>
								</thead>

								<tbody id="tbody">

								</tbody>
							</table>
							<div></div>
						</div>
					</div>
				</div>

				<div id="test2">
					<div>
						<div class="row card-action">
							<div class="input-field col s6">
								<i class="material-icons prefix">list</i> <input id="id_cliente"
									type="number" class="validate"> <label for="icon_list">Buscar
									Cliente</label>
							</div>
							<div class="col s6">
								<button class="waves-effect waves-light btn" id="buscar"
									type="button">Buscar</button>
								<button class="waves-effect waves-light btn" id="limpiar"
									type="button">Limpiar tabla</button>
							</div>
						</div>
						<div class="card-action">
							<table>
								<thead>
									<tr>
										<th class="">ID Factura</th>
										<th>ID Carga</th>
										<th>Tipo Factura</th>
										<th>Fecha Emisión</th>
										<th>Monto Total</th>
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
	</div>

	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script>
		$(document).ready(function() {
			$("#buscar").click(function() {

				$.ajax({
					url : 'ListarMisFacturas',
					data : {
						id_cliente : $("#id_cliente").val()
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
		
			$.each(json, function(i, factura) {

				$("#tbody").append(
						"<tr><td style='color: #3949ab; font-weight: bold;'>"
								+ factura.id + " </td><td>" + factura.carga.id
								+ " </td><td>" + factura.tipoFactura
								+ " </td><td>" + factura.fechaCreacion
								+ " </td><td>" + "$ " + factura.montoTotal
								+ " </td></tr>");
			});
		};

		$("#limpiar").click(function() {
			$("#tbody tr").remove();
		});

		$(document).ready(function() {
			$('ul.tabs').tabs();
		});
	</script>

</body>
</html>
