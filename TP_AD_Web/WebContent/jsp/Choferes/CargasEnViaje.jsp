<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div class="card-action row">
		<div class="input-field col s6">
			<i class="material-icons prefix">list</i> <input id="filtrado"
				type="text" class="validate"> <label for="icon_list">Ingrese
				el ID de la Carga</label>
		</div>
	</div>
	<ul class="collapsible" data-collapsible="accordion">
		<li>
			<div class="collapsible-header">
				<i class="material-icons">description</i>Carga 1
			</div>
			<div class="collapsible-body">
				<p>Lorem ipsum dolor sit amet.</p>
			</div>
		</li>
	</ul>

	<script>
		$(document).ready(function() {
			$('.collapsible').collapsible({
				accordion : false
			// A setting that changes the collapsible behavior to expandable instead of the default accordion style
			});
		});
	</script>

</body>
</html>