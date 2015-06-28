<%@page import="impl.cargas.TipoCarga"%>
<%@page import="impl.personal.TipoPuesto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Alta Carga</title>
</head>
<body>

	<form id="" action="" class="box-padding" class="form-control">

		<div class="input-field col s12">
			<input type="date" class="datepicker" name="fechaMaxEntrega">
			<label>Fecha máxima de entrega</label>
		</div>
		

		<div class="input-field col s12">
			<input type="date" class="datepicker" name="fechaProbEntrega">
			<label>Fecha probable de entrega</label>
		</div>
		<div class="input-field col s12">
			<textarea id="textarea1" class="materialize-textarea"></textarea>
			<label for="textarea1">Manifiesto</label>
		</div>
		
		<div>
			<p>
				<input type="checkbox" id="test5" /> <label for="test5">Retira
					la carga en persona</label>
			</p>
		</div>


	</form>
	<a class="waves-effect waves-light btn modal-trigger" href="#modal1">Confirmar</a>

	<div id="modal1" class="modal">
		<div class="modal-content">
			<h4>Confirmación de la Carga</h4>
			<p>La fecha de llegada será AGREGAR METODO QUE CALCULA LA FECHA
				PROBABLEXXX, ¿desea finalizar?</p>
		</div>
		<div class="modal-footer">
			<a href="#!"
				class="modal-action modal-close waves-effect waves-green btn-flat">Cancelar</a>
			<input class="btn btn-primary" type="submit" value="Alta">
		</div>
	</div>



	<script>
		$(document).ready(function() {
			// the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
			$('.modal-trigger').leanModal();
		});

		$('.datepicker').pickadate({
			selectMonths : true, // Creates a dropdown to control month
			selectYears : 15
		// Creates a dropdown of 15 years to control year
		});
	</script>


</body>
</html>