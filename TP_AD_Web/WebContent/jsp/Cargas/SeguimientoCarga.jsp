<%@page import="impl.cargas.TipoCarga"%>
<%@page import="impl.personal.TipoPuesto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.js">
	
</script>
<script>
	$(document).ready(function() {
		$('#search').click(function(event) {
			var idCarga = $('#idCarga').val();
			$.get('ObtenerInformacionCarga', {
				idCarga : idCarga
			}, function(responseText) {
				$('#tabla').html(responseText);
			});
		});
	});
</script>
<title>Seguimiento Carga</title>
</head>
<body>
	<input id="idCarga" type="text">
	<button id="search">Buscar</button>
	<div id="tabla"></div>
</body>
</html>
