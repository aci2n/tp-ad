<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Alta Cliente Particular</title>
</head>
<body>

	<div class="row">
		<div class="container">
			<div class="card blue-grey darken-1">
				<div class="card-content white-text">
					<span class="card-title">Alta Cliente Particular</span>
				</div>
			</div>
		</div>
	</div>
	<div class="row container z-depth-1">
		<form id="alta-cliente" action="AltaClienteParticular" class="box-padding">
			<!-- <div class="form-group">
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
			</div> -->
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
	</div>
	
	<script>
		var form = $('#alta-cliente');
		form.submit(function(event) {
			var loader = renderLoader();
			form.find('input[type="submit"]').after(loader);
			event.preventDefault();
			
			$.ajax({
				url: form.attr('action'),
				type: 'POST',
				data: form.serialize(),
				success: function() {
					loader.fadeOut('fast', function() {
						$(this).remove();
					});
					Materialize.toast('Cliente dado de alta exitosamente', 4000);
					$('input[type="text"]').val('');
				}
			});
		});
		
		function renderLoader() {
			var loader = $('<div class="preloader-wrapper small active"><div class="spinner-layer spinner-blue"><div class="circle-clipper left"><div class="circle"></div></div><div class="gap-patch"><div class="circle"></div></div><div class="circle-clipper right"><div class="circle"></div></div></div><div class="spinner-layer spinner-red"><div class="circle-clipper left"><div class="circle"></div></div><div class="gap-patch"><div class="circle"></div></div><div class="circle-clipper right"><div class="circle"></div></div></div><div class="spinner-layer spinner-yellow"><div class="circle-clipper left"><div class="circle"></div></div><div class="gap-patch"><div class="circle"></div></div><div class="circle-clipper right"><div class="circle"></div></div></div><div class="spinner-layer spinner-green"><div class="circle-clipper left"><div class="circle"></div></div><div class="gap-patch"><div class="circle"></div></div><div class="circle-clipper right"><div class="circle"></div></div></div></div>');
			loader.width(25).height(25).css('margin-bottom', -8).css('margin-left', 20);
			return loader;
		}
	</script>
	
</body>











