<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/materialize.min.css">

<link type="text/css" rel="stylesheet" href="css/styles.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">

<title>Insert title here</title>


</head>
<body>
	<div>
		<nav class="blue darken-1">
			<ul id="slide-out" class="side-nav">
				<li class="pink inactive">Navegación</li>
				<li><a class="menu-anchor" href="Inicio">Inicio</a></li>
				<li class="no-padding">
					<ul class="collapsible collapsible-accordion">
						<li><a class="collapsible-header">Clientes<i
								class="mdi-navigation-arrow-drop-down"></i></a>
							<div class="collapsible-body">
								<ul>
									<!-- <li><a class="menu-anchor"
										id="jsp/Clientes/AdministrarClientes" href="">Administración</a></li> -->
									<li><a id="jsp/Clientes/ListarClientes.jsp"
										class="menu-anchor" href="#!">Listar</a></li>
											<li><a id="jsp/Clientes/MisFacturas.jsp"
										class="menu-anchor" href="#!">Facturas</a></li>
								</ul>
							</div></li>
					</ul>
				</li>
				<!--<li class="no-padding">
					<ul class="collapsible collapsible-accordion">
						<li><a class="collapsible-header">Empleados<i
								class="mdi-navigation-arrow-drop-down"></i></a>
							<div class="collapsible-body">
								<ul>
									<li><a class="menu-anchor"
										id="jsp/Personal/AdministrarEmpleado" href="#!">Administración</a></li>
									<li><a class="menu-anchor"
										id="jsp/Personal/ListarEmpleados" href="#!">Listar</a></li>
								</ul>
							</div></li>
					</ul>
				</li>
				<li class="no-padding">
					<ul class="collapsible collapsible-accordion">
						<li><a class="collapsible-header">Sucursales<i
								class="mdi-navigation-arrow-drop-down"></i></a>
							<div class="collapsible-body">
								<ul>
									<li><a class="menu-anchor"
										id="jsp/Sucursales/AdministrarSucursal" href="#!">Administración</a></li>
									<li><a class="menu-anchor"
										id="jsp/Sucursales/ListarSucursal" href="#!">Listar</a></li>
								</ul>
							</div></li>
					</ul>
				</li> -->
				<li class="no-padding">
					<ul class="collapsible collapsible-accordion">
						<li><a class="collapsible-header">Productos<i
								class="mdi-navigation-arrow-drop-down"></i></a>
							<div class="collapsible-body">
								<ul>
									<li><a class="menu-anchor"
										id="jsp/Productos/AdministrarProductos.jsp" href="#!">Administración</a></li>
									<li><a class="menu-anchor"
										id="jsp/Productos/ListarProductos.jsp" href="#!">Listar</a></li>
								</ul>
							</div></li>
					</ul>
				</li>
				<!-- <li class="no-padding">
					<ul class="collapsible collapsible-accordion">
						<li><a class="collapsible-header">Vehiculos<i
								class="mdi-navigation-arrow-drop-down"></i></a>
							<div class="collapsible-body">
								<ul>
									<li><a class="menu-anchor"
										id="jsp/Vehiculos/AdministrarVehiculo" href="#!">Administración</a></li>
									<li><a href="#!">Listar Vehiculos Locales</a></li>
									<li><a href="#!">Listar Vehiculos EXternos</a></li>
								</ul>
							</div></li>
					</ul>
				</li> -->
				<li class="no-padding">
					<ul class="collapsible collapsible-accordion">
						<li><a class="collapsible-header">Cargas<i
								class="mdi-navigation-arrow-drop-down"></i></a>
							<div class="collapsible-body">
								<ul>
									<li><a class="menu-anchor"
										id="jsp/Cargas/AdministrarCarga.jsp" href="#!">Alta</a></li>
									<li><a class="menu-anchor"
										id="jsp/Cargas/SeguimientoCarga.jsp" href="#!">Seguimiento</a></li>
									<li><a href="#!">Listar</a></li>
								</ul>
							</div></li>
					</ul>
				</li>
				<li class="no-padding">
					<ul class="collapsible collapsible-accordion">
						<li><a class="collapsible-header">Viajes<i
								class="mdi-navigation-arrow-drop-down"></i></a>
							<div class="collapsible-body">
								<ul>
									<li><a class="menu-anchor"
										id="jsp/Viajes/AdministrarViaje.jsp" href="#!">Administración</a></li>
									<li><a href="#!">Baja</a></li>
									<li><a class="menu-anchor"
										id="jsp/Viajes/ListarViajes.jsp" href="#!">Listar</a></li>
								</ul>
							</div></li>
					</ul>
				</li>
				<!-- <li class="no-padding">
					<ul class="collapsible collapsible-accordion">
						<li><a class="collapsible-header">Proveedores<i
								class="mdi-navigation-arrow-drop-down"></i></a>
							<div class="collapsible-body">
								<ul>
									<li><a class="menu-anchor"
										id="jsp/Proveedores/AdministrarProveedor" href="#!">Administración</a></li>
									<li><a class="menu-anchor"
										id="jsp/Proveedores/ListarProveedores" href="#!">Listar</a></li>
									<li><a class="menu-anchor"
										id="jsp/Proveedores/PagoAProveedores" href="#!">Pago</a></li>
								</ul>
							</div></li>
					</ul>
				</li> -->
				<ul class="collapsible collapsible-accordion">
					<li><a class="collapsible-header">Choferes<i
							class="mdi-navigation-arrow-drop-down"></i></a>
						<div class="collapsible-body">
							<ul>
								<li><a class="menu-anchor" id="jsp/Choferes/MisParadas.jsp" href="#!">Mi
										viaje</a></li>
							</ul>
						</div></li>
				</ul>
			</ul>
			<a href="#" data-activates="slide-out" class="button-collapse"
				style="display: inline;"><i class="mdi-navigation-menu"></i></a>

			<h5 class="nav-header" id="title"></h5>

		</nav>

	</div>

	<div id="content" class=""></div>


	<!-- SCRIPTS -->

	<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.96.1/js/materialize.min.js"></script>

	<script type="text/javascript">
		$(".button-collapse").sideNav();

		$('.menu-anchor').click(function(event) {
			event.preventDefault();
			$('#content').load($(this).attr('id'));
			$('#title').html($(this).text());
			$('.button-collapse').sideNav('hide');
		});

		$(document).ready(function() {
			$('a').addClass('waves-effect waves-teal');
		});
	</script>

</body>


</html>
