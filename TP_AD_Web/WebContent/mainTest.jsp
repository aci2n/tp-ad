<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/materialize.min.css">

<link type="text/css" rel="stylesheet" href="css/style.css">


<title>Insert title here</title>


</head>
<body>

	<nav>
		<ul id="slide-out" class="side-nav">
			<li><a id="select1" class="menu-anchor" href="AltaClienteParticular">Inicio</a></li>
			<li class="no-padding">
				<ul class="collapsible collapsible-accordion">
					<li><a class="collapsible-header">Clientes<i
							class="mdi-navigation-arrow-drop-down"></i></a>
						<div class="collapsible-body">
							<ul>
								<li><a href="#!">Alta</a></li>
								<li><a href="#!">Baja</a></li>
								<li><a href="#!">Modificaci�n</a></li>
								<li><a href="#!">Listar</a></li>
							</ul>
						</div></li>
				</ul>
			</li>
		</ul>
		<a href="#" data-activates="slide-out" class="button-collapse"
			style="display: inline;"><i class="mdi-navigation-menu"></i></a>
	</nav>


	<div id="content"></div>


	<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.96.1/js/materialize.min.js"></script>

	<script type="text/javascript">
		$(".button-collapse").sideNav();

		$('.menu-anchor').click(function(event) {
			event.preventDefault();
			$('#content').load('jsp/Clientes/' + $(this).attr('href') + '.jsp');
			$('.button-collapse').sideNav('hide');
		});
	</script>

</body>
</html>
