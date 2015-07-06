<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Notificar Rotura de carga</title>
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
					<span class="card-title">Notificar Roturas de Cargas</span>
				</div>

				<div class="card-content">
					<div class="row">
						<div class="input-field col s6">
							<i class="material-icons prefix">list</i> <input id="id_carga"
								type="number" class="validate"> <label for="icon_list">Indique ID de la carga con roturas</label>
						</div>
						<div class="col s6">
							<button class="waves-effect waves-light btn" id="buscar"
								type="button">Marcar como rota</button>
						</div>
					</div>
					<div>
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
					url : 'NotificarRotura',
					data : {
						id_carga : $("#id_carga").val()
					},
					type : 'POST',
					dateType : 'json',
					success : function(json) {
						alert("La carga ha sido marcada como rota");
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
