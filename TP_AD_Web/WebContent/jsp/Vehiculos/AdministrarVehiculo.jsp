<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Vehiculos</title>
</head>
<body>


	<div class="row">
		<div class="col s12 m4 l1">
			<p></p>
		</div>

		<div class="col s12 m4 l10 ">
			<div class="card">
				<div class="card-content white-text pink">
					<span class="card-title">Administración de Vehiculos</span>
				</div>

				<div class="card-content">

					<div class="col s12">
						<ul class="tabs">
							<li class="tab col s3"><a class="active" href="#test1">Alta Local</a></li>
							<li class="tab col s3"><a href="#test2">Alta Externo</a></li>
							<li class="tab col s3"><a href="#test3">Baja</a></li>
							<li class="tab col s3"><a href="#test4">Modificación</a></li>
						</ul>
					</div>

					<!--	ALTA VEHICUL LOCAL 	 -->
					<div id="test1" class="col s12">
						<%@include file="AltaVehiculoLocal.jsp"%>
					</div>

					<!--	ALTA VEHICUL LOCAL 	 -->
					<div id="test2" class="col s12">
						<%@include file="AltaVehiculoExterno.jsp" %>
					</div>
					
					<!--	BAJA VEHICULO 	 -->
					<div id="test3" class="col s12">Implementar baja</div>
					
					<!--	MODIFICACION VEHICULO LOCAL 	 -->
					<div id="test4" class="col s12">Implementar modificación</div>
					
	
				</div>
			</div>

		</div>
		<div class="col s12 m4 l1">
			<p></p>
		</div>
	</div>






	<script>
		var form = $('#alta-cliente');
		form.submit(function(event) {
			var loader = renderLoader();
			form.find('input[type="submit"]').after(loader);
			event.preventDefault();

			$.ajax({
				url : form.attr('action'),
				type : 'POST',
				data : form.serialize(),
				success : function() {
					loader.fadeOut('fast', function() {
						$(this).remove();
					});
					Materialize
							.toast('Cliente dado de alta exitosamente', 6000);
					//Input DNI
					$('input[type="number"]').val('');
					$('input[type="number"]').removeClass('valid');
					//Input nombre, apellido
					$('input[type="text"]').val('');
					$('input[type="text"]').removeClass('valid');
					$('label').removeClass('active');
				}
			});
		});

		function renderLoader() {
			var loader = $('<div class="preloader-wrapper small active"><div class="spinner-layer spinner-blue"><div class="circle-clipper left"><div class="circle"></div></div><div class="gap-patch"><div class="circle"></div></div><div class="circle-clipper right"><div class="circle"></div></div></div><div class="spinner-layer spinner-red"><div class="circle-clipper left"><div class="circle"></div></div><div class="gap-patch"><div class="circle"></div></div><div class="circle-clipper right"><div class="circle"></div></div></div><div class="spinner-layer spinner-yellow"><div class="circle-clipper left"><div class="circle"></div></div><div class="gap-patch"><div class="circle"></div></div><div class="circle-clipper right"><div class="circle"></div></div></div><div class="spinner-layer spinner-green"><div class="circle-clipper left"><div class="circle"></div></div><div class="gap-patch"><div class="circle"></div></div><div class="circle-clipper right"><div class="circle"></div></div></div></div>');
			loader.width(25).height(25).css('margin-bottom', -8).css(
					'margin-left', 20);
			return loader;
		}

		$(document).ready(function() {
			$('ul.tabs').tabs();
		});
	</script>

</body>