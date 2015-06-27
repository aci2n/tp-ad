<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Alta Cliente Particular</title>
</head>
<body>

	<!-- <div class="row">
		<div class="container">
			<div class="card blue-grey darken-1">
				<div class="card-content white-text">
					<span class="card-title">Alta Cliente Particular</span>
				</div>
			</div>
		</div>
	</div>
	<div class="row container z-depth-1">
		<form id="alta-cliente" action="AltaClienteParticular"
			class="box-padding" class="form-control">
			<div class="form-group">
				<label for="dni">DNI</label> <input type="text" class="form-control"
					placeholder="DNI" name="dni">
			</div>
			<div class="form-group">
				<label for="nombre">Nombre</label> <input type="text"
					class="form-control" placeholder="Nombre" name="nombre">
			</div>
			<div class="form-group">
				<label for="password">Apellido</label> <input type="password"
					class="form-control" placeholder="Apellido" name="apellido">
			</div>
			<div class="input-field col s12">
				<input type="number" class="validate" name="dni"> <label
					for="email">DNI</label>
			</div>
			<div class="input-field col s12">
				<input type="text" class="validate" name="nombre"> <label
					for="email">Nombre</label>
			</div>
			<div class="input-field col s12">
				<input type="text" class="validate" name="apellido"> <label
					for="email">Apellido</label>
			</div>
			<input class="btn btn-primary" type="submit" value="Alta">
		</form>
	</div> -->

	<div class="row">
		<div class="col s12 m4 l1">
			<p></p>
		</div>

		<div class="col s12 m4 l10 ">
			<div class="card">
				<div class="card-content white-text pink">
					<span class="card-title">Administración de Clientes</span>
				</div>

				<div class="card-content">

					<div class="col s12">
						<ul class="tabs">
							<li class="tab col s3"><a class="active" href="#test1">Alta
									Particular</a></li>
							<li class="tab col s3"><a href="#test2">Alta Empresa</a></li>
							<li class="tab col s3"><a href="#test3">Baja</a></li>
							<li class="tab col s3"><a href="#test4">Modificación</a></li>
							<li class="tab col s3"><a href="#test5">Cuentas Corrientes</a></li>

						</ul>
					</div>

					<!--	ALTA CLIENTE PARTICULAR 	 -->
					<div id="test1" class="col s12">
						<%@include file="AltaClienteParticular.jsp"%>
					</div>

					<!--	ALTA CLIENTE EMPRESA 	 -->
					<div id="test2" class="col s12">
						<%@include file="AltaClienteEmpresa.jsp"%>
					</div>

					<!--	BAJA CLIENTE	 -->
					<div id="test3" class="col s12">Implementar baja</div>

					<!--	MODIFICACION CLIENTE	 -->
					<div id="test4" class="col s12">Implementar modificación</div>
					
					<!--	MODIFICACION CLIENTE	 -->
					<div id="test5" class="col s12">Implementar cuenta corriente</div>
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
			var loader = $('<div class="preloader-wrapper small active"><div class="spinner-layer spinner-blue"><div class="circle-clipper left"><div class="circle"></div></div><div class="gap-patch"><div class="circle"></div></div><div class="circle-clipper right"><div class="circle"></div></div>_$tag__$tag___________________________________$tag_____________________________$tag________________$tag__$tag__$tag___________________$tag________________$tag__$tag__$tag______________________________$tag________________$tag__$tag__$tag__$tag______________________________________$tag_____________________________$tag________________$tag__$tag__$tag___________________$tag________________$tag__$tag__$tag______________________________$tag________________$tag__$tag__$tag__$tag_____________________________________$tag_____________________________$tag________________$tag__$tag__$tag___________________$tag________________$tag__$tag__$tag______________________________$tag________________$tag__$tag__$tag__$tag_');
			loader.width(25).height(25).css('margin-bottom', -8).css(
					'margin-left', 20);
			return loader;
		}

		$(document).ready(function() {
			$('ul.tabs').tabs();
		});
	</script>

</body>