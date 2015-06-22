<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/principal.css">


<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

<title>Der große Verteiler - Logística</title>

<nav class="navbar navbar-default" role="navigation">
<div class="navbar-header">
	<a class="navbar-brand" href="#">Der große Verteiler</a>
</div>
<div>
	<ul class="nav navbar-nav">
		<li id="inicio" class=""><a href="#">Inicio</a></li>
		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown"> Clientes <b class="caret"></b>
		</a>
			<ul class="dropdown-menu">
				<li id="alta-cliente"><a href="#">Alta</a></li>
				<li><a href="#">Baja</a></li>
				<li><a href="#">Modificación</a></li>
				<li class="divider"></li>
				<li><a href="#">Listar</a></li>
				<li class="divider"></li>
				<li><a href="#">Cuentas clientes</a></li>
			</ul></li>

		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown"> Empleados <b class="caret"></b>
		</a>
			<ul class="dropdown-menu">
				<li><a href="#">Alta</a></li>
				<li><a href="#">Baja</a></li>
				<li><a href="#">Listar</a></li>
			</ul></li>
		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown"> Productos <b class="caret"></b>
		</a>
			<ul class="dropdown-menu">
				<li><a href="#">Alta</a></li>
				<li><a href="#">Baja</a></li>
				<li><a href="#">Listar</a></li>

			</ul></li>

		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown"> Sucursales <b class="caret"></b>
		</a>
			<ul class="dropdown-menu">
				<li><a href="#">Listar</a></li>
				<li><a href="#">a</a></li>
				<li><a href="#">b</a></li>
				<li class="divider"></li>
				<li><a href="#">JUST DO IT</a></li>
				<li class="divider"></li>
			</ul></li>
	</ul>
</div>
</nav>


</head>
<body>

<div id="content">


</div>

	<%-- <jsp:include page="AltaClienteParticular.jsp"></jsp:include>
 --%>


	<!-- Compiled and minified JavaScript -->
	<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<!-- Latest compiled and minified JavaScript -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		
	<script>
	
	$( "#alta-cliente" ).click(function() {
		  $("#content").load('../Cliente/AltaClienteParticular.jsp');
	});

	$('#inicio').click(function(){
		  $("#content").load('inicio.jsp');
	});
	
	</script>	
		

</body>
</html>