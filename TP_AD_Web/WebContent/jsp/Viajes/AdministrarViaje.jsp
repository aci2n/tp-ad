<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Alta Cliente Particular</title>
</head>
<body>

	<div class="row">
		<div class="col s12 m4 l1">
			<p></p>
		</div>
		<div class="col s12 m4 l10 ">
			<div class="card">
				<div class="card-content white-text pink">
					<span class="card-title">Alta de Viaje</span>
				</div>

				<div class="card-content">

					<div class="col s12">
						<ul class="tabs">
							<li class="tab col s3"><a class="active" href="#test1">Alta</a></li>
							<li class="tab col s3 disabled"><a href="#test2">Ubicaci�n
									Origen</a></li>
							


						</ul>
					</div>

					<div id="test1" class="col s12">
						<%@include file="AltaViaje.jsp"%>
					</div>
					<div id="test2" class="col s12">
						<div class="input-field col s12">
							<input type="number" class="" name="sucursal">
							<label>ID Surcursal de Origen</label>
						</div>
					</div>
					
				</div>

			</div>

		</div>
		<div class="col s12 m4 l1">
			<p></p>
		</div>
	</div>

	<script>
		$(document).ready(function() {
			$('ul.tabs').tabs();
		});
		
		$(document).ready(function(){
		    // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
		    $('.modal-trigger').leanModal();
		  });
	</script>

</body>